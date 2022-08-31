create database  if not exists vue;

use vue;

drop table if exists employee;

create table employee(
                         id int primary key auto_increment,
                         name varchar(50),
                         age int,
                         salary double
);