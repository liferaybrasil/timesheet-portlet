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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.InvalidDatesException;
import com.liferay.timesheet.InvalidNameException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Antonio Junior
 */
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public Task addTask(
			long projectId, String name, int type, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute)
		throws PortalException, SystemException {

		Task task = null;

		Date startDate = PortalUtil.getDate(
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, null);
		Date endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, null);

		validate(name, startDate, endDate);

		long taskId = counterLocalService.increment();
		task = taskPersistence.create(taskId);

		task.setProjectId(projectId);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setType(type);

		taskPersistence.update(task, false);

		return task;
	}

	public List<Task> getTaskByProjectId(long projectId)
		throws SystemException {
		return taskPersistence.findByProjectId(projectId);
	}

	public Task updateTask(
			long taskId, long projectId, String name, int type,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour,
			int endDateMinute)
		throws PortalException, SystemException {

		Task task = taskPersistence.findByPrimaryKey(taskId);

		Date startDate = PortalUtil.getDate(
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, null);
		Date endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, null);

		validate(name, startDate, endDate);

		task.setProjectId(projectId);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setType(type);

		taskPersistence.update(task, false);

		return task;
	}

	protected void validate(String taskName, Date startDate, Date endDate)
		throws PortalException {

		if (Validator.isNull(taskName)) {
			throw new InvalidNameException();
		}

		if (startDate.after(endDate)) {
			throw new InvalidDatesException();
		}
	}

}