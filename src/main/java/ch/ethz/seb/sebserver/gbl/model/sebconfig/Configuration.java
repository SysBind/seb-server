/*
 * Copyright (c) 2018 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.gbl.model.sebconfig;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.model.Domain.CONFIGURATION;
import ch.ethz.seb.sebserver.gbl.model.Domain.CONFIGURATION_NODE;
import ch.ethz.seb.sebserver.webservice.servicelayer.authorization.GrantEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Configuration implements GrantEntity {

    @JsonProperty(CONFIGURATION.ATTR_ID)
    public final Long id;

    @NotNull
    @JsonProperty(CONFIGURATION_NODE.ATTR_INSTITUTION_ID)
    public final Long institutionId;

    @NotNull
    @JsonProperty(CONFIGURATION.ATTR_CONFIGURATION_NODE_ID)
    public final Long nodeId;

    @JsonProperty(CONFIGURATION.ATTR_VERSION)
    public final String version;

    @NotNull
    @JsonProperty(CONFIGURATION.ATTR_VERSION_DATE)
    public final DateTime versionDate;

    @NotNull
    @JsonProperty(CONFIGURATION.ATTR_FOLLOWUP)
    public final Boolean followup;

    @JsonCreator
    public Configuration(
            @JsonProperty(CONFIGURATION.ATTR_ID) final Long id,
            @JsonProperty(CONFIGURATION_NODE.ATTR_INSTITUTION_ID) final Long institutionId,
            @JsonProperty(CONFIGURATION.ATTR_CONFIGURATION_NODE_ID) final Long nodeId,
            @JsonProperty(CONFIGURATION.ATTR_VERSION) final String version,
            @JsonProperty(CONFIGURATION.ATTR_VERSION_DATE) final DateTime versionDate,
            @JsonProperty(CONFIGURATION.ATTR_FOLLOWUP) final Boolean followup) {

        this.id = id;
        this.institutionId = institutionId;
        this.nodeId = nodeId;
        this.version = version;
        this.versionDate = versionDate;
        this.followup = followup;
    }

    @Override
    public EntityType entityType() {
        return EntityType.CONFIGURATION;
    }

    @Override
    public String getName() {
        return (this.version != null)
                ? this.version
                : this.versionDate.toString(Constants.DEFAULT_DISPLAY_DATE_FORMAT);
    }

    @Override
    public String getModelId() {
        return (this.id != null)
                ? String.valueOf(this.id)
                : null;
    }

    @Override
    public Long getInstitutionId() {
        return this.institutionId;
    }

    public Long getId() {
        return this.id;
    }

    public Long getNodeId() {
        return this.nodeId;
    }

    public String getVersion() {
        return this.version;
    }

    public DateTime getVersionDate() {
        return this.versionDate;
    }

    public Boolean getFollowup() {
        return this.followup;
    }

    @Override
    public String toString() {
        return "Configuration [id=" + this.id + ", nodeId=" + this.nodeId + ", version=" + this.version
                + ", versionDate="
                + this.versionDate + ", followup=" + this.followup + "]";
    }

}