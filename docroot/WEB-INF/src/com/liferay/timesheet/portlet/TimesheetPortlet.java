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

package com.liferay.timesheet.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.InvalidDatesException;
import com.liferay.timesheet.InvalidDescriptionException;
import com.liferay.timesheet.InvalidMoneyFormatException;
import com.liferay.timesheet.InvalidNameException;
import com.liferay.timesheet.service.ExpenseLocalServiceUtil;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Antonio Junior
 */
public class TimesheetPortlet extends MVCPortlet {

	public void deleteExpense (
			ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception {

		long expenseId = ParamUtil.getLong(actionRequest, "expenseId");

		ExpenseLocalServiceUtil.deleteExpense(expenseId);
	}

	public void deleteProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception {

		long projectId = ParamUtil.getLong(actionRequest, "projectId");

		ProjectLocalServiceUtil.deleteProject(projectId);
	}

	public void deleteTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception {

		long taskId = ParamUtil.getLong(actionRequest, "taskId");

		TaskLocalServiceUtil.deleteTask(taskId);
	}

	public void updateExpense(
			ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception {

		String currentUrl = ParamUtil.getString(actionRequest, "currentUrl");

		try {
			long expenseId = ParamUtil.getLong(actionRequest, "expenseId");
			long projectId = ParamUtil.getLong(actionRequest, "projectId");

			String description = ParamUtil.getString(
					actionRequest, "description");

			double value = ParamUtil.getDouble(actionRequest, "value");

			int billedDateMonth = ParamUtil.getInteger(
					actionRequest, "billedDateMonth");
			int billedDateDay = ParamUtil.getInteger(
					actionRequest, "billedDateDay");
			int billedDateYear = ParamUtil.getInteger(
					actionRequest, "billedDateYear");

			UploadPortletRequest uploadRequest = PortalUtil
			.getUploadPortletRequest(actionRequest);

			File file = null;

			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest
			.getAttribute(WebKeys.THEME_DISPLAY);

			long groupId = themeDisplay.getScopeGroupId();

			int type = ParamUtil.getInteger(actionRequest, "type");

			if ( expenseId == 0) {
				ExpenseLocalServiceUtil.addExpense(
						projectId, description, billedDateMonth, billedDateDay,
						billedDateYear, type, value, file, groupId);
			} else {
				ExpenseLocalServiceUtil.updateExpense(
						expenseId, projectId, description, billedDateMonth,
						billedDateDay, billedDateYear, type, value, file,
						groupId);
			}
		} catch (Exception e) {
			if (e instanceof InvalidDescriptionException ||
				e instanceof InvalidMoneyFormatException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				actionResponse.sendRedirect(currentUrl);
			}
			else {
				throw e;
			}
		}
	}

	public void updateProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception {

		String currentUrl = ParamUtil.getString(actionRequest, "currentUrl");

		try {
			long userId = ParamUtil.getLong(actionRequest, "userId");

			long projectId = ParamUtil.getLong(actionRequest, "projectId");
			String name = ParamUtil.getString(actionRequest, "name");

			double wage = ParamUtil.getDouble(actionRequest, "wage");
			String description = ParamUtil.getString(
					actionRequest, "description");

			int startDateMonth = ParamUtil.getInteger(
					actionRequest, "startDateMonth");
			int startDateDay = ParamUtil.getInteger(
					actionRequest, "startDateDay");
			int startDateYear = ParamUtil.getInteger(
					actionRequest, "startDateYear");

			int endDateMonth = ParamUtil.getInteger(
					actionRequest, "endDateMonth");
			int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
			int endDateYear = ParamUtil.getInteger(
					actionRequest, "endDateYear");

			if ( projectId == 0) {
				ProjectLocalServiceUtil.addProject(userId, name, wage,
						description, startDateMonth, startDateDay,
						startDateYear, endDateMonth, endDateDay, endDateYear);
			} else {
				ProjectLocalServiceUtil.updateProject(projectId, userId, name,
						wage, description, startDateMonth, startDateDay,
						startDateYear, endDateMonth, endDateDay, endDateYear);
			}
		} catch (Exception e) {
			if (e instanceof InvalidNameException ||
					e instanceof InvalidDescriptionException ||
					e instanceof InvalidMoneyFormatException ||
					e instanceof InvalidDatesException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				actionResponse.sendRedirect(currentUrl);
			}
			else {
				throw e;
			}
		}
	}

	public void updateTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception {

		String currentUrl = ParamUtil.getString(actionRequest, "currentUrl");

		try {
			long projectId = ParamUtil.getLong(actionRequest, "projectId");
			long taskId = ParamUtil.getLong(actionRequest, "taskId");
			String name = ParamUtil.getString(actionRequest, "name");

			int startDateMonth = ParamUtil.getInteger(
					actionRequest, "startDateMonth");
			int startDateDay = ParamUtil.getInteger(
					actionRequest, "startDateDay");
			int startDateYear = ParamUtil.getInteger(
					actionRequest, "startDateYear");
			int startDateHour = ParamUtil.getInteger(
					actionRequest, "startDateHour");
			int startDateMinute = ParamUtil.getInteger(
					actionRequest, "startDateMinute");
			int startDateAmPm = ParamUtil.getInteger(
					actionRequest, "startDateAmPm");

			if (startDateAmPm == Calendar.PM) {
				startDateHour += 12;
			}

			int endDateMonth = ParamUtil.getInteger(
					actionRequest, "endDateMonth");
			int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
			int endDateYear = ParamUtil.getInteger(
					actionRequest, "endDateYear");
			int endDateHour = ParamUtil.getInteger(
					actionRequest, "endDateHour");
			int endDateMinute = ParamUtil.getInteger(
					actionRequest, "endDateMinute");
			int endDateAmPm = ParamUtil.getInteger(
					actionRequest, "endDateAmPm");

			if (endDateAmPm == Calendar.PM) {
				endDateHour += 12;
			}

			int type = ParamUtil.getInteger(actionRequest, "type");

			if ( taskId == 0) {
				TaskLocalServiceUtil.addTask(projectId, name, type,
						startDateMonth, startDateDay, startDateYear,
						startDateHour, startDateMinute, endDateMonth,
						endDateDay, endDateYear, endDateHour, endDateMinute);
			} else {
				TaskLocalServiceUtil.updateTask(taskId, projectId, name, type,
						startDateMonth, startDateDay, startDateYear,
						startDateHour, startDateMinute, endDateMonth,
						endDateDay, endDateYear, endDateHour, endDateMinute);
			}
		} catch (Exception e) {
			if (e instanceof InvalidNameException ||
					e instanceof InvalidDatesException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				actionResponse.sendRedirect(currentUrl);
			}
			else {
				throw e;
			}
		}
	}

}