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
 * This class is a wrapper for {@link TaskService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TaskService
 * @generated
 */
public class TaskServiceWrapper implements TaskService {
	public TaskServiceWrapper(TaskService taskService) {
		_taskService = taskService;
	}

	public com.liferay.timesheet.model.Task addTask(long projectId,
		java.lang.String name, int type, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskService.addTask(projectId, name, type, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			serviceContext);
	}

	public void deleteTask(long companyId, long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_taskService.deleteTask(companyId, taskId);
	}

	public com.liferay.timesheet.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return _taskService.getTask(taskId);
	}

	public com.liferay.timesheet.model.Task updateTask(long taskId,
		long projectId, java.lang.String name, int type, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskService.updateTask(taskId, projectId, name, type,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, serviceContext);
	}

	public TaskService getWrappedTaskService() {
		return _taskService;
	}

	public void setWrappedTaskService(TaskService taskService) {
		_taskService = taskService;
	}

	private TaskService _taskService;
}