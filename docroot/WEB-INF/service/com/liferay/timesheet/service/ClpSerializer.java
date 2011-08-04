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

				Method method1 = newModelClass.getMethod("setBilledDate",
						new Class[] { Date.class });

				Date value1 = oldCplModel.getBilledDate();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value2 = oldCplModel.getDescription();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setProjectId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getProjectId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setType",
						new Class[] { Integer.TYPE });

				Integer value4 = new Integer(oldCplModel.getType());

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setValue",
						new Class[] { Double.TYPE });

				Double value5 = new Double(oldCplModel.getValue());

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setDlFieldEntryId",
						new Class[] { Long.TYPE });

				Long value6 = new Long(oldCplModel.getDlFieldEntryId());

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

				Method method1 = newModelClass.getMethod("setUserId",
						new Class[] { Long.TYPE });

				Long value1 = new Long(oldCplModel.getUserId());

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setDescription",
						new Class[] { String.class });

				String value2 = oldCplModel.getDescription();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value3 = oldCplModel.getEndDate();

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value4 = oldCplModel.getName();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value5 = oldCplModel.getStartDate();

				method5.invoke(newModel, value5);

				Method method6 = newModelClass.getMethod("setWage",
						new Class[] { Double.TYPE });

				Double value6 = new Double(oldCplModel.getWage());

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

				Method method1 = newModelClass.getMethod("setEndDate",
						new Class[] { Date.class });

				Date value1 = oldCplModel.getEndDate();

				method1.invoke(newModel, value1);

				Method method2 = newModelClass.getMethod("setName",
						new Class[] { String.class });

				String value2 = oldCplModel.getName();

				method2.invoke(newModel, value2);

				Method method3 = newModelClass.getMethod("setProjectId",
						new Class[] { Long.TYPE });

				Long value3 = new Long(oldCplModel.getProjectId());

				method3.invoke(newModel, value3);

				Method method4 = newModelClass.getMethod("setStartDate",
						new Class[] { Date.class });

				Date value4 = oldCplModel.getStartDate();

				method4.invoke(newModel, value4);

				Method method5 = newModelClass.getMethod("setType",
						new Class[] { Integer.TYPE });

				Integer value5 = new Integer(oldCplModel.getType());

				method5.invoke(newModel, value5);

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

				Method method1 = oldModelClass.getMethod("getBilledDate");

				Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

				newModel.setBilledDate(value1);

				Method method2 = oldModelClass.getMethod("getDescription");

				String value2 = (String)method2.invoke(oldModel, (Object[])null);

				newModel.setDescription(value2);

				Method method3 = oldModelClass.getMethod("getProjectId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setProjectId(value3);

				Method method4 = oldModelClass.getMethod("getType");

				Integer value4 = (Integer)method4.invoke(oldModel,
						(Object[])null);

				newModel.setType(value4);

				Method method5 = oldModelClass.getMethod("getValue");

				Double value5 = (Double)method5.invoke(oldModel, (Object[])null);

				newModel.setValue(value5);

				Method method6 = oldModelClass.getMethod("getDlFieldEntryId");

				Long value6 = (Long)method6.invoke(oldModel, (Object[])null);

				newModel.setDlFieldEntryId(value6);

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

				Method method1 = oldModelClass.getMethod("getUserId");

				Long value1 = (Long)method1.invoke(oldModel, (Object[])null);

				newModel.setUserId(value1);

				Method method2 = oldModelClass.getMethod("getDescription");

				String value2 = (String)method2.invoke(oldModel, (Object[])null);

				newModel.setDescription(value2);

				Method method3 = oldModelClass.getMethod("getEndDate");

				Date value3 = (Date)method3.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value3);

				Method method4 = oldModelClass.getMethod("getName");

				String value4 = (String)method4.invoke(oldModel, (Object[])null);

				newModel.setName(value4);

				Method method5 = oldModelClass.getMethod("getStartDate");

				Date value5 = (Date)method5.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value5);

				Method method6 = oldModelClass.getMethod("getWage");

				Double value6 = (Double)method6.invoke(oldModel, (Object[])null);

				newModel.setWage(value6);

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

				Method method1 = oldModelClass.getMethod("getEndDate");

				Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

				newModel.setEndDate(value1);

				Method method2 = oldModelClass.getMethod("getName");

				String value2 = (String)method2.invoke(oldModel, (Object[])null);

				newModel.setName(value2);

				Method method3 = oldModelClass.getMethod("getProjectId");

				Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

				newModel.setProjectId(value3);

				Method method4 = oldModelClass.getMethod("getStartDate");

				Date value4 = (Date)method4.invoke(oldModel, (Object[])null);

				newModel.setStartDate(value4);

				Method method5 = oldModelClass.getMethod("getType");

				Integer value5 = (Integer)method5.invoke(oldModel,
						(Object[])null);

				newModel.setType(value5);

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