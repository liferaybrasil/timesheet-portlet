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
long projectId = ParamUtil.getLong(request, "projectId");

String currentUrl = PortalUtil.getCurrentURL(renderRequest);

List<Task> tasks = TaskLocalServiceUtil.getTaskByProjectId(projectId);

int count = tasks.size();
%>

<c:if test="<%= TimesheetPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_TASK) %>">
	<aui:button-row>
		<portlet:renderURL var="addTaskURL">
			<portlet:param name="jspPage" value="/html/edit_task.jsp" />
			<portlet:param name="projectId" value="<%= String.valueOf(projectId) %>" />
			<portlet:param name="redirect" value="<%= HtmlUtil.escapeHREF(currentUrl) %>" />
		</portlet:renderURL>

		<aui:button value="add-task" onClick="<%= addTaskURL.toString() %>" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="no-entries-were-found">
	<liferay-ui:search-container-results results="<%= tasks %>"
		total="<%= count %>" />

	<liferay-ui:search-container-row
		className="com.liferay.timesheet.model.Task"
		keyProperty="taskId" modelVar="task">
		<liferay-ui:search-container-column-text name="name" property="name" />

		<liferay-ui:search-container-column-text name="responsible"
			value='<%= PortalUtil.getUserName(task.getUserId(), "none") %>' />

		<liferay-ui:search-container-column-text name="startDate"
			value="<%= formatTask.format(task.getStartDate()) %>" />

		<liferay-ui:search-container-column-text name="endDate"
			value="<%= formatTask.format(task.getEndDate()) %>" />

		<liferay-ui:search-container-column-text name="typeDescription"
			property="typeDescription" translate="<%= true %>" />

		<liferay-ui:search-container-column-jsp align="right"
			path="/html/task_actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>