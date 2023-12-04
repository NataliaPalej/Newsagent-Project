-- CREATE DATABASE-- #################################################################
DROP DATABASE IF EXISTS newsagentdb;
CREATE DATABASE IF NOT EXISTS newsagentdb;
USE newsagentdb;

-- USERDETAILS#################################################################
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

insert into userdetails values (null, "n", "n", "newsagent");
insert into userdetails values (null,"d", "d", "driver");
insert into userdetails values (null,"a", "a", "admin");

-- CUSTOMER DETATAILS#################################################################
-- Create the 'customerdetails' table
DROP TABLE IF EXISTS customerdetails;
CREATE TABLE IF NOT EXISTS customerdetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,    
    address VARCHAR(50) NOT NULL,
    phoneNo VARCHAR(15) DEFAULT NULL,
    areaCode int NOT NULL);
INSERT INTO customerdetails (custID, firstName, lastName, address, phoneNo, areaCode) VALUES
(1, 'Natalia', 'Palej', '123 Main St', '555-123-4567', 1),
(2, 'Ivan', 'Lapickij', '456 Elm St', '555-234-5678', 2),
(3, 'Ronan', 'Harris', '789 Oak St', '555-345-6789', 3),
(4, 'Lilly', 'Lola', '101 Pine St', '555-456-7890', 4),
(5, 'Kevin', 'Murphy', '202 Maple St', '555-567-8901', 5),
(6, 'Doja', 'Cot', '303 Cedar St', '555-678-9012', 6),
(7, 'Miley', 'Cyrus', '404 Birch St', '555-789-0123', 7),
(8, 'Selena', 'Gomez', '505 Redwood St', '555-890-1234', 8),
(9, 'Cardi', 'B', '606 Sequoia St', '555-901-2345', 9),
(10, 'Brad', 'Pitt', '707 Spruce St', '555-012-3456', 10),
(11, 'Bond', '007', '008 Willow St', '555-001-2345', 11),
(12,"Natalia", "Palej", "123 Main St", "555-123-4567", 1);

-- PUBLICATIONS#################################################################
-- Create the 'publications' table
DROP TABLE IF EXISTS publications;
CREATE TABLE publications (
	publicationID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    issueNo INT NOT NULL,
    author VARCHAR(50) NOT NULL,
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);
-- INSERT INTO PUBLICATIONS
INSERT INTO publications (title, issueNo, author, price, stock) VALUES
    ('Westmeath Independant', 100, 'John Doe', 2.99, 5),
    ('Cosmopolitan', 123, 'John Smith', 1.99, 350),
    ('Galactic Discoveries Digest', 456, 'Jane Doe', 3.99, 4),
    ('AI Advancements Almanac', 789, 'Michael Johnson', 2.99, 10),
    ('Health Horizon Herald', 321, 'John Doe', 2.99, 150),
    ('Quantum Insights Quarterly', 50, 'Alice White', 5.99, 6),
    ('EcoTech Innovator', 200, 'David Green', 3.99, 250),
    ('Stellar Science Journal', 789, 'Emily Brown', 4.99, 4),
    ('Global Perspectives Gazette', 150, 'Robert Black', 2.50, 2),
    ('BioTech Breakthrough Bulletin', 300, 'Sophia Gray', 3.50, 0),
    ('Cultural Chronicles Chronicle', 75, 'Daniel Lee', 2.75, 5),
    ('Cybernetic Trends Tribune', 400, 'Olivia Davis', 4.99, 10),
    ('Sustainable Solutions Review', 250, 'Matthew Johnson', 3.99, 15),
    ('Robotic Revolution Report', 600, 'Ava Robinson', 3.75, 16),
    ('Psyche & Society Sentinel', 100, 'Liam Miller', 2.25, 22);

-- ORDERS#################################################################
-- Create the 'orders' table
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated DATE NOT NULL,
    custID INT NOT NULL,
    orderType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    publicationID INT NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID) ON DELETE CASCADE,
    FOREIGN KEY (publicationID) REFERENCES publications (publicationID) ON DELETE CASCADE
);

-- INSERT INTO ORDERS OLD DATE
INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES 
('2023-10-10', 1, 'daily', 1),
('2023-10-20', 2, 'weekly', 1),
('2023-10-21', 3, 'monthly', 1),
('2023-10-22', 4, 'daily', 2),
('2023-10-23', 5, 'weekly', 2),
('2023-10-24', 6, 'monthly', 2),
('2023-10-25', 7, 'daily', 2),
('2023-10-26', 8, 'weekly', 3), 
('2023-10-27', 9, 'monthly', 3), 
('2023-10-28', 10, 'daily', 3),
('2023-11-13', 11, 'weekly', 4),
('2023-11-13', 2, 'weekly', 1),
('2023-11-13', 1, 'monthly', 1);
-- CURRENT DATE-- 
INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(CURDATE(), 1, "weekly", 3),
(CURDATE(), 1, "daily", 2),
(CURDATE(), 1, "monthly", 1),
(CURDATE(), 1, "daily", 4),
(CURDATE(), 1, "weekly", 5),
(CURDATE(), 2, "weekly", 1),
(CURDATE(), 2, "monthly", 2),
(CURDATE(), 2, "daily", 3),
(CURDATE(), 2, "daily", 4),
(CURDATE(), 2, "weekly", 5),
(CURDATE(), 3, "monthly", 2),
(CURDATE(), 3, "daily", 3),
(CURDATE(), 3, "monthly", 4),
(CURDATE(), 3, "daily", 5),
(CURDATE(), 3, "weekly", 1),
(CURDATE(), 11, "monthly", 3),
(CURDATE(), 11, "daily", 4),
(CURDATE(), 11, "monthly", 5),
(CURDATE(), 11, "daily", 1),
(CURDATE(), 11, "weekly", 2);

