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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.timesheet.model.ExpenseClp;
import com.liferay.timesheet.model.ProjectClp;
import com.liferay.timesheet.model.TaskClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"timesheet-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to locate deployment context from portlet properties",
						t);
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"timesheet-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isWarnEnabled()) {
						_log.warn("Unable to locate deployment context from portal properties",
							t);
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "timesheet-portlet";
			}

			return _servletContextName;
		}
	}

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(ExpenseClp.class.getName())) {
			return translateInputExpense(oldModel);
		}

		if (oldModelClassName.equals(ProjectClp.class.getName())) {
			return translateInputProject(oldModel);
		}

		if (oldModelClassName.equals(TaskClp.class.getName())) {
			return translateInputTask(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputExpense(BaseModel<?> oldModel) {
		ExpenseClp oldCplModel = (ExpenseClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.timesheet.model.impl.ExpenseImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setExpenseId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getExpenseId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getUserId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setProjectId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getProjectId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value3 = oldCplModel.getDescription();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setPurchasedDate",
						new Class[] { Date.class });

				Date value4 = oldCplModel.getPurchasedDate();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setType",
						new Class[] { Integer.TYPE });

				Integer value5 = new Integer(oldCplModel.getType());

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setValue",
						new Class[] { Double.TYPE });

				Double value6 = new Double(oldCplModel.getValue());

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setFileEntryId",
						new Class[] { Long.TYPE });

				Long value7 = new Long(oldCplModel.getFileEntryId());

				method7.invoke(newModel, value7);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputProject(BaseModel<?> oldModel) {
		ProjectClp oldCplModel = (ProjectClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.timesheet.model.impl.ProjectImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setProjectId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getProjectId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setGroupId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getGroupId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setCompanyId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getCompanyId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getUserId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value4 = oldCplModel.getDescription();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getEndDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getStartDate();

				method6.invoke(newModel, value6);

				Method method7 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value7 = oldCplModel.getName();

				method7.invoke(newModel, value7);

				Method method8 = newModelClass.getMethod("setWage",
						new Class[] { Double.TYPE });

				Double value8 = new Double(oldCplModel.getWage());

				method8.invoke(newModel, value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInputTask(BaseModel<?> oldModel) {
		TaskClp oldCplModel = (TaskClp)oldModel;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				Class<?> newModelClass = Class.forName("com.liferay.timesheet.model.impl.TaskImpl",
						true, _classLoader);

				Object newModel = newModelClass.newInstance();

				Method method0 = newModelClass.getMethod("setTaskId",
						new Class[] { Long.TYPE });

				Long value0 = new Long(oldCplModel.getTaskId());

				method0.invoke(newModel, value0);

				Method method1 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getUserId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setProjectId",
						new Class[] { Long.TYPE });

				Long value2 = new Long(oldCplModel.getProjectId());

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value3 = oldCplModel.getName();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setType",
						new Class[] { Integer.TYPE });

				Integer value4 = new Integer(oldCplModel.getType());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getStartDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value6 = oldCplModel.getEndDate();

				method6.invoke(newModel, value6);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.timesheet.model.impl.ExpenseImpl")) {
			return translateOutputExpense(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.timesheet.model.impl.ProjectImpl")) {
			return translateOutputProject(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.timesheet.model.impl.TaskImpl")) {
			return translateOutputTask(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutputExpense(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				ExpenseClp newModel = new ExpenseClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getExpenseId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setExpenseId(value0);

				Method method1 = oldModelClass.getMethod("getUserId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setUserId(value1);

				Method method2 = oldModelClass.getMethod("getProjectId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setProjectId(value2);

				Method method3 = oldModelClass.getMethod("getDescription");

				String value3 = (String)method3.invoke(oldModel, (Object[])null);

				newModel.setDescription(value3);

				Method method4 = oldModelClass.getMethod("getPurchasedDate");

				Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

				newModel.setPurchasedDate(value4);

				Method method5 = oldModelClass.getMethod("getType");

				Integer value5 = (Integer)method5.invoke(oldModel,
						(Object[])null);

				newModel.setType(value5);

				Method method6 = oldModelClass.getMethod("getValue");

				Double value6 = (Double)method6.invoke(oldModel, (Object[])null);

				newModel.setValue(value6);

				Method method7 = oldModelClass.getMethod("getFileEntryId");

				Long value7 = (Long)method7.invoke(oldModel, (Object[])null);

				newModel.setFileEntryId(value7);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputProject(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				ProjectClp newModel = new ProjectClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getProjectId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setProjectId(value0);

				Method method1 = oldModelClass.getMethod("getGroupId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setGroupId(value1);

				Method method2 = oldModelClass.getMethod("getCompanyId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setCompanyId(value2);

				Method method3 = oldModelClass.getMethod("getUserId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setUserId(value3);

				Method method4 = oldModelClass.getMethod("getDescription");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setDescription(value4);

				Method method5 = oldModelClass.getMethod("getEndDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value5);

				Method method6 = oldModelClass.getMethod("getStartDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value6);

				Method method7 = oldModelClass.getMethod("getName");

				String value7 = (String)method7.invoke(oldModel, (Object[])null);

				newModel.setName(value7);

				Method method8 = oldModelClass.getMethod("getWage");

				Double value8 = (Double)method8.invoke(oldModel, (Object[])null);

				newModel.setWage(value8);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	public static Object translateOutputTask(BaseModel<?> oldModel) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(_classLoader);

			try {
				TaskClp newModel = new TaskClp();

				Class<?> oldModelClass = oldModel.getClass();

				Method method0 = oldModelClass.getMethod("getTaskId");

				Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

				newModel.setTaskId(value0);

				Method method1 = oldModelClass.getMethod("getUserId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setUserId(value1);

				Method method2 = oldModelClass.getMethod("getProjectId");

				Long value2 = (Long)method2.invoke(oldModel, (Object[])null);

				newModel.setProjectId(value2);

				Method method3 = oldModelClass.getMethod("getName");

				String value3 = (String)method3.invoke(oldModel, (Object[])null);

				newModel.setName(value3);

				Method method4 = oldModelClass.getMethod("getType");

				Integer value4 = (Integer)method4.invoke(oldModel,
						(Object[])null);

				newModel.setType(value4);

				Method method5 = oldModelClass.getMethod("getStartDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value5);

				Method method6 = oldModelClass.getMethod("getEndDate");

				Date value6 = (Date)method6.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value6);

				return newModel;
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}

		return oldModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
	private static String _servletContextName;
}