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

Project project = ProjectLocalServiceUtil.getProject(projectId);

String tabs1 = ParamUtil.getString(request, "tabs1", LanguageUtil.get(locale, "task"));

String tabNames = LanguageUtil.get(locale, "task") + StringPool.COMMA + LanguageUtil.get(locale, "expense");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("jspPage", "/html/project_details.jsp");
portletURL.setParameter("projectId", String.valueOf(projectId));
portletURL.setParameter("redirect", redirect);
%>

<liferay-util:include page="/html/toolbar.jsp" servletContext="<%= application %>" />

<liferay-ui:header backURL="<%= redirect %>" title='<%= project.getName() %>' />

<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "responsible-x", PortalUtil.getUserName(project.getUserId(), "none")) %>' />

<br />

<liferay-ui:message	key='<%= LanguageUtil.format(pageContext, "total-tasks", String.valueOf(currencyFormat.format(project.getTotalTaskCost()))) %>' />

<br />

<liferay-ui:message	key='<%= LanguageUtil.format(pageContext, "total-expenses", String.valueOf(currencyFormat.format(project.getTotalExpenseCost()))) %>' />

<br />

<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total-project", String.valueOf(currencyFormat.format(project.getTotalProjectCost()))) %>' />

<br />

<div class="lfr-portlet-timesheet-div-icon" onclick="<portlet:namespace />showReport();">
	<liferay-ui:icon
		image="all_pages"
		message="report"
		url='<%= "javascript:" + renderResponse.getNamespace() + "showReport();" %>'
		cssClass="lfr-portlet-timesheet-report-icon"
		label="report"
	/>
	<%= LanguageUtil.get(locale, "Report") %>
</div>

<liferay-ui:header showBackURL="<%= false %>" title='<%= LanguageUtil.get(pageContext, "details-resources") %>' />

<liferay-portlet:actionURL portletConfiguration="true"var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<liferay-ui:tabs names="<%= tabNames %>" param="tabs1" url="<%= portletURL.toString() %>" />

	<c:choose>
		<c:when test='<%= tabs1.equals(LanguageUtil.get(locale, "task")) %>'>
			<liferay-util:include page="/html/view_tasks.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/html/view_expenses.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</aui:form>

<div class="aui-helper-hidden" id="<portlet:namespace />report">
	<liferay-ui:header showBackURL="<%= false %>" title='<%= project.getName() %>' />

	<div class="lfr-portlet-timesheet-total-label lfr-portlet-timesheet-total-label-left">
		<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "responsible-x", PortalUtil.getUserName(project.getUserId(), "none")) %>' />
	</div>

	<br />

	<liferay-ui:header showBackURL="<%= false %>" title='<%= LanguageUtil.get(pageContext, "tasks") %>' />

	<liferay-ui:search-container emptyResultsMessage="no-data">
		<liferay-ui:search-container-results
			results="<%= TaskLocalServiceUtil.getTaskByProjectId(projectId) %>"
			total="<%= TaskLocalServiceUtil.getTaskByProjectId(projectId).size() %>" />

		<liferay-ui:search-container-row
			className="com.liferay.timesheet.model.Task" keyProperty="taskId"
			modelVar="task">
			<liferay-ui:search-container-column-text name="name" property="name" />

			<liferay-ui:search-container-column-text name="startDate"
				value="<%= formatTask.format(task.getStartDate()) %>" />

			<liferay-ui:search-container-column-text name="endDate"
				value="<%= formatTask.format(task.getEndDate()) %>" />

			<liferay-ui:search-container-column-text name="typeDescription"
				property="typeDescription" translate="<%= true %>" />
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>

	<div class="lfr-portlet-timesheet-total-label">
		<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total", String.valueOf(currencyFormat.format(project.getTotalTaskCost()))) %>' />
	</div>

	<liferay-ui:header showBackURL="<%= false %>" title='<%= LanguageUtil.get(pageContext, "expenses") %>' />

	<liferay-ui:search-container emptyResultsMessage="no-data">
		<liferay-ui:search-container-results
			results="<%= ExpenseLocalServiceUtil.getExpenseByProjectId(projectId) %>"
			total="<%= ExpenseLocalServiceUtil.getExpenseByProjectId(projectId).size() %>" />

		<liferay-ui:search-container-row
			className="com.liferay.timesheet.model.Expense"
			keyProperty="expenseId" modelVar="expense">
			<liferay-ui:search-container-column-text name="description"
				property="description" />

			<liferay-ui:search-container-column-text name="purchasedDate"
				value="<%= format.format(expense.getPurchasedDate()) %>" />

			<liferay-ui:search-container-column-text name="value"
				value="<%= currencyFormat.format(expense.getValue()) %>" />

			<liferay-ui:search-container-column-text name="typeDescription"
				translate="<%= true %>" property="typeDescription" />
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>

	<div class="lfr-portlet-timesheet-total-label">
		<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total", String.valueOf(currencyFormat.format(project.getTotalExpenseCost()))) %>' />
	</div>

	<br />

	<div class="lfr-portlet-timesheet-total-label">
		<liferay-ui:message key='<%= LanguageUtil.format(pageContext, "total-project", String.valueOf(currencyFormat.format(project.getTotalProjectCost()))) %>' />
	</div>
</div>

<aui:script>
	function <portlet:namespace />showReport() {
		var A = AUI();

		var form = A.Node.create('<form />');

		form.setAttribute('method', 'POST');

		var content = A.one('#<portlet:namespace />report');

		if (content) {
			form.append(content);
			content.show();
		}
		var dialog = new A.Dialog(
			{
				bodyContent: form,
				buttons: [
					{
						handler: function() {
							<portlet:namespace />printSelection(document.getElementById('<portlet:namespace />report'));
						},
						text: Liferay.Language.get('print')
					},
					{
						handler: function() {
							this.close();
						},
						text: Liferay.Language.get('ok')
					}
			],
			centered: true,
			modal: true,
			title: '<liferay-ui:message key="report" />',
			width: 800
			}
		).render();
	}

	function <portlet:namespace />printSelection(node) {
		var content = node.innerHTML
		var pwin = window.open('','print_content','width=100,height=100');

		pwin.document.open();
		pwin.document.write('<html><body onload="window.print()">'+content+'</body></html>');
		pwin.document.close();

		setTimeout(function(){pwin.close();},1000);
		return false;
   }
</aui:script>