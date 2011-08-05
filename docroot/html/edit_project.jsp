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
<%@ page import="com.liferay.timesheet.InvalidNameException" %>
<%@ page import="com.liferay.timesheet.InvalidDescriptionException" %>
<%@ page import="com.liferay.timesheet.InvalidMoneyFormatException" %>
<%@ page import="com.liferay.timesheet.InvalidDatesException" %>

<%@ include file="/html/init.jsp" %>

<%
	String currentUrl = PortalUtil.getCurrentURL(renderRequest);
	String redirect = ParamUtil.getString(request, "redirect");
	Project project = null;
	long projectId = ParamUtil.getLong(request, "projectId");
	Calendar startDate = Calendar.getInstance();
	Calendar endDate = Calendar.getInstance();
	if (projectId > 0) {
		project = ProjectLocalServiceUtil.getProject(projectId);
		startDate = CalendarFactoryUtil.getCalendar(project
				.getStartDate().getYear() + 1900, project
				.getStartDate().getMonth(), project.getStartDate()
				.getDate());

		endDate = CalendarFactoryUtil.getCalendar(project.getEndDate()
				.getYear() + 1900, project.getEndDate().getMonth(),
				project.getEndDate().getDate());
	}
%>
<liferay-ui:error exception="<%= InvalidNameException.class %>"
	message="please-enter-a-valid-name" />
<liferay-ui:error exception="<%= InvalidDescriptionException.class %>"
	message="please-enter-a-valid-description" />
<liferay-ui:error exception="<%= InvalidMoneyFormatException.class %>"
	message="please-enter-a-valid-money" />
<liferay-ui:error exception="<%= InvalidDatesException.class %>"
	message="please-enter-a-valid-period-date" />

<liferay-ui:header backURL="<%= redirect %>"
	title='<%= (project != null) ? project.getName() : "new-project" %>' />

<aui:model-context bean="<%= project %>" model="<%= Project.class %>" />

<portlet:actionURL name="updateProject" var="editProjectURL" />

<aui:form action="<%= editProjectURL %>" method="POST" name="fm">
	<aui:fieldset>
		<aui:input type="hidden" name="currentUrl" value="<%= currentUrl %>" />

		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />

		<aui:input type="hidden" name="userId"
			value="<%= request.getRemoteUser() %>" />

		<aui:input type="hidden" name="projectId"
			value='<%= project == null ? "" : project.getProjectId() %>' />

		<aui:input name="name" />

		<aui:input name="wage" />

		<aui:input name="description" />

		<label class="aui-field-label"> <liferay-ui:message
				key="startDate" /> </label>
		<liferay-ui:input-date yearRangeEnd="2050" yearRangeStart="1980"
			dayValue="<%=startDate.get(Calendar.DAY_OF_MONTH) %>"
			dayParam="startDateDay" monthParam="startDateMonth"
			monthValue="<%= startDate.get(Calendar.MONTH) %>"
			yearParam="startDateYear"
			yearValue="<%= startDate.get(Calendar.YEAR) %>" />
		<br />
		<br />
		<label class="aui-field-label"> <liferay-ui:message
				key="endDate" /> </label>
		<liferay-ui:input-date yearRangeEnd="2050" yearRangeStart="1980"
			dayValue="<%= endDate.get(Calendar.DAY_OF_MONTH) %>"
			dayParam="endDateDay" monthParam="endDateMonth"
			monthValue="<%=endDate.get(Calendar.MONTH) %>"
			yearParam="endDateYear" yearValue="<%=endDate.get(Calendar.YEAR) %>" />
		<br />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
		<aui:button type="cancel" onClick="<%= redirect %>" />
	</aui:button-row>
</aui:form>