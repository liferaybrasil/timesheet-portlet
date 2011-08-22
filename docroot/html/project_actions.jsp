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

<%@ include file="/html/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Project project = (Project)row.getObject();

String name = Project.class.getName();

long projectId = project.getProjectId();

String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= TimesheetProjectPermission.contains(permissionChecker, projectId, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/html/edit_project.jsp" />
			<portlet:param name="projectId" value="<%= String.valueOf(projectId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%=editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= TimesheetProjectPermission.contains(permissionChecker, projectId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Project.class.getName() %>"
			modelResourceDescription="<%= project.getName() %>"
			resourcePrimKey="<%= String.valueOf(projectId) %>"
			var="entryURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= entryURL %>" />
	</c:if>

	<c:if test="<%= TimesheetProjectPermission.contains(permissionChecker, projectId, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteProject" var="deleteURL">
			<portlet:param name="projectId" value="<%= String.valueOf(projectId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>