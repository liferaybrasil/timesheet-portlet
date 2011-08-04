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
 * This class is a wrapper for {@link Project}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Project
 * @generated
 */
public class ProjectWrapper implements Project {
	public ProjectWrapper(Project project) {
		_project = project;
	}

	public Class<?> getModelClass() {
		return Project.class;
	}

	public String getModelClassName() {
		return Project.class.getName();
	}

	/**
	* Returns the primary key of this project.
	*
	* @return the primary key of this project
	*/
	public long getPrimaryKey() {
		return _project.getPrimaryKey();
	}

	/**
	* Sets the primary key of this project.
	*
	* @param primaryKey the primary key of this project
	*/
	public void setPrimaryKey(long primaryKey) {
		_project.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the project ID of this project.
	*
	* @return the project ID of this project
	*/
	public long getProjectId() {
		return _project.getProjectId();
	}

	/**
	* Sets the project ID of this project.
	*
	* @param projectId the project ID of this project
	*/
	public void setProjectId(long projectId) {
		_project.setProjectId(projectId);
	}

	/**
	* Returns the user ID of this project.
	*
	* @return the user ID of this project
	*/
	public long getUserId() {
		return _project.getUserId();
	}

	/**
	* Sets the user ID of this project.
	*
	* @param userId the user ID of this project
	*/
	public void setUserId(long userId) {
		_project.setUserId(userId);
	}

	/**
	* Returns the user uuid of this project.
	*
	* @return the user uuid of this project
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _project.getUserUuid();
	}

	/**
	* Sets the user uuid of this project.
	*
	* @param userUuid the user uuid of this project
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_project.setUserUuid(userUuid);
	}

	/**
	* Returns the description of this project.
	*
	* @return the description of this project
	*/
	public java.lang.String getDescription() {
		return _project.getDescription();
	}

	/**
	* Sets the description of this project.
	*
	* @param description the description of this project
	*/
	public void setDescription(java.lang.String description) {
		_project.setDescription(description);
	}

	/**
	* Returns the end date of this project.
	*
	* @return the end date of this project
	*/
	public java.util.Date getEndDate() {
		return _project.getEndDate();
	}

	/**
	* Sets the end date of this project.
	*
	* @param endDate the end date of this project
	*/
	public void setEndDate(java.util.Date endDate) {
		_project.setEndDate(endDate);
	}

	/**
	* Returns the name of this project.
	*
	* @return the name of this project
	*/
	public java.lang.String getName() {
		return _project.getName();
	}

	/**
	* Sets the name of this project.
	*
	* @param name the name of this project
	*/
	public void setName(java.lang.String name) {
		_project.setName(name);
	}

	/**
	* Returns the start date of this project.
	*
	* @return the start date of this project
	*/
	public java.util.Date getStartDate() {
		return _project.getStartDate();
	}

	/**
	* Sets the start date of this project.
	*
	* @param startDate the start date of this project
	*/
	public void setStartDate(java.util.Date startDate) {
		_project.setStartDate(startDate);
	}

	/**
	* Returns the wage of this project.
	*
	* @return the wage of this project
	*/
	public double getWage() {
		return _project.getWage();
	}

	/**
	* Sets the wage of this project.
	*
	* @param wage the wage of this project
	*/
	public void setWage(double wage) {
		_project.setWage(wage);
	}

	public boolean isNew() {
		return _project.isNew();
	}

	public void setNew(boolean n) {
		_project.setNew(n);
	}

	public boolean isCachedModel() {
		return _project.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_project.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _project.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_project.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _project.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_project.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _project.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_project.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectWrapper((Project)_project.clone());
	}

	public int compareTo(com.liferay.timesheet.model.Project project) {
		return _project.compareTo(project);
	}

	@Override
	public int hashCode() {
		return _project.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.timesheet.model.Project> toCacheModel() {
		return _project.toCacheModel();
	}

	public com.liferay.timesheet.model.Project toEscapedModel() {
		return new ProjectWrapper(_project.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _project.toString();
	}

	public java.lang.String toXmlString() {
		return _project.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_project.persist();
	}

	public double getTotalProjectCost()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _project.getTotalProjectCost();
	}

	public double getTotalTaskCost()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _project.getTotalTaskCost();
	}

	public double getTotalExpenseCost()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _project.getTotalExpenseCost();
	}

	public Project getWrappedProject() {
		return _project;
	}

	public void resetOriginalValues() {
		_project.resetOriginalValues();
	}

	private Project _project;
}