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
public class ProjectFinderUtil {
	public static int countByKeywords(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByKeywords(keywords);
	}

	public static int countByN_D(java.lang.String name,
		java.lang.String description, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().countByN_D(name, description, andOperator);
	}

	public static java.util.List<com.liferay.timesheet.model.Project> findByKeywords(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByKeywords(keywords, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.timesheet.model.Project> findByN_D(
		java.lang.String name, java.lang.String description,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByN_D(name, description, andOperator, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.timesheet.model.Project> findByN_D(
		java.lang.String[] names, java.lang.String[] descriptions,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder()
				   .findByN_D(names, descriptions, andOperator, start, end,
			orderByComparator);
	}

	public static ProjectFinder getFinder() {
		if (_finder == null) {
			_finder = (ProjectFinder)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					ProjectFinder.class.getName());

			ReferenceRegistry.registerReference(ProjectFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(ProjectFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(ProjectFinderUtil.class, "_finder");
	}

	private static ProjectFinder _finder;
}