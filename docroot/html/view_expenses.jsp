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
	String urlViewExpenses = PortalUtil.getCurrentURL(renderRequest);
	long pId = ParamUtil.getLong(request, "projectId");
	List<Expense> expenses = ExpenseLocalServiceUtil.getExpenseByProjectId(pId);
	int count = expenses.size();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
%>

<aui:button-row>
	<portlet:renderURL var="addExpenseURL">
		<portlet:param name="jspPage" value="/html/edit_expense.jsp" />
		<portlet:param name="projectId" value="<%= String.valueOf(pId) %>" />
		<portlet:param name="redirect" value="<%= HtmlUtil.escapeHREF(urlViewExpenses) %>" />
	</portlet:renderURL>

	<aui:button value="add-expense" onClick="<%= addExpenseURL.toString() %>" />
</aui:button-row>

<liferay-ui:search-container
	emptyResultsMessage="expense-empty-results-message">
	<liferay-ui:search-container-results results="<%= expenses %>"
		total="<%= count %>" />

	<liferay-ui:search-container-row
		className="com.liferay.timesheet.model.Expense"
		keyProperty="expenseId" modelVar="expense">
		<liferay-ui:search-container-column-text name="description" property="description" />

		<liferay-ui:search-container-column-text name="value" property="value" />

		<liferay-ui:search-container-column-text name="billedDate"
			value="<%= sdf.format(expense.getBilledDate()) %>" />

		<liferay-ui:search-container-column-text name="typeDescription"
			property="typeDescription" />

		<liferay-ui:search-container-column-jsp align="right"
			path="/html/expense_actions.jsp" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>