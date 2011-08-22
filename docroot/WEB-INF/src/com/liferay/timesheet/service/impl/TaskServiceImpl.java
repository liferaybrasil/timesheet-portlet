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
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.base.TaskServiceBaseImpl;
import com.liferay.timesheet.service.permission.TimesheetPermission;
import com.liferay.timesheet.service.permission.TimesheetTaskPermission;
import com.liferay.timesheet.util.ActionKeys;

/**
 * @author Antonio Junior
 */
public class TaskServiceImpl extends TaskServiceBaseImpl {

	public Task addTask(
			long projectId, String name, int type, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TimesheetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_TASK);

		return taskLocalService.addTask(
			projectId, name, type, startDateMonth, startDateDay, startDateYear,
			startDateHour, startDateMinute, endDateMonth, endDateDay,
			endDateYear, endDateHour, endDateMinute, serviceContext);
	}

	public void deleteTask(long companyId, long taskId)
		throws PortalException, SystemException {

		TimesheetTaskPermission.check(
			getPermissionChecker(), taskId, ActionKeys.DELETE);

		taskLocalService.deleteTask(companyId, taskId);
	}

	public Task getTask(long taskId)
		throws PortalException, PrincipalException, SystemException {

		TimesheetTaskPermission.check(
			getPermissionChecker(), taskId, ActionKeys.VIEW);

		return taskLocalService.getTask(taskId);
	}

	public Task updateTask(
			long taskId, long projectId, String name, int type,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TimesheetTaskPermission.check(
			getPermissionChecker(), taskId, ActionKeys.UPDATE);

		return taskLocalService.updateTask(
			taskId, projectId, name, type, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute,
			serviceContext);
	}

}