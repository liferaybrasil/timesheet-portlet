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

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class ProjectServiceClp implements ProjectService {
	public ProjectServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addProjectMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addProject", long.class, java.lang.String.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				java.lang.String.class, double.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteProjectMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteProject", long.class, long.class);

		_getProjectMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getProject", long.class);

		_updateProjectMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateProject", long.class, long.class,
				java.lang.String.class, int.class, int.class, int.class,
				int.class, int.class, int.class, java.lang.String.class,
				double.class, com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.timesheet.model.Project addProject(long userId,
		java.lang.String description, int endDateMonth, int endDateDay,
		int endDateYear, int startDateMonth, int startDateDay,
		int startDateYear, java.lang.String name, double wage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addProjectMethodKey0,
				userId, ClpSerializer.translateInput(description),
				endDateMonth, endDateDay, endDateYear, startDateMonth,
				startDateDay, startDateYear,
				ClpSerializer.translateInput(name), wage,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.timesheet.model.Project)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteProject(long companyId, long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteProjectMethodKey1,
				companyId, projectId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.timesheet.model.Project getProject(long projectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getProjectMethodKey2,
				projectId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.timesheet.model.Project)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.timesheet.model.Project updateProject(long projectId,
		long userId, java.lang.String description, int endDateMonth,
		int endDateDay, int endDateYear, int startDateMonth, int startDateDay,
		int startDateYear, java.lang.String name, double wage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateProjectMethodKey3,
				projectId, userId, ClpSerializer.translateInput(description),
				endDateMonth, endDateDay, endDateYear, startDateMonth,
				startDateDay, startDateYear,
				ClpSerializer.translateInput(name), wage,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.timesheet.model.Project)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addProjectMethodKey0;
	private MethodKey _deleteProjectMethodKey1;
	private MethodKey _getProjectMethodKey2;
	private MethodKey _updateProjectMethodKey3;
}