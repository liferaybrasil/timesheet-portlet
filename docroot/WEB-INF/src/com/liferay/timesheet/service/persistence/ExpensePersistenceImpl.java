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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.timesheet.NoSuchExpenseException;
import com.liferay.timesheet.model.Expense;
import com.liferay.timesheet.model.impl.ExpenseImpl;
import com.liferay.timesheet.model.impl.ExpenseModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the expense service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpensePersistence
 * @see ExpenseUtil
 * @generated
 */
public class ExpensePersistenceImpl extends BasePersistenceImpl<Expense>
	implements ExpensePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ExpenseUtil} to access the expense persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ExpenseImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_PROJECTID = new FinderPath(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseModelImpl.FINDER_CACHE_ENABLED, ExpenseImpl.class,
			FINDER_CLASS_NAME_LIST, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByProjectId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseModelImpl.FINDER_CACHE_ENABLED, ExpenseImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the expense in the entity cache if it is enabled.
	 *
	 * @param expense the expense
	 */
	public void cacheResult(Expense expense) {
		EntityCacheUtil.putResult(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseImpl.class, expense.getPrimaryKey(), expense);

		expense.resetOriginalValues();
	}

	/**
	 * Caches the expenses in the entity cache if it is enabled.
	 *
	 * @param expenses the expenses
	 */
	public void cacheResult(List<Expense> expenses) {
		for (Expense expense : expenses) {
			if (EntityCacheUtil.getResult(
						ExpenseModelImpl.ENTITY_CACHE_ENABLED,
						ExpenseImpl.class, expense.getPrimaryKey(), this) == null) {
				cacheResult(expense);
			}
		}
	}

	/**
	 * Clears the cache for all expenses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ExpenseImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ExpenseImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the expense.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Expense expense) {
		EntityCacheUtil.removeResult(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseImpl.class, expense.getPrimaryKey());
	}

	/**
	 * Creates a new expense with the primary key. Does not add the expense to the database.
	 *
	 * @param expenseId the primary key for the new expense
	 * @return the new expense
	 */
	public Expense create(long expenseId) {
		Expense expense = new ExpenseImpl();

		expense.setNew(true);
		expense.setPrimaryKey(expenseId);

		return expense;
	}

	/**
	 * Removes the expense with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the expense
	 * @return the expense that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Expense remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the expense with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param expenseId the primary key of the expense
	 * @return the expense that was removed
	 * @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Expense remove(long expenseId)
		throws NoSuchExpenseException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Expense expense = (Expense)session.get(ExpenseImpl.class,
					Long.valueOf(expenseId));

			if (expense == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + expenseId);
				}

				throw new NoSuchExpenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					expenseId);
			}

			return expensePersistence.remove(expense);
		}
		catch (NoSuchExpenseException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the expense from the database. Also notifies the appropriate model listeners.
	 *
	 * @param expense the expense
	 * @return the expense that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Expense remove(Expense expense) throws SystemException {
		return super.remove(expense);
	}

	@Override
	protected Expense removeImpl(Expense expense) throws SystemException {
		expense = toUnwrappedModel(expense);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, expense);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseImpl.class, expense.getPrimaryKey());

		return expense;
	}

	@Override
	public Expense updateImpl(com.liferay.timesheet.model.Expense expense,
		boolean merge) throws SystemException {
		expense = toUnwrappedModel(expense);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, expense, merge);

			expense.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
			ExpenseImpl.class, expense.getPrimaryKey(), expense);

		return expense;
	}

	protected Expense toUnwrappedModel(Expense expense) {
		if (expense instanceof ExpenseImpl) {
			return expense;
		}

		ExpenseImpl expenseImpl = new ExpenseImpl();

		expenseImpl.setNew(expense.isNew());
		expenseImpl.setPrimaryKey(expense.getPrimaryKey());

		expenseImpl.setExpenseId(expense.getExpenseId());
		expenseImpl.setProjectId(expense.getProjectId());
		expenseImpl.setDescription(expense.getDescription());
		expenseImpl.setPurchasedDate(expense.getPurchasedDate());
		expenseImpl.setType(expense.getType());
		expenseImpl.setValue(expense.getValue());
		expenseImpl.setFileEntryId(expense.getFileEntryId());

		return expenseImpl;
	}

	/**
	 * Returns the expense with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the expense
	 * @return the expense
	 * @throws com.liferay.portal.NoSuchModelException if a expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Expense findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the expense with the primary key or throws a {@link com.liferay.timesheet.NoSuchExpenseException} if it could not be found.
	 *
	 * @param expenseId the primary key of the expense
	 * @return the expense
	 * @throws com.liferay.timesheet.NoSuchExpenseException if a expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Expense findByPrimaryKey(long expenseId)
		throws NoSuchExpenseException, SystemException {
		Expense expense = fetchByPrimaryKey(expenseId);

		if (expense == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + expenseId);
			}

			throw new NoSuchExpenseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				expenseId);
		}

		return expense;
	}

	/**
	 * Returns the expense with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the expense
	 * @return the expense, or <code>null</code> if a expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Expense fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the expense with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param expenseId the primary key of the expense
	 * @return the expense, or <code>null</code> if a expense with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Expense fetchByPrimaryKey(long expenseId) throws SystemException {
		Expense expense = (Expense)EntityCacheUtil.getResult(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
				ExpenseImpl.class, expenseId, this);

		if (expense == _nullExpense) {
			return null;
		}

		if (expense == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				expense = (Expense)session.get(ExpenseImpl.class,
						Long.valueOf(expenseId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (expense != null) {
					cacheResult(expense);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(ExpenseModelImpl.ENTITY_CACHE_ENABLED,
						ExpenseImpl.class, expenseId, _nullExpense);
				}

				closeSession(session);
			}
		}

		return expense;
	}

	/**
	 * Returns all the expenses where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching expenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Expense> findByProjectId(long projectId)
		throws SystemException {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	public List<Expense> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
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
	public List<Expense> findByProjectId(long projectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Expense> list = (List<Expense>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PROJECTID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_EXPENSE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ExpenseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				list = (List<Expense>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_PROJECTID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_PROJECTID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
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
	public Expense findByProjectId_First(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchExpenseException, SystemException {
		List<Expense> list = findByProjectId(projectId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchExpenseException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Expense findByProjectId_Last(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchExpenseException, SystemException {
		int count = countByProjectId(projectId);

		List<Expense> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchExpenseException(msg.toString());
		}
		else {
			return list.get(0);
		}
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
	public Expense[] findByProjectId_PrevAndNext(long expenseId,
		long projectId, OrderByComparator orderByComparator)
		throws NoSuchExpenseException, SystemException {
		Expense expense = findByPrimaryKey(expenseId);

		Session session = null;

		try {
			session = openSession();

			Expense[] array = new ExpenseImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, expense, projectId,
					orderByComparator, true);

			array[1] = expense;

			array[2] = getByProjectId_PrevAndNext(session, expense, projectId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Expense getByProjectId_PrevAndNext(Session session,
		Expense expense, long projectId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_EXPENSE_WHERE);

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(ExpenseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(expense);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Expense> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the expenses.
	 *
	 * @return the expenses
	 * @throws SystemException if a system exception occurred
	 */
	public List<Expense> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<Expense> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
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
	public List<Expense> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Expense> list = (List<Expense>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_EXPENSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_EXPENSE.concat(ExpenseModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Expense>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Expense>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the expenses where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByProjectId(long projectId) throws SystemException {
		for (Expense expense : findByProjectId(projectId)) {
			expensePersistence.remove(expense);
		}
	}

	/**
	 * Removes all the expenses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Expense expense : findAll()) {
			expensePersistence.remove(expense);
		}
	}

	/**
	 * Returns the number of expenses where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching expenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByProjectId(long projectId) throws SystemException {
		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROJECTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_EXPENSE_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROJECTID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of expenses.
	 *
	 * @return the number of expenses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_EXPENSE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the expense persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.timesheet.model.Expense")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Expense>> listenersList = new ArrayList<ModelListener<Expense>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Expense>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ExpenseImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = ExpensePersistence.class)
	protected ExpensePersistence expensePersistence;
	@BeanReference(type = ProjectPersistence.class)
	protected ProjectPersistence projectPersistence;
	@BeanReference(type = TaskPersistence.class)
	protected TaskPersistence taskPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_EXPENSE = "SELECT expense FROM Expense expense";
	private static final String _SQL_SELECT_EXPENSE_WHERE = "SELECT expense FROM Expense expense WHERE ";
	private static final String _SQL_COUNT_EXPENSE = "SELECT COUNT(expense) FROM Expense expense";
	private static final String _SQL_COUNT_EXPENSE_WHERE = "SELECT COUNT(expense) FROM Expense expense WHERE ";
	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "expense.projectId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "expense.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Expense exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Expense exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ExpensePersistenceImpl.class);
	private static Expense _nullExpense = new ExpenseImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<Expense> toCacheModel() {
				return _nullExpenseCacheModel;
			}
		};

	private static CacheModel<Expense> _nullExpenseCacheModel = new CacheModel<Expense>() {
			public Expense toEntityModel() {
				return _nullExpense;
			}
		};
}