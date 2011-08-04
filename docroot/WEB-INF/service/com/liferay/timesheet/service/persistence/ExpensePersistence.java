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

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.timesheet.model.Expense;

/**
 * The persistence interface for the expense service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpensePersistenceImpl
 * @see ExpenseUtil
 * @generated
 */
public interface ExpensePersistence extends BasePersistence<Expense> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExpenseUtil} to access the expense persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the expense in the entity cache if it is enabled.
	*
	* @param expense the expense
	*/
	public void cacheResult(com.liferay.timesheet.model.Expense expense);

	/**
	* Caches the expenses in the entity cache if it is enabled.
	*
	* @param expenses the expenses
	*/
	public void cacheResult(
		java.util.List<com.liferay.timesheet.model.Expense> expenses);

	/**
	* Creates a new expense with the primary key. Does not add the expense to the database.
	*
	* @param expenseId the primary key for the new expense
	* @return the new expense
	*/
	public com.liferay.timesheet.model.Expense create(long expenseId);

	/**
	* Removes the expense with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param expenseId the primary key of the expense
	* @return the expense that was removed
	* @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense remove(long expenseId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException;

	public com.liferay.timesheet.model.Expense updateImpl(
		com.liferay.timesheet.model.Expense expense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the expense with the primary key or throws a {@link com.liferay.timesheet.NoSuchExpenseException} if it could not be found.
	*
	* @param expenseId the primary key of the expense
	* @return the expense
	* @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense findByPrimaryKey(long expenseId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException;

	/**
	* Returns the expense with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param expenseId the primary key of the expense
	* @return the expense, or <code>null</code> if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense fetchByPrimaryKey(long expenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the expenses where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Expense> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the expenses where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of expenses
	* @param end the upper bound of the range of expenses (not inclusive)
	* @return the range of matching expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Expense> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the expenses where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of expenses
	* @param end the upper bound of the range of expenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Expense> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first expense in the ordered set where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching expense
	* @throws com.liferay.timesheet.NoSuchExpenseException if a matching expense could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException;

	/**
	* Returns the last expense in the ordered set where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching expense
	* @throws com.liferay.timesheet.NoSuchExpenseException if a matching expense could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException;

	/**
	* Returns the expenses before and after the current expense in the ordered set where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param expenseId the primary key of the current expense
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next expense
	* @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Expense[] findByProjectId_PrevAndNext(
		long expenseId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException;

	/**
	* Returns all the expenses.
	*
	* @return the expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Expense> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.timesheet.model.Expense> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the expenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of expenses
	* @param end the upper bound of the range of expenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of expenses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.timesheet.model.Expense> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the expenses where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the expenses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of expenses where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching expenses
	* @throws SystemException if a system exception occurred
	*/
	public int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of expenses.
	*
	* @return the number of expenses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public Expense remove(Expense expense) throws SystemException;
}