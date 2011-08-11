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

package com.liferay.timesheet.model.impl;

import com.liferay.timesheet.util.PortletPropsValues;

/**
 * @author Antonio Junior
 */
public class ExpenseImpl extends ExpenseBaseImpl {

	public String getTypeDescription() {
		return PortletPropsValues.EXPENSE_TYPES[getType()];
	}
	
	public String getFileName() {
		return _fileName;
	}
	
	public String getFilePath() {
		return _filePath;
	}
	
	public void setFileName(String fileName) {
		_fileName = fileName;
	}

	public void setFilePath(String filePath) {
		_filePath = filePath;
	}

	private String _fileName;
	private String _filePath;

}