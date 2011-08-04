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

<%@ page import="com.liferay.portal.service.UserServiceUtil" %>
<%@ include file="/html/init.jsp" %>

<%
	String redirect = PortalUtil.getCurrentURL(renderRequest);
	int count = ProjectLocalServiceUtil.getProjectsCount();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
%>

<aui:button-row>
	<portlet:renderURL var="addProjectURL">
		<portlet:param name="jspPage" value="/html/edit_project.jsp" />
		<portlet:param name="redirect" value="<%= redirect %>" />
	</portlet:renderURL>

	<aui:button value="add-project"
		onClick="<%= addProjectURL.toString() %>" />
</aui:button-row>

<liferay-ui:search-container
	emptyResultsMessage="project-empty-results-message">

	<liferay-ui:search-container-results
		results="<%= ProjectLocalServiceUtil.getProjects(searchContainer.getStart(), searchContainer.getEnd()) %>"
		total="<%= count %>" />

	<liferay-ui:search-container-row
		className="com.liferay.timesheet.model.Project"
		keyProperty="projectId" modelVar="project">

		<liferay-ui:search-container-row-parameter name="project"
			value="<%= project %>" />

		<portlet:renderURL var="rowProjectDetailsURL">
			<portlet:param name="jspPage" value="/html/project_details.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="projectId"
				value="<%= String.valueOf(project.getProjectId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text name="name" property="name" href="<%= rowProjectDetailsURL %>" />

		<liferay-ui:search-container-column-text name="responsible" href="<%= rowProjectDetailsURL %>"
			value='<%= PortalUtil.getUserName(project.getUserId(), "default") %>' />

		<liferay-ui:search-container-column-text name="description" href="<%= rowProjectDetailsURL %>"
			property="description" />

		<liferay-ui:search-container-column-text name="startDate" href="<%= rowProjectDetailsURL %>"
			value="<%= sdf.format(project.getStartDate()) %>" />

		<liferay-ui:search-container-column-text name="endDate" href="<%= rowProjectDetailsURL %>"
			value="<%= sdf.format(project.getEndDate()) %>" />

		<liferay-ui:search-container-column-jsp align="center"
			path="/html/project_actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>