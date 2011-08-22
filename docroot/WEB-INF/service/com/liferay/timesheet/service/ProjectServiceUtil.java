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

package com.liferay.timesheet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the project remote service. This utility wraps {@link com.liferay.timesheet.service.impl.ProjectServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectService
 * @see com.liferay.timesheet.service.base.ProjectServiceBaseImpl
 * @see com.liferay.timesheet.service.impl.ProjectServiceImpl
 * @generated
 */
public class ProjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.timesheet.service.impl.ProjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.timesheet.model.Project addProject(long userId,
		java.lang.String description, int endDateMonth, int endDateDay,
		int endDateYear, int startDateMonth, int startDateDay,
		int startDateYear, java.lang.String name, double wage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addProject(userId, description, endDateMonth, endDateDay,
			endDateYear, startDateMonth, startDateDay, startDateYear, name,
			wage, serviceContext);
	}

	public static void deleteProject(long companyId, long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteProject(companyId, projectId);
	}

	public static com.liferay.timesheet.model.Project getProject(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getProject(projectId);
	}

	public static com.liferay.timesheet.model.Project updateProject(
		long projectId, long userId, java.lang.String description,
		int endDateMonth, int endDateDay, int endDateYear, int startDateMonth,
		int startDateDay, int startDateYear, java.lang.String name,
		double wage, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateProject(projectId, userId, description, endDateMonth,
			endDateDay, endDateYear, startDateMonth, startDateDay,
			startDateYear, name, wage, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ProjectService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ProjectService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					ProjectService.class.getName(), portletClassLoader);

			_service = new ProjectServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(ProjectServiceUtil.class,
				"_service");
			MethodCache.remove(ProjectService.class);
		}

		return _service;
	}

	public void setService(ProjectService service) {
		MethodCache.remove(ProjectService.class);

		_service = service;

		ReferenceRegistry.registerReference(ProjectServiceUtil.class, "_service");
		MethodCache.remove(ProjectService.class);
	}

	private static ProjectService _service;
}