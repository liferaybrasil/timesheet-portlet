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

List<Expense> expenses = ExpenseLocalServiceUtil.getExpenseByProjectId(projectId);

int count = expenses.size();
%>

<c:if test="<%= TimesheetPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_EXPENSE) %>">
	<aui:button-row>
		<portlet:renderURL var="addExpenseURL">
			<portlet:param name="jspPage" value="/html/edit_expense.jsp" />
			<portlet:param name="projectId" value="<%= String.valueOf(projectId) %>" />
			<portlet:param name="redirect" value="<%= HtmlUtil.escapeHREF(currentUrl) %>" />
		</portlet:renderURL>

		<aui:button value="add-expense" onClick="<%= addExpenseURL.toString() %>" />
	</aui:button-row>
</c:if>

<liferay-ui:search-container
	emptyResultsMessage="no-entries-were-found">
	<liferay-ui:search-container-results results="<%= expenses %>"
		total="<%= count %>" />

	<liferay-ui:search-container-row
		className="com.liferay.timesheet.model.Expense"
		keyProperty="expenseId" modelVar="expense">
		<liferay-ui:search-container-column-text name="description" property="description" />

		<liferay-ui:search-container-column-text name="responsible"
			value='<%= PortalUtil.getUserName(expense.getUserId(), "none") %>' />

		<liferay-ui:search-container-column-text name="purchasedDate"
			value="<%= format.format(expense.getPurchasedDate()) %>" />

		<liferay-ui:search-container-column-text name="value"
			value="<%= currencyFormat.format(expense.getValue()) %>" />

		<liferay-ui:search-container-column-text name="typeDescription" translate="<%= true %>"
			property="typeDescription" />

		<liferay-ui:search-container-column-jsp align="right"
			path="/html/expense_actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>