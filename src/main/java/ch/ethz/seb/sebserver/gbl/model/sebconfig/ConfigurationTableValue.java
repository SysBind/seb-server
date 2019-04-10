/*
 * Copyright (c) 2018 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gbl.model.sebconfig;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ethz.seb.sebserver.gbl.model.Domain.CONFIGURATION_VALUE;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class ConfigurationTableValue {

    public static final String ATTR_COLUMNS = "columnAttributeIds";
    public static final String ATTR_VALUES = "values";

    @JsonProperty(CONFIGURATION_VALUE.ATTR_CONFIGURATION_ID)
    public final Long configurationId;

    @JsonProperty(CONFIGURATION_VALUE.ATTR_CONFIGURATION_ATTRIBUTE_ID)
    public final long attributeId;

    @JsonProperty(ATTR_COLUMNS)
    public final List<Long> columnAttributeIds;

    @JsonProperty(ATTR_VALUES)
    public final List<String> values;

    @JsonCreator
    public ConfigurationTableValue(
            @JsonProperty(CONFIGURATION_VALUE.ATTR_CONFIGURATION_ID) final Long configurationId,
            @JsonProperty(CONFIGURATION_VALUE.ATTR_CONFIGURATION_ATTRIBUTE_ID) final Long attributeId,
            @JsonProperty(ATTR_COLUMNS) final List<Long> columns,
            @JsonProperty(ATTR_VALUES) final List<String> values) {

        this.configurationId = configurationId;
        this.attributeId = attributeId;
        this.columnAttributeIds = Collections.unmodifiableList(columns);
        this.values = Collections.unmodifiableList(values);
    }

    public Long getConfigurationId() {
        return this.configurationId;
    }

    public long getAttributeId() {
        return this.attributeId;
    }

    public List<Long> getColumnAttributeIds() {
        return this.columnAttributeIds;
    }

    public List<String> getValues() {
        return this.values;
    }

    @Override
    public String toString() {
        return "ConfigurationTableValue [configurationId=" + this.configurationId + ", attributeId=" + this.attributeId
                + ", columnAttributeIds=" + this.columnAttributeIds + ", values=" + this.values + "]";
    }

}