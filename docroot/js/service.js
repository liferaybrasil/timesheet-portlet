Liferay.Service.register("Liferay.Service.Timesheet", "com.liferay.timesheet.service", "timesheet-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Timesheet, "Expense",
	{
		addExpense: true,
		deleteExpense: true,
		getExpense: true,
		updateExpense: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Timesheet, "Project",
	{
		addProject: true,
		deleteProject: true,
		getProject: true,
		updateProject: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Timesheet, "Task",
	{
		addTask: true,
		deleteTask: true,
		getTask: true,
		updateTask: true
	}
);