/*
 * Copyright (c) 2019 ETH Zürich, Educational Development and Technology (LET)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package ch.ethz.seb.sebserver.webservice.weblayer.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.SqlTable;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ch.ethz.seb.sebserver.gbl.Constants;
import ch.ethz.seb.sebserver.gbl.api.API;
import ch.ethz.seb.sebserver.gbl.api.API.BulkActionType;
import ch.ethz.seb.sebserver.gbl.api.EntityType;
import ch.ethz.seb.sebserver.gbl.api.POSTMapper;
import ch.ethz.seb.sebserver.gbl.authorization.PrivilegeType;
import ch.ethz.seb.sebserver.gbl.model.Entity;
import ch.ethz.seb.sebserver.gbl.model.EntityKey;
import ch.ethz.seb.sebserver.gbl.model.EntityName;
import ch.ethz.seb.sebserver.gbl.model.EntityProcessingReport;
import ch.ethz.seb.sebserver.gbl.model.Page;
import ch.ethz.seb.sebserver.gbl.util.Result;
import ch.ethz.seb.sebserver.webservice.servicelayer.PaginationService;
import ch.ethz.seb.sebserver.webservice.servicelayer.authorization.AuthorizationService;
import ch.ethz.seb.sebserver.webservice.servicelayer.authorization.GrantEntity;
import ch.ethz.seb.sebserver.webservice.servicelayer.authorization.UserService;
import ch.ethz.seb.sebserver.webservice.servicelayer.bulkaction.BulkAction;
import ch.ethz.seb.sebserver.webservice.servicelayer.bulkaction.BulkActionService;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.EntityDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.FilterMap;
import ch.ethz.seb.sebserver.webservice.servicelayer.dao.UserActivityLogDAO;
import ch.ethz.seb.sebserver.webservice.servicelayer.validation.BeanValidationService;

/** Abstract Entity-Controller that defines generic Entity rest API endpoints that are supported
 * by all entity types.
 *
 * @param <T> The concrete Entity domain-model type used on all GET, PUT
 * @param <M> The concrete Entity domain-model type used for POST methods (new) */
public abstract class EntityController<T extends Entity, M extends Entity> {

    protected final AuthorizationService authorization;
    protected final BulkActionService bulkActionService;
    protected final EntityDAO<T, M> entityDAO;
    protected final UserActivityLogDAO userActivityLogDAO;
    protected final PaginationService paginationService;
    protected final BeanValidationService beanValidationService;

    protected EntityController(
            final AuthorizationService authorization,
            final BulkActionService bulkActionService,
            final EntityDAO<T, M> entityDAO,
            final UserActivityLogDAO userActivityLogDAO,
            final PaginationService paginationService,
            final BeanValidationService beanValidationService) {

        this.authorization = authorization;
        this.bulkActionService = bulkActionService;
        this.entityDAO = entityDAO;
        this.userActivityLogDAO = userActivityLogDAO;
        this.paginationService = paginationService;
        this.beanValidationService = beanValidationService;
    }

    /** This is called by Spring to initialize the WebDataBinder and is used here to
     * initialize the default value binding for the institutionId request-parameter
     * that has the current users insitutionId as default.
     *
     * See also UserService.addUsersInstitutionDefaultPropertySupport */
    @InitBinder
    public void initBinder(final WebDataBinder binder) throws Exception {
        this.authorization
                .getUserService()
                .addUsersInstitutionDefaultPropertySupport(binder);
    }

    // ******************
    // * GET (getAll)
    // ******************

