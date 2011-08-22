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

package com.liferay.timesheet.service;

/**
 * <p>
 * This class is a wrapper for {@link ExpenseLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExpenseLocalService
 * @generated
 */
public class ExpenseLocalServiceWrapper implements ExpenseLocalService {
	public ExpenseLocalServiceWrapper(ExpenseLocalService expenseLocalService) {
		_expenseLocalService = expenseLocalService;
	}

	/**
	* Adds the expense to the database. Also notifies the appropriate model listeners.
	*
	* @param expense the expense
	* @return the expense that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense addExpense(
		com.liferay.timesheet.model.Expense expense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.addExpense(expense);
	}

	/**
	* Creates a new expense with the primary key. Does not add the expense to the database.
	*
	* @param expenseId the primary key for the new expense
	* @return the new expense
	*/
	public com.liferay.timesheet.model.Expense createExpense(long expenseId) {
		return _expenseLocalService.createExpense(expenseId);
	}

	/**
	* Deletes the expense with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param expenseId the primary key of the expense
	* @throws PortalException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteExpense(long expenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expenseLocalService.deleteExpense(expenseId);
	}

	/**
	* Deletes the expense from the database. Also notifies the appropriate model listeners.
	*
	* @param expense the expense
	* @throws SystemException if a system exception occurred
	*/
	public void deleteExpense(com.liferay.timesheet.model.Expense expense)
		throws com.liferay.portal.kernel.exception.SystemException {
		_expenseLocalService.deleteExpense(expense);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the expense with the primary key.
	*
	* @param expenseId the primary key of the expense
	* @return the expense
	* @throws PortalException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense getExpense(long expenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.getExpense(expenseId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the expenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of expenses
	* @param end the upper bound of the range of expenses (not inclusive)
	* @return the range of expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Expense> getExpenses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.getExpenses(start, end);
	}

	/**
	* Returns the number of expenses.
	*
	* @return the number of expenses
	* @throws SystemException if a system exception occurred
	*/
	public int getExpensesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.getExpensesCount();
	}

	/**
	* Updates the expense in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param expense the expense
	* @return the expense that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense updateExpense(
		com.liferay.timesheet.model.Expense expense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.updateExpense(expense);
	}

	/**
	* Updates the expense in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param expense the expense
	* @param merge whether to merge the expense with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the expense that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense updateExpense(
		com.liferay.timesheet.model.Expense expense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.updateExpense(expense, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _expenseLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_expenseLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.timesheet.model.Expense addExpense(long projectId,
		java.lang.String description, int purchasedDateMonth,
		int purchasedDateDay, int purchasedDateYear, int type, double value,
		long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.addExpense(projectId, description,
			purchasedDateMonth, purchasedDateDay, purchasedDateYear, type,
			value, fileEntryId, serviceContext);
	}

	public void addExpenseResources(
		com.liferay.timesheet.model.Expense expense, long companyId,
		long groupId, long userId, boolean addGroupPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expenseLocalService.addExpenseResources(expense, companyId, groupId,
			userId, addGroupPermissions, addGuestPermissions);
	}

	public void addExpenseResources(
		com.liferay.timesheet.model.Expense expense, long companyId,
		long groupId, long userId, java.lang.String[] groupPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expenseLocalService.addExpenseResources(expense, companyId, groupId,
			userId, groupPermissions, guestPermissions);
	}

	public void deleteExpense(long companyId, long expenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_expenseLocalService.deleteExpense(companyId, expenseId);
	}

	public java.util.List<com.liferay.timesheet.model.Expense> getExpenseByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.getExpenseByProjectId(projectId);
	}

	public double getTotal(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.getTotal(projectId);
	}

	public com.liferay.timesheet.model.Expense updateExpense(long expenseId,
		long projectId, java.lang.String description, int purchasedDateMonth,
		int purchasedDateDay, int purchasedDateYear, int type, double value,
		long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _expenseLocalService.updateExpense(expenseId, projectId,
			description, purchasedDateMonth, purchasedDateDay,
			purchasedDateYear, type, value, fileEntryId, serviceContext);
	}

	public ExpenseLocalService getWrappedExpenseLocalService() {
		return _expenseLocalService;
	}

	public void setWrappedExpenseLocalService(
		ExpenseLocalService expenseLocalService) {
		_expenseLocalService = expenseLocalService;
	}

	private ExpenseLocalService _expenseLocalService;
}