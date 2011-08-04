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

package com.liferay.timesheet.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.timesheet.model.Project;

import java.util.Date;

/**
 * The cache model class for representing Project in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Project
 * @generated
 */
public class ProjectCacheModel implements CacheModel<Project> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{projectId=");
		sb.append(projectId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", description=");
		sb.append(description);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", wage=");
		sb.append(wage);
		sb.append("}");

		return sb.toString();
	}

	public Project toEntityModel() {
		ProjectImpl projectImpl = new ProjectImpl();

		projectImpl.setProjectId(projectId);
		projectImpl.setUserId(userId);

		if (description == null) {
			projectImpl.setDescription(StringPool.BLANK);
		}
		else {
			projectImpl.setDescription(description);
		}

		if (endDate == Long.MIN_VALUE) {
			projectImpl.setEndDate(null);
		}
		else {
			projectImpl.setEndDate(new Date(endDate));
		}

		if (name == null) {
			projectImpl.setName(StringPool.BLANK);
		}
		else {
			projectImpl.setName(name);
		}

		if (startDate == Long.MIN_VALUE) {
			projectImpl.setStartDate(null);
		}
		else {
			projectImpl.setStartDate(new Date(startDate));
		}

		projectImpl.setWage(wage);

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	public long projectId;
	public long userId;
	public String description;
	public long endDate;
	public String name;
	public long startDate;
	public double wage;
}