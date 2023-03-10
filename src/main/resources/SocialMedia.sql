drop table if exists number;
drop table if exists account;
CREATE TABLE UserAccounts (
    account_id int primary key auto_increment,
    username varchar(255) UNIQUE,
    password varchar(255) UNIQUE
);
create table ticket (
    ticket_id int primary key auto_increment,
    "power ball" varchar(255),
    foreign key (ticket_id) references  UserAccount(account_id)
);