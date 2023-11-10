DROP DATABASE IF EXISTS newsagentdb;
CREATE DATABASE IF NOT EXISTS newsagentdb;
USE newsagentdb;

-- Create the 'userdetails' table for admins, drivers, and newsagents
DROP TABLE IF EXISTS userdetails;
CREATE TABLE IF NOT EXISTS userdetails (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(10),
    password VARCHAR(10),
    role ENUM('admin', 'newsagent', 'driver'),
	CONSTRAINT unique_users UNIQUE (userID, role, username, password)
);

-- INSERT INTO USERS
INSERT INTO userdetails (username, password, role) VALUES 
('driver1', 'Driver1', 'driver'),
('driver2', 'Driver2', 'driver'),
('driver3', 'Driver3', 'driver'),
('driver4', 'Driver4', 'driver'),
('driver5', 'Driver5', 'driver'),
('driver6', 'Driver6', 'driver'),
('driver7', 'Driver7', 'driver'),
('driver8', 'Driver8', 'driver'),
('driver9', 'Driver9', 'driver'),
('driver10', 'Driver10', 'driver'),
('driver11', 'Driver11', 'driver'),
('driver12', 'Driver12', 'driver'),
('admin1', 'Admin1', 'admin'), 
('newsagent1', 'Newsagent1', 'newsagent');
-- Create the 'customerdetails' table
DROP TABLE IF EXISTS customerdetails;
CREATE TABLE IF NOT EXISTS customerdetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,
    areaCode int(12) NOT NULL,
    address VARCHAR(20) NOT NULL,
    phoneNo INT(10) DEFAULT NULL,
    CONSTRAINT unique_customer_details UNIQUE (firstName, lastName, address, phoneNo)
);

-- INSERT INTO customers 
INSERT INTO customerdetails (firstName, lastName,areaCode, address, phoneNo) VALUES
-- AREA BY CODE
-- 1.Carlow
-- 2.Dublin
-- 3.Kildare
-- 4.Kilkenny
-- 5.Laois
-- 6.Longford
-- 7.Louth
-- 8.Meath
-- 9.Offaly
-- 10Westmeath
-- 11.Wexford
-- 12.Wicklow
('John', 'Doe',1, '123 Main St , Carlow', 555987254),
('Alice', 'Smith',2, '456 Elm St, Dublin', 555987654),
('Robert', 'Johnson',2, '789 Oak St, Dublin', 555555555),
('Sara', 'Williams',2, '1122 Elm St, Dublin', 555777777),
('Michael', 'Brown',3, '88 Oak St, Kildare', 555999999),
('Emily', 'Miller',3, '321 Pine St,Kildare', 555333444),
('Daniel', 'Clark',4, '789 Snow St, Kilkenny', 555555555),
('Emanuel', 'Garcia', 5,'111 Three St, Laois', 555777777),
('Ciara', 'Taylor',6, '222 River St, Longford', 555999999);
-- INSERT INTO customers 20 more
INSERT INTO customerdetails (firstName, lastName, areaCode, address, phoneNo) VALUES
('Mark', 'Johnson', 1, '789 Oak St, Carlow', 555444555),
('Emma', 'Davis', 2, '456 Pine St, Dublin', 555333666),
('Christopher', 'Lee', 3, '123 Maple St, Kildare', 555222777),
('Olivia', 'Moore', 4, '987 Birch St, Kilkenny', 555111888),
('James', 'White', 5, '654 Cedar St, Laois', 555777000),
('Sophia', 'Hall', 6, '321 Redwood St, Longford', 555888999),
('Liam', 'Jones', 7, '876 Willow St, Louth', 555999111),
('Ava', 'Taylor', 8, '234 Oak St, Meath', 555222333),
('Noah', 'Brown', 9, '567 Pine St, Offaly', 555444555),
('Isabella', 'Wilson', 10, '876 Elm St, Westmeath', 555666777),
('Logan', 'Anderson', 11, '543 Maple St, Wexford', 555888999),
('Mia', 'Moore', 12, '789 Birch St, Wicklow', 555111222),
('Ethan', 'Smith', 1, '432 Cedar St, Carlow', 555333444),
('Aria', 'Johnson', 2, '765 Redwood St, Dublin', 555555777),
('Lucas', 'Williams', 3, '987 Willow St, Kildare', 555777888),
('Amelia', 'Martin', 4, '543 Pine St, Kilkenny', 555999111),
('Jackson', 'Harris', 5, '876 Maple St, Laois', 555111222),
('Ella', 'Scott', 6, '321 Elm St, Longford', 555333444),
('Carter', 'Garcia', 7, '654 Cedar St, Louth', 555555666),
('Grace', 'Davis', 8, '876 Redwood St, Meath', 555777888);

-- Create the 'publications' table
DROP TABLE IF EXISTS publications;
CREATE TABLE publications (
    publicationID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT unique_publications UNIQUE (title, price)
);

-- INSERT INTO publications
INSERT INTO publications (title, price,stock) VALUES 
('Book Title 1', 19.99, 100), ('Magazine 1', 5.99, 200),
('Book Title 2', 29.99, 100), ('Newspaper 1', 2.49, 200),
('Journal 1', 14.99, 100), ('Magazine 2', 6.99, 200),
('Academic Paper 1', 9.99, 100), ('Comic Book 1', 7.99, 200),
('Technical Manual 1', 39.99, 100), ('Children''s Book 1', 12.99, 200);

