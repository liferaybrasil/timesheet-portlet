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
String currentUrl = PortalUtil.getCurrentURL(renderRequest);

String redirect = ParamUtil.getString(request, "redirect");

Project project = null;

long projectId = ParamUtil.getLong(request, "projectId");

Calendar startCal = Calendar.getInstance();
Calendar endCal = Calendar.getInstance();

Date startDate = DateUtil.newDate();
Date endDate = DateUtil.newDate();

if (projectId > 0) {
	project = ProjectLocalServiceUtil.getProject(projectId);
	startDate = project.getStartDate();
	endDate = project.getEndDate();
}

startCal.setTime(startDate);
endCal.setTime(endDate);
%>

<liferay-util:include page="/html/toolbar.jsp" servletContext="<%= application %>" />

<liferay-ui:error exception="<%= InvalidNameException.class %>" message="please-enter-a-valid-name" />

<liferay-ui:error exception="<%= InvalidDescriptionException.class %>" message="please-enter-a-valid-description" />

<liferay-ui:error exception="<%= InvalidCurrencyFormatException.class %>" message="please-enter-a-valid-currency-format" />

<liferay-ui:error exception="<%= InvalidDatesException.class %>" message="please-enter-a-valid-period-date" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<liferay-ui:header backURL="<%= redirect %>" localizeTitle="<%= project == null %>"
	title='<%= (project != null) ? project.getName() : "new-project" %>' />

<aui:model-context bean="<%= project %>" model="<%= Project.class %>" />

<portlet:actionURL name="updateProject" var="editProjectURL" />

<aui:form action="<%= editProjectURL %>" method="POST" name="fm">
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />
		<aui:input type="hidden" name="userId" value="<%= request.getRemoteUser() %>" />
		<aui:input type="hidden" name="projectId" value='<%= project == null ? "" : project.getProjectId() %>' />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input name="wage" />

		<aui:input name="description" />

		<label class="aui-field-label"> <liferay-ui:message key="startDate" /> </label>

		<liferay-ui:input-date yearRangeEnd="2050" yearRangeStart="1980"
			dayParam="startDateDay"
			dayValue="<%= startCal.get(Calendar.DAY_OF_MONTH) %>"
			monthParam="startDateMonth"
			monthValue="<%= startCal.get(Calendar.MONTH) %>"
			yearParam="startDateYear"
			yearValue="<%= startCal.get(Calendar.YEAR) %>"
		/>

		<br />

		<br />

		<label class="aui-field-label"> <liferay-ui:message key="endDate" /> </label>

		<liferay-ui:input-date yearRangeEnd="2050" yearRangeStart="1980"
			dayParam="endDateDay"
			dayValue="<%= endCal.get(Calendar.DAY_OF_MONTH) %>"
			monthParam="endDateMonth"
			monthValue="<%= endCal.get(Calendar.MONTH) %>"
			yearParam="endDateYear"
			yearValue="<%= endCal.get(Calendar.YEAR) %>"
		/>

		<br />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel" onClick="<%= redirect %>" />
	</aui:button-row>
</aui:form>