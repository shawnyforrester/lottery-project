drop table if exists user_accounts CASCADE;
drop table if exists ticket;
CREATE TABLE user_accounts
(
    account_id int primary key auto_increment,
    username   varchar(255),
    password   varchar(255)
);
create table ticket
(
    ticket_id int references user_accounts (account_id),
    powerball varchar (255)


);