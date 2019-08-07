/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.servicelayer.sebconfig.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.async.AsyncServiceSpringConfig;
import ch.ethz.seb.sebserver.gbl.model.sebconfig.ConfigurationAttribute;
import ch.ethz.seb.sebserver.gbl.model.sebconfig.ConfigurationValue;
import ch.ethz.seb.sebserver.gbl.profile.WebServiceProfile;
import ch.ethz.seb.sebserver.gbl.util.Utils;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ConfigurationAttributeDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ConfigurationDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.ConfigurationValueDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.sebconfig.AttributeValueConverter;
import ch.ethz.seb.sebserver.webservice.servicelayer.sebconfig.AttributeValueConverterService;
import ch.ethz.seb.sebserver.webservice.servicelayer.sebconfig.ConfigurationFormat;

@Lazy
@Component
@WebServiceProfile
public class ExamConfigIO {

    private static final Logger log = LoggerFactory.getLogger(ExamConfigIO.class);

    private static final byte[] XML_VERSION_HEADER_UTF_8 = Utils.toByteArray(Constants.XML_VERSION_HEADER);
    private static final byte[] XML_DOCTYPE_HEADER_UTF_8 = Utils.toByteArray(Constants.XML_DOCTYPE_HEADER);
    private static final byte[] XML_PLIST_START_V1_UTF_8 = Utils.toByteArray(Constants.XML_PLIST_START_V1);
    private static final byte[] XML_PLIST_END_UTF_8 = Utils.toByteArray(Constants.XML_PLIST_END);
    private static final byte[] XML_DICT_START_UTF_8 = Utils.toByteArray(Constants.XML_DICT_START);
    private static final byte[] XML_DICT_END_UTF_8 = Utils.toByteArray(Constants.XML_DICT_END);
    private static final byte[] JSON_START = Utils.toByteArray("{");
    private static final byte[] JSON_END = Utils.toByteArray("}");
    private static final byte[] JSON_SEPARATOR = Utils.toByteArray(Constants.LIST_SEPARATOR);

    private final ConfigurationAttributeDAO configurationAttributeDAO;
    private final ConfigurationValueDAO configurationValueDAO;
    private final ConfigurationDAO configurationDAO;
    private final AttributeValueConverterService attributeValueConverterService;

    protected ExamConfigIO(
            final ConfigurationAttributeDAO configurationAttributeDAO,
            final ConfigurationValueDAO configurationValueDAO,
            final ConfigurationDAO configurationDAO,
            final AttributeValueConverterService attributeValueConverterService) {

        this.configurationAttributeDAO = configurationAttributeDAO;
        this.configurationValueDAO = configurationValueDAO;
        this.configurationDAO = configurationDAO;
        this.attributeValueConverterService = attributeValueConverterService;
    }

    @Async(AsyncServiceSpringConfig.EXECUTOR_BEAN_NAME)
    void exportPlain(
            final ConfigurationFormat exportFormat,
            final OutputStream out,
            final Long institutionId,
            final Long configurationNodeId) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Start export SEB plain XML configuration asynconously");
        }

        try {

            // get all defined root configuration attributes prepared and sorted
            final List<ConfigurationAttribute> sortedAttributes = this.configurationAttributeDAO.getAllRootAttributes()
                    .getOrThrow()
                    .stream()
                    .flatMap(this::convertAttribute)
                    .filter(exportFormatBasedAttributeFilter(exportFormat))
                    .sorted()
                    .collect(Collectors.toList());

            // get follow-up configurationId for given configurationNodeId
            final Long configurationId = this.configurationDAO
                    .getFollowupConfiguration(configurationNodeId)
                    .getOrThrow().id;

            final Function<ConfigurationAttribute, ConfigurationValue> configurationValueSupplier =
                    getConfigurationValueSupplier(institutionId, configurationId);

            writeHeader(exportFormat, out);

            // write attributes
            final Iterator<ConfigurationAttribute> iterator = sortedAttributes.iterator();
            while (iterator.hasNext()) {

                final ConfigurationAttribute attribute = iterator.next();
                final AttributeValueConverter attributeValueConverter =
                        this.attributeValueConverterService.getAttributeValueConverter(attribute);

                switch (exportFormat) {
                    case XML: {
                        attributeValueConverter.convertToXML(
                                out,
                                attribute,
                                configurationValueSupplier);
                        break;
                    }
                    case JSON: {
                        attributeValueConverter.convertToJSON(
                                out,
                                attribute,
                                configurationValueSupplier);
                        if (iterator.hasNext()) {
                            out.write(JSON_SEPARATOR);
                        }
                        break;
                    }
                }
            }

            writeFooter(exportFormat, out);

            if (log.isDebugEnabled()) {
                log.debug("Finished export SEB plain XML configuration asynconously");
            }

        } catch (final Exception e) {
            log.error("Unexpected error while trying to write SEB Exam Configuration XML to output stream: ", e);
            throw e;
        } finally {
            try {
                out.flush();
            } catch (final IOException e1) {
                log.error("Unable to flush output stream after error");
            }
            IOUtils.closeQuietly(out);
        }
    }

    private Predicate<ConfigurationAttribute> exportFormatBasedAttributeFilter(final ConfigurationFormat format) {
        // Filter originatorVersion according to: https://www.safeexambrowser.org/developer/seb-config-key.html
        return attr -> !("originatorVersion".equals(attr.getName()) && format == ConfigurationFormat.JSON);
    }

    private void writeFooter(
            final ConfigurationFormat exportFormat,
            final OutputStream out) throws IOException {

        if (exportFormat == ConfigurationFormat.XML) {
            // plist close
            out.write(XML_DICT_END_UTF_8);
            out.write(XML_PLIST_END_UTF_8);
        } else {
            out.write(JSON_END);
        }
    }

    private void writeHeader(
            final ConfigurationFormat exportFormat,
            final OutputStream out) throws IOException {

        if (exportFormat == ConfigurationFormat.XML) {
            writeXMLHeaderInformation(out);
        } else {
            writeJSONHeaderInformation(out);
        }
    }

    private void writeJSONHeaderInformation(final OutputStream out) throws IOException {
        out.write(JSON_START);
    }

    private void writeXMLHeaderInformation(final OutputStream out) throws IOException {
        // write headers
        out.write(XML_VERSION_HEADER_UTF_8);
        out.write(XML_DOCTYPE_HEADER_UTF_8);

        // plist open
        out.write(XML_PLIST_START_V1_UTF_8);
        out.write(XML_DICT_START_UTF_8);
    }

    private Stream<ConfigurationAttribute> convertAttribute(final ConfigurationAttribute attr) {
        final AttributeValueConverter attributeValueConverter =
                this.attributeValueConverterService.getAttributeValueConverter(attr);
        if (attributeValueConverter != null) {
            return attributeValueConverter.convertAttribute(attr);
        } else {
            return Stream.of(attr);
        }
    }

    @Async(AsyncServiceSpringConfig.EXECUTOR_BEAN_NAME)
    void importPlainXML(final InputStream in, final Long institutionId, final Long configurationNodeId) {
        // TODO version 1
    }

    private Function<ConfigurationAttribute, ConfigurationValue> getConfigurationValueSupplier(
            final Long institutionId,
            final Long configurationId) {

        final Map<Long, ConfigurationValue> mapping = this.configurationValueDAO
                .allRootAttributeValues(institutionId, configurationId)
                .getOrThrow()
                .stream()
                .collect(Collectors.toMap(
                        ConfigurationValue::getAttributeId,
                        Function.identity()));

        return attr -> mapping.get(attr.id);
    }

}