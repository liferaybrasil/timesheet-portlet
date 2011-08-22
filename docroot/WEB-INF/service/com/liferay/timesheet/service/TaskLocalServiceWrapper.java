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
 * This class is a wrapper for {@link TaskLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TaskLocalService
 * @generated
 */
public class TaskLocalServiceWrapper implements TaskLocalService {
	public TaskLocalServiceWrapper(TaskLocalService taskLocalService) {
		_taskLocalService = taskLocalService;
	}

	/**
	* Adds the task to the database. Also notifies the appropriate model listeners.
	*
	* @param task the task
	* @return the task that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Task addTask(
		com.liferay.timesheet.model.Task task)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.addTask(task);
	}

	/**
	* Creates a new task with the primary key. Does not add the task to the database.
	*
	* @param taskId the primary key for the new task
	* @return the new task
	*/
	public com.liferay.timesheet.model.Task createTask(long taskId) {
		return _taskLocalService.createTask(taskId);
	}

	/**
	* Deletes the task with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param taskId the primary key of the task
	* @throws PortalException if a task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_taskLocalService.deleteTask(taskId);
	}

	/**
	* Deletes the task from the database. Also notifies the appropriate model listeners.
	*
	* @param task the task
	* @throws SystemException if a system exception occurred
	*/
	public void deleteTask(com.liferay.timesheet.model.Task task)
		throws com.liferay.portal.kernel.exception.SystemException {
		_taskLocalService.deleteTask(task);
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
		return _taskLocalService.dynamicQuery(dynamicQuery);
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
		return _taskLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _taskLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _taskLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the task with the primary key.
	*
	* @param taskId the primary key of the task
	* @return the task
	* @throws PortalException if a task with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.getTask(taskId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.timesheet.model.Task> getTasks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.getTasks(start, end);
	}

	/**
	* Returns the number of tasks.
	*
	* @return the number of tasks
	* @throws SystemException if a system exception occurred
	*/
	public int getTasksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.getTasksCount();
	}

	/**
	* Updates the task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param task the task
	* @return the task that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Task updateTask(
		com.liferay.timesheet.model.Task task)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.updateTask(task);
	}

	/**
	* Updates the task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param task the task
	* @param merge whether to merge the task with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the task that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.timesheet.model.Task updateTask(
		com.liferay.timesheet.model.Task task, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.updateTask(task, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _taskLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_taskLocalService.setBeanIdentifier(beanIdentifier);
	}

	public com.liferay.timesheet.model.Task addTask(long projectId,
		java.lang.String name, int type, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.addTask(projectId, name, type, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			serviceContext);
	}

	public void addTaskResources(com.liferay.timesheet.model.Task task,
		long companyId, long groupId, long userId, boolean addGroupPermissions,
		boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_taskLocalService.addTaskResources(task, companyId, groupId, userId,
			addGroupPermissions, addGuestPermissions);
	}

	public void addTaskResources(com.liferay.timesheet.model.Task task,
		long companyId, long groupId, long userId,
		java.lang.String[] groupPermissions, java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_taskLocalService.addTaskResources(task, companyId, groupId, userId,
			groupPermissions, guestPermissions);
	}

	public void deleteTask(long companyId, long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_taskLocalService.deleteTask(companyId, taskId);
	}

	public java.util.List<com.liferay.timesheet.model.Task> getTaskByProjectId(
		long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.getTaskByProjectId(projectId);
	}

	public double getSumHoursByProject(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.getSumHoursByProject(projectId);
	}

	public com.liferay.timesheet.model.Task updateTask(long taskId,
		long projectId, java.lang.String name, int type, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _taskLocalService.updateTask(taskId, projectId, name, type,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, serviceContext);
	}

	public TaskLocalService getWrappedTaskLocalService() {
		return _taskLocalService;
	}

	public void setWrappedTaskLocalService(TaskLocalService taskLocalService) {
		_taskLocalService = taskLocalService;
	}

	private TaskLocalService _taskLocalService;
}