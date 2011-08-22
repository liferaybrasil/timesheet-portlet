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

long expenseId = ParamUtil.getLong(request, "expenseId");

Project project = ProjectLocalServiceUtil.getProject(projectId);

StringBuilder sb = new StringBuilder();

Calendar cal = Calendar.getInstance();
Date date = DateUtil.newDate();

Expense expense = null;

if (expenseId > 0) {
	expense = ExpenseLocalServiceUtil.getExpense(expenseId);
	date = expense.getPurchasedDate();
}

cal.setTime(date);
%>

<liferay-ui:error exception="<%= InvalidDescriptionException.class %>" message="please-enter-a-valid-description" />

<liferay-ui:error exception="<%= InvalidCurrencyFormatException.class %>" message="please-enter-a-valid-currency-format" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<liferay-ui:header backURL="<%= redirect %>" localizeTitle="<%= expense == null %>"
	title='<%= (expense != null) ? expense.getDescription() : "new-expense" %>' />

<c:if test="<%= expense != null %>">
	<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "responsible-x", PortalUtil.getUserName(expense.getUserId(), "none")) %>' />
</c:if>

<aui:model-context bean="<%= expense %>" model="<%= Expense.class %>" />

<portlet:actionURL name="updateExpense" var="editExpenseURL" />

<aui:form action="<%= editExpenseURL %>" method="POST" name="fm" enctype="multipart/form-data">
		<aui:input type="hidden" name="redirect" value="<%= redirect %>" />
		<aui:input type="hidden" name="expenseId" value="<%= expenseId %>" />
		<aui:input type="hidden" name="fileEntryId" value="<%= (expense != null) ? expense.getFileEntryId() : 0  %>" />
		<aui:input type="hidden" name="projectId" value='<%= projectId %>' />

	<aui:fieldset>
		<aui:input name="description" />

		<aui:input name="value" />

		<label class="aui-field-label"> <liferay-ui:message key="date" /> </label>

		<liferay-ui:input-date yearRangeEnd="2050" yearRangeStart="1980"
			dayParam="purchasedDateDay"
			dayValue="<%= cal.get(Calendar.DAY_OF_MONTH) %>"
			monthParam="purchasedDateMonth"
			monthValue="<%= cal.get(Calendar.MONTH) %>"
			yearParam="purchasedDateYear"
			yearValue="<%= cal.get(Calendar.YEAR) %>"
		/>

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
			<aui:a href="<%= sb.append(PortalUtil.getCDNHost(false))
							.append(StringPool.FORWARD_SLASH)
							.append(expense.getFilePath())
							.toString()  %>"><%= expense.getFileName() %>
			</aui:a>
		</c:if>

		<aui:input name="file" type="file" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button type="cancel" onClick="<%= redirect %>" />
	</aui:button-row>
</aui:form>