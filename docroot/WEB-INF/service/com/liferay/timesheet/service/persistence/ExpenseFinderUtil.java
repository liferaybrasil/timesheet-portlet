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
public class ExpenseFinderUtil {
	public static double sumByProject(long projectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().sumByProject(projectId);
	}

	public static ExpenseFinder getFinder() {
		if (_finder == null) {
			_finder = (ExpenseFinder)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					ExpenseFinder.class.getName());

			ReferenceRegistry.registerReference(ExpenseFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ExpenseFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ExpenseFinderUtil.class, "_finder");
	}

	private static ExpenseFinder _finder;
}