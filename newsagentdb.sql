CREATE DATABASE IF NOT EXISTS newsagentdb;
USE newsagentdb;

CREATE TABLE customerdetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,
    address VARCHAR(20) NOT NULL,
    phoneNo VARCHAR(13) DEFAULT NULL,
    CONSTRAINT unique_customer_details UNIQUE (firstName, lastName, address, phoneNo)
);

CREATE TABLE publications (
	publicationID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(20) NOT NULL,
    issueNo INT NOT NULL,
    author VARCHAR(15) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT unique_publications UNIQUE (title,price)
);

CREATE TABLE userdetails (
    userID INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(10),
    password VARCHAR(10),
    role ENUM('admin', 'newsagent', 'driver'),
	CONSTRAINT unique_users UNIQUE (userID,role,username,password)
);

CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated TIMESTAMP NOT NULL,
    custID INT,
    publicationID INT,
    title VARCHAR(50) NOT NULL,
    orderType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID),
    FOREIGN KEY (publicationID) REFERENCES publications (publicationID)
);

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

INSERT INTO customerdetails (firstName, lastName, address, phoneNo) VALUES
('Natalia', 'Palej', '123 Main St', '555-123-4567'),
('Ivan', 'Lapickij', '456 Elm St', '555-234-5678'),
('Ronan', 'Harris', '789 Oak St', '555-345-6789'),
('Lilly', 'Lola', '101 Pine St', '555-456-7890'),
('Kevin', 'Murphy', '202 Maple St', '555-567-8901'),
('Doja', 'Cot', '303 Cedar St', '555-678-9012'),
('Miley', 'Cyrus', '404 Birch St', '555-789-0123'),
('Selena', 'Gomez', '505 Redwood St', '555-890-1234'),
('Cardi', 'B', '606 Sequoia St', '555-901-2345'),
('Brad', 'Pitt', '707 Spruce St', '555-012-3456'),
('Bond', '007', '008 Willow St', '555-001-2345');

INSERT INTO publications (title, issueNo, author, price, stock) VALUES
    ('Irish Independent', '1', 'John Doe', 2.5, 100),
    ('Offaly Topic', '4', 'Tom Smith', 3, 150),
    ('RTÉ Guide', '10', 'Ryan Tubridy', 1.99, 75),
    ('Irish Daily Star', '13', 'Enoch Burke', 10.99, 100),
    ('Book Title 1', '1', 'John Doe', 10.99, 100),
    ('Irish Independent', '2', 'John Doe', 2.5, 100),
    ('Book Title 1', '1', 'John Doe', 10.99, 100),
    ('Book Title 1', '1', 'John Doe', 10.99, 100),
    ('Book Title 1', '1', 'John Doe', 10.99, 100),
    ('Book Title 1', '1', 'John Doe', 10.99, 100)
;

--  3 orders for each customer (1 daily, 1 weekly, and 1 monthly)
INSERT INTO orders (custID, publicationID, title, orderType, dateCreated)
SELECT cd.custID, p.publicationID, p.title, 'daily', '2023-10-26'
FROM customerdetails cd CROSS JOIN publications p
LIMIT 11;

INSERT INTO orders (custID, publicationID, title, orderType, dateCreated)
SELECT cd.custID, p.publicationID, p.title, 'weekly', '2023-10-25'
FROM customerdetails cd CROSS JOIN publications p
LIMIT 11;

INSERT INTO orders (custID, publicationID, title, orderType, dateCreated)
SELECT cd.custID, p.publicationID, p.title, 'monthly', '2023-10-25'
FROM customerdetails cd CROSS JOIN publications p
LIMIT 11;

INSERT INTO invoice (custID, publicationID, orderID, totalPrice, totalQuantityDelivered)
SELECT o.custID, o.publicationID, o.orderID, SUM(p.price),
(SELECT COUNT(*) FROM orders o2 WHERE o2.custID = o.custID AND DATE_FORMAT(o2.dateCreated, '%Y-%m') = '2023-10') AS totalBooksDelivered
FROM orders o JOIN publications p ON o.publicationID = p.publicationID GROUP BY o.custID;

SELECT * FROM customerdetails ORDER BY custID;
SELECT * FROM userdetails ORDER BY userID;
SELECT * from invoice ORDER BY invoiceID;

SELECT
    i.custID AS 'CustID', c.firstName AS 'First Name', c.lastName AS 'Last Name', c.address AS 'Address', c.phoneNo AS 'Phone No',
    p.title AS 'Publication Title', p.price AS 'Price', i.totalQuantityDelivered AS 'Quantity Delivered', i.totalPrice AS 'Total Price'
FROM invoice i JOIN customerdetails c ON i.custID = c.custID JOIN publications p ON i.publicationID = p.publicationID ORDER BY i.invoiceID;

SELECT * from orders ORDER BY orderID;
SELECT * from publications ORDER BY publicationID;

DELETE FROM customerdetails;
DELETE FROM orders;

-- INSERT INTO publications
INSERT INTO publications (title, price) VALUES 
('Book Title 1', 19.99, 100), ('Magazine 1', 5.99, 200),
('Book Title 2', 29.99, 100), ('Newspaper 1', 2.49, 200),
('Journal 1', 14.99, 100), ('Magazine 2', 6.99, 200),
('Academic Paper 1', 9.99, 100), ('Comic Book 1', 7.99, 200),
('Technical Manual 1', 39.99, 100), ('Children''s Book 1', 12.99, 200);
 
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
INSERT INTO orders (dateCreated, custID, title, orderType) VALUES 
('2023-10-19', 1, 'Book Title 1', 'daily'),
('2023-10-20', 2, 'Magazine 1', 'weekly'),
('2023-10-21', 3, 'Book Title 2', 'monthly'),
('2023-10-22', 4, 'Newspaper 1', 'daily'),
('2023-10-23', 5, 'Journal 1', 'weekly'),
('2023-10-24', 6, 'Magazine 2', 'monthly'),
('2023-10-25', 7, 'Academic Paper 1', 'daily'),
('2023-10-26', 8, 'Comic Book 1', 'weekly'), 
('2023-10-27', 9, 'Technical Manual 1', 'monthly'), 
('2023-10-28', 10, 'Children''s Book 1', 'daily');

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
