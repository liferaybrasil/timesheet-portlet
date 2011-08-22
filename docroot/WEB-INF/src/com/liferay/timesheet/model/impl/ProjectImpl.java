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

package com.liferay.timesheet.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.service.ExpenseLocalServiceUtil;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

/**
 * @author Antonio Junior
 */
public class ProjectImpl extends ProjectBaseImpl {

	public double getTotalExpenseCost() throws SystemException {

		return ExpenseLocalServiceUtil.getTotal(getProjectId());
	}

	public double getTotalProjectCost() throws SystemException {
		return getTotalTaskCost() + getTotalExpenseCost();
	}

	public double getTotalTaskCost() throws SystemException {

		double totalHours = TaskLocalServiceUtil.getSumHoursByProject(
			getProjectId());

		return totalHours * getWage();
	}

}