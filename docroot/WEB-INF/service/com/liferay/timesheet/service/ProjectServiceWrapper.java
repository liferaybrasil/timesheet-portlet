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

package com.liferay.timesheet.service;

/**
 * <p>
 * This class is a wrapper for {@link ProjectService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProjectService
 * @generated
 */
public class ProjectServiceWrapper implements ProjectService {
	public ProjectServiceWrapper(ProjectService projectService) {
		_projectService = projectService;
	}

	public com.liferay.timesheet.model.Project addProject(long userId,
		java.lang.String description, int endDateMonth, int endDateDay,
		int endDateYear, int startDateMonth, int startDateDay,
		int startDateYear, java.lang.String name, double wage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.addProject(userId, description, endDateMonth,
			endDateDay, endDateYear, startDateMonth, startDateDay,
			startDateYear, name, wage, serviceContext);
	}

	public void deleteProject(long companyId, long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_projectService.deleteProject(companyId, projectId);
	}

	public com.liferay.timesheet.model.Project getProject(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.getProject(projectId);
	}

	public com.liferay.timesheet.model.Project updateProject(long projectId,
		long userId, java.lang.String description, int endDateMonth,
		int endDateDay, int endDateYear, int startDateMonth, int startDateDay,
		int startDateYear, java.lang.String name, double wage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _projectService.updateProject(projectId, userId, description,
			endDateMonth, endDateDay, endDateYear, startDateMonth,
			startDateDay, startDateYear, name, wage, serviceContext);
	}

	public ProjectService getWrappedProjectService() {
		return _projectService;
	}

	public void setWrappedProjectService(ProjectService projectService) {
		_projectService = projectService;
	}

	private ProjectService _projectService;
}