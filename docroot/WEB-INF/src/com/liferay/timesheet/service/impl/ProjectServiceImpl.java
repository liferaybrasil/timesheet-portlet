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

package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.base.ProjectServiceBaseImpl;
import com.liferay.timesheet.service.permission.TimesheetPermission;
import com.liferay.timesheet.service.permission.TimesheetProjectPermission;
import com.liferay.timesheet.util.ActionKeys;

/**
 * @author Antonio Junior
 */
public class ProjectServiceImpl extends ProjectServiceBaseImpl {

	public Project addProject(
			long userId, String description, int endDateMonth, int endDateDay,
			int endDateYear, int startDateMonth, int startDateDay,
			int startDateYear, String name, double wage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TimesheetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_PROJECT);

		return projectLocalService.addProject(
			userId, description, endDateMonth, endDateDay, endDateYear,
			startDateMonth, startDateDay, startDateYear,name, wage,
			serviceContext);
	}

	public void deleteProject(long companyId, long projectId)
		throws PortalException, SystemException {

		TimesheetProjectPermission.check(
			getPermissionChecker(), projectId, ActionKeys.DELETE);

		projectLocalService.deleteProject(companyId, projectId);
	}

	public Project getProject(long projectId)
		throws PortalException, SystemException {

		TimesheetProjectPermission.check(
			getPermissionChecker(), projectId, ActionKeys.VIEW);

		return projectLocalService.getProject(projectId);
	}

	public Project updateProject(
			long projectId, long userId, String description, int endDateMonth,
			int endDateDay, int endDateYear, int startDateMonth,
			int startDateDay, int startDateYear, String name, double wage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TimesheetProjectPermission.check(
			getPermissionChecker(), projectId, ActionKeys.UPDATE);

		return projectLocalService.updateProject(
			projectId, userId, description, endDateMonth,
			endDateDay, endDateYear, startDateMonth, startDateDay,
			startDateYear,name, wage, serviceContext);
	}

}