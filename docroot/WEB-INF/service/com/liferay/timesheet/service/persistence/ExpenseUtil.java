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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.timesheet.model.Expense;

import java.util.List;

/**
 * The persistence utility for the expense service. This utility wraps {@link ExpensePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpensePersistence
 * @see ExpensePersistenceImpl
 * @generated
 */
public class ExpenseUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(Expense expense) {
		getPersistence().clearCache(expense);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Expense> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Expense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Expense> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Expense remove(Expense expense) throws SystemException {
		return getPersistence().remove(expense);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Expense update(Expense expense, boolean merge)
		throws SystemException {
		return getPersistence().update(expense, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Expense update(Expense expense, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(expense, merge, serviceContext);
	}

	/**
	* Caches the expense in the entity cache if it is enabled.
	*
	* @param expense the expense
	*/
	public static void cacheResult(com.liferay.timesheet.model.Expense expense) {
		getPersistence().cacheResult(expense);
	}

	/**
	* Caches the expenses in the entity cache if it is enabled.
	*
	* @param expenses the expenses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.timesheet.model.Expense> expenses) {
		getPersistence().cacheResult(expenses);
	}

	/**
	* Creates a new expense with the primary key. Does not add the expense to the database.
	*
	* @param expenseId the primary key for the new expense
	* @return the new expense
	*/
	public static com.liferay.timesheet.model.Expense create(long expenseId) {
		return getPersistence().create(expenseId);
	}

	/**
	* Removes the expense with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param expenseId the primary key of the expense
	* @return the expense that was removed
	* @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Expense remove(long expenseId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException {
		return getPersistence().remove(expenseId);
	}

	public static com.liferay.timesheet.model.Expense updateImpl(
		com.liferay.timesheet.model.Expense expense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(expense, merge);
	}

	/**
	* Returns the expense with the primary key or throws a {@link com.liferay.timesheet.NoSuchExpenseException} if it could not be found.
	*
	* @param expenseId the primary key of the expense
	* @return the expense
	* @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Expense findByPrimaryKey(
		long expenseId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException {
		return getPersistence().findByPrimaryKey(expenseId);
	}

	/**
	* Returns the expense with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param expenseId the primary key of the expense
	* @return the expense, or <code>null</code> if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Expense fetchByPrimaryKey(
		long expenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(expenseId);
	}

	/**
	* Returns all the expenses where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching expenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Expense> findByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId);
	}

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
	public static java.util.List<com.liferay.timesheet.model.Expense> findByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProjectId(projectId, start, end);
	}

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
	public static java.util.List<com.liferay.timesheet.model.Expense> findByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.Expense findByProjectId_First(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.Expense findByProjectId_Last(
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

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
	public static com.liferay.timesheet.model.Expense[] findByProjectId_PrevAndNext(
		long expenseId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(expenseId, projectId,
			orderByComparator);
	}

	/**
	* Returns all the expenses that the user has permission to view where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching expenses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Expense> filterFindByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByProjectId(projectId);
	}

	/**
	* Returns a range of all the expenses that the user has permission to view where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of expenses
	* @param end the upper bound of the range of expenses (not inclusive)
	* @return the range of matching expenses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Expense> filterFindByProjectId(
		long projectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByProjectId(projectId, start, end);
	}

	/**
	* Returns an ordered range of all the expenses that the user has permissions to view where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of expenses
	* @param end the upper bound of the range of expenses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching expenses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Expense> filterFindByProjectId(
		long projectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByProjectId(projectId, start, end,
			orderByComparator);
	}

	/**
	* Returns the expenses before and after the current expense in the ordered set of expenses that the user has permission to view where projectId = &#63;.
	*
	* @param expenseId the primary key of the current expense
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next expense
	* @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.timesheet.model.Expense[] filterFindByProjectId_PrevAndNext(
		long expenseId, long projectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.timesheet.NoSuchExpenseException {
		return getPersistence()
				   .filterFindByProjectId_PrevAndNext(expenseId, projectId,
			orderByComparator);
	}

	/**
	* Returns all the expenses.
	*
	* @return the expenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.timesheet.model.Expense> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.timesheet.model.Expense> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.timesheet.model.Expense> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the expenses where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByProjectId(projectId);
	}

	/**
	* Removes all the expenses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of expenses where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching expenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByProjectId(projectId);
	}

	/**
	* Returns the number of expenses that the user has permission to view where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching expenses that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByProjectId(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByProjectId(projectId);
	}

	/**
	* Returns the number of expenses.
	*
	* @return the number of expenses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ExpensePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ExpensePersistence)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					ExpensePersistence.class.getName());

			ReferenceRegistry.registerReference(ExpenseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	public void setPersistence(ExpensePersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(ExpenseUtil.class, "_persistence");
	}

	private static ExpensePersistence _persistence;
}