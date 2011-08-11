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

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.imagegallery.NoSuchFolderException;
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
		
		UploadPortletRequest uploadRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);
		
		try {
			long expenseId = ParamUtil.getLong(uploadRequest, "expenseId");
			long projectId = ParamUtil.getLong(uploadRequest, "projectId");
			long fileEntryId = ParamUtil.getLong(uploadRequest, "fileEntryId");
			
			String description = ParamUtil.getString(
				uploadRequest, "description");

			double value = ParamUtil.getDouble(uploadRequest, "value");

			int purchasedDateMonth = ParamUtil.getInteger(
				uploadRequest, "purchasedDateMonth");
			int purchasedDateDay = ParamUtil.getInteger(
				uploadRequest, "purchasedDateDay");
			int purchasedDateYear = ParamUtil.getInteger(
				uploadRequest, "purchasedDateYear");

			int type = ParamUtil.getInteger(uploadRequest, "type");

			// Uploaded file					

			File file = uploadRequest.getFile("file");
			String sourceFileName = uploadRequest.getFileName("file");

			if (Validator.isNotNull(file) &&
				Validator.isNotNull(sourceFileName)) {
			
				if (!file.exists()) {
					file.createNewFile();
				}

				String contentType = uploadRequest.getContentType("file");			
				String title = sourceFileName;
				String changeLog = StringPool.BLANK;

				ThemeDisplay themeDisplay = 
					(ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				long groupId = themeDisplay.getScopeGroupId();

				DLFolder folder = null;

				try {
					folder = DLFolderLocalServiceUtil.getDLFolder(
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
				}
				catch (Exception e) {
					folder = DLFolderLocalServiceUtil.createDLFolder(
						DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

					folder = DLFolderLocalServiceUtil.addDLFolder(folder);
				}

				long folderId = folder.getFolderId();			

				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(
						DLFileEntry.class.getName(), actionRequest);
				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);						

				FileEntry fileEntry = null;

				if(fileEntryId > 0) {
					fileEntry = DLAppServiceUtil.updateFileEntry(
						fileEntryId, sourceFileName, contentType, title,
						description, changeLog, true, file, serviceContext);
				} 
				else {
					fileEntry = DLAppServiceUtil.addFileEntry(
						groupId, folderId, sourceFileName, contentType, title,
						title, changeLog, file, serviceContext);
				}

				fileEntryId = fileEntry.getFileEntryId();
			}
			
			if ( expenseId <= 0) {
				ExpenseLocalServiceUtil.addExpense(
					projectId, description, purchasedDateMonth, 
					purchasedDateDay, purchasedDateYear, type, value, 
					fileEntryId);
			}
			else {
				ExpenseLocalServiceUtil.updateExpense(
					expenseId, projectId, description, purchasedDateMonth, 
					purchasedDateDay, purchasedDateYear, type, value, 
					fileEntryId);
			}
		}
		catch (Exception e) {
			if (e instanceof InvalidDescriptionException ||
				e instanceof InvalidMoneyFormatException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				_renderPage(
					actionRequest, actionResponse, "/html/edit_expense.jsp");
			}
			else {
				throw e;
			}
		}
	}

	public void updateProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

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
				ProjectLocalServiceUtil.addProject(
					userId, description, endDateMonth, endDateDay, endDateYear,
					startDateMonth, startDateDay, startDateYear, name, wage);
			}
			else {
				ProjectLocalServiceUtil.updateProject(
					projectId, userId, description, endDateMonth, endDateDay,
					endDateYear, startDateMonth, startDateDay, startDateYear,
					name, wage);
			}
		} catch (Exception e) {
			if (e instanceof InvalidNameException ||
				e instanceof InvalidDescriptionException ||
				e instanceof InvalidMoneyFormatException ||
				e instanceof InvalidDatesException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				_renderPage(
					actionRequest, actionResponse, "/html/edit_project.jsp");
			}
			else {
				throw e;
			}
		}
	}

	public void updateTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

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
				TaskLocalServiceUtil.addTask(
					projectId, name, type, startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute, endDateMonth,
					endDateDay, endDateYear, endDateHour, endDateMinute);
			}
			else {
				TaskLocalServiceUtil.updateTask(
					taskId, projectId, name, type, startDateMonth, startDateDay,
					startDateYear, startDateHour, startDateMinute, endDateMonth,
					endDateDay, endDateYear, endDateHour, endDateMinute);
			}
		}
		catch (Exception e) {
			if (e instanceof InvalidNameException ||
				e instanceof InvalidDatesException) {

				SessionErrors.add(actionRequest, e.getClass().getName());

				_renderPage(
					actionRequest, actionResponse, "/html/edit_task.jsp");
			}
			else {
				throw e;
			}
		}
	}

	private void _renderPage(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String page) {

		PortalUtil.copyRequestParameters(actionRequest, actionResponse);

		actionResponse.setRenderParameter("jspPage", page);
	}

}