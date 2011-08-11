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

package com.liferay.timesheet.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.timesheet.service.ExpenseLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ExpenseClp extends BaseModelImpl<Expense> implements Expense {
	public ExpenseClp() {
	}

	public Class<?> getModelClass() {
		return Expense.class;
	}

	public String getModelClassName() {
		return Expense.class.getName();
	}

	public long getPrimaryKey() {
		return _expenseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setExpenseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_expenseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getExpenseId() {
		return _expenseId;
	}

	public void setExpenseId(long expenseId) {
		_expenseId = expenseId;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getPurchasedDate() {
		return _purchasedDate;
	}

	public void setPurchasedDate(Date purchasedDate) {
		_purchasedDate = purchasedDate;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public double getValue() {
		return _value;
	}

	public void setValue(double value) {
		_value = value;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public java.lang.String getTypeDescription() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getFilePath() {
		throw new UnsupportedOperationException();
	}

	public void setFilePath(java.lang.String filePath) {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getFileName() {
		throw new UnsupportedOperationException();
	}

	public void setFileName(java.lang.String fileName) {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		ExpenseLocalServiceUtil.updateExpense(this);
	}

	@Override
	public Expense toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (Expense)Proxy.newProxyInstance(Expense.class.getClassLoader(),
				new Class[] { Expense.class }, new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		ExpenseClp clone = new ExpenseClp();

		clone.setExpenseId(getExpenseId());
		clone.setProjectId(getProjectId());
		clone.setDescription(getDescription());
		clone.setPurchasedDate(getPurchasedDate());
		clone.setType(getType());
		clone.setValue(getValue());
		clone.setFileEntryId(getFileEntryId());

		return clone;
	}

	public int compareTo(Expense expense) {
		int value = 0;

		value = DateUtil.compareTo(getPurchasedDate(),
				expense.getPurchasedDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		ExpenseClp expense = null;

		try {
			expense = (ExpenseClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = expense.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{expenseId=");
		sb.append(getExpenseId());
		sb.append(", projectId=");
		sb.append(getProjectId());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", purchasedDate=");
		sb.append(getPurchasedDate());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", value=");
		sb.append(getValue());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.timesheet.model.Expense");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>expenseId</column-name><column-value><![CDATA[");
		sb.append(getExpenseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>purchasedDate</column-name><column-value><![CDATA[");
		sb.append(getPurchasedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>value</column-name><column-value><![CDATA[");
		sb.append(getValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _expenseId;
	private long _projectId;
	private String _description;
	private Date _purchasedDate;
	private int _type;
	private double _value;
	private long _fileEntryId;
}