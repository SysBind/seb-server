/*
 * Copyright (c) 2018 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.weblayer.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.model.EntityType;
import ch.ethz.seb.sebserver.gbl.model.Page;
import ch.ethz.seb.sebserver.gbl.model.user.UserActivityLog;
import ch.ethz.seb.sebserver.gbl.profile.WebServiceProfile;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.UserActivityLogRecordDynamicSqlSupport;
import ch.ethz.seb.sebserver.webservice.datalayer.batis.mapper.UserRecordDynamicSqlSupport;
import ch.ethz.seb.sebserver.webservice.servicelayer.PaginationService;
import ch.ethz.seb.sebserver.webservice.servicelayer.authorization.AuthorizationGrantService;
import ch.ethz.seb.sebserver.webservice.servicelayer.authorization.PrivilegeType;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.UserActivityLogDAO;

@WebServiceProfile
@RestController
@RequestMapping("/${sebserver.webservice.api.admin.endpoint}" + RestAPI.ENDPOINT_USER_ACTIVITY_LOG)
public class UserActivityLogController {

    private final UserActivityLogDAO userActivityLogDAO;
    private final AuthorizationGrantService authorizationGrantService;
    private final PaginationService paginationService;

    public UserActivityLogController(
            final UserActivityLogDAO userActivityLogDAO,
            final AuthorizationGrantService authorizationGrantService,
            final PaginationService paginationService) {

        this.userActivityLogDAO = userActivityLogDAO;
        this.authorizationGrantService = authorizationGrantService;
        this.paginationService = paginationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<UserActivityLog> getAll(
            @RequestParam(required = false) final Long from,
            @RequestParam(required = false) final Long to,
            @RequestParam(required = false) final String activityTypes,
            @RequestParam(required = false) final String entityTypes) {

        checkBaseReadPrivilege();
        this.paginationService.setDefaultLimit(UserActivityLogRecordDynamicSqlSupport.userActivityLogRecord);
        return _getAll(null, from, to, activityTypes, entityTypes);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public Collection<UserActivityLog> getAllForUser(
            @PathVariable final String userId,
            @RequestParam(required = false) final Long from,
            @RequestParam(required = false) final Long to,
            @RequestParam(required = false) final String activityTypes,
            @RequestParam(required = false) final String entityTypes) {

        checkBaseReadPrivilege();
        this.paginationService.setDefaultLimit(UserActivityLogRecordDynamicSqlSupport.userActivityLogRecord);
        return _getAll(userId, from, to, activityTypes, entityTypes);
    }

    @RequestMapping(path = "/page", method = RequestMethod.GET)
    public Page<UserActivityLog> getPage(
            @RequestParam(required = false) final Long from,
            @RequestParam(required = false) final Long to,
            @RequestParam(required = false) final String activityTypes,
            @RequestParam(required = false) final String entityTypes,
            @RequestParam(name = Page.ATTR_PAGE_NUMBER, required = false) final Integer pageNumber,
            @RequestParam(name = Page.ATTR_PAGE_SIZE, required = false) final Integer pageSize,
            @RequestParam(name = Page.ATTR_SORT_BY, required = false) final String sortBy,
            @RequestParam(name = Page.ATTR_SORT_ORDER, required = false) final Page.SortOrder sortOrder) {

        checkBaseReadPrivilege();
        return this.paginationService.getPage(
                pageNumber,
                pageSize,
                sortBy,
                sortOrder,
                UserRecordDynamicSqlSupport.userRecord,
                () -> _getAll(null, from, to, activityTypes, entityTypes));
    }

    @RequestMapping(path = "/page/{userId}", method = RequestMethod.GET)
    public Page<UserActivityLog> getPageForUser(
            @PathVariable final String userId,
            @RequestParam(required = false) final Long from,
            @RequestParam(required = false) final Long to,
            @RequestParam(required = false) final String activityTypes,
            @RequestParam(required = false) final String entityTypes,
            @RequestParam(name = Page.ATTR_PAGE_NUMBER, required = false) final Integer pageNumber,
            @RequestParam(name = Page.ATTR_PAGE_SIZE, required = false) final Integer pageSize,
            @RequestParam(name = Page.ATTR_SORT_BY, required = false) final String sortBy,
            @RequestParam(name = Page.ATTR_SORT_ORDER, required = false) final Page.SortOrder sortOrder) {

        checkBaseReadPrivilege();
        return this.paginationService.getPage(
                pageNumber,
                pageSize,
                sortBy,
                sortOrder,
                UserRecordDynamicSqlSupport.userRecord,
                () -> _getAll(userId, from, to, activityTypes, entityTypes));
    }

    private void checkBaseReadPrivilege() {
        this.authorizationGrantService.checkHasAnyPrivilege(
                EntityType.USER_ACTIVITY_LOG,
                PrivilegeType.READ_ONLY);
    }

    private Collection<UserActivityLog> _getAll(
            final String userId,
            final Long from,
            final Long to,
            final String activityTypes,
            final String entityTypes) {

        final Set<String> _activityTypes = (activityTypes != null)
                ? Collections.unmodifiableSet(new HashSet<>(
                        Arrays.asList(StringUtils.split(activityTypes, Constants.LIST_SEPARATOR))))
                : null;
        final Set<String> _entityTypes = (entityTypes != null)
                ? Collections.unmodifiableSet(new HashSet<>(
                        Arrays.asList(StringUtils.split(entityTypes, Constants.LIST_SEPARATOR))))
                : null;

        if (_activityTypes != null || _entityTypes != null) {

            return this.userActivityLogDAO.all(userId, from, to, record -> {
                if (_activityTypes != null && !_activityTypes.contains(record.getActivityType())) {
                    return false;
                }
                if (_entityTypes != null && !_entityTypes.contains(record.getEntityType())) {
                    return false;
                }

                return true;
            }).getOrThrow();

        } else {

            return this.userActivityLogDAO.all(userId, from, to, record -> true)
                    .getOrThrow();
        }
    }

}