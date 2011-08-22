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
public class TaskServiceClp implements TaskService {
	public TaskServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addTaskMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addTask", long.class, java.lang.String.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteTaskMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteTask", long.class, long.class);

		_getTaskMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getTask", long.class);

		_updateTaskMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateTask", long.class, long.class, java.lang.String.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, int.class, int.class, int.class, int.class,
				int.class, com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.timesheet.model.Task addTask(long projectId,
		java.lang.String name, int type, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addTaskMethodKey0,
				projectId, ClpSerializer.translateInput(name), type,
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, endDateMonth, endDateDay, endDateYear,
				endDateHour, endDateMinute,
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

		return (com.liferay.timesheet.model.Task)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteTask(long companyId, long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteTaskMethodKey1,
				companyId, taskId);

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

	public com.liferay.timesheet.model.Task getTask(long taskId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getTaskMethodKey2,
				taskId);

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

			if (t instanceof com.liferay.portal.security.auth.PrincipalException) {
				throw (com.liferay.portal.security.auth.PrincipalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.timesheet.model.Task)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.timesheet.model.Task updateTask(long taskId,
		long projectId, java.lang.String name, int type, int startDateMonth,
		int startDateDay, int startDateYear, int startDateHour,
		int startDateMinute, int endDateMonth, int endDateDay, int endDateYear,
		int endDateHour, int endDateMinute,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateTaskMethodKey3,
				taskId, projectId, ClpSerializer.translateInput(name), type,
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, endDateMonth, endDateDay, endDateYear,
				endDateHour, endDateMinute,
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

		return (com.liferay.timesheet.model.Task)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addTaskMethodKey0;
	private MethodKey _deleteTaskMethodKey1;
	private MethodKey _getTaskMethodKey2;
	private MethodKey _updateTaskMethodKey3;
}