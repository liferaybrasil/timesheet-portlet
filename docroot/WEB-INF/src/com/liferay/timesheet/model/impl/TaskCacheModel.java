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

import com.liferay.timesheet.model.Task;

import java.util.Date;

/**
 * The cache model class for representing Task in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Task
 * @generated
 */
public class TaskCacheModel implements CacheModel<Task> {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{taskId=");
		sb.append(taskId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", type=");
		sb.append(type);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append("}");

		return sb.toString();
	}

	public Task toEntityModel() {
		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setTaskId(taskId);
		taskImpl.setUserId(userId);
		taskImpl.setProjectId(projectId);

		if (name == null) {
			taskImpl.setName(StringPool.BLANK);
		}
		else {
			taskImpl.setName(name);
		}

		taskImpl.setType(type);

		if (startDate == Long.MIN_VALUE) {
			taskImpl.setStartDate(null);
		}
		else {
			taskImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			taskImpl.setEndDate(null);
		}
		else {
			taskImpl.setEndDate(new Date(endDate));
		}

		taskImpl.resetOriginalValues();

		return taskImpl;
	}

	public long taskId;
	public long userId;
	public long projectId;
	public String name;
	public int type;
	public long startDate;
	public long endDate;
}