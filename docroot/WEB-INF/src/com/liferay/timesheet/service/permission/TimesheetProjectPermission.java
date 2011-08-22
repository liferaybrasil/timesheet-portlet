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

package com.liferay.timesheet.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;

/**
 * @author Antonio Junior
 */
public class TimesheetProjectPermission {

	public static void check(
			PermissionChecker permissionChecker, Project project,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, project, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long projectId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, projectId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Project project,
			String actionId) {

		if (permissionChecker.hasOwnerPermission(
				project.getCompanyId(), Project.class.getName(),
				project.getProjectId(), project.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			project.getGroupId(), Project.class.getName(),
			project.getProjectId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long projectId,
			String actionId)
		throws PortalException, SystemException {

		Project project = ProjectLocalServiceUtil.getProject(projectId);

		return contains(permissionChecker, project, actionId);
	}

}