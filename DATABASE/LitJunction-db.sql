CREATE DATABASE LitJunction
GO
USE LitJunction
GO
CREATE TABLE Category (
category_id int PRIMARY KEY,
category_name nvarchar(50)
)

CREATE TABLE Admin (
admin_id int PRIMARY KEY,
admin_name nvarchar(50),
username_admin nvarchar(50),
password_admin nvarchar(50)
)

CREATE TABLE Supplier (
supplier_id int PRIMARY KEY,
supplier_name nvarchar(50),
supplier_address nvarchar(100),
admin_id int FOREIGN KEY(admin_id) REFERENCES Admin(admin_id)
)

CREATE TABLE Book (
book_id int PRIMARY KEY,
book_image nvarchar(50),
title nvarchar(100),
author nvarchar(100),
price float,
describe nvarchar(1500),
bookstore_quantity int,
admin_id int FOREIGN KEY(admin_id) REFERENCES Admin(admin_id),
category_id int FOREIGN KEY(category_id) REFERENCES Category(category_id),
supplier_id int FOREIGN KEY(supplier_id) REFERENCES Supplier(supplier_id)
)

CREATE TABLE Customer (
customer_id int PRIMARY KEY,
customer_name nvarchar(50),
customer_address nvarchar(50),
phonenumber varchar(10),
current_points int,
username nvarchar(50),
password nvarchar(50),
gender nvarchar(6),
statuses varchar(50),
admin_id int FOREIGN KEY(admin_id) REFERENCES Admin(admin_id)
)

CREATE TABLE Invoice (
invoice_id int IDENTITY(1,1) PRIMARY KEY,
date date,
total_amount float,
buy_status varchar(50),
customer_id int FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
)

CREATE TABLE Invoice_detail (
invoice_id int FOREIGN KEY(invoice_id) REFERENCES Invoice(invoice_id),
book_id int FOREIGN KEY(book_id) REFERENCES Book(book_id),
buy_quantity int,
price float
)

CREATE TABLE Reward_points (
reward_id int PRIMARY KEY,
earned_points int,
date_earned date,
customer_id int FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
invoice_id int FOREIGN KEY(invoice_id) REFERENCES Invoice(invoice_id)
)


