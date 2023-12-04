DROP DATABASE IF EXISTS newsagentdb;
CREATE DATABASE IF NOT EXISTS newsagentdb;
USE newsagentdb;
-- select * from userdetails;
-- insert into userdetails values (null, "n", "n", "newsagent");
-- insert into userdetails values (null,"d", "d", "driver");
-- insert into userdetails values (null,"a", "a", "admin");customerdetails
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

-- Create the 'publications' table
DROP TABLE IF EXISTS publications;
CREATE TABLE publications (
	publicationID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    issueNo INT NOT NULL,
    author VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL
);

-- INSERT INTO PUBLICATIONS
INSERT INTO publications (title, issueNo, author, price, stock) VALUES
    ('Irish Independent', 100, 'John Doe', 2, 100),
    ('Irish Times', 123, 'John Smith', 1, 350),
    ('Time', 456, 'Jane Doe', 3, 500),
    ('Offaly Topic', 789, 'Michael Johnson', 2, 200),
    ('Irish Independent', 321, 'John Doe', 2, 150),
    ('Irish Times', 234, 'John Smith', 1, 400),
    ('Westmeath Independent', 567, 'Robert Green', 2, 300),
    ('Vogue', 123, 'Emily White', 4, 600),
    ('Time', 890, 'Jane Doe', 3, 250),
    ('Irish Independent', 432, 'John Doe', 2, 350),
    ('Irish Times', 765, 'John Smith', 1, 300),
    ('Offaly Topic', 109, 'Michael Johnson', 2, 450),
    ('Vogue', 543, 'Emily White', 4, 200),
    ('Rolling Stone', 234, 'Liam Johnson', 1, 750),
    ('Irish Times', 456, 'John Smith', 1, 300),
    ('Offaly Topic', 654, 'Michael Johnson', 2, 400),
    ('Vogue', 987, 'Emily White', 4, 350),
    ('Irish Times', 234, 'John Smith', 1, 250),
    ('Irish Independent', 402, 'John Doe', 2, 400),
    ('Irish Times', 111, 'John Smith', 1, 280)
;

-- Create the 'customerdetails' table
DROP TABLE IF EXISTS customerdetails;
CREATE TABLE IF NOT EXISTS customerdetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,    
    address VARCHAR(50) NOT NULL,
    phoneNo INT(15) DEFAULT NULL,
    areaCode int(12) NOT NULL,
    CONSTRAINT unique_customer_details UNIQUE (firstName, lastName, address, phoneNo)
);

-- INSERT INTO customers 
INSERT INTO customerdetails (firstName, lastName, address, phoneNo, areaCode) VALUES
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
('Ciara', 'Taylor',6, '222 River St, Longford', 555999999),
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

-- Create the 'orders' table
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated date NOT NULL,
    custID INT NOT NULL,
    orderType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    publicationID INT NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID) ON DELETE CASCADE,
    FOREIGN KEY (publicationID) REFERENCES publications (publicationID) ON DELETE CASCADE
);


-- INSERT INTO ORDERS
INSERT INTO orders (dateCreated, custID, orderType, publicationID) VALUES 
('2023-10-10', 1, 'daily', 20),
('2023-10-20', 2, 'weekly', 19),
('2023-10-21', 3, 'monthly', 18),
('2023-10-22', 4, 'daily', 17),
('2023-10-23', 5, 'weekly', 16),
('2023-10-24', 6, 'monthly', 15),
('2023-10-25', 7, 'daily', 14),
('2023-10-26', 8, 'weekly', 13), 
('2023-10-27', 9, 'monthly', 12), 
('2023-10-28', 10, 'daily', 11),
('2023-11-13', 11, 'weekly', 10),
('2023-11-13', 12, 'weekly', 9),
('2023-11-13', 13, 'monthly', 8),
('2023-11-13', 14, 'daily', 7),
('2023-11-13', 15, 'weekly', 6),
('2023-11-13', 16, 'monthly', 5),
('2023-11-13', 17, 'daily', 4),
('2023-11-13', 18, 'weekly', 3), 
('2023-11-13', 19, 'monthly', 2), 
('2023-11-13', 20, 'daily', 1),
('2023-11-13', 21, 'weekly', 20),	
('2023-11-13', 22, 'monthly', 19),
('2023-11-13', 23, 'daily', 18),
('2023-11-13', 24, 'weekly', 17),
('2023-11-13', 25, 'monthly', 16),
('2023-11-13', 26, 'daily', 15),
('2023-11-13', 27, 'weekly', 14), 
('2023-11-13', 28, 'monthly', 13), 
('2023-11-13', 29, 'daily', 12),
('2023-11-13', 2, 'weekly', 11),
('2023-11-13', 1, 'monthly', 10);

-- Temporary table to generate dates up to 06/12/2023
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

-- Insert orders based on order frequency
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
    
-- Drop temporary table
DROP TEMPORARY TABLE IF EXISTS temp_dates;

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

-- Display data
SELECT * FROM invoices ORDER BY custID;


DROP TABLE IF EXISTS invoices;
DROP TABLE IF EXISTS orders;


-- Display data
SELECT * FROM userdetails ORDER BY userID;
SELECT * FROM customerdetails ORDER BY custID;
SELECT * FROM publications ORDER BY publicationID;
SELECT * FROM orders ORDER BY orderID;

ALTER TABLE customerdetails drop foreign key custID;
alter table customerdetails add constraint custID foreign key (custID) references customerdetails (custID) on delete cascade;