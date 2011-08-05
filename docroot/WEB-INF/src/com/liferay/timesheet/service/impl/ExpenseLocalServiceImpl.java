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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.InvalidDescriptionException;
import com.liferay.timesheet.InvalidMoneyFormatException;
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.service.base.ExpenseLocalServiceBaseImpl;

import java.io.File;

import java.util.Date;
import java.util.List;

/**
 * @author Antonio Junior
 */
public class ExpenseLocalServiceImpl extends ExpenseLocalServiceBaseImpl {

	// TODO CHECAR ORDEM DOS PARAMETROS, GROUPID VEM PRIMEIRO
	// TODO mudar de billlllllllled pra algo
	public Expense addExpense(
			long projectId, String description, int billledDateMonth,
			int billledDateDay, int billledDateYear, int type, double value,
			File file, long groupId)
		throws PortalException, SystemException {

		Date date = PortalUtil.getDate(
			billledDateMonth, billledDateDay, billledDateYear);

		validate(description, value);

		long expenseId = counterLocalService.increment();

		Expense expense =  expensePersistence.create(expenseId);

		// RENOMEAR PRA FILENETRYID SO
		long dlFieldEntryId = 0;

		if (file != null) {
			getDlFileId(file, groupId);
		}

		expense.setProjectId(projectId);
		expense.setDescription(description);
		expense.setBilledDate(date);
		expense.setType(type);
		expense.setValue(value);
		expense.setDlFieldEntryId(dlFieldEntryId);

		expensePersistence.update(expense, false);

		return expense;
	}

	public List<Expense> getExpenseByProjectId(long projectId)
		throws SystemException {

		return expensePersistence.findByProjectId(projectId);
	}

	// TODO CHECAR ORDEM DE PARAMSS
	public Expense updateExpense(
			long expenseId, long projectId, String description,
			int billledDateDay, int billledDateMonth, int billledDateYear,
			int type, double value, File file, long groupId)
		throws PortalException, SystemException {

		Expense expense = expensePersistence.findByPrimaryKey(expenseId);

		validate(description, value);

		Date date = PortalUtil.getDate(
			billledDateMonth, billledDateDay, billledDateYear);

		long dlFieldEntryId = 0;

		if (file != null) {
			getDlFileId(file, groupId);
		}

		expense.setProjectId(projectId);
		expense.setDescription(description);
		expense.setBilledDate(date);
		expense.setType(type);
		expense.setValue(value);
		expense.setDlFieldEntryId(dlFieldEntryId);

		expensePersistence.update(expense, false);

		return expense;
	}

	private long getDlFileId(File file, long groupId) {
		long dlFielId = 0;

		/*		try {

			DLFolder folder = DLFolderLocalServiceUtil.getFolder(
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

			String fileName = file.getName();

			if (Validator.isNotNull(fileName) && !file.exists()) {
				file.createNewFile();
				DLFileEntry fileEntry = null;
				//DLFileEntry fileEntry = DLFileEntryServiceUtil. .addFileEntry(
					//	groupId, 0, folder.getFolderId(), fileName, "mimeType",
					//	"title", "description", null, file,
					//	serviceContext);

				dlFielId = fileEntry.getPrimaryKey();
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		 */
		return dlFielId;
	}

	@Override
	public Expense getExpense(long expenseId)
		throws PortalException, SystemException {

		Expense expense = super.getExpense(expenseId);
		//if ( expense.getDlFieldId() > 0) {
		//DLFileEntry fileEntry = DLFileEntryLocalServiceUtil.getFileEntry(
		//expense.getDlFieldId());
		//	String filePath = "http://localhost:8080/documents/" + 
		//fileEntry.getGroupId() + "/" + fileEntry.getUuid();
		//expense.setFilePath(filePath);
		//}
		return expense;
	}

	protected void validate(
			String expenseDescription, double expenseValue)
		throws PortalException {

		if (Validator.isNull(expenseDescription)) {
			throw new InvalidDescriptionException();
		}

		String value = String.valueOf(expenseValue);

		value = StringUtil.remove(value, StringPool.PERIOD);
	
		if (Validator.isNull(expenseValue) || !Validator.isDigit(value) || 
			(expenseValue == 0)) {

			throw new InvalidMoneyFormatException();
		}
	}

}