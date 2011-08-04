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
import com.liferay.portal.util.PortalUtil;

import com.liferay.timesheet.service.ProjectLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ProjectClp extends BaseModelImpl<Project> implements Project {
	public ProjectClp() {
	}

	public Class<?> getModelClass() {
		return Project.class;
	}

	public String getModelClassName() {
		return Project.class.getName();
	}

	public long getPrimaryKey() {
		return _projectId;
	}

	public void setPrimaryKey(long primaryKey) {
		setProjectId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_projectId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public double getWage() {
		return _wage;
	}

	public void setWage(double wage) {
		_wage = wage;
	}

	public double getTotalProjectCost() {
		throw new UnsupportedOperationException();
	}

	public double getTotalTaskCost() {
		throw new UnsupportedOperationException();
	}

	public double getTotalExpenseCost() {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		ProjectLocalServiceUtil.updateProject(this);
	}

	@Override
	public Project toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			return (Project)Proxy.newProxyInstance(Project.class.getClassLoader(),
				new Class[] { Project.class }, new AutoEscapeBeanHandler(this));
		}
	}

	@Override
	public Object clone() {
		ProjectClp clone = new ProjectClp();

		clone.setProjectId(getProjectId());
		clone.setUserId(getUserId());
		clone.setDescription(getDescription());
		clone.setEndDate(getEndDate());
		clone.setName(getName());
		clone.setStartDate(getStartDate());
		clone.setWage(getWage());

		return clone;
	}

	public int compareTo(Project project) {
		int value = 0;

		value = DateUtil.compareTo(getStartDate(), project.getStartDate());

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

		ProjectClp project = null;

		try {
			project = (ProjectClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = project.getPrimaryKey();

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

		sb.append("{projectId=");
		sb.append(getProjectId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", wage=");
		sb.append(getWage());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.timesheet.model.Project");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>wage</column-name><column-value><![CDATA[");
		sb.append(getWage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _projectId;
	private long _userId;
	private String _userUuid;
	private String _description;
	private Date _endDate;
	private String _name;
	private Date _startDate;
	private double _wage;
}