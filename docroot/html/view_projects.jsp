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

<%@ include file="/html/init.jsp"%>

<%
	String redirect = PortalUtil.getCurrentURL(renderRequest);
	int count = ProjectLocalServiceUtil.getProjectsCount();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-util:include page="/html/toolbar.jsp"
	servletContext="<%= application %>" />

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden"
		value="<%= portletURL.toString() %>" />

	<liferay-ui:search-container
		searchContainer="<%= new ProjectSearch(renderRequest, portletURL) %>">

		<%
			ProjectDisplayTerms displayTerms = (ProjectDisplayTerms) searchContainer.getDisplayTerms();
			ProjectSearchTerms searchTerms = (ProjectSearchTerms) searchContainer.getSearchTerms();
		%>

		<liferay-util:include page="/html/project_search.jsp"
			servletContext="<%= application %>" />

		<liferay-ui:search-container-results>
			<%@ include file="/html/project_search_results.jspf"%>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.timesheet.model.Project"
			keyProperty="projectId" escapedModel="<%= true %>" modelVar="project">

			<portlet:renderURL var="rowURL">
				<portlet:param name="jspPage" value="/html/project_details.jsp" />
				<portlet:param name="redirect"
					value="<%= searchContainer.getIteratorURL().toString() %>" />
				<portlet:param name="projectId"
					value="<%= String.valueOf(project.getProjectId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-row-parameter name="project"
				value="<%= project %>" />

			<liferay-ui:search-container-column-text name="name" property="name"
				href="<%= rowURL %>" />

			<liferay-ui:search-container-column-text name="responsible"
				href="<%= rowURL %>"
				value='<%= PortalUtil.getUserName(project.getUserId(), "default") %>' />

			<liferay-ui:search-container-column-text name="description"
				href="<%= rowURL %>" property="description" />

			<liferay-ui:search-container-column-text name="startDate"
				href="<%= rowURL %>"
				value="<%= sdf.format(project.getStartDate()) %>" />

			<liferay-ui:search-container-column-text name="endDate"
				href="<%= rowURL %>" value="<%= sdf.format(project.getEndDate()) %>" />

			<liferay-ui:search-container-column-jsp align="center"
				path="/html/project_actions.jsp" />
		</liferay-ui:search-container-row>

		<div class="separator">
			<!-- -->
		</div>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>