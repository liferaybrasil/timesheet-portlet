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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.InvalidCurrencyFormatException;
import com.liferay.timesheet.InvalidDatesException;
import com.liferay.timesheet.InvalidDescriptionException;
import com.liferay.timesheet.InvalidNameException;
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.ExpenseLocalServiceUtil;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.base.ProjectLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Antonio Junior
 */
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {

	public Project addProject(
			long userId, String description, int endDateMonth, int endDateDay,
			int endDateYear, int startDateMonth, int startDateDay,
			int startDateYear, String name, double wage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear);
		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear);

		validate(name, wage, description, startDate, endDate);

		long projectId = counterLocalService.increment();

		Project project = projectPersistence.create(projectId);

		project.setCompanyId(serviceContext.getCompanyId());
		project.setGroupId(serviceContext.getScopeGroupId());
		project.setUserId(user.getUserId());
		project.setName(name);
		project.setWage(wage);
		project.setDescription(description);
		project.setStartDate(startDate);
		project.setEndDate(endDate);

		projectPersistence.update(project, false);

		// Resources

		if (serviceContext.getAddGroupPermissions() ||
			serviceContext.getAddGuestPermissions()) {

			addProjectResources(
				project, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getAddGroupPermissions(),
				serviceContext.getAddGuestPermissions());
		}
		else {
			addProjectResources(
				project, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return project;
	}

	public void addProjectResources(
			Project project, long companyId, long groupId, long userId,
			boolean addGroupPermissions, boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			companyId, groupId, userId, Project.class.getName(),
			project.getProjectId(), false, addGroupPermissions,
			addGuestPermissions);
	}

	public void addProjectResources(
			Project project, long companyId, long groupId, long userId,
			String[] groupPermissions, String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			companyId, groupId, userId, Project.class.getName(),
			project.getProjectId(), groupPermissions, guestPermissions);
	}

	public void deleteProject(long companyId, long projectId)
		throws PortalException, SystemException {

		List<Task> tasks = TaskLocalServiceUtil.getTaskByProjectId(projectId);
		List<Expense> expenses =
			ExpenseLocalServiceUtil.getExpenseByProjectId(projectId);

		for (Task task : tasks) {
			TaskLocalServiceUtil.deleteTask(task.getTaskId());
		}

		for (Expense expense : expenses) {
			ExpenseLocalServiceUtil.deleteExpense(expense.getExpenseId());
		}

		resourceLocalService.deleteResource(
			companyId, Project.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, projectId);

		super.deleteProject(projectId);
	}

	public List<Project> search(
			long companyId, long groupId, String keywords, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return projectFinder.findByKeywords(
			companyId, groupId, keywords, start, end, orderByComparator);
	}

	public List<Project> search(
			long companyId, long groupId, String name, String description,
			boolean andOperator, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return projectFinder.findByN_D(
			companyId, groupId, name, description, andOperator, start, end,
			orderByComparator);
	}

	public int searchCount(
			long companyId, long groupId, String name, String description,
			boolean andOperator)
		throws SystemException {

		return projectFinder.countByN_D(
			companyId, groupId, name, description, andOperator);
	}

	public int searchCount(long companyId, long groupId, String keywords)
		throws SystemException {

		return projectFinder.countByKeywords(companyId, groupId, keywords);
	}

	public Project updateProject(
			long projectId, long userId, String description, int endDateMonth,
			int endDateDay, int endDateYear, int startDateMonth,
			int startDateDay, int startDateYear, String name, double wage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Project project = projectPersistence.findByPrimaryKey(projectId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear);
		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear);

		validate(name, wage, description, startDate, endDate);

		project.setCompanyId(serviceContext.getCompanyId());
		project.setGroupId(serviceContext.getScopeGroupId());
		project.setName(name);
		project.setWage(wage);
		project.setDescription(description);
		project.setStartDate(startDate);
		project.setEndDate(endDate);

		projectPersistence.update(project, false);

		// Resources

		if (serviceContext.getAddGroupPermissions() ||
			serviceContext.getAddGuestPermissions()) {

			addProjectResources(
				project, serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getAddGroupPermissions(),
				serviceContext.getAddGuestPermissions());
		}
		else {
			addProjectResources(
				project,  serviceContext.getCompanyId(),
				serviceContext.getScopeGroupId(), project.getUserId(),
				serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		return project;
	}

	protected void validate(
			String name, double wage, String description, Date startDate,
			Date endDate)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new InvalidNameException();
		}

		if (Validator.isNull(description)) {
			throw new InvalidDescriptionException();
		}

		if (Validator.isNull(String.valueOf(wage)) || (wage == 0)) {
			throw new InvalidCurrencyFormatException();
		}

		if (startDate.after(endDate)) {
			throw new InvalidDatesException();
		}
	}

}