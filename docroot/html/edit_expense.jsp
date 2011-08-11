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
<%@ page import="com.liferay.portal.kernel.util.StringPool"%>
<%@ page import="com.liferay.timesheet.util.PortletPropsValues"%>
<%@ include file="/html/init.jsp"%>

<%
String redirect = ParamUtil.getString(request, "redirect");
long projectId = ParamUtil.getLong(request, "projectId");
long expenseId = ParamUtil.getLong(request, "expenseId");
Project project = ProjectLocalServiceUtil.getProject(projectId);
Expense expense = null;
Calendar date = Calendar.getInstance();
if (expenseId > 0) {
	expense = ExpenseLocalServiceUtil.getExpense(expenseId);
	date = CalendarFactoryUtil.getCalendar(expense.getPurchasedDate()
			.getYear() + 1900, expense.getPurchasedDate().getMonth(),
			expense.getPurchasedDate().getDate());
}
StringBuilder sb = new StringBuilder();
%>

<liferay-ui:error exception="<%= InvalidDescriptionException.class %>"
	message="please-enter-a-valid-description" />
<liferay-ui:error exception="<%= InvalidMoneyFormatException.class %>"
	message="please-enter-a-valid-money-format" />

<liferay-ui:header backURL="<%= redirect %>" localizeTitle="<%= expense == null %>"
	title='<%= (expense != null) ? expense.getDescription() : "new-expense" %>' />

<aui:model-context bean="<%= expense %>" model="<%= Expense.class %>" />

<portlet:actionURL name="updateExpense" var="editExpenseURL" />

<aui:form action="<%= editExpenseURL %>" method="POST" name="fm"
	enctype="multipart/form-data">
	<aui:fieldset>
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />

		<aui:input type="hidden" name="expenseId" value="<%= expenseId %>" />

		<aui:input type="hidden" name="fileEntryId"
			value="<%= (expense != null) ? expense.getFileEntryId() : 0  %>" />

		<aui:input type="hidden" name="projectId" value='<%= projectId %>' />

		<aui:input name="description" />

		<aui:input name="value" />

		<label class="aui-field-label"> date </label>
		<liferay-ui:input-date yearRangeEnd="2050" yearRangeStart="1980"
			dayValue="<%=date.get(Calendar.DAY_OF_MONTH) %>"
			dayParam="purchasedDateDay" monthParam="purchasedDateMonth"
			monthValue="<%= date.get(Calendar.MONTH) %>"
			yearParam="purchasedDateYear"
			yearValue="<%= date.get(Calendar.YEAR) %>" />
		<br />

		<aui:select label="type" name="type">
			<%
				String[] types = PortletPropsValues.EXPENSE_TYPES;
				for (int i = 0; i < types.length; i++) {
			%>
			<aui:option selected="<%= i==0 %>" value="<%= i %>">
				<liferay-ui:message key="<%= types[i] %>" />
			</aui:option>
			<%
				}
			%>
		</aui:select>
		<c:if test="<%= expense != null && expense.getFileEntryId() > 0 %>">
			<aui:a
				href="<%= 	sb.append(PortalUtil.getCDNHost())
							.append(StringPool.FORWARD_SLASH)
							.append(expense.getFilePath())
							.toString()  %>"><%= expense.getFileName() %></aui:a>
		</c:if>
		<aui:input name="file" type="file" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel" onClick="<%= redirect %>" />
	</aui:button-row>
</aui:form>