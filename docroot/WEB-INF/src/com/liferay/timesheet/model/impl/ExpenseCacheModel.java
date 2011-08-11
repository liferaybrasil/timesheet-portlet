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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.timesheet.model.Expense;

import java.util.Date;

/**
 * The cache model class for representing Expense in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Expense
 * @generated
 */
public class ExpenseCacheModel implements CacheModel<Expense> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{expenseId=");
		sb.append(expenseId);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", purchasedDate=");
		sb.append(purchasedDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", value=");
		sb.append(value);
		sb.append(", fileEntryId=");
		sb.append(fileEntryId);
		sb.append("}");

		return sb.toString();
	}

	public Expense toEntityModel() {
		ExpenseImpl expenseImpl = new ExpenseImpl();

		expenseImpl.setExpenseId(expenseId);
		expenseImpl.setProjectId(projectId);

		if (description == null) {
			expenseImpl.setDescription(StringPool.BLANK);
		}
		else {
			expenseImpl.setDescription(description);
		}

		if (purchasedDate == Long.MIN_VALUE) {
			expenseImpl.setPurchasedDate(null);
		}
		else {
			expenseImpl.setPurchasedDate(new Date(purchasedDate));
		}

		expenseImpl.setType(type);
		expenseImpl.setValue(value);
		expenseImpl.setFileEntryId(fileEntryId);

		expenseImpl.resetOriginalValues();

		return expenseImpl;
	}

	public long expenseId;
	public long projectId;
	public String description;
	public long purchasedDate;
	public int type;
	public double value;
	public long fileEntryId;
}