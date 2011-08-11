create table Timesheet_Expense (
	expenseId LONG not null primary key,
	projectId LONG,
	description VARCHAR(75) null,
	purchasedDate DATE null,
	type_ INTEGER,
	value DOUBLE,
	fileEntryId LONG
);

create table Timesheet_Project (
	projectId LONG not null primary key,
	userId LONG,
	description VARCHAR(75) null,
	endDate DATE null,
	startDate DATE null,
	name VARCHAR(75) null,
	wage DOUBLE
);

create table Timesheet_Task (
	taskId LONG not null primary key,
	projectId LONG,
	name VARCHAR(75) null,
	type_ INTEGER,
	startDate DATE null,
	endDate DATE null
);