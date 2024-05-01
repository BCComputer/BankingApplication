CREATE DATABASE IF NOT EXISTS BankApplication;
USE BankApplication;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(40),
    lastName VARCHAR(40),
    address VARCHAR(200),
    email VARCHAR(50),
    phonenumber VARCHAR (13),
    username VARCHAR(50) UNIQUE,
    password_hash VARCHAR(64),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    account_type ENUM('checking', 'savings'),
    balance DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    transaction_type ENUM('deposit', 'withdrawal', 'transfer'),
    amount DECIMAL(10, 2),
    description VARCHAR(100),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);


-- drop table users;
alter table users auto_increment = 100;
select*from users;
select user_id, firstName, lastName, address, email, phonenumber, username, password_hash from users where username = "Bishnu";

update users set firstName='asdf', lastName='asf', address='asfas', username='asd000fasd', password_hash='asfasdf' where user_id=100;
delete from users where user_id=104;

-- delete from accounts where user_id=1;
-- delete from users where user_id=1;
-- delete from transactions where user_id=1;

-- drop table users;
-- drop table accounts;
-- drop table transactions;

select*from users;
select*from accounts;
select*from transactions;

update accounts set balance = 200 where account_id=1;

alter table accounts auto_increment = 1000;

select acc.account_id, u.user_id, u.username, u.password_hash
from accounts as acc
inner join
users u on acc.user_id = u.user_id
where u.username = 'Bishnu';

select account_id, account_type from accounts where user_id=1;
select user_id, password_hash from users where username = 'Bishnu';

insert into transactions(account_id, transaction_type, amount, description)
values(1002, 'deposit', 40.00, 'deposit first');

select account_id, transaction_type, amount, description from transactions where account_id = 1;