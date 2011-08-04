/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.timesheet.model.Project;

import java.util.List;

/**
 * The persistence utility for the project service. This utility wraps {@link ProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectPersistence
 * @see ProjectPersistenceImpl
 * @generated
 */
public class ProjectUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Project project) {
		getPersistence().clearCache(project);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Project> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Project> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Project> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Project remove(Project project) throws SystemException {
		return getPersistence().remove(project);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Project update(Project project, boolean merge)
		throws SystemException {
		return getPersistence().update(project, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Project update(Project project, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(project, merge, serviceContext);
	}

	/**
	* Caches the project in the entity cache if it is enabled.
	*
	* @param project the project
	*/
	public static void cacheResult(com.liferay.timesheet.model.Project project) {
		getPersistence().cacheResult(project);
	}

	/**
	* Caches the projects in the entity cache if it is enabled.
	*
	* @param projects the projects
	*/
	public static void cacheResult(
		java.util.List<com.liferay.timesheet.model.Project> projects) {
		getPersistence().cacheResult(projects);
	}

	/**
	* Creates a new project with the primary key. Does not add the project to the database.
	*
	* @param projectId the primary key for the new project
	* @return the new project
	*/
	public static com.liferay.timesheet.model.Project create(long projectId) {
		return getPersistence().create(projectId);
	}

	/**
	* Removes the project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectId the primary key of the project
	* @return the project that was removed
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Project remove(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException {
		return getPersistence().remove(projectId);
	}

	public static com.liferay.timesheet.model.Project updateImpl(
		com.liferay.timesheet.model.Project project, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(project, merge);
	}

	/**
	* Returns the project with the primary key or throws a {@link com.liferay.timesheet.NoSuchProjectException} if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project
	* @throws com.liferay.timesheet.NoSuchProjectException if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Project findByPrimaryKey(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchProjectException {
		return getPersistence().findByPrimaryKey(projectId);
	}

	/**
	* Returns the project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectId the primary key of the project
	* @return the project, or <code>null</code> if a project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Project fetchByPrimaryKey(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(projectId);
	}

	/**
	* Returns all the projects.
	*
	* @return the projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Project> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @return the range of projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Project> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of projects
	* @param end the upper bound of the range of projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Project> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of projects.
	*
	* @return the number of projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ProjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					ProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(ProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(ProjectPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(ProjectUtil.class, "_persistence");
	}

	private static ProjectPersistence _persistence;
}