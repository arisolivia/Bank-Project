
--table for all users
create table users (
id serial not null,
first_name text,
last_name text,
ss varchar(11),
street text,
city text,
state text,
zip integer,
username varchar(18) not null,
user_password varchar(18) not null,
user_type varchar(8) not null
);
select * from users;
--add employee to start log in
insert into users values(default, 'John', 'Doe', '100-10-1000', '123 Maple Lane', 'Indianapolis', 'IN', 46205, 'employee1', 'employee1', 'employee');

--table for all accounts
create table accounts (
id serial not null,
account_type varchar(8) not null,
balance numeric
);

--create view to pull account objects by username
create view user_accounts as
select * from user_account right join accounts 
on user_account.account_id=accounts.id;

--table linking users to accounts
create table user_account (
username varchar(18) not null,
account_id integer not null
);

--transactions made in each account
create table transaction_history (
id serial not null,
account_id integer not null,
username varchar(18) not null,
amount numeric,
date_made timestamp
);

--Adding primary keys
alter table users add constraint users_pk primary key (username);
alter table accounts add constraint accounts_pk primary key (ID);
alter table user_account add constraint user_account_pk primary key (username, account_id);
alter table transaction_history add constraint transaction_history_pk primary key (id);

--Adding foreign keys
alter table user_account add constraint user_account_fk foreign key (username) references users(username) on delete cascade;
alter table user_account add constraint user_account_fkv2 foreign key (account_id) references accounts(id)on delete cascade;
alter table transaction_history add constraint transaction_history_fk foreign key (account_id) references accounts(id)on delete cascade;
alter table transaction_history add constraint transaction_history_fkv2 foreign key (username) references users(username)on delete cascade;

--ID generator for account table
create sequence Acc_seq;

--function to return total of users in database
create or replace function total_users() returns integer as $$
declare total integer;
begin
	select count("id") into total from "users";
	return total;
end;
$$ language plpgsql;
select total_users();

--function to return account id after insertion
create or replace function get_id(a text, b numeric) returns integer as $$
declare id integer;
begin
	select nextval('acc_seq') into id;
	insert into accounts values(id ,$1, $2);
	return id;
end;
$$ language plpgsql;
delete from accounts;
select get_id('checking', 0.0);