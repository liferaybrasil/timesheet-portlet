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
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.timesheet.NoSuchTaskException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.impl.TaskImpl;
import com.liferay.timesheet.model.impl.TaskModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TaskPersistence
 * @see TaskUtil
 * @generated
 */
public class TaskPersistenceImpl extends BasePersistenceImpl<Task>
	implements TaskPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TaskUtil} to access the task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TaskImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_PROJECTID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countByProjectId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, TaskImpl.class,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the task in the entity cache if it is enabled.
	 *
	 * @param task the task
	 */
	public void cacheResult(Task task) {
		EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey(), task);

		task.resetOriginalValues();
	}

	/**
	 * Caches the tasks in the entity cache if it is enabled.
	 *
	 * @param tasks the tasks
	 */
	public void cacheResult(List<Task> tasks) {
		for (Task task : tasks) {
			if (EntityCacheUtil.getResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
						TaskImpl.class, task.getPrimaryKey(), this) == null) {
				cacheResult(task);
			}
		}
	}

	/**
	 * Clears the cache for all tasks.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(TaskImpl.class.getName());
		}

		EntityCacheUtil.clearCache(TaskImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the task.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Task task) {
		EntityCacheUtil.removeResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey());
	}

	/**
	 * Creates a new task with the primary key. Does not add the task to the database.
	 *
	 * @param taskId the primary key for the new task
	 * @return the new task
	 */
	public Task create(long taskId) {
		Task task = new TaskImpl();

		task.setNew(true);
		task.setPrimaryKey(taskId);

		return task;
	}

	/**
	 * Removes the task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the task
	 * @return the task that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param taskId the primary key of the task
	 * @return the task that was removed
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task remove(long taskId) throws NoSuchTaskException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Task task = (Task)session.get(TaskImpl.class, Long.valueOf(taskId));

			if (task == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + taskId);
				}

				throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					taskId);
			}

			return taskPersistence.remove(task);
		}
		catch (NoSuchTaskException nsee) {
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
	 * Removes the task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param task the task
	 * @return the task that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task remove(Task task) throws SystemException {
		return super.remove(task);
	}

	@Override
	protected Task removeImpl(Task task) throws SystemException {
		task = toUnwrappedModel(task);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, task);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey());

		return task;
	}

	@Override
	public Task updateImpl(com.liferay.timesheet.model.Task task, boolean merge)
		throws SystemException {
		task = toUnwrappedModel(task);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, task, merge);

			task.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
			TaskImpl.class, task.getPrimaryKey(), task);

		return task;
	}

	protected Task toUnwrappedModel(Task task) {
		if (task instanceof TaskImpl) {
			return task;
		}

		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setNew(task.isNew());
		taskImpl.setPrimaryKey(task.getPrimaryKey());

		taskImpl.setTaskId(task.getTaskId());
		taskImpl.setUserId(task.getUserId());
		taskImpl.setProjectId(task.getProjectId());
		taskImpl.setName(task.getName());
		taskImpl.setType(task.getType());
		taskImpl.setStartDate(task.getStartDate());
		taskImpl.setEndDate(task.getEndDate());

		return taskImpl;
	}

	/**
	 * Returns the task with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the task
	 * @return the task
	 * @throws com.liferay.portal.NoSuchModelException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the task with the primary key or throws a {@link com.liferay.timesheet.NoSuchTaskException} if it could not be found.
	 *
	 * @param taskId the primary key of the task
	 * @return the task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task findByPrimaryKey(long taskId)
		throws NoSuchTaskException, SystemException {
		Task task = fetchByPrimaryKey(taskId);

		if (task == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + taskId);
			}

			throw new NoSuchTaskException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				taskId);
		}

		return task;
	}

	/**
	 * Returns the task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the task
	 * @return the task, or <code>null</code> if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Task fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param taskId the primary key of the task
	 * @return the task, or <code>null</code> if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task fetchByPrimaryKey(long taskId) throws SystemException {
		Task task = (Task)EntityCacheUtil.getResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
				TaskImpl.class, taskId, this);

		if (task == _nullTask) {
			return null;
		}

		if (task == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				task = (Task)session.get(TaskImpl.class, Long.valueOf(taskId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (task != null) {
					cacheResult(task);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(TaskModelImpl.ENTITY_CACHE_ENABLED,
						TaskImpl.class, taskId, _nullTask);
				}

				closeSession(session);
			}
		}

		return task;
	}

	/**
	 * Returns all the tasks where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> findByProjectId(long projectId) throws SystemException {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the tasks where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> findByProjectId(long projectId, int start, int end)
		throws SystemException {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> findByProjectId(long projectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				projectId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_PROJECTID,
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

			query.append(_SQL_SELECT_TASK_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				list = (List<Task>)QueryUtil.list(q, getDialect(), start, end);
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
	 * Returns the first task in the ordered set where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task findByProjectId_First(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		List<Task> list = findByProjectId(projectId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last task in the ordered set where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a matching task could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task findByProjectId_Last(long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		int count = countByProjectId(projectId);

		List<Task> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("projectId=");
			msg.append(projectId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTaskException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param taskId the primary key of the current task
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task[] findByProjectId_PrevAndNext(long taskId, long projectId,
		OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		Task task = findByPrimaryKey(taskId);

		Session session = null;

		try {
			session = openSession();

			Task[] array = new TaskImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, task, projectId,
					orderByComparator, true);

			array[1] = task;

			array[2] = getByProjectId_PrevAndNext(session, task, projectId,
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

	protected Task getByProjectId_PrevAndNext(Session session, Task task,
		long projectId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TASK_WHERE);

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
			query.append(TaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(task);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Task> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks that the user has permission to view where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching tasks that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> filterFindByProjectId(long projectId)
		throws SystemException {
		return filterFindByProjectId(projectId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks that the user has permission to view where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of matching tasks that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> filterFindByProjectId(long projectId, int start, int end)
		throws SystemException {
		return filterFindByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks that the user has permissions to view where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching tasks that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> filterFindByProjectId(long projectId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProjectId(projectId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Task.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, TaskImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, TaskImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(projectId);

			return (List<Task>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the tasks before and after the current task in the ordered set of tasks that the user has permission to view where projectId = &#63;.
	 *
	 * @param taskId the primary key of the current task
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next task
	 * @throws com.liferay.timesheet.NoSuchTaskException if a task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Task[] filterFindByProjectId_PrevAndNext(long taskId,
		long projectId, OrderByComparator orderByComparator)
		throws NoSuchTaskException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByProjectId_PrevAndNext(taskId, projectId,
				orderByComparator);
		}

		Task task = findByPrimaryKey(taskId);

		Session session = null;

		try {
			session = openSession();

			Task[] array = new TaskImpl[3];

			array[0] = filterGetByProjectId_PrevAndNext(session, task,
					projectId, orderByComparator, true);

			array[1] = task;

			array[2] = filterGetByProjectId_PrevAndNext(session, task,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Task filterGetByProjectId_PrevAndNext(Session session, Task task,
		long projectId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASK_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_TASK_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_TASK_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(TaskModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(TaskModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Task.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, TaskImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, TaskImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(task);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Task> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the tasks.
	 *
	 * @return the tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @return the range of tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of tasks
	 * @param end the upper bound of the range of tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of tasks
	 * @throws SystemException if a system exception occurred
	 */
	public List<Task> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<Task> list = (List<Task>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TASK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TASK.concat(TaskModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Task>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the tasks where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByProjectId(long projectId) throws SystemException {
		for (Task task : findByProjectId(projectId)) {
			taskPersistence.remove(task);
		}
	}

	/**
	 * Removes all the tasks from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Task task : findAll()) {
			taskPersistence.remove(task);
		}
	}

	/**
	 * Returns the number of tasks where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching tasks
	 * @throws SystemException if a system exception occurred
	 */
	public int countByProjectId(long projectId) throws SystemException {
		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROJECTID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TASK_WHERE);

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
	 * Returns the number of tasks that the user has permission to view where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching tasks that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByProjectId(long projectId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByProjectId(projectId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_TASK_WHERE);

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				Task.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(projectId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the number of tasks.
	 *
	 * @return the number of tasks
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

				Query q = session.createQuery(_SQL_COUNT_TASK);

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
	 * Initializes the task persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.timesheet.model.Task")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Task>> listenersList = new ArrayList<ModelListener<Task>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Task>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(TaskImpl.class.getName());
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
	private static final String _SQL_SELECT_TASK = "SELECT task FROM Task task";
	private static final String _SQL_SELECT_TASK_WHERE = "SELECT task FROM Task task WHERE ";
	private static final String _SQL_COUNT_TASK = "SELECT COUNT(task) FROM Task task";
	private static final String _SQL_COUNT_TASK_WHERE = "SELECT COUNT(task) FROM Task task WHERE ";
	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "task.projectId = ?";
	private static final String _FILTER_SQL_SELECT_TASK_WHERE = "SELECT DISTINCT {task.*} FROM Timesheet_Task task WHERE ";
	private static final String _FILTER_SQL_SELECT_TASK_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {Timesheet_Task.*} FROM (SELECT DISTINCT task.taskId FROM Timesheet_Task task WHERE ";
	private static final String _FILTER_SQL_SELECT_TASK_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN Timesheet_Task ON TEMP_TABLE.taskId = Timesheet_Task.taskId";
	private static final String _FILTER_SQL_COUNT_TASK_WHERE = "SELECT COUNT(DISTINCT task.taskId) AS COUNT_VALUE FROM Timesheet_Task task WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "task";
	private static final String _FILTER_ENTITY_TABLE = "Timesheet_Task";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "task.taskId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "task.";
	private static final String _ORDER_BY_ENTITY_TABLE = "Timesheet_Task.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Task exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Task exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(TaskPersistenceImpl.class);
	private static Task _nullTask = new TaskImpl() {
			public Object clone() {
				return this;
			}

			public CacheModel<Task> toCacheModel() {
				return _nullTaskCacheModel;
			}
		};

	private static CacheModel<Task> _nullTaskCacheModel = new CacheModel<Task>() {
			public Task toEntityModel() {
				return _nullTask;
			}
		};
}