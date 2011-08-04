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
		StringBundler sb = new StringBundler(13);

		sb.append("{taskId=");
		sb.append(taskId);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public Task toEntityModel() {
		TaskImpl taskImpl = new TaskImpl();

		taskImpl.setTaskId(taskId);

		if (endDate == Long.MIN_VALUE) {
			taskImpl.setEndDate(null);
		}
		else {
			taskImpl.setEndDate(new Date(endDate));
		}

		if (name == null) {
			taskImpl.setName(StringPool.BLANK);
		}
		else {
			taskImpl.setName(name);
		}

		taskImpl.setProjectId(projectId);

		if (startDate == Long.MIN_VALUE) {
			taskImpl.setStartDate(null);
		}
		else {
			taskImpl.setStartDate(new Date(startDate));
		}

		taskImpl.setType(type);

		taskImpl.resetOriginalValues();

		return taskImpl;
	}

	public long taskId;
	public long endDate;
	public String name;
	public long projectId;
	public long startDate;
	public int type;
}