create index IX_85E0B93 on Timesheet_Expense (projectId);

create index IX_C7689551 on Timesheet_Project (name);
create index IX_6C30CB7B on Timesheet_Project (name, description);

create index IX_406E5F4C on Timesheet_Task (projectId);