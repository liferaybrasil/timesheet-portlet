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

<%@ page import="com.liferay.portal.kernel.util.StringUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="/html/init.jsp"%>

<%
	String redirect = ParamUtil.getString(request, "redirect");
	long projectId = ParamUtil.getLong(request, "projectId");
	Project project = ProjectLocalServiceUtil.getProject(projectId);

	String tabs1 = ParamUtil.getString(request, "tabs1", "tasks");
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setParameter("tabs1", tabs1);
	portletURL.setParameter("jspPage", "/html/project_details.jsp");
	portletURL.setParameter("projectId", String.valueOf(projectId));
	portletURL.setParameter("redirect", redirect);

	SimpleDateFormat sdfExpenses = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat sdfTasks = new SimpleDateFormat("dd-MM-yyyy HH:mm");
%>
<liferay-util:include page="/html/toolbar.jsp"
	servletContext="<%= application %>" />

<liferay-ui:header backURL="<%= redirect %>"
	title='<%= project.getName() %>' />

<liferay-ui:message key="responsible"></liferay-ui:message>
:
<%=PortalUtil.getUserName(project.getUserId(), "none")%>
<br />
<liferay-ui:message
	key='<%= LanguageUtil.format(pageContext, "total-tasks", String.valueOf(project.getTotalTaskCost())) %>'></liferay-ui:message>
<br />
<liferay-ui:message
	key='<%= LanguageUtil.format(pageContext, "total-expenses", String.valueOf(project.getTotalExpenseCost())) %>'></liferay-ui:message>
<br />
<liferay-ui:message
	key='<%= LanguageUtil.format(pageContext, "total-project", String.valueOf(project.getTotalProjectCost())) %>'></liferay-ui:message>
<br />

<liferay-ui:icon image="all_pages" message="report"
	url='<%= "javascript: " + renderResponse.getNamespace() + "showReport();" %>'
	cssClass="lfr-portlet-timesheet-report-icon" label="report"></liferay-ui:icon>

<liferay-ui:header showBackURL="<%= false %>"
	title='<%= LanguageUtil.format(pageContext, "details-resources", "") %>' />

<liferay-portlet:actionURL portletConfiguration="true"
	var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">

	<liferay-ui:tabs names="tasks,expenses" param="tabs1"
		url="<%= portletURL.toString() %>" />
	<c:choose>
		<c:when test='<%= tabs1.equals("tasks") %>'>
			<liferay-util:include page="/html/view_tasks.jsp"
				servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/html/view_expenses.jsp"
				servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</aui:form>

<div class="aui-helper-hidden" id="<portlet:namespace />report">
	<liferay-ui:header showBackURL="<%= false %>"
		title='<%= project.getName() %>' />

	<div
		class="lfr-portlet-timesheet-total-label lfr-portlet-timesheet-total-label-right">
		<liferay-ui:message key="responsible"></liferay-ui:message>
		:
		<%=PortalUtil.getUserName(project.getUserId(), "none")%>
	</div>
	<br />
	<liferay-ui:header showBackURL="<%= false %>"
		title='<%= LanguageUtil.format(pageContext, "tasks", "") %>' />

	<liferay-ui:search-container emptyResultsMessage="no-data">
		<liferay-ui:search-container-results
			results="<%= TaskLocalServiceUtil.getTaskByProjectId(projectId) %>"
			total="<%= TaskLocalServiceUtil.getTaskByProjectId(projectId).size() %>" />

		<liferay-ui:search-container-row
			className="com.liferay.timesheet.model.Task" keyProperty="taskId"
			modelVar="task">
			<liferay-ui:search-container-column-text name="name" property="name" />

			<liferay-ui:search-container-column-text name="startDate"
				value="<%= sdfTasks.format(task.getStartDate()) %>" />

			<liferay-ui:search-container-column-text name="endDate"
				value="<%= sdfTasks.format(task.getEndDate()) %>" />

			<liferay-ui:search-container-column-text name="typeDescription"
				property="typeDescription" translate="<%= true %>" />
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
	<div class="lfr-portlet-timesheet-total-label">
		<liferay-ui:message
			key='<%= LanguageUtil.format(pageContext, "total", String.valueOf(project.getTotalTaskCost())) %>'></liferay-ui:message>
	</div>
	<liferay-ui:header showBackURL="<%= false %>"
		title='<%= LanguageUtil.format(pageContext, "expenses", "") %>' />

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
				value="<%= sdfExpenses.format(expense.getPurchasedDate()) %>" />

			<liferay-ui:search-container-column-text name="value"
				property="value" />

			<liferay-ui:search-container-column-text name="typeDescription"
				translate="<%= true %>" property="typeDescription" />
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
	<div class="lfr-portlet-timesheet-total-label">
		<liferay-ui:message
			key='<%= LanguageUtil.format(pageContext, "total", String.valueOf(project.getTotalExpenseCost())) %>'></liferay-ui:message>
	</div>
	<br />
	<div class="lfr-portlet-timesheet-total-label">
		<liferay-ui:message
			key='<%= LanguageUtil.format(pageContext, "total-project", String.valueOf(project.getTotalProjectCost())) %>'></liferay-ui:message>
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
								<portlet:namespace />printIt('#<portlet:namespace />report').innerHTML);
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
		
		function <portlet:namespace />printIt(printThis) {
		    win = window.open();
		    self.focus();
		    win.document.open();
		    win.document.write(printThis);
		    win.document.close();
		    win.print();
		    win.close();
		  }
</aui:script>