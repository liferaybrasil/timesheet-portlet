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
 * The utility for the expense remote service. This utility wraps {@link com.liferay.timesheet.service.impl.ExpenseServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpenseService
 * @see com.liferay.timesheet.service.base.ExpenseServiceBaseImpl
 * @see com.liferay.timesheet.service.impl.ExpenseServiceImpl
 * @generated
 */
public class ExpenseServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.timesheet.service.impl.ExpenseServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.timesheet.model.Expense addExpense(
		long projectId, java.lang.String description, int purchasedDateMonth,
		int purchasedDateDay, int purchasedDateYear, int type, double value,
		long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addExpense(projectId, description, purchasedDateMonth,
			purchasedDateDay, purchasedDateYear, type, value, fileEntryId,
			serviceContext);
	}

	public static void deleteExpense(long companyId, long expenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteExpense(companyId, expenseId);
	}

	public static com.liferay.timesheet.model.Expense getExpense(long expenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.security.auth.PrincipalException {
		return getService().getExpense(expenseId);
	}

	public static com.liferay.timesheet.model.Expense updateExpense(
		long expenseId, long projectId, java.lang.String description,
		int purchasedDateMonth, int purchasedDateDay, int purchasedDateYear,
		int type, double value, long fileEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateExpense(expenseId, projectId, description,
			purchasedDateMonth, purchasedDateDay, purchasedDateYear, type,
			value, fileEntryId, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static ExpenseService getService() {
		if (_service == null) {
			Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ExpenseService.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
					ExpenseService.class.getName(), portletClassLoader);

			_service = new ExpenseServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);

			ReferenceRegistry.registerReference(ExpenseServiceUtil.class,
				"_service");
			MethodCache.remove(ExpenseService.class);
		}

		return _service;
	}

	public void setService(ExpenseService service) {
		MethodCache.remove(ExpenseService.class);

		_service = service;

		ReferenceRegistry.registerReference(ExpenseServiceUtil.class, "_service");
		MethodCache.remove(ExpenseService.class);
	}

	private static ExpenseService _service;
}