-- Create the 'deliveryschema' database
CREATE DATABASE IF NOT EXISTS deliveryschema;
USE deliveryschema;

-- Create the 'publications' table
CREATE TABLE publications (
    title VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT unique_publications UNIQUE (title,price)
);

-- INSERT INTO publications
INSERT INTO publications (title, price) VALUES 
('Book Title 1', 19.99), ('Magazine 1', 5.99),
('Book Title 2', 29.99), ('Newspaper 1', 2.49),
('Journal 1', 14.99), ('Magazine 2', 6.99),
('Academic Paper 1', 9.99), ('Comic Book 1', 7.99),
('Technical Manual 1', 39.99), ('Children''s Book 1', 12.99);



-- Create the 'orders' table
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated TIMESTAMP NOT NULL,
    custID INT,
    title VARCHAR(50) NOT NULL,
    subscriptionType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID),
    FOREIGN KEY (title) REFERENCES publications (title),
    CONSTRAINT unique_orders UNIQUE (orderID,dateCreated,custID,title,subscriptionType)
);
-- Makes sure there wont be duplicates

CREATE TABLE customerDetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,
    address VARCHAR(20) NOT NULL,
    phoneNo INT(10) DEFAULT NULL,
    CONSTRAINT unique_customer_details UNIQUE (firstName, lastName, address, phoneNo)
);
 

-- INSERT INTO customers 
INSERT INTO customerdetails (firstName, lastName, address, phoneNo) VALUES('John', 'Doe', '123 Main St', '555-123-4567'),
('Alice', 'Smith', '456 Elm St', '555-987-6543'),
('Robert', 'Johnson', '789 Oak St', '555-555-5555'),
('Sara', 'Williams', '1122 Elm St', '555-777-7777'),
('Michael', 'Brown', '88 Oak St', '555-999-9999'),
('Emily', 'Miller', '321 Pine St', '555-111-2222'),
('Linda', 'White', '456 Birch St', '555-333-4444'),
('Daniel', 'Clark', '789 Snow St', '555-555-5555'),
('Emanuel', 'Garcia', '111 Three St', '555-777-7777'),
('Ciara', 'Taylor', '222 River St', '555-999-9999');



-- INSERT INTO ORDERS
INSERT INTO orders (dateCreated, custID, title, subscriptionType) VALUES 
('2023-10-19', 1, 'Book Title 1', 'daily'),
('2023-10-20', 2, 'Magazine 1', 'weekly'),
('2023-10-21', 3, 'Book Title 2', 'monthly'),
('2023-10-22', 4, 'Newspaper 1', 'daily'),
('2023-10-23', 5, 'Journal 1', 'weekly'),
('2023-10-24', 6, 'Magazine 2', 'monthly'),
('2023-10-25', 7, 'Academic Paper 1', 'daily'),
('2023-10-26', 8, 'Comic Book 1', 'weekly'), 
('2023-10-27', 9, 'Technical Manual 1', 'monthly'), 
('2023-10-28', 10, 'Children''s Book 1', 'daily'),


-- Create the 'userdetails' table admins/drivers/newsagents
CREATE TABLE userdetails (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    role ENUM('admin', 'newsagent', 'driver'),
    username VARCHAR(10),
    password VARCHAR(10),
	CONSTRAINT unique_users UNIQUE (userID,role,username,password)
);

-- INSERT INTO USERS
INSERT INTO userdetails (userRole, username, password) VALUES 
('admin', 'admin1', 'Admin1'),
('admin', 'admin2', 'Admin2'),
('newsagent', 'newsagent1', 'Newsagent1'),
('newsagent', 'newsagent2', 'Newsagent2'),
('driver', 'driver1', 'Driver1'),
('driver', 'driver2', 'Driver2');


-- SHOW COLUMNS 
-- SHOW columns from publications;
-- SHOW columns from customers;
-- SHOW columns from orders;
-- SHOW columns from users;

-- Select ALL DATA from the 'deliveryschema' database
SELECT * FROM publications;
SELECT * FROM customerdetails;
SELECT * FROM orders;
SELECT * FROM userdetails;

-- Desirable order display wit custName , custLasName
SELECT 
    o.orderID,
    o.dateCreated,
    c.firstName AS custFirstName,
    c.lastName AS custLastName,
    o.title,
    o.subscriptionType
FROM orders AS o
INNER JOIN customerdetails AS c ON o.custID = c.custID;