-- Temporary table to generate dates up to 06/12/2023
-- Drop temporary table if Exists
DROP TEMPORARY TABLE IF EXISTS temp_dates;
CREATE TEMPORARY TABLE temp_dates AS (
    SELECT DATE_ADD('2023-10-29', INTERVAL t.seq DAY) AS order_date
    FROM (
        SELECT (t4*1000 + t3*100 + t2*10 + t1) AS seq
        FROM (SELECT 0 AS t1 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t1,
             (SELECT 0 AS t2 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t2,
             (SELECT 0 AS t3 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) t3,
             (SELECT 0 AS t4 UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4) t4
    ) t
    WHERE DATE_ADD('2023-10-29', INTERVAL t.seq DAY) <= '2023-12-06'
);

-- ORDERS CURRENT DAY
INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 2, 'monthly', 1),
(NOW(), 4, 'monthly', 1);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 2, 'weekly', 3),
(NOW(), 2, 'monthly', 4);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 3, 'monthly', 5),
(NOW(), 3, 'daily', 1);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 4, 'monthly', 2),
(NOW(), 4, 'weekly', 3);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 5, 'daily', 4),
(NOW(), 5, 'monthly', 5);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 6, 'weekly', 1),
(NOW(), 6, 'daily', 2);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 7, 'monthly', 3),
(NOW(), 7, 'weekly', 4);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 8, 'daily', 5),
(NOW(), 8, 'monthly', 1);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 9, 'monthly', 2),
(NOW(), 9, 'daily', 3);

INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES
(NOW(), 10, 'weekly', 4),
(NOW(), 10, 'monthly', 5);

-- Insert orders based on order frequency DO WE NEED THIS?????????????????????????????????????????????????????????????????????????
INSERT INTO orders (dateCreated, custID, orderType, publicationID)
SELECT 
    d.order_date,
    o.custID,
    o.orderType,
    o.publicationID
FROM 
    temp_dates d
JOIN 
    orders o ON o.dateCreated <= d.order_date
WHERE 
    (
        (o.orderType = 'daily' AND d.order_date > o.dateCreated) OR
        (o.orderType = 'weekly' AND d.order_date = DATE_ADD(o.dateCreated, INTERVAL 7 DAY)) OR
        (
            o.orderType = 'monthly' AND (
                DAY(d.order_date) = DAY(o.dateCreated) OR
                (
                    DAY(o.dateCreated) > 28 AND
                    DAY(d.order_date) = DAY(LAST_DAY(DATE_SUB(d.order_date, INTERVAL 1 DAY)))
                )
            )
        )
    );
 

-- joining orders, customerdetails and publications
SELECT 
    o.orderID,
    o.dateCreated,
    c.firstName,
    c.lastName,
    c.areaCode,
    c.address,
    p.title AS publicationTitle,
    p.issueNo AS publicationIssueNo
FROM 
    orders o
INNER JOIN 
    customerdetails c ON o.custID = c.custID
INNER JOIN 
    publications p ON o.publicationID = p.publicationID
ORDER BY 
    o.orderID;


-- INVOICES-- #################################################################
DROP TABLE IF EXISTS invoices;
-- Create the 'invoices' table
CREATE TABLE IF NOT EXISTS invoices (
    invoiceID INT AUTO_INCREMENT PRIMARY KEY,
    custID INT,
    totalPrice INT,
    FOREIGN KEY (custID) REFERENCES customerdetails(custID) ON DELETE CASCADE
);

-- INSERT INTO invoices
INSERT INTO invoices (custID, totalPrice)
SELECT
    o.custID,
    SUM(
        CASE
            WHEN o.orderType = 'daily' THEN p.price * 7
            WHEN o.orderType = 'monthly' THEN p.price * 28
            WHEN o.orderType = 'weekly' THEN p.price
            ELSE 0
        END
    ) AS totalPrice
FROM
    orders o
JOIN
    publications p ON o.publicationID = p.publicationID
WHERE
    o.custID IS NOT NULL
GROUP BY
    o.custID;

-- Display data#################################################################
SELECT * FROM invoices ORDER BY custID;
SELECT * FROM userdetails ORDER BY userID;
SELECT * FROM customerdetails ORDER BY custID;
SELECT * FROM publications ORDER BY publicationID;
SELECT * FROM orders ORDER BY orderID;