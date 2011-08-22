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
String redirect = ParamUtil.getString(request, "redirect");

long projectId = ParamUtil.getLong(request, "projectId");

long taskId = ParamUtil.getLong(request, "taskId");

Project project = ProjectLocalServiceUtil.getProject(projectId);

Task task = null;
if (taskId > 0) {
	task = TaskLocalServiceUtil.getTask(taskId);
}
%>
<liferay-ui:error exception="<%= InvalidNameException.class %>" message="please-enter-a-valid-name" />

<liferay-ui:error exception="<%= InvalidDatesException.class %>" message="please-enter-a-valid-period-date" />

<liferay-ui:header backURL="<%= redirect %>" localizeTitle="<%= task == null %>"
	title='<%= (task != null) ? task.getName() : "new-task" %>' />

<c:if test="<%= task != null %>">
	<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "responsible-x", PortalUtil.getUserName(task.getUserId(), "none")) %>' />
</c:if>

<aui:model-context bean="<%= task %>" model="<%= Task.class %>" />

<portlet:actionURL name="updateTask" var="editTasktURL" />

<aui:form action="<%= editTasktURL %>" method="POST" name="fm">
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />
		<aui:input type="hidden" name="projectId" value='<%= project.getProjectId() %>' />
		<aui:input type="hidden" name="taskId" value="<%= taskId %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input name="startDate" />

		<aui:input name="endDate" />

		<aui:select label="type" name="type">

			<%
				String[] types = PortletPropsValues.TASK_TYPES;
				for (int i = 0; i < types.length; i++) {
			%>

			<aui:option selected="<%= i==0 %>" value="<%= i %>">

				<liferay-ui:message key="<%= types[i] %>" />

			</aui:option>

			<%
				}
			%>
		</aui:select>

	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel" onClick="<%= redirect %>" />
	</aui:button-row>
</aui:form>