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

package com.liferay.timesheet.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Project service. Represents a row in the &quot;Timesheet_Project&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ProjectModel
 * @see com.liferay.timesheet.model.impl.ProjectImpl
 * @see com.liferay.timesheet.model.impl.ProjectModelImpl
 * @generated
 */
public interface Project extends ProjectModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.timesheet.model.impl.ProjectImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public double getTotalExpenseCost()
		throws com.liferay.portal.kernel.exception.SystemException;

	public double getTotalProjectCost()
		throws com.liferay.portal.kernel.exception.SystemException;

	public double getTotalTaskCost()
		throws com.liferay.portal.kernel.exception.SystemException;
}