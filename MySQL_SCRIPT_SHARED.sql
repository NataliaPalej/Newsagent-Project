-- Create the 'deliveryschema' database
CREATE DATABASE IF NOT EXISTS deliveryschema;
USE deliveryschema;

-- Create the 'publications' table
CREATE TABLE publications (
    title VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT unique_publications UNIQUE (title,price)
);

CREATE TABLE customers (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    custAddress VARCHAR(255),
    phoneNo VARCHAR(15),
    CONSTRAINT unique_customer_details UNIQUE (firstName, lastName, custAddress, phoneNo)
);
-- Makes sure there wont be duplicates 




-- Create the 'orders' table
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated TIMESTAMP NOT NULL,
    custID INT,
    title VARCHAR(50) NOT NULL,
    subscription_type ENUM('daily', 'weekly', 'monthly') NOT NULL,
    FOREIGN KEY (custID) REFERENCES customers (custID),
    FOREIGN KEY (title) REFERENCES publications (title),
    CONSTRAINT unique_orders UNIQUE (orderID,dateCreated,custID,title,subscription_type)
);

-- Create the 'users' table
CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userRole ENUM('admin', 'newsagent', 'driver'),
    userName VARCHAR(50),
    userPassword VARCHAR(255),
	CONSTRAINT unique_users UNIQUE (userId,userRole,userName,userPassword)

);

-- INSERT INTO publications
INSERT INTO publications (title, price) VALUES ('Book Title 1', 19.99);
INSERT INTO publications (title, price) VALUES ('Magazine 1', 5.99);
INSERT INTO publications (title, price) VALUES ('Book Title 2', 29.99);
INSERT INTO publications (title, price) VALUES ('Newspaper 1', 2.49);
INSERT INTO publications (title, price) VALUES ('Journal 1', 14.99);
INSERT INTO publications (title, price) VALUES ('Magazine 2', 6.99);
INSERT INTO publications (title, price) VALUES ('Academic Paper 1', 9.99);
INSERT INTO publications (title, price) VALUES ('Comic Book 1', 7.99);
INSERT INTO publications (title, price) VALUES ('Technical Manual 1', 39.99);
INSERT INTO publications (title, price) VALUES ('Children''s Book 1', 12.99);

-- INSERT INTO customers 
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('John', 'Doe', '123 Main St', '555-123-4567');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Alice', 'Smith', '456 Elm St', '555-987-6543');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Robert', 'Johnson', '789 Oak St', '555-555-5555');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Sara', 'Williams', '1122 Elm St', '555-777-7777');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Michael', 'Brown', '88 Oak St', '555-999-9999');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Emily', 'Miller', '321 Pine St', '555-111-2222');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Linda', 'White', '456 Birch St', '555-333-4444');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Daniel', 'Clark', '789 Snow St', '555-555-5555');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Emanuel', 'Garcia', '111 Three St', '555-777-7777');
INSERT INTO customers (firstName, lastName, custAddress, phoneNo) VALUES ('Ciara', 'Taylor', '222 River St', '555-999-9999');



-- INSERT INTO ORDERS
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-19', 1, 'Book Title 1', 'daily');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-20', 2, 'Magazine 1', 'weekly');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-21', 3, 'Book Title 2', 'monthly');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-22', 4, 'Newspaper 1', 'daily');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-23', 5, 'Journal 1', 'weekly');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-24', 6, 'Magazine 2', 'monthly');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-25', 7, 'Academic Paper 1', 'daily');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-26', 8, 'Comic Book 1', 'weekly');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-27', 9, 'Technical Manual 1', 'monthly');
INSERT INTO orders (dateCreated, custID, title, subscription_type) VALUES ('2023-10-28', 10, 'Children''s Book 1', 'daily');

-- INSERT INTO USERS
INSERT INTO users (userRole, userName, userPassword) VALUES ('admin', 'admin1', 'adminpassword1');
INSERT INTO users (userRole, userName, userPassword) VALUES ('admin', 'admin2', 'adminpassword2');

INSERT INTO users (userRole, userName, userPassword) VALUES ('newsagent', 'newsagent1', 'newsagentpassword1');
INSERT INTO users (userRole, userName, userPassword) VALUES ('newsagent', 'newsagent2', 'newsagentpassword2');

INSERT INTO users (userRole, userName, userPassword) VALUES ('driver', 'driver1', 'driverpassword1');
INSERT INTO users (userRole, userName, userPassword) VALUES ('driver', 'driver2', 'driverpassword2');


-- SHOW COLUMNS 
-- SHOW columns from publications;
-- SHOW columns from customers;
-- SHOW columns from orders;
-- SHOW columns from users;

-- Select ALL DATA from the 'deliveryschema' database
SELECT * FROM publications;
SELECT * FROM customers;
SELECT * FROM orders;
SELECT * FROM users;

-- Desirable order display wit custName , custLasName
SELECT 
    o.orderID,
    o.dateCreated,
    c.firstName AS custFirstName,
    c.lastName AS custLastName,
    o.title,
    o.subscriptionType
FROM orders AS o
INNER JOIN customers AS c ON o.custID = c.custID;
