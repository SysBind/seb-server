/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gui.service.examconfig.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.model.sebconfig.ConfigurationAttribute;
import ch.ethz.seb.sebserver.gbl.util.Tuple;
import ch.ethz.seb.sebserver.gbl.util.Utils;
import ch.ethz.seb.sebserver.gui.service.examconfig.ExamConfigurationService;
import ch.ethz.seb.sebserver.gui.service.i18n.I18nSupport;

public abstract class SelectionFieldBuilder {

    protected List<Tuple<String>> getLocalizedResources(
            final ConfigurationAttribute attribute,
            final I18nSupport i18nSupport) {

        return getLocalizedRes(attribute, i18nSupport, false);
    }

    protected List<Tuple<String>> getLocalizedResourcesAsToolTip(
            final ConfigurationAttribute attribute,
            final I18nSupport i18nSupport) {

        return getLocalizedRes(attribute, i18nSupport, true);
    }

    private List<Tuple<String>> getLocalizedRes(
            final ConfigurationAttribute attribute,
            final I18nSupport i18nSupport,
            final boolean toolTipResources) {

        if (attribute == null) {
            return Collections.emptyList();
        }

        final Map<String, String> attributeDependencyMap = Utils.getAttributeDependencyMap(attribute);
        final String prefix =
                (attributeDependencyMap.containsKey(ConfigurationAttribute.DEPENDENCY_RESOURCE_LOC_TEXT_KEY))
                        ? attributeDependencyMap.get(ConfigurationAttribute.DEPENDENCY_RESOURCE_LOC_TEXT_KEY) + "."
                        : ExamConfigurationService.ATTRIBUTE_LABEL_LOC_TEXT_PREFIX + attribute.name + ".";

        return Arrays.asList(StringUtils.split(
                attribute.resources,
                Constants.LIST_SEPARATOR))
                .stream()
                .map(value -> {
                    final String key = prefix + value + ((toolTipResources)
                            ? ExamConfigurationService.TOOL_TIP_SUFFIX
                            : "");
                    final String text = i18nSupport.getText(key, "");
                    return new Tuple<>(value, (StringUtils.isBlank(text))
                            ? (toolTipResources)
                                    ? text
                                    : value
                            : text);
                })
                .collect(Collectors.toList());
    }

}