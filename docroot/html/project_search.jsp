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
ProjectDisplayTerms displayTerms = new ProjectDisplayTerms(renderRequest);
%>

<liferay-ui:search-toggle
	id="toggle_id_project_search"
	displayTerms="<%= displayTerms %>"
	buttonLabel="search"
>
	<aui:fieldset>
		<aui:input name="<%= ProjectDisplayTerms.NAME %>" size="20" type="text" value="<%= displayTerms.getName() %>" />

		<aui:input name="<%= ProjectDisplayTerms.DESCRIPTION %>" size="20" type="text" value="<%= displayTerms.getDescription() %>" />
	</aui:fieldset>
</liferay-ui:search-toggle>