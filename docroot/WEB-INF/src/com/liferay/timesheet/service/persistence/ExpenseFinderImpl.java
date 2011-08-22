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

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.timesheet.model.Expense;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;

/**
 * @author Antonio Junior
 */
public class ExpenseFinderImpl extends BasePersistenceImpl<Expense>
	implements ExpenseFinder {

	public static String SUM_BY_P =
		ExpenseFinder.class.getName() + ".sumByProject";

	public double sumByProject(long projectId) throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(SUM_BY_P);

			SQLQuery q = session.createSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			q.addScalar("ExpenseTotal", Type.DOUBLE);

			qPos.add(projectId);

			Iterator<Double> itr = q.list().iterator();

			if (itr.hasNext()) {
				Double sum = itr.next();

				if (sum != null) {
					return sum.doubleValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}