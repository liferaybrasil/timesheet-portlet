<%--
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
--%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ page import="com.liferay.portal.kernel.util.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Currency" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.Format" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.PortletRequest" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@ page import="com.liferay.portal.kernel.exception.SystemException" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.service.permission.PortalPermissionUtil" %>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil" %>
<%@ page import="com.liferay.portal.service.UserServiceUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.timesheet.InvalidDatesException" %>
<%@ page import="com.liferay.timesheet.InvalidDescriptionException" %>
<%@ page import="com.liferay.timesheet.InvalidCurrencyFormatException" %>
<%@ page import="com.liferay.timesheet.InvalidNameException" %>
<%@ page import="com.liferay.timesheet.model.Expense" %>
<%@ page import="com.liferay.timesheet.model.Project" %>
<%@ page import="com.liferay.timesheet.model.Task" %>
<%@ page import="com.liferay.timesheet.search.ProjectDisplayTerms" %>
<%@ page import="com.liferay.timesheet.search.ProjectSearch" %>
<%@ page import="com.liferay.timesheet.search.ProjectSearchTerms" %>
<%@ page import="com.liferay.timesheet.service.ExpenseLocalServiceUtil" %>
<%@ page import="com.liferay.timesheet.service.ProjectLocalServiceUtil" %>
<%@ page import="com.liferay.timesheet.service.TaskLocalServiceUtil" %>
<%@ page import="com.liferay.timesheet.service.permission.TimesheetPermission" %>
<%@ page import="com.liferay.timesheet.service.permission.TimesheetExpensePermission" %>
<%@ page import="com.liferay.timesheet.service.permission.TimesheetProjectPermission" %>
<%@ page import="com.liferay.timesheet.service.permission.TimesheetTaskPermission" %>
<%@ page import="com.liferay.timesheet.util.PortletPropsValues" %>
<%@ page import="com.liferay.timesheet.util.ActionKeys" %>

<portlet:defineObjects/>
<liferay-theme:defineObjects />

<%
Currency currency = Currency.getInstance(locale);

NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

currencyFormat.setCurrency(currency);

Format format = FastDateFormatFactoryUtil.getSimpleDateFormat("dd-MM-yyyy", locale);
Format formatTask = FastDateFormatFactoryUtil.getSimpleDateFormat("dd-MM-yyyy HH:mm", locale);
%>