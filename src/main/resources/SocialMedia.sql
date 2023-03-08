drop table if exists number;
drop table if exists account;
create table account (
    account_id int primary key auto_increment,
    username varchar(255) unique,
    password varchar(255)
);
create table number (
    message_id int primary key auto_increment,
    posted_by int,
    message_text varchar(255),
    time_posted_epoch bigint,
    foreign key (posted_by) references  account(account_id)
);