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
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.InvalidDatesException;
import com.liferay.timesheet.InvalidDescriptionException;
import com.liferay.timesheet.InvalidMoneyFormatException;
import com.liferay.timesheet.InvalidNameException;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.base.ProjectLocalServiceBaseImpl;

import java.util.Date;

/**
 * @author Antonio Junior
 */
public class ProjectLocalServiceImpl extends ProjectLocalServiceBaseImpl {

	public Project addProject(
			long userId, String name, double wage, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int endDateMonth, int endDateDay, int endDateYear)
		throws PortalException, SystemException {

		Project project = null;

		Date startDate = PortalUtil.getDate(
				startDateMonth, startDateDay, startDateYear);
		Date endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear);

		validate(name, wage, description, startDate, endDate);

		User user = userPersistence.findByPrimaryKey(userId);

		long projectId = counterLocalService.increment();
		project =  projectPersistence.create(projectId);

		project.setUserId(user.getUserId());
		project.setName(name);
		project.setWage(wage);
		project.setDescription(description);
		project.setStartDate(startDate);
		project.setEndDate(endDate);

		projectPersistence.update(project, false);

		return project;
	}

	public Project updateProject(
			long projectId, long userId, String name, double wage,
			String description, int startDateMonth, int startDateDay,
			int startDateYear, int endDateMonth, int endDateDay,
			int endDateYear)
		throws PortalException, SystemException {

		Project project = projectPersistence.findByPrimaryKey(projectId);

		Date startDate = PortalUtil.getDate(
				startDateMonth, startDateDay, startDateYear);
		Date endDate = PortalUtil.getDate(
				endDateMonth, endDateDay, endDateYear);

		validate(name, wage, description, startDate, endDate);

		User user = userPersistence.findByPrimaryKey(userId);

		project.setUserId(user.getUserId());
		project.setName(name);
		project.setWage(wage);
		project.setDescription(description);
		project.setStartDate(startDate);
		project.setEndDate(endDate);

		projectPersistence.update(project, false);

		return project;
	}

	protected void validate(
			String projectName, double wage, String projectDescription,
			Date startDate, Date endDate)
		throws PortalException {

		if (Validator.isNull(projectName)) {
			throw new InvalidNameException();
		}

		if (Validator.isNull(projectDescription)) {
			throw new InvalidDescriptionException();
		}

		String wageString = String.valueOf(wage);
		if (Validator.isNull(wage) ||
			!Validator.isDigit(wageString.replace(".", "")) ||
			wage == 0) {
			throw new InvalidMoneyFormatException();
		}

		if (startDate.after(endDate)) {
			throw new InvalidDatesException();
		}
	}

}