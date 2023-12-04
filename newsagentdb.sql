insert into customerdetails (firstName, lastName, address, phoneNo, areaCode) 
values ("Natalia", "Palej", "123 Main St", "555-123-4567", 1);

select * from customerdetails;
delete from customerdetails;

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
(11, 'Bond', '007', '008 Willow St', '555-001-2345', 11);

select * from orders;
select * from publications;
select * from customerdetails;
select * from invoices;


DROP DATABASE IF EXISTS newsagentdb;
CREATE DATABASE IF NOT EXISTS newsagentdb;
USE newsagentdb;
select * from userdetails;
insert into userdetails values (null, "n", "n", "newsagent");
insert into userdetails values (null,"d", "d", "driver");
insert into userdetails values (null,"a", "a", "admin");
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

drop table if exists publications;
-- INSERT INTO PUBLICATIONS
INSERT INTO publications (title, issueNo, author, price, stock) VALUES
    ('Daily', 100, 'John Doe', 2, 100),
    ('Weekly', 123, 'John Smith', 1, 350),
    ('Montly', 456, 'Jane Doe', 3, 500),
    ('Kids', 789, 'Michael Johnson', 2, 200),
    ('IT', 321, 'John Doe', 2, 150)
;

-- Create the 'customerdetails' table
DROP TABLE IF EXISTS customerdetails;
CREATE TABLE IF NOT EXISTS customerdetails (
    custID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(15) NOT NULL,
    lastName VARCHAR(15) NOT NULL,    
    address VARCHAR(50) NOT NULL,
    phoneNo VARCHAR(15) DEFAULT NULL,
    areaCode int NOT NULL
);

-- INSERT INTO customers 
INSERT INTO customerdetails (firstName, lastName, address, phoneNo, areaCode) VALUES
('John', 'Doe', '123 Main St , Carlow', '555-986-7254', 5),
('Alice', 'Smith', '456 Elm St, Dublin', '555-986-6654', 4),
('Robert', 'Johnson', '789 Oak St, Dublin', '555-555-6555', 3),
('Sara', 'Williams', '1122 Elm St, Dublin', '555-776-7777', 2),
('Michael', 'Brown', '88 Oak St, Kildare', '555-999-6999', 1),
('Emily', 'Miller', '321 Pine St,Kildare', '555-333-4644', 12),
('Daniel', 'Clark', '789 Snow St, Kilkenny', '555-555-6555', 11),
('Emanuel', 'Garcia', '111 Three St, Laois', '555-767-7777', 10),
('Ciara', 'Taylor', '222 River St, Longford', '555-969-9999', 9),
('Mark', 'Johnson',  '789 Oak St, Carlow', '555-444-6555', 8),
('Emma', 'Davis',  '456 Pine St, Dublin', '555-333-6666', 7),
('Christopher', 'Lee', '123 Maple St, Kildare', '555-622-2777', 6),
('Olivia', 'Moore',  'Birch St, Kilkenny', '555-111-6888', 5),
('James', 'White', 'Laois', '555-777-6000', 4),
('Sophia', 'Hall',  'Redwood St, Longford', '555-888-6999', 1),
('Liam', 'Jones',  'Willow St, Louth', '555-999-1611', 2),
('Ava', 'Taylor', 'Oak St, Meath', '555-222-3363', 3),
('Noah', 'Brown',  'Pine St, Offaly', '555-444-5565', 12),
('Isabella', 'Wilson',  'Elm St, Westmeath', '555-666-7677', 11),
('Logan', 'Anderson', 'Maple St, Wexford', '555-888-9996', 10);


-- Create the 'orders' table
DROP TABLE IF EXISTS orders;
select * from orders;
CREATE TABLE orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    dateCreated DATE NOT NULL,
    custID INT NOT NULL,
    orderType ENUM('daily', 'weekly', 'monthly') NOT NULL,
    publicationID INT NOT NULL,
    FOREIGN KEY (custID) REFERENCES customerdetails (custID) ON DELETE CASCADE,
    FOREIGN KEY (publicationID) REFERENCES publications (publicationID) ON DELETE CASCADE
);

select * from customerdetails;
select * from orders;

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

drop table if exists invoices;
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







-- Display data
SELECT * FROM userdetails ORDER BY userID;
SELECT * FROM customerdetails ORDER BY custID;
SELECT * FROM publications ORDER BY publicationID;
SELECT * FROM orders ORDER BY orderID;



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