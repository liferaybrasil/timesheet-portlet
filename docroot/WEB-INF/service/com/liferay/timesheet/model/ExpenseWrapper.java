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

/**
 * <p>
 * This class is a wrapper for {@link Expense}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Expense
 * @generated
 */
public class ExpenseWrapper implements Expense {
	public ExpenseWrapper(Expense expense) {
		_expense = expense;
	}

	public Class<?> getModelClass() {
		return Expense.class;
	}

	public String getModelClassName() {
		return Expense.class.getName();
	}

	/**
	* Returns the primary key of this expense.
	*
	* @return the primary key of this expense
	*/
	public long getPrimaryKey() {
		return _expense.getPrimaryKey();
	}

	/**
	* Sets the primary key of this expense.
	*
	* @param primaryKey the primary key of this expense
	*/
	public void setPrimaryKey(long primaryKey) {
		_expense.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the expense ID of this expense.
	*
	* @return the expense ID of this expense
	*/
	public long getExpenseId() {
		return _expense.getExpenseId();
	}

	/**
	* Sets the expense ID of this expense.
	*
	* @param expenseId the expense ID of this expense
	*/
	public void setExpenseId(long expenseId) {
		_expense.setExpenseId(expenseId);
	}

	/**
	* Returns the billed date of this expense.
	*
	* @return the billed date of this expense
	*/
	public java.util.Date getBilledDate() {
		return _expense.getBilledDate();
	}

	/**
	* Sets the billed date of this expense.
	*
	* @param billedDate the billed date of this expense
	*/
	public void setBilledDate(java.util.Date billedDate) {
		_expense.setBilledDate(billedDate);
	}

	/**
	* Returns the description of this expense.
	*
	* @return the description of this expense
	*/
	public java.lang.String getDescription() {
		return _expense.getDescription();
	}

	/**
	* Sets the description of this expense.
	*
	* @param description the description of this expense
	*/
	public void setDescription(java.lang.String description) {
		_expense.setDescription(description);
	}

	/**
	* Returns the project ID of this expense.
	*
	* @return the project ID of this expense
	*/
	public long getProjectId() {
		return _expense.getProjectId();
	}

	/**
	* Sets the project ID of this expense.
	*
	* @param projectId the project ID of this expense
	*/
	public void setProjectId(long projectId) {
		_expense.setProjectId(projectId);
	}

	/**
	* Returns the type of this expense.
	*
	* @return the type of this expense
	*/
	public int getType() {
		return _expense.getType();
	}

	/**
	* Sets the type of this expense.
	*
	* @param type the type of this expense
	*/
	public void setType(int type) {
		_expense.setType(type);
	}

	/**
	* Returns the value of this expense.
	*
	* @return the value of this expense
	*/
	public double getValue() {
		return _expense.getValue();
	}

	/**
	* Sets the value of this expense.
	*
	* @param value the value of this expense
	*/
	public void setValue(double value) {
		_expense.setValue(value);
	}

	/**
	* Returns the dl field entry ID of this expense.
	*
	* @return the dl field entry ID of this expense
	*/
	public long getDlFieldEntryId() {
		return _expense.getDlFieldEntryId();
	}

	/**
	* Sets the dl field entry ID of this expense.
	*
	* @param dlFieldEntryId the dl field entry ID of this expense
	*/
	public void setDlFieldEntryId(long dlFieldEntryId) {
		_expense.setDlFieldEntryId(dlFieldEntryId);
	}

	public boolean isNew() {
		return _expense.isNew();
	}

	public void setNew(boolean n) {
		_expense.setNew(n);
	}

	public boolean isCachedModel() {
		return _expense.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_expense.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _expense.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_expense.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _expense.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_expense.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _expense.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_expense.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExpenseWrapper((Expense)_expense.clone());
	}

	public int compareTo(com.liferay.timesheet.model.Expense expense) {
		return _expense.compareTo(expense);
	}

	@Override
	public int hashCode() {
		return _expense.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Expense> toCacheModel() {
		return _expense.toCacheModel();
	}

	public com.liferay.timesheet.model.Expense toEscapedModel() {
		return new ExpenseWrapper(_expense.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _expense.toString();
	}

	public java.lang.String toXmlString() {
		return _expense.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_expense.persist();
	}

	public java.lang.String getTypeDescription() {
		return _expense.getTypeDescription();
	}

	public java.lang.String getFilePath() {
		return _expense.getFilePath();
	}

	public void setFilePath(java.lang.String filePath) {
		_expense.setFilePath(filePath);
	}

	public Expense getWrappedExpense() {
		return _expense;
	}

	public void resetOriginalValues() {
		_expense.resetOriginalValues();
	}

	private Expense _expense;
}