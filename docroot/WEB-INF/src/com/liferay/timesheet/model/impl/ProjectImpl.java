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
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.ExpenseLocalServiceUtil;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

import java.util.List;

/**
 * @author Antonio Junior
 */
public class ProjectImpl extends ProjectBaseImpl {

	public ProjectImpl() {

	}

	public double getTotalProjectCost() throws SystemException {
		return getTotalTaskCost() + getTotalExpenseCost();
	}

	public double getTotalTaskCost() throws SystemException {
		double total = 0;

		List<Task> tasks = TaskLocalServiceUtil.getTaskByProjectId(
				getProjectId());

		for (Task task : tasks) {
			total += task.getTotalHours() * this.getWage();
		}

		return total;
	}

	public double getTotalExpenseCost() throws SystemException {
		long total = 0;

		List<Expense> expenses = ExpenseLocalServiceUtil.getExpenseByProjectId(
				getProjectId());

		for (Expense expense : expenses) {
			total += expense.getValue();
		}

		return total;
	}

}