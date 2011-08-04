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

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ include file="/html/init.jsp" %>

<liferay-portlet:renderURL var="portletURL" />

<%
String redirect = PortalUtil.getCurrentURL(renderRequest);
long projectId = ParamUtil.getLong(request, "projectId");
Project project = ProjectLocalServiceUtil.getProject(projectId);

String tabURL = ParamUtil.getString(request, "tab", "/html/view_tasks.jsp");
System.out.println(tabURL);
String tabNames = "Tasks,Expenses";
String tabVal = "/html/view_tasks.jsp,/html/view_expenses.jsp";
%>

<liferay-ui:header backURL="<%= redirect %>"
	title='<%= project.getName() %>' />

<liferay-ui:message key="responsible"></liferay-ui:message>
:
<%= PortalUtil.getUserName(project.getUserId(), "default") %>
<br />

<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total-expenses", String.valueOf(project.getTotalExpenseCost())) %>'></liferay-ui:message>
<br />
<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total-tasks", String.valueOf(project.getTotalTaskCost())) %>'></liferay-ui:message>
<br />
<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total-project", String.valueOf(project.getTotalProjectCost())) %>'></liferay-ui:message>

<liferay-ui:header backLabel=""
	title='<%= LanguageUtil.format(pageContext, "details-resources", "") %>' />



<liferay-ui:tabs names="<%= tabNames %>" tabsValues="<%= tabVal %>" refresh="<%= false %>"
	param="tab" url="<%= portletURL %>" />

<c:import url="<%= tabURL %>"></c:import>