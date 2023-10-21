-- Create the 'deliveryschema' database
CREATE DATABASE IF NOT EXISTS deliveryschema;
USE deliveryschema;


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

-- Create the 'orders' table
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated TIMESTAMP NOT NULL,
    custID INT,
    orderType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    title VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID),
    CONSTRAINT unique_orders UNIQUE (orderID,dateCreated,custID,orderType,title,price)
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




-- Create the 'userdetails' table for admins, drivers, and newsagents
CREATE TABLE userdetails (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    userRole ENUM('admin', 'newsagent', 'driver'),
    username VARCHAR(10),
    userPassword VARCHAR(10),
    CONSTRAINT unique_users UNIQUE (userID, userRole, username, userPassword)
);

-- INSERT INTO USERS
INSERT INTO userdetails (userRole, username, userPassword) VALUES 
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
    o.orderType
FROM orders AS o
INNER JOIN customerdetails AS c ON o.custID = c.custID;
