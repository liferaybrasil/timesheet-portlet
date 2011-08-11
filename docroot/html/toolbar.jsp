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
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ include file="/html/init.jsp" %>

<%
String currentURL = PortalUtil.getCurrentURL(renderRequest);
String toolbarItem = ParamUtil.getString(request, "toolbarItem", "view-all");
%>

<div class="lfr-portlet-toolbar">
	<portlet:renderURL var="viewEntriesURL">
		<portlet:param name="jspPage" value="/html/view_projects.jsp" />
		<portlet:param name="toolbarItem" value="view-all" />
	</portlet:renderURL>

	<span
		class="lfr-toolbar-button view-button <%= toolbarItem.equals("view-all") ? "current" :  StringPool.BLANK %>">
		<a href="<%= viewEntriesURL %>"><liferay-ui:message key="view-all" />
	</a> </span>


	<portlet:renderURL var="addProjetcURL">
		<portlet:param name="jspPage" value="/html/edit_project.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="toolbarItem" value="add" />
	</portlet:renderURL>

	<span
		class="lfr-toolbar-button add-button <%= toolbarItem.equals("add") ? "current" : StringPool.BLANK %>">
		<a href="<%= addProjetcURL %>"><liferay-ui:message key="add-project" />	</a>
	</span>

</div>