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
('admin1', 'Admin1', 'admin'), 
('newsagent1', 'Newsagent1', 'newsagent'),
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
('driver12', 'Driver12', 'driver');

-- Create the 'customerdetails' table
DROP TABLE IF EXISTS customerdetails;
CREATE TABLE IF NOT EXISTS customerdetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,
    address VARCHAR(20) NOT NULL,
    phoneNo INT(10) DEFAULT NULL,
    CONSTRAINT unique_customer_details UNIQUE (firstName, lastName, address, phoneNo)
);

-- INSERT INTO customers 
INSERT INTO customerdetails (firstName, lastName, address, phoneNo) VALUES
('John', 'Doe', '123 Main St', 555987254),
('Alice', 'Smith', '456 Elm St', 555987654),
('Robert', 'Johnson', '789 Oak St', 555555555),
('Sara', 'Williams', '1122 Elm St', 555777777),
('Michael', 'Brown', '88 Oak St', 555999999),
('Emily', 'Miller', '321 Pine St', 555111222),
('Linda', 'White', '456 Birch St', 555333444),
('Daniel', 'Clark', '789 Snow St', 555555555),
('Emanuel', 'Garcia', '111 Three St', 555777777),
('Ciara', 'Taylor', '222 River St', 555999999);

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
('2023-10-19', 1, 'daily', 'Book Title 1', 19.99),
('2023-10-20', 2, 'weekly', 'Magazine 1', 5.99),
('2023-10-21', 3, 'monthly', 'Book Title 2', 29.99),
('2023-10-22', 4, 'daily', 'Newspaper 1', 2.49),
('2023-10-23', 5, 'weekly', 'Journal 1', 14.99),
('2023-10-24', 6, 'monthly', 'Magazine 2', 6.99),
('2023-10-25', 7, 'daily', 'Academic Paper 1', 9.99),
('2023-10-26', 8, 'weekly', 'Comic Book 1', 7.99), 
('2023-10-27', 9, 'monthly', 'Technical Manual 1', 39.99), 
('2023-10-28', 10, 'daily', 'Children''s Book 1', 12.99);

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

