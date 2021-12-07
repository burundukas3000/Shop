create database accenture;

use accenture;
--
-- Table structure for table user
-- Passwords are encrypted using BCrypt
-- Passwords generated at: http://www.luv2code.com/generate-bcrypt-password
-- Passwords for created users: the same what username (e.g. uname: admin pssw: admin)
--
DROP TABLE IF EXISTS user;

CREATE TABLE IF NOT EXISTS user (
    id int(11) NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    password char(80) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for user
--
INSERT INTO user (email, password, username) VALUES
                                                 ('admin@gmail.com', '$2a$10$62AAhgEWKwHLzEs1k1dqEuwOVHIM2MgCbI.yzLbx./5m2md9StfAC','admin'),
                                                 ('baiba.skujevsk@gmail.com', '$2a$10$TZXYm3FwHbhLlGkmClsIpOnk7jQcOKe2Rt2ByHMUsTNC.u4alo5Gy','manager'),
                                                 ('aprily@inbox.lv', '$2a$10$BPFrIEJvcjMY8oV2cQHYS.hqO9g6oAXKBqWOQtcT24GBRtBIw8rCq','customer');

--
-- Creating table for role
--        
CREATE TABLE IF NOT EXISTS role (
    id int(11) NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for role
--

INSERT INTO role VALUES
                     (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER'),(3,'ROLE_CUSTOMER');

--
-- Table structure for table users_roles
--
CREATE TABLE IF NOT EXISTS users_roles (
    user_id int(11) NOT NULL,
    role_id int(11) NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT FK_USER_01 FOREIGN KEY (user_id)
    REFERENCES user (id)
    ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT FK_ROLE_02 FOREIGN KEY (role_id)
    REFERENCES role (id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table users_roles
--
INSERT INTO users_roles (user_id, role_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (3, 3);


--
-- Creating table for discounts
--
SET TIME_ZONE = '+02:00';

CREATE TABLE IF NOT EXISTS discount (
    id int(11) NOT NULL AUTO_INCREMENT,
    promo varchar(10) NOT NULL,
    date_from date,
    date_to date,
    percentage int(3) NOT NULL,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for discount
--
INSERT INTO discount (promo, date_from, date_to, percentage) VALUES
                                                                 ('NEWYEAR', now(), '2021-12-31', 20),
                                                                 ('LOYAL', null, null, 5),
                                                                 ('XMAS', '2021-12-10', '2021-12-24', 30);

--
-- Creating table for product
--
CREATE TABLE IF NOT EXISTS product (
    id int(11) NOT NULL AUTO_INCREMENT,
    title varchar(50) NOT NULL,
    price decimal(10,2) NOT NULL,
    info varchar(300) NOT NULL,
    amount int(10) NOT NULL,
    category varchar(50) NOT NULL,
    discount_id int(11),

    PRIMARY KEY (id),
    CONSTRAINT FK_DISC_03 FOREIGN KEY (discount_id)
    REFERENCES discount (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for product
--
INSERT INTO product (title, price, info, amount, category, discount_id) VALUES
                                                                            ('pink bag', 20,'little bag for a madam', 20, 'ACCESSORIES', null),
                                                                            ('truck', 21.50, 'large truck', 7, 'TOYS', 1),
                                                                            ('red dress', 80.99,'party dress', 3, 'CLOTHES', 3);

--
-- Creating table for image
--
CREATE TABLE image (
                       id int(11) NOT NULL AUTO_INCREMENT,
                       name varchar(128) DEFAULT NULL,
                       content longblob,
                       product_id int(11) NOT NULL,
                       PRIMARY KEY (id),

                       CONSTRAINT FK_PROD_06 FOREIGN KEY (product_id)
                           REFERENCES product (id)
                           ON DELETE CASCADE ON UPDATE CASCADE
);

--
-- Creating table for order
--
CREATE TABLE IF NOT EXISTS purchase (
    id int(11) NOT NULL AUTO_INCREMENT,
    status varchar(50) NOT NULL,
    date_created date,
    date_completed date,
    date_cancelled date,
    user_id int(11) NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FK_USER_03 FOREIGN KEY (user_id)
    REFERENCES user (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for purchase
--
INSERT INTO purchase (status, date_created, date_completed, date_cancelled, user_id) VALUES
                                                                                         ('NEW', now(), null, null, 1),
                                                                                         ('COMPLETED', '2021-12-01', '2021-12-03', null, 2),
                                                                                         ('CANCELLED', '2021-12-02', null , '2021-12-02', 2),
                                                                                         ('NEW', now(), null, null, 3),
                                                                                         ('COMPLETED', '2021-12-01', '2021-12-05', null, 3),
                                                                                         ('CANCELLED', '2021-12-06', null , '2021-12-06', 3);

--
-- Creating table for purchases_products
--

CREATE TABLE IF NOT EXISTS purchases_products (
    purchase_id int(11) NOT NULL,
    product_id int(11) NOT NULL,
    amount int(4) NOT NULL,

    PRIMARY KEY (purchase_id, product_id),

    CONSTRAINT FK_PURCH_04 FOREIGN KEY (purchase_id)
    REFERENCES purchase (id)
    ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT FK_PROD_05 FOREIGN KEY (product_id)
    REFERENCES product (id)
    ON DELETE CASCADE ON UPDATE CASCADE
    ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table purchases_products
--
INSERT INTO purchases_products (purchase_id, product_id, amount)
VALUES
    (1, 1, 1),
    (1, 2, 1),
    (2, 3, 2),
    (3, 2, 1),
    (4, 1, 5),
    (5, 1, 1),
    (5, 2, 1),
    (6, 1, 4);


--
-- Checking data
--
SELECT * FROM purchase;

-- Selecting products with related discounts

SELECT * FROM product p
                  LEFT JOIN
              discount d
              ON p.discount_id = d.id;

-- Selecting purchase with related product and customer

SELECT *
FROM purchase AS pur
         LEFT JOIN purchases_products AS pp
                   ON pur.id = pp.purchase_id
         LEFT JOIN product AS pr
                   ON pp.product_id = pr.id;

use accenture;
SELECT * FROM role;