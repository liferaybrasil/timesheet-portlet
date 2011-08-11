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

import com.liferay.timesheet.util.PortletPropsValues;

/**
 * @author Antonio Junior
 */
public class TaskImpl extends TaskBaseImpl {

	public TaskImpl() {

	}

	public long getTotalHours() {
		long diff = this.getEndDate().getTime() - this.getStartDate().getTime();
		return diff / (1000 * 60 * 60);
	}

	public String getTypeDescription() {
		return PortletPropsValues.TASK_TYPES[getType()];
	}

}