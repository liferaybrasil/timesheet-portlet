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
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.service.base.ExpenseServiceBaseImpl;
import com.liferay.timesheet.service.permission.TimesheetExpensePermission;
import com.liferay.timesheet.service.permission.TimesheetPermission;
import com.liferay.timesheet.util.ActionKeys;

/**
 * @author Antonio Junior
 */
public class ExpenseServiceImpl extends ExpenseServiceBaseImpl {

	public Expense addExpense(
			long projectId, String description, int purchasedDateMonth,
			int purchasedDateDay, int purchasedDateYear, int type, double value,
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		TimesheetPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_EXPENSE);

		return expenseLocalService.addExpense(
			projectId, description, purchasedDateMonth, purchasedDateDay,
			purchasedDateYear, type, value, fileEntryId, serviceContext);
	}

	public void deleteExpense(long companyId, long expenseId)
		throws SystemException, PortalException {

		TimesheetExpensePermission.check(
			getPermissionChecker(), expenseId, ActionKeys.DELETE);

		expenseLocalService.deleteExpense(expenseId);
	}

	public Expense getExpense(long expenseId)
		throws PortalException, PrincipalException, SystemException {

		TimesheetExpensePermission.check(
			getPermissionChecker(), expenseId, ActionKeys.VIEW);

		return expenseLocalService.getExpense(expenseId);
	}

	public Expense updateExpense(
			long expenseId, long projectId, String description,
			int purchasedDateMonth, int purchasedDateDay,
			int purchasedDateYear, 	int type, double value, long fileEntryId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		TimesheetExpensePermission.check(
			getPermissionChecker(), expenseId, ActionKeys.UPDATE);

		return expenseLocalService.updateExpense(
			expenseId, projectId, description, purchasedDateMonth,
			purchasedDateDay, purchasedDateYear, type,
			value, fileEntryId, serviceContext);
	}

}