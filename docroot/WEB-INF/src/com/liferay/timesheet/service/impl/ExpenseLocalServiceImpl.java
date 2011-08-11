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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.timesheet.InvalidDescriptionException;
import com.liferay.timesheet.InvalidMoneyFormatException;
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.service.base.ExpenseLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Antonio Junior
 */
public class ExpenseLocalServiceImpl extends ExpenseLocalServiceBaseImpl {

	public Expense addExpense(
			long projectId, String description, int purchasedDateMonth,
			int purchasedDateDay, int purchasedDateYear, int type, double value,
			long fileEntryId)
		throws PortalException, SystemException {

		Date date = PortalUtil.getDate(
			purchasedDateMonth, purchasedDateDay, purchasedDateYear);

		validate(description, value);

		long expenseId = counterLocalService.increment();

		Expense expense =  expensePersistence.create(expenseId);

		expense.setProjectId(projectId);
		expense.setDescription(description);
		expense.setPurchasedDate(date);
		expense.setType(type);
		expense.setValue(value);
		expense.setFileEntryId(fileEntryId);

		expensePersistence.update(expense, false);

		return expense;
	}

	public List<Expense> getExpenseByProjectId(long projectId)
		throws SystemException {

		return expensePersistence.findByProjectId(projectId);
	}

	public Expense updateExpense(
			long expenseId, long projectId, String description, 
			int purchasedDateMonth, int purchasedDateDay, int purchasedDateYear,
			int type, double value, long fileEntryId)
		throws PortalException, SystemException {

		Expense expense = expensePersistence.findByPrimaryKey(expenseId);

		validate(description, value);

		Date date = PortalUtil.getDate(
			purchasedDateMonth, purchasedDateDay, purchasedDateYear);

		expense.setProjectId(projectId);
		expense.setDescription(description);
		expense.setPurchasedDate(date);
		expense.setType(type);
		expense.setValue(value);
		expense.setFileEntryId(fileEntryId);

		expensePersistence.update(expense, false);

		return expense;
	}

	@Override
	public Expense getExpense(long expenseId)
		throws PortalException, SystemException {

		Expense expense = super.getExpense(expenseId);
		if ( expense.getFileEntryId() > 0) {
			
			try {
				DLFileEntry fileEntry = 
					DLFileEntryLocalServiceUtil.getFileEntry(
						expense.getFileEntryId());
				
				StringBuilder sb = new StringBuilder();

				sb.append("documents");
				sb.append(StringPool.FORWARD_SLASH);
				sb.append(fileEntry.getGroupId());
				sb.append(StringPool.FORWARD_SLASH);
				sb.append(fileEntry.getUuid());
				
				expense.setFilePath(sb.toString());
				expense.setFileName(fileEntry.getTitle());				
			} 
			catch (Exception e) {
				expense.setFileEntryId(0);
			}			
		}

		return expense;
	}

	protected void validate(String description, double value)
		throws PortalException {

		if (Validator.isNull(description)) {
			throw new InvalidDescriptionException();
		}

		if (Validator.isNull(String.valueOf(value)) || (value == 0)) {
			throw new InvalidMoneyFormatException();
		}
	}

}