    /** The get-all or get-page rest endpoint for all types of Entity and returns a Page of
     * entities of specific type.
     *
     * GET /{api}/{entity-type-endpoint-name}
     *
     * GET /admin-api/v1/exam
     * GET /admin-api/v1/exam?page_number=2&page_size=10&sort=-name
     * GET /admin-api/v1/exam?name=seb&active=true
     *
     * @param institutionId The institution identifier of the request.
     *            Default is the institution identifier of the institution of the current user
     * @param pageNumber the number of the page that is requested
     * @param pageSize the size of the page that is requested
     * @param sort the sort parameter to sort the list of entities before paging
     *            the sort parameter is the name of the entity-model attribute to sort with a leading '-' sign for
     *            descending sort order
     * @param allRequestParams a MultiValueMap of all request parameter that is used for filtering
     * @return Page of domain-model-entities of specified type */
    @RequestMapping(
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<T> getPage(
            @RequestParam(
                    name = API.PARAM_INSTITUTION_ID,
                    required = true,
                    defaultValue = UserService.USERS_INSTITUTION_AS_DEFAULT) final Long institutionId,
            @RequestParam(name = Page.ATTR_PAGE_NUMBER, required = false) final Integer pageNumber,
            @RequestParam(name = Page.ATTR_PAGE_SIZE, required = false) final Integer pageSize,
            @RequestParam(name = Page.ATTR_SORT, required = false) final String sort,
            @RequestParam final MultiValueMap<String, String> allRequestParams) {

        // at least current user must have read access for specified entity type within its own institution
        checkReadPrivilege(institutionId);

        final FilterMap filterMap = new FilterMap(allRequestParams);

        // if current user has no read access for specified entity type within other institution
        // then the current users institutionId is put as a SQL filter criteria attribute to extends query performance
        if (!this.authorization.hasGrant(PrivilegeType.READ, getGrantEntityType())) {
            filterMap.putIfAbsent(API.PARAM_INSTITUTION_ID, String.valueOf(institutionId));
        }

        return this.paginationService.getPage(
                pageNumber,
                pageSize,
                sort,
                getSQLTableOfEntity(),
                () -> getAll(filterMap)).getOrThrow();
    }

    // ******************
    // * GET (names)
    // ******************

    @RequestMapping(
            path = API.NAMES_PATH_SEGMENT,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<EntityName> getNames(
            @RequestParam(
                    name = API.PARAM_INSTITUTION_ID,
                    required = true,
                    defaultValue = UserService.USERS_INSTITUTION_AS_DEFAULT) final Long institutionId,
            @RequestParam final MultiValueMap<String, String> allRequestParams) {

        // at least current user must have read access for specified entity type within its own institution
        checkReadPrivilege(institutionId);

        final FilterMap filterMap = new FilterMap(allRequestParams);

        // if current user has no read access for specified entity type within other institution then its own institution,
        // then the current users institutionId is put as a SQL filter criteria attribute to extends query performance
        if (!this.authorization.hasGrant(PrivilegeType.READ, this.getGrantEntityType())) {
            filterMap.putIfAbsent(API.PARAM_INSTITUTION_ID, String.valueOf(institutionId));
        }

        return getAll(filterMap)
                .getOrThrow()
                .stream()
                .map(Entity::toName)
                .collect(Collectors.toList());
    }

    // ******************
    // * GET (dependency)
    // ******************

    @RequestMapping(
            path = API.MODEL_ID_VAR_PATH_SEGMENT + API.DEPENDENCY_PATH_SEGMENT,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Collection<EntityKey> getDependencies(
            @PathVariable final String modelId,
            @RequestParam(name = API.PARAM_BULK_ACTION_TYPE, required = true) final BulkActionType bulkActionType) {

        this.entityDAO
                .byModelId(modelId)
                .map(this::checkReadAccess);

        final BulkAction bulkAction = new BulkAction(
                bulkActionType,
                this.entityDAO.entityType(),
                Arrays.asList(new EntityKey(modelId, this.entityDAO.entityType())));

        this.bulkActionService.collectDependencies(bulkAction);
        return bulkAction.getDependencies();
    }

    // ******************
    // * GET (single)
    // ******************

    @RequestMapping(
            path = API.MODEL_ID_VAR_PATH_SEGMENT,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public T getBy(@PathVariable final String modelId) {

        return this.entityDAO
                .byModelId(modelId)
                .flatMap(this::checkReadAccess)
                .getOrThrow();
    }

    // ******************
    // * GET (list)
    // ******************

    @RequestMapping(
            path = API.LIST_PATH_SEGMENT,
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<T> getForIds(@RequestParam(name = API.PARAM_MODEL_ID_LIST, required = true) final String modelIds) {

        return Result.tryCatch(() -> {

            return Arrays.asList(StringUtils.split(modelIds, Constants.LIST_SEPARATOR_CHAR))
                    .stream()
                    .map(modelId -> new EntityKey(modelId, this.entityDAO.entityType()))
                    .collect(Collectors.toSet());

        })
                .flatMap(this.entityDAO::byEntityKeys)
                .getOrThrow()
                .stream()
                .filter(this::hasReadAccess)
                .collect(Collectors.toList());
    }

    // ******************
    // * POST (create)
    // ******************

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public T create(
            @RequestParam final MultiValueMap<String, String> allRequestParams,
            @RequestParam(
                    name = API.PARAM_INSTITUTION_ID,
                    required = true,
                    defaultValue = UserService.USERS_INSTITUTION_AS_DEFAULT) final Long institutionId) {

        // check modify privilege for requested institution and concrete entityType
        this.checkModifyPrivilege(institutionId);

        final POSTMapper postMap = new POSTMapper(allRequestParams)
                .putIfAbsent(API.PARAM_INSTITUTION_ID, String.valueOf(institutionId));

        final M requestModel = this.createNew(postMap);

        return this.checkCreateAccess(requestModel)
                .flatMap(this::validForCreate)
                .flatMap(this.entityDAO::createNew)
                .flatMap(this.userActivityLogDAO::logCreate)
                .flatMap(this::notifyCreated)
                .getOrThrow();
    }

    // ****************
    // * PUT (save)
    // ****************

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public T savePut(@Valid @RequestBody final T modifyData) {

        return this.checkModifyAccess(modifyData)
                .flatMap(this::validForSave)
                .flatMap(this.entityDAO::save)
                .flatMap(this.userActivityLogDAO::logModify)
                .flatMap(this::notifySaved)
                .getOrThrow();
    }

    // ******************
    // * DELETE (delete)
    // ******************

    @RequestMapping(
            path = API.MODEL_ID_VAR_PATH_SEGMENT,
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public EntityProcessingReport hardDelete(@PathVariable final String modelId) {
        final EntityType entityType = this.entityDAO.entityType();
        final BulkAction bulkAction = new BulkAction(
                BulkActionType.HARD_DELETE,
                entityType,
                new EntityKey(modelId, entityType));

        return this.entityDAO.byModelId(modelId)
                .flatMap(this::checkWriteAccess)
                .flatMap(entity -> this.bulkActionService.createReport(bulkAction))
                .getOrThrow();
    }

    protected void checkReadPrivilege() {
        this.authorization.check(
                PrivilegeType.READ,
                getGrantEntityType());
    }

    protected void checkReadPrivilege(final Long institutionId) {
        this.authorization.check(
                PrivilegeType.READ,
                getGrantEntityType(),
                institutionId);
    }

    protected void checkModifyPrivilege(final Long institutionId) {
        this.authorization.check(
                PrivilegeType.MODIFY,
                getGrantEntityType(),
                institutionId);
    }

    protected Result<Collection<T>> getAll(final FilterMap filterMap) {
        return this.entityDAO.allMatching(
                filterMap,
                this::hasReadAccess);
    }

    protected Result<T> notifyCreated(final T entity) {
        return Result.of(entity);
    }

    protected Result<M> validForCreate(final M entity) {
        if (entity.getModelId() == null) {
            return this.beanValidationService.validateBean(entity);
        } else {
            return Result.ofError(
                    new IllegalAPIArgumentException("Model identifier already defined: " + entity.getModelId()));
        }
    }

    protected Result<T> validForSave(final T entity) {
        if (entity.getModelId() != null) {
            return Result.of(entity);
        } else {
            return Result.ofError(new IllegalAPIArgumentException("Missing model identifier"));
        }
    }

    protected Result<T> notifySaved(final T entity) {
        return Result.of(entity);
    }

    protected Result<T> checkReadAccess(final T entity) {
        final GrantEntity grantEntity = toGrantEntity(entity);
        if (grantEntity != null) {
            this.authorization.checkRead(grantEntity);
        }
        return Result.of(entity);
    }

    protected boolean hasReadAccess(final T entity) {
        final GrantEntity grantEntity = toGrantEntity(entity);
        if (grantEntity != null) {
            return this.authorization.hasReadonlyGrant(grantEntity);
        }

        return true;
    }

    protected Result<T> checkModifyAccess(final T entity) {
        final GrantEntity grantEntity = toGrantEntity(entity);
        if (grantEntity != null) {
            this.authorization.checkModify(grantEntity);
        }
        return Result.of(entity);
    }

    protected Result<T> checkWriteAccess(final T entity) {
        final GrantEntity grantEntity = toGrantEntity(entity);
        if (grantEntity != null) {
            this.authorization.checkWrite(grantEntity);
        }
        return Result.of(entity);
    }

    /** Checks creation (write) privileges for a given Entity.
     * Usually the GrantEntity and the Entity instance are the same if the Entity extends from GrantEntity.
     * Otherwise the implementing EntityController must override this method and resolve the
     * related GrantEntity for a given Entity.
     * For example, the GrantEntity of Indicator is the related Exam
     * 
     * @param entity the Entity to check creation/write access for
     * @return Result of the access check containing either the original entity or an error if no access granted */
    protected Result<M> checkCreateAccess(final M entity) {
        if (entity instanceof GrantEntity) {
            this.authorization.checkWrite((GrantEntity) entity);
            return Result.of(entity);
        }

        return Result.ofError(new IllegalArgumentException("Entity instance is not of type GrantEntity. "
                + "Do override the checkCreateAccess method from EntityController within the specific -Controller implementation"));
    }

    /** Gets the GrantEntity instance for a given Entity instance.
     * Usually the GrantEntity and the Entity instance are the same if the Entity extends from GrantEntity.
     * Otherwise the implementing EntityController must override this method and resolve the
     * related GrantEntity for a given Entity.
     * For example, the GrantEntity of Indicator is the related Exam
     * 
     * @param entity the Entity to get the related GrantEntity for
     * @return the GrantEntity instance for a given Entity instance */
    protected GrantEntity toGrantEntity(final T entity) {
        if (entity == null) {
            return null;
        }

        if (entity instanceof GrantEntity) {
            return (GrantEntity) entity;
        }

        throw new IllegalArgumentException("Entity instance is not of type GrantEntity. "
                + "Do override the toGrantEntity method from EntityController within the specific -Controller implementation");
    }

    /** Get the EntityType of the GrantEntity that is used for grant checks of the concrete Entity.
     *
     * NOTE: override this if the EntityType of the GrantEntity is not the same as the Entity itself.
     * For example, the Exam is the GrantEntity of a Indicator
     *
     * @return the EntityType of the GrantEntity that is used for grant checks of the concrete Entity */
    protected EntityType getGrantEntityType() {
        return this.entityDAO.entityType();
    }

    /** Implements the creation of a new entity from the post parameters given within the POSTMapper
     *
     * @param postParams contains all post parameter from request
     * @return new created Entity instance */
    protected abstract M createNew(POSTMapper postParams);

    /** Gets the MyBatis SqlTable for the concrete Entity
     *
     * @return the MyBatis SqlTable for the concrete Entity */
    protected abstract SqlTable getSQLTableOfEntity();

}