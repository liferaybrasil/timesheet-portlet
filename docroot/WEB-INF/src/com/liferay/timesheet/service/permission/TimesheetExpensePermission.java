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
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ExpenseLocalServiceUtil;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;

/**
 * @author Antonio Junior
 */
public class TimesheetExpensePermission {

	public static void check(
			PermissionChecker permissionChecker, Expense expense,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, expense, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long expenseId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, expenseId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, Expense expense,
			String actionId)
		throws PortalException, SystemException {

		Project project = ProjectLocalServiceUtil.getProject(
			expense.getProjectId());

		if (permissionChecker.hasOwnerPermission(
				project.getCompanyId(), Expense.class.getName(),
				expense.getExpenseId(), project.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			project.getGroupId(), Expense.class.getName(),
			expense.getExpenseId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long expenseId,
			String actionId)
		throws PortalException, SystemException {

		Expense expense = ExpenseLocalServiceUtil.getExpense(expenseId);

		return contains(permissionChecker, expense, actionId);
	}

}