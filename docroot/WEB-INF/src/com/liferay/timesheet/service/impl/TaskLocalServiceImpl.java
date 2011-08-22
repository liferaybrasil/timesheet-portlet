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
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.InvalidDatesException;
import com.liferay.timesheet.InvalidNameException;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;
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
			int endDateYear, int endDateHour, int endDateMinute,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, new PortalException());

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			new PortalException());

		validate(name, startDate, endDate);

		long taskId = counterLocalService.increment();

		Project project = ProjectLocalServiceUtil.getProject(projectId);

		Task task = taskPersistence.create(taskId);

		task.setUserId(user.getUserId());
		task.setProjectId(projectId);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setType(type);

		taskPersistence.update(task, false);

		// Resources

		if (serviceContext.getAddGroupPermissions() ||
			serviceContext.getAddGuestPermissions()) {

			addTaskResources(
				task, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getAddGroupPermissions(),
				serviceContext.getAddGuestPermissions());
		}
		else {
			addTaskResources(
				task, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return task;
	}

	public void addTaskResources(
			Task task, long companyId, long groupId, long userId,
			boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			companyId, groupId, userId, Task.class.getName(), task.getTaskId(),
			false, addGroupPermissions, addGuestPermissions);
	}

	public void addTaskResources(
			Task task, long companyId, long groupId, long userId,
			String[] groupPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			companyId, groupId, userId, Task.class.getName(), task.getTaskId(),
			groupPermissions, guestPermissions);
	}

	public void deleteTask(long companyId, long taskId)
		throws PortalException, SystemException {

		resourceLocalService.deleteResource(
			companyId, Project.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, taskId);

		super.deleteTask(taskId);
	}

	public List<Task> getTaskByProjectId(long projectId)
		throws SystemException {

		return taskPersistence.findByProjectId(projectId);
	}

	public double getSumHoursByProject(long projectId) throws SystemException {

		return taskFinder.sumHoursByProject(projectId);
	}

	public Task updateTask(
			long taskId, long projectId, String name, int type,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Task task = taskPersistence.findByPrimaryKey(taskId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, new PortalException());

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour,
			endDateMinute, new PortalException());

		validate(name, startDate, endDate);

		Project project = ProjectLocalServiceUtil.getProject(projectId);

		task.setProjectId(projectId);
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setType(type);

		taskPersistence.update(task, false);

		// Resources

		if (serviceContext.getAddGroupPermissions() ||
			serviceContext.getAddGuestPermissions()) {

			addTaskResources(
				task, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getAddGroupPermissions(),
				serviceContext.getAddGuestPermissions());
		}
		else {
			addTaskResources(
				task, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

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