-- Create the 'orders' table
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated TIMESTAMP NOT NULL,
    custID INT,
    orderType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    title VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID),
    CONSTRAINT unique_orders UNIQUE (orderID, dateCreated, custID, orderType, title, price)
);

-- INSERT INTO ORDERS
INSERT INTO orders (dateCreated, custID, orderType, title, price) VALUES 
('2023-10-10', 1, 'daily', 'Book Title 1', 19.99),
('2023-10-20', 2, 'weekly', 'Magazine 1', 5.99),
('2023-10-21', 3, 'monthly', 'Book Title 2', 29.99),
('2023-10-22', 4, 'daily', 'Newspaper 1', 2.49),
('2023-10-23', 5, 'weekly', 'Journal 1', 14.99),
('2023-10-24', 6, 'monthly', 'Magazine 2', 6.99),
('2023-10-25', 7, 'daily', 'Academic Paper 1', 9.99),
('2023-10-26', 8, 'weekly', 'Comic Book 1', 7.99), 
('2023-10-27', 9, 'monthly', 'Technical Manual 1', 39.99), 
('2023-10-28', 10, 'daily', 'Children''s Book 1', 12.99);

-- EXTRA 20 orders of 10-11-2023
INSERT INTO orders (dateCreated, custID, orderType, title, price) VALUES 
('2023-11-10', 1, 'daily', 'Book Title 1', 19.99),
('2023-11-10', 2, 'weekly', 'Magazine 1', 5.99),
('2023-11-10', 3, 'monthly', 'Book Title 2', 29.99),
('2023-11-10', 4, 'daily', 'Newspaper 1', 2.49),
('2023-11-10', 5, 'weekly', 'Journal 1', 14.99),
('2023-11-10', 6, 'monthly', 'Magazine 2', 6.99),
('2023-11-10', 7, 'daily', 'Academic Paper 1', 9.99),
('2023-11-10', 8, 'weekly', 'Comic Book 1', 7.99), 
('2023-11-10', 9, 'monthly', 'Technical Manual 1', 39.99), 
('2023-11-10', 10, 'daily', 'Children''s Book 1', 12.99),
('2023-11-10', 11, 'weekly', 'Book Title 3', 17.99),
('2023-11-10', 12, 'monthly', 'Magazine 3', 8.99),
('2023-11-10', 13, 'daily', 'Newspaper 2', 3.99),
('2023-11-10', 14, 'weekly', 'Journal 2', 11.99),
('2023-11-10', 15, 'monthly', 'Book Title 4', 24.99),
('2023-11-10', 16, 'daily', 'Academic Paper 2', 15.99),
('2023-11-10', 17, 'weekly', 'Comic Book 2', 6.49), 
('2023-11-10', 18, 'monthly', 'Technical Manual 2', 49.99), 
('2023-11-10', 19, 'daily', 'Children''s Book 2', 8.99),
('2023-11-10', 20, 'weekly', 'Book Title 5', 21.99);


-- Create the 'invoice' table
CREATE TABLE invoice (
    invoiceID INT AUTO_INCREMENT PRIMARY KEY,
    custID INT,
    publicationID INT,
    orderID INT,
    totalPrice DOUBLE,
    totalQuantityDelivered INT,
    FOREIGN KEY (publicationID) REFERENCES publications(publicationID),
    FOREIGN KEY (custID) REFERENCES customerdetails(custID),
    FOREIGN KEY (orderID) REFERENCES orders(orderID)
);

-- INSERT INTO invoice
INSERT INTO invoice (custID, publicationID, orderID, totalPrice, totalQuantityDelivered)
SELECT o.custID, p.publicationID, o.orderID, SUM(p.price),
(SELECT COUNT(*) FROM orders o2 WHERE o2.custID = o.custID AND DATE_FORMAT(o2.dateCreated, '%Y-%m') = '2023-10') AS totalBooksDelivered
FROM orders o JOIN publications p ON o.title = p.title AND o.price = p.price GROUP BY o.custID, o.orderID;

-- Display data
SELECT * FROM userdetails ORDER BY userID;
SELECT * FROM customerdetails ORDER BY custID;
SELECT * FROM publications ORDER BY publicationID;
SELECT * FROM orders ORDER BY orderID;
SELECT * FROM invoice ORDER BY invoiceID;

-- Desirable order display with custName, custLasName
SELECT 
    o.orderID,
    o.dateCreated,
    c.firstName AS custFirstName,
    c.lastName AS custLastName,
    o.title,
    o.orderType
FROM orders AS o
INNER JOIN customerdetails AS c ON o.custID = c.custID;

-- ORDER BASED ON AREACODE-- 
SELECT
    o.orderID,
    o.dateCreated,
    c.firstName AS custFirstName,
    c.lastName AS custLastName,
    o.title,
    o.orderType
FROM
    orders AS o
INNER JOIN
    customerdetails AS c ON o.custID = c.custID
WHERE
    c.areaCode = 2; -- Replace '1' with the desired areaCode
