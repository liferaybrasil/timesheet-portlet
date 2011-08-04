create table Timesheet_Expense (
	expenseId LONG not null primary key,
	billedDate DATE null,
	description VARCHAR(75) null,
	projectId LONG,
	type_ INTEGER,
	value DOUBLE,
	dlFieldEntryId LONG
);

create table Timesheet_Project (
	projectId LONG not null primary key,
	userId LONG,
	description VARCHAR(75) null,
	endDate DATE null,
	name VARCHAR(75) null,
	startDate DATE null,
	wage DOUBLE
);

create table Timesheet_Task (
	taskId LONG not null primary key,
	endDate DATE null,
	name VARCHAR(75) null,
	projectId LONG,
	startDate DATE null,
	type_ INTEGER
);