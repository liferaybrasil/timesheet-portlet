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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
public class TaskFinderUtil {
	public static double sumHoursByProject(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().sumHoursByProject(projectId);
	}

	public static TaskFinder getFinder() {
		if (_finder == null) {
			_finder = (TaskFinder)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					TaskFinder.class.getName());

			ReferenceRegistry.registerReference(TaskFinderUtil.class, "_finder");
		}

		return _finder;
	}

	public void setFinder(TaskFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TaskFinderUtil.class, "_finder");
	}

	private static TaskFinder _finder;
}