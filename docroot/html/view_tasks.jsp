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
	long pId = ParamUtil.getLong(request, "projectId");
	String urlViewTasks = PortalUtil.getCurrentURL(renderRequest);
	List<Task> tasks = TaskLocalServiceUtil.getTaskByProjectId(pId);
	int count = tasks.size();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
%>

<aui:button-row>
	<portlet:renderURL var="addTaskURL">
		<portlet:param name="jspPage" value="/html/edit_task.jsp" />
		<portlet:param name="projectId" value="<%= String.valueOf(pId) %>" />
		<portlet:param name="redirect" value="<%= HtmlUtil.escapeHREF(urlViewTasks) %>" />
	</portlet:renderURL>

	<aui:button value="add-task" onClick="<%= addTaskURL.toString() %>" />
</aui:button-row>

<liferay-ui:search-container
	emptyResultsMessage="task-empty-results-message">
	<liferay-ui:search-container-results results="<%= tasks %>"
		total="<%= count %>" />

	<liferay-ui:search-container-row
		className="com.liferay.timesheet.model.Task"
		keyProperty="taskId" modelVar="task">
		<liferay-ui:search-container-column-text name="name" property="name" />

		<liferay-ui:search-container-column-text name="startDate"
			value="<%= sdf.format(task.getStartDate()) %>" />

		<liferay-ui:search-container-column-text name="endDate"
			value="<%= sdf.format(task.getEndDate()) %>" />

		<liferay-ui:search-container-column-text name="typeDescription"
			property="typeDescription" />

		<liferay-ui:search-container-column-jsp align="right"
			path="/html/task_actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>