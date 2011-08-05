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

<%@page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="/html/init.jsp"%>

<%
String redirect = PortalUtil.getCurrentURL(renderRequest);
long projectId = ParamUtil.getLong(request, "projectId");
Project project = ProjectLocalServiceUtil.getProject(projectId);

String tabs1Names = "tasks,expenses";
String[] tabs1NamesArray = StringUtil.split(tabs1Names);
String tabs1Default = tabs1NamesArray[0];

String tabs1 = ParamUtil.getString(request, "tabs1", tabs1Default);
PortletURL tabs1URL = renderResponse.createRenderURL();
tabs1URL.setParameter("tabs1", tabs1);

tabs1URL.setParameter("tasks", "");
tabs1URL.setParameter("expenses", "");

%>

<liferay-ui:header backURL="<%= redirect %>"
	title='<%= project.getName() %>' />

<liferay-ui:message key="responsible"></liferay-ui:message>
:
<%= PortalUtil.getUserName(project.getUserId(), "default") %>
<br />

<liferay-ui:message
	key='<%= LanguageUtil.format(pageContext, "total-expenses", String.valueOf(project.getTotalExpenseCost())) %>'></liferay-ui:message>
<br />
<liferay-ui:message
	key='<%= LanguageUtil.format(pageContext, "total-tasks", String.valueOf(project.getTotalTaskCost())) %>'></liferay-ui:message>
<br />
<liferay-ui:message
	key='<%= LanguageUtil.format(pageContext, "total-project", String.valueOf(project.getTotalProjectCost())) %>'></liferay-ui:message>

<liferay-ui:header showBackURL="<%= false %>"
	title='<%= LanguageUtil.format(pageContext, "details-resources", "") %>' />

<liferay-ui:tabs names="<%= tabs1Names %>"
	url="<%= tabs1URL.toString() %>" value="<%= tabs1 %>" />
	
<c:choose>
	<c:when test='<%= tabs1.equals("tasks") %>'>
		<%@ include file="/html/view_tasks.jsp"%>
	</c:when>
	<c:when test='<%= tabs1.equals("expenses") %>'>
		<%@ include file="/html/view_expenses.jsp"%>
	</c:when>
</c:choose>