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

package com.liferay.timesheet.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Antonio Junior
 */
public class PortletPropsValues {

	public static final String[] EXPENSE_TYPES = PortletProps.getArray(
		PortletPropsKeys.EXPENSE_TYPES);

	public static final String[] TASK_TYPES = PortletProps.getArray(
		PortletPropsKeys.TASK_TYPES);

}