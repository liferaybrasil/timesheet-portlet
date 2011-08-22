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

Expense expense = (Expense)row.getObject();

long projectId = ParamUtil.getLong(request, "projectId");

String name = Expense.class.getName();

long expenseId = expense.getExpenseId();

String redirect = PortalUtil.getCurrentURL(renderRequest);
%>

<liferay-ui:icon-menu>
	<c:if test="<%= TimesheetExpensePermission.contains(permissionChecker, expenseId, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="jspPage" value="/html/edit_expense.jsp" />
			<portlet:param name="expenseId" value="<%= String.valueOf(expenseId) %>" />
			<portlet:param name="projectId" value="<%= String.valueOf(projectId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%=editURL.toString() %>"
		/>
	</c:if>

	<c:if test="<%= TimesheetExpensePermission.contains(permissionChecker, expenseId, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Expense.class.getName() %>"
			modelResourceDescription="<%= expense.getDescription() %>"
			resourcePrimKey="<%= String.valueOf(expenseId) %>"
			var="entryURL"
		/>

		<liferay-ui:icon image="permissions" url="<%= entryURL %>" />
	</c:if>

	<c:if test="<%= TimesheetExpensePermission.contains(permissionChecker, expenseId, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteExpense" var="deleteURL">
			<portlet:param name="expenseId" value="<%= String.valueOf(expenseId) %>" />
			<portlet:param name="redirect" value="<%= redirect %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL.toString() %>" />
	</c:if>
</liferay-ui:icon-menu>