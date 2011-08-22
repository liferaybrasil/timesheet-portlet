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
 * This class is a wrapper for {@link Task}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Task
 * @generated
 */
public class TaskWrapper implements Task {
	public TaskWrapper(Task task) {
		_task = task;
	}

	public Class<?> getModelClass() {
		return Task.class;
	}

	public String getModelClassName() {
		return Task.class.getName();
	}

	/**
	* Returns the primary key of this task.
	*
	* @return the primary key of this task
	*/
	public long getPrimaryKey() {
		return _task.getPrimaryKey();
	}

	/**
	* Sets the primary key of this task.
	*
	* @param primaryKey the primary key of this task
	*/
	public void setPrimaryKey(long primaryKey) {
		_task.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the task ID of this task.
	*
	* @return the task ID of this task
	*/
	public long getTaskId() {
		return _task.getTaskId();
	}

	/**
	* Sets the task ID of this task.
	*
	* @param taskId the task ID of this task
	*/
	public void setTaskId(long taskId) {
		_task.setTaskId(taskId);
	}

	/**
	* Returns the user ID of this task.
	*
	* @return the user ID of this task
	*/
	public long getUserId() {
		return _task.getUserId();
	}

	/**
	* Sets the user ID of this task.
	*
	* @param userId the user ID of this task
	*/
	public void setUserId(long userId) {
		_task.setUserId(userId);
	}

	/**
	* Returns the user uuid of this task.
	*
	* @return the user uuid of this task
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _task.getUserUuid();
	}

	/**
	* Sets the user uuid of this task.
	*
	* @param userUuid the user uuid of this task
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_task.setUserUuid(userUuid);
	}

	/**
	* Returns the project ID of this task.
	*
	* @return the project ID of this task
	*/
	public long getProjectId() {
		return _task.getProjectId();
	}

	/**
	* Sets the project ID of this task.
	*
	* @param projectId the project ID of this task
	*/
	public void setProjectId(long projectId) {
		_task.setProjectId(projectId);
	}

	/**
	* Returns the name of this task.
	*
	* @return the name of this task
	*/
	public java.lang.String getName() {
		return _task.getName();
	}

	/**
	* Sets the name of this task.
	*
	* @param name the name of this task
	*/
	public void setName(java.lang.String name) {
		_task.setName(name);
	}

	/**
	* Returns the type of this task.
	*
	* @return the type of this task
	*/
	public int getType() {
		return _task.getType();
	}

	/**
	* Sets the type of this task.
	*
	* @param type the type of this task
	*/
	public void setType(int type) {
		_task.setType(type);
	}

	/**
	* Returns the start date of this task.
	*
	* @return the start date of this task
	*/
	public java.util.Date getStartDate() {
		return _task.getStartDate();
	}

	/**
	* Sets the start date of this task.
	*
	* @param startDate the start date of this task
	*/
	public void setStartDate(java.util.Date startDate) {
		_task.setStartDate(startDate);
	}

	/**
	* Returns the end date of this task.
	*
	* @return the end date of this task
	*/
	public java.util.Date getEndDate() {
		return _task.getEndDate();
	}

	/**
	* Sets the end date of this task.
	*
	* @param endDate the end date of this task
	*/
	public void setEndDate(java.util.Date endDate) {
		_task.setEndDate(endDate);
	}

	public boolean isNew() {
		return _task.isNew();
	}

	public void setNew(boolean n) {
		_task.setNew(n);
	}

	public boolean isCachedModel() {
		return _task.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_task.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _task.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_task.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _task.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_task.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _task.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_task.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TaskWrapper((Task)_task.clone());
	}

	public int compareTo(com.liferay.timesheet.model.Task task) {
		return _task.compareTo(task);
	}

	@Override
	public int hashCode() {
		return _task.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Task> toCacheModel() {
		return _task.toCacheModel();
	}

	public com.liferay.timesheet.model.Task toEscapedModel() {
		return new TaskWrapper(_task.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _task.toString();
	}

	public java.lang.String toXmlString() {
		return _task.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_task.persist();
	}

	public java.lang.String getTypeDescription() {
		return _task.getTypeDescription();
	}

	public Task getWrappedTask() {
		return _task;
	}

	public void resetOriginalValues() {
		_task.resetOriginalValues();
	}

	private Task _task;
}