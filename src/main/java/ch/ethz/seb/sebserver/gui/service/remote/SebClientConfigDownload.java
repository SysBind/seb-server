/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.service.remote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.rap.rwt.RWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.api.API;
import ch.ethz.seb.sebserver.gbl.profile.GuiProfile;
import ch.ethz.seb.sebserver.gui.service.remote.webservice.api.RestService;
import ch.ethz.seb.sebserver.gui.service.remote.webservice.api.lmssetup.ExportSEBConfig;

@Lazy
@Component
@GuiProfile
public class SebClientConfigDownload implements DownloadServiceHandler {

    private static final Logger log = LoggerFactory.getLogger(SebClientConfigDownload.class);

    // TODO must this be configurable?
    public static final String SEB_CLIENT_CONFIG_FILE_NAME = "SebClientSettings.seb";

    private final RestService restService;

    protected SebClientConfigDownload(final RestService restService) {
        this.restService = restService;
    }

    @Override
    public String getName() {
        return SEB_CLIENT_CONFIG_FILE_NAME;
    }

    @Override
    public void processDownload(final HttpServletRequest request, final HttpServletResponse response) {
        try {

            log.debug("download requested... trying to get needed parameter from request");

            final String modelId = request.getParameter(API.PARAM_MODEL_ID);
            if (StringUtils.isBlank(modelId)) {
                log.error("No needed modelId parameter found within HttpServletRequest. Download request is ignored");
                return;
            }

            log.debug("Found modelId: {} for {} download. Trying to request webservice...", modelId,
                    SEB_CLIENT_CONFIG_FILE_NAME);

            final byte[] configFile = this.restService.getBuilder(ExportSEBConfig.class)
                    .withURIVariable(API.PARAM_MODEL_ID, modelId)
                    .call()
                    .getOrThrow();

            if (configFile == null) {
                log.error("No or empty SEB Client configuration received from webservice. Download request is ignored");
                return;
            }

            log.debug("Sucessfully get SEB Client configuration from webservice. File size: {}", configFile.length);

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentLength(configFile.length);
            response.setHeader(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + SEB_CLIENT_CONFIG_FILE_NAME + "\"");

            log.debug("Write the SEB Client configuration to response output");

            response.getOutputStream().write(configFile);

        } catch (final Exception e) {
            log.error(
                    "Unexpected error while trying to start SEB Client configuration download. The download is ignored. Cause: ",
                    e);
        }
    }

    public String downloadSEBClientConfigURL(final String modelId) {
        final StringBuilder url = new StringBuilder()
                .append(RWT.getServiceManager()
                        .getServiceHandlerUrl(DownloadService.DOWNLOAD_SERVICE_NAME))
                .append(Constants.FORM_URL_ENCODED_SEPARATOR)
                .append(API.PARAM_MODEL_ID)
                .append(Constants.FORM_URL_ENCODED_NAME_VALUE_SEPARATOR)
                .append(modelId)
                .append(Constants.FORM_URL_ENCODED_SEPARATOR)
                .append(DownloadService.HANDLER_NAME_PARAMETER)
                .append(Constants.FORM_URL_ENCODED_NAME_VALUE_SEPARATOR)
                .append(SebClientConfigDownload.SEB_CLIENT_CONFIG_FILE_NAME);
        return url.toString();
    }

}