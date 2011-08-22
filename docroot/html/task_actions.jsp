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

Task task = (Task)row.getObject();

long projectId = ParamUtil.getLong(request, "projectId");

String name = Task.class.getName();

long taskId = task.getTaskId();

String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= TimesheetTaskPermission.contains(permissionChecker, taskId, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/html/edit_task.jsp" />
			<portlet:param name="taskId" value="<%= String.valueOf(taskId) %>" />
			<portlet:param name="projectId" value="<%= String.valueOf(projectId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%=editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= TimesheetTaskPermission.contains(permissionChecker, taskId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Task.class.getName() %>"
			modelResourceDescription="<%= task.getName() %>"
			resourcePrimKey="<%= String.valueOf(taskId) %>"
			var="entryURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= entryURL %>" />
	</c:if>

	<c:if test="<%= TimesheetTaskPermission.contains(permissionChecker, taskId, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteTask" var="deleteURL">
			<portlet:param name="taskId" value="<%= String.valueOf(taskId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>