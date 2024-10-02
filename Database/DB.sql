-- Drop existing database
DROP DATABASE IF EXISTS shopcake;

-- Create new database
CREATE DATABASE shopcake;
USE shopcake;

-- Create 'voucher' table
CREATE TABLE voucher (
                         code VARCHAR(6) PRIMARY KEY,
                         value INT NOT NULL,
                         quantity INT NOT NULL,
                         remaining INT NOT NULL,
                         status INT,
                         createdAt DATE,
                         updatedAt DATE
);

-- Create 'authority' table
CREATE TABLE authority (
                           code INT PRIMARY KEY,
                           name VARCHAR(50)
);

-- Create 'user' table
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username TEXT,
                      password TEXT,
                      fName TEXT,
                      gender BIT,
                      email TEXT,
                      phone VARCHAR(13),
                      dob DATE,
                      avatar TEXT,
                      address TEXT,
                      status INT,
                      createdAt DATE,
                      updatedAt DATE,
                      role INT,
                      CONSTRAINT FK_Authority_User FOREIGN KEY (role) REFERENCES authority(code)
);

-- Create 'order' table
CREATE TABLE `order` (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         consignee TEXT,
                         phone VARCHAR(13),
                         email text, 
                         shipAddress TEXT,
                         orderTime DATETIME,
                         deliveryTime DATETIME,
                         receiptTime DATETIME,
                         status INT,
                         voucherCode VARCHAR(6),
                         userId INT,
                         CONSTRAINT FK_Voucher_Order FOREIGN KEY (voucherCode) REFERENCES voucher(code),
                         CONSTRAINT FK_User_Order FOREIGN KEY (userId) REFERENCES user(id)
);

-- Create 'cart' table
CREATE TABLE cart (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      userID INT UNIQUE,
                      CONSTRAINT FK_User_Cart FOREIGN KEY (userID) REFERENCES user(id)
);

-- Create 'category' table
CREATE TABLE category (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name TEXT,
                          status INT,
                          createdAt DATE,
                          updatedAt DATE
);

-- Create 'product' table
CREATE TABLE product (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name TEXT,
                         ingredient TEXT,
                         description TEXT,
                         status INT,
                         createdAt DATE,
                         updatedAt DATE,
                         categoryID INT,
                         CONSTRAINT FK_Category_Product FOREIGN KEY (categoryID) REFERENCES category(id)
);

-- Create 'productDetail' table
CREATE TABLE productDetail (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               price FLOAT,
                               size VARCHAR(30),
                               productID INT,
                               CONSTRAINT FK_Product_ProductDetail FOREIGN KEY (productID) REFERENCES product(id)
);

-- Create 'cartDetail' table
CREATE TABLE cartDetail (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            quantity INT,
                            status INT,
                            cartID INT,
                            CONSTRAINT FK_Cart_CartDetail FOREIGN KEY (cartID) REFERENCES cart(id),
                            productDetailID INT,
                            CONSTRAINT FK_ProductDetail_CartDetail FOREIGN KEY (productDetailID) REFERENCES productDetail(id)
);

-- Create 'post' table
CREATE TABLE post (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      title TEXT,
                      content TEXT,
                      status INT,
                      createdAt DATE,
                      updatedAt DATE,
                      userID INT,
                      CONSTRAINT FK_User_Post FOREIGN KEY (userID) REFERENCES user(id)
);

-- Create 'media' table
CREATE TABLE media (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       image TEXT,
                       postID INT,
                       productID INT,
                       CONSTRAINT FK_Post_Media FOREIGN KEY (postID) REFERENCES post(id),
                       CONSTRAINT FK_Product_Media FOREIGN KEY (productID) REFERENCES product(id)
);

-- Create 'orderDetail' table
CREATE TABLE orderDetail (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             price FLOAT,
                             quantity INT,
                             orderID INT,
                             productDetailID INT,
                             CONSTRAINT FK_Order_OrderDetail FOREIGN KEY (orderID) REFERENCES `order`(id),
                             CONSTRAINT FK_ProductDetail_OrderDetail FOREIGN KEY (productDetailID) REFERENCES productDetail(id)
);

################################################################
INSERT INTO voucher (code, value, quantity, remaining, status, createdAt, updatedAt) VALUES
    ('ABC123', 10, 100, 80, 1, '2024-09-01', '2024-09-10'),
    ('XYZ456', 20, 50, 45, 1, '2024-09-02', '2024-09-11'),
    ('LMN789', 15, 200, 180, 1, '2024-09-03', '2024-09-12'),
    ('DEF321', 25, 75, 60, 0, '2024-09-04', '2024-09-13'),
    ('GHI654', 30, 100, 70, 1, '2024-09-05', '2024-09-14'),
    ('JKL987', 50, 150, 140, 1, '2024-09-06', '2024-09-15'),
    ('QWE123', 5, 90, 85, 0, '2024-09-07', '2024-09-16'),
    ('RTY456', 35, 60, 40, 1, '2024-09-08', '2024-09-17'),
    ('UIO789', 40, 120, 100, 1, '2024-09-09', '2024-09-18'),
    ('PAS123', 20, 80, 60, 1, '2024-09-10', '2024-09-19');


INSERT INTO authority (code, name) VALUES
    (1, 'Customer'),
    (2, 'Staff'),
    (3, 'Shipper'),
    (4, 'Admin');

INSERT INTO user (username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role) VALUES
    ('customer', '12345', 'John Doe', 1, 'john@example.com', '0123456789', '1990-01-01', '01.jpg', '123 Main St', 1, '2024-09-01', '2024-09-10', 1),
    ('staff', '12345', 'Jane Smith', 0, 'jane@example.com', '0123456798', '1992-02-02', '02.jpg', '456 Park Ave', 1, '2024-09-02', '2024-09-11', 2),
    ('shipper', '12345', 'Alice Jones', 0, 'alice@example.com', '0123456797', '1993-03-03', '03.jpg', '789 Oak St', 1, '2024-09-03', '2024-09-12', 3),
    ('admin', '12345', 'Bob Brown', 1, 'bob@example.com', '0123456796', '1994-04-04', '04.jpg', '321 Pine St', 1, '2024-09-04', '2024-09-13', 4),
    ('charlie_black', 'password654', 'Charlie Black', 1, 'charlie@example.com', '0123456795', '1995-05-05', '05.jpg', '654 Cedar St', 0, '2024-09-05', '2024-09-14', 1),
    ('david_green', 'password987', 'David Green', 1, 'david@example.com', '0123456794', '1996-06-06', '07.jpg', '987 Birch St', 0, '2024-09-06', '2024-09-15', 2),
    ('emily_white', 'password111', 'Emily White', 0, 'emily@example.com', '0123456793', '1997-07-07', '08.jpg', '111 Elm St', 0, '2024-09-07', '2024-09-16', 3),
    ('frank_silver', 'password222', 'Frank Silver', 1, 'frank@example.com', '0123456792', '1998-08-08', '09.jpg', '222 Maple St', 0, '2024-09-08', '2024-09-17', 4),
    ('george_gold', 'password333', 'George Gold', 1, 'george@example.com', '0123456791', '1999-09-09', '10.jpg', '333 Willow St', 1, '2024-09-09', '2024-09-18', 1),
    ('hannah_blue', 'password444', 'Hannah Blue', 0, 'hannah@example.com', '0123456790', '2000-10-10', '11.jpg', '444 Ash St', 1, '2024-09-10', '2024-09-19', 2),
    ('sam_adams', 'password555', 'Sam Adams', 1, 'sam@example.com', '0123456701', '1991-01-01', '12.jpg', '101 Liberty St', 1, '2024-09-01', '2024-09-10', 1),
    ('mia_clark', 'password666', 'Mia Clark', 0, 'mia@example.com', '0123456702', '1990-02-01', '01.jpg', '202 Freedom Ave', 0, '2024-09-02', '2024-09-11', 2),
    ('liam_taylor', 'password777', 'Liam Taylor', 1, 'liam@example.com', '0123456703', '1992-03-01', '02.jpg', '303 Unity Rd', 1, '2024-09-03', '2024-09-12', 3),
    ('sophia_hill', 'password888', 'Sophia Hill', 0, 'sophia@example.com', '0123456704', '1991-04-01', '03.jpg', '404 Harmony St', 0, '2024-09-04', '2024-09-13', 4),
    ('noah_jones', 'password999', 'Noah Jones', 1, 'noah@example.com', '0123456705', '1993-05-01', '04.jpg', '505 Victory Ln', 1, '2024-09-05', '2024-09-14', 1),
    ('olivia_johnson', 'password101', 'Olivia Johnson', 0, 'olivia@example.com', '0123456706', '1990-06-01', '05.jpg', '606 Equality Ave', 1, '2024-09-06', '2024-09-15', 2),
    ('logan_brown', 'password102', 'Logan Brown', 1, 'logan@example.com', '0123456707', '1994-07-01', '06.jpg', '707 Justice St', 1, '2024-09-07', '2024-09-16', 3),
    ('emma_thomas', 'password103', 'Emma Thomas', 0, 'emma@example.com', '0123456708', '1992-08-01', '07.jpg', '808 Prosperity Blvd', 1, '2024-09-08', '2024-09-17', 4),
    ('jacob_garcia', 'password104', 'Jacob Garcia', 1, 'jacob@example.com', '0123456709', '1991-09-01', '08.jpg', '909 Serenity Ln', 1, '2024-09-09', '2024-09-18', 1),
    ('amelia_martin', 'password105', 'Amelia Martin', 0, 'amelia@example.com', '0123456710', '1990-10-01', '09.jpg', '1010 Paradise St', 1, '2024-09-10', '2024-09-19', 2),
    ('ethan_lee', 'password106', 'Ethan Lee', 1, 'ethan@example.com', '0123456711', '1993-11-01', '10.jpg', '1111 Liberty Rd', 1, '2024-09-11', '2024-09-20', 3),
    ('ava_clarkson', 'password107', 'Ava Clarkson', 0, 'ava@example.com', '0123456712', '1992-12-01', '11.jpg', '1212 Freedom Ln', 1, '2024-09-12', '2024-09-21', 4),
    ('mason_wright', 'password108', 'Mason Wright', 1, 'mason@example.com', '0123456713', '1991-11-01', '12.jpg', '1313 Unity Ave', 1, '2024-09-13', '2024-09-22', 1),
    ('isabella_hall', 'password109', 'Isabella Hall', 0, 'isabella@example.com', '0123456714', '1990-01-01', '01.jpg', '1414 Victory Blvd', 1, '2024-09-14', '2024-09-23', 2),
    ('james_turner', 'password110', 'James Turner', 1, 'james@example.com', '0123456715', '1994-02-01', '01.jpg', '1515 Equality St', 1, '2024-09-15', '2024-09-24', 3);


INSERT INTO category (name, status, createdAt, updatedAt) VALUES
    ('Birthday Cakes', 1, '2024-09-01', '2024-09-10'),
    ('Wedding Cakes', 1, '2024-09-02', '2024-09-11'),
    ('Cupcakes', 1, '2024-09-03', '2024-09-12'),
    ('Custom Cakes', 1, '2024-09-04', '2024-09-13'),
    ('Desserts', 1, '2024-09-05', '2024-09-14'),
    ('Chocolate Cakes', 0, '2024-09-06', '2024-09-15'),
    ('Fruit Cakes', 1, '2024-09-07', '2024-09-16'),
    ('Pastries', 1, '2024-09-08', '2024-09-17'),
    ('Cheesecakes', 1, '2024-09-09', '2024-09-18'),
    ('Ice Cream Cakes', 1, '2024-09-10', '2024-09-19');

INSERT INTO product (name, ingredient, description, status, createdAt, updatedAt, categoryID) VALUES
    ('Chocolate Cake', 'Chocolate, Flour, Sugar, Eggs', 'Delicious chocolate cake', 1, '2024-09-01', '2024-09-10', 1),
    ('Vanilla Cupcake', 'Vanilla, Flour, Sugar, Eggs', 'Sweet vanilla cupcake', 1, '2024-09-02', '2024-09-11', 3),
    ('Strawberry Cheesecake', 'Strawberries, Cheese, Sugar, Eggs', 'Fresh strawberry cheesecake', 1, '2024-09-03', '2024-09-12', 9),
    ('Wedding Cake', 'Flour, Sugar, Eggs, Butter', 'Elegant wedding cake', 1, '2024-09-04', '2024-09-13', 2),
    ('Fruit Tart', 'Fruits, Sugar, Cream', 'Delicious fruit tart', 1, '2024-09-05', '2024-09-14', 8),
    ('Custom Birthday Cake', 'Custom ingredients', 'Personalized birthday cake', 1, '2024-09-06', '2024-09-15', 4),
    ('Chocolate Cupcake', 'Chocolate, Flour, Sugar, Eggs', 'Rich chocolate cupcake', 1, '2024-09-07', '2024-09-16', 3),
    ('Ice Cream Cake', 'Ice Cream, Flour, Sugar, Eggs', 'Refreshing ice cream cake', 1, '2024-09-08', '2024-09-17', 10),
    ('Fruit Cake', 'Mixed Fruits, Flour, Sugar, Eggs', 'Tasty fruit cake', 0, '2024-09-09', '2024-09-18', 7),
    ('Pastry Delight', 'Flour, Sugar, Butter', 'Assorted pastries', 1, '2024-09-10', '2024-09-19', 8);

INSERT INTO productDetail (price, size, productID) VALUES
    (15.99, 'Small', 1),
    (25.99, 'Medium', 1),
    (35.99, 'Large', 1),
    (2.99, 'Single', 2),
    (5.99, 'Pack of 4', 2),
    (29.99, 'Whole', 3),
    (45.99, '2-Tier', 4),
    (65.99, '3-Tier', 4),
    (12.99, 'Small', 5),
    (18.99, 'Large', 5);

INSERT INTO cart (userID) VALUES
    (1),
    (5),
    (9);

INSERT INTO cartDetail (quantity, status, cartID, productDetailID) VALUES
    (2, 1, 1, 1),
    (3, 0, 1, 2),
    (1, 1, 1, 4),
    (4, 1, 2, 5),
    (5, 1, 2, 3),
    (1, 1, 2, 6),
    (2, 1, 2, 8),
    (3, 1, 3, 9),
    (4, 1, 3, 10),
    (5, 1, 3, 7);

insert into `order` (consignee, phone, shipAddress, orderTime, deliveryTime, receiptTime, status, voucherCode, userId, email) VALUES
    ('John Doe', '0123456789', '123 Main St', '2024-09-10 14:00:00', '2024-09-12 10:00:00', '2024-09-12 12:00:00', 1, 'ABC123', 1, '123@gmail.com'),
    ('Jane Smith', '0123456798', '456 Park Ave', '2024-09-11 15:00:00', '2024-09-13 11:00:00', '2024-09-13 13:00:00', 1, 'XYZ456', 1, '123@gmail.com'),
    ('Alice Jones', '0123456797', '789 Oak St', '2024-09-12 16:00:00', '2024-09-14 12:00:00', '2024-09-14 14:00:00', 1, 'LMN789', 5, '123@gmail.com'),
    ('Bob Brown', '0123456796', '321 Pine St', '2024-09-13 17:00:00', '2024-09-15 13:00:00', '2024-09-15 15:00:00', 1, 'DEF321', 5, '123@gmail.com'),
    ('Charlie Black', '0123456795', '654 Cedar St', '2024-09-14 18:00:00', '2024-09-16 14:00:00', '2024-09-16 16:00:00', 1, 'GHI654', 9, '123@gmail.com'),
    ('David Green', '0123456794', '987 Birch St', '2024-09-15 19:00:00', '2024-09-17 15:00:00', '2024-09-17 17:00:00', 1, 'JKL987', 9, '123@gmail.com'),
    ('Emily White', '0123456793', '111 Elm St', '2024-09-16 20:00:00', '2024-09-18 16:00:00', '2024-09-18 18:00:00', 1, 'QWE123', 1, '123@gmail.com'),
    ('Frank Silver', '0123456792', '222 Maple St', '2024-09-17 21:00:00', '2024-09-19 17:00:00', '2024-09-19 19:00:00', 1, 'RTY456', 5, '123@gmail.com'),
    ('George Gold', '0123456791', '333 Willow St', '2024-09-18 22:00:00', '2024-09-20 18:00:00', '2024-09-20 20:00:00', 1, 'UIO789', 9, '123@gmail.com'),
    ('Hannah Blue', '0123456790', '444 Ash St', '2024-09-19 23:00:00', '2024-09-21 19:00:00', '2024-09-21 21:00:00', 1, 'PAS123', 1, '123@gmail.com');
INSERT INTO post (title, content, status, createdAt, updatedAt) VALUES
('New Cake Recipe', 'Check out our new cake recipe!', 1, '2024-09-18', '2024-09-18'),
('Special Pastry', 'Special offer on our croissants!', 1, '2024-09-18', '2024-09-18');

INSERT INTO media (image, postID, productID) VALUES
('145688445-chup-hinh-banh-ngot_08.jpg', 1, NULL),
('145688445-chup-hinh-banh-ngot_08.jpg', 2, NULL);

INSERT INTO media (image, postID, productID) VALUES
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 1),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 1),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 2),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 2),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 3),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 3),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 4),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 4),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 5),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 5),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 6),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 6),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 7),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 7),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 8),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 8),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 9),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 9),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 10),
('145688445-chup-hinh-banh-ngot_08.jpg', NULL, 10);
DELIMITER $$
CREATE PROCEDURE insertUser(
	IN p_username TEXT,
	IN p_password TEXT,
	IN p_fName TEXT,
	IN p_gender BIT,
	IN p_email TEXT,
	IN p_phone VARCHAR(13),
	IN p_dob DATE,
	IN p_avatar TEXT,
	IN p_address TEXT,
	IN p_status INT,
	IN p_role INT
)
BEGIN
	-- Insert dữ liệu vào bảng user
	INSERT INTO users (username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role)
	VALUES (p_username, p_password, p_fName, p_gender, p_email, p_phone, p_dob, p_avatar, p_address, p_status, now(), now(), p_role);
	
	-- Trả về ID của user vừa mới được insert
	SELECT LAST_INSERT_ID() AS newUserID;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE insertOrder(
    IN p_consignee TEXT,
    IN p_phone VARCHAR(13),
    IN p_email text,
    IN p_shipAddress TEXT,
    IN p_voucherCode VARCHAR(6),
    IN p_userId INT
)
BEGIN
    -- Insert dữ liệu vào bảng `order`
    INSERT INTO `order` (consignee, phone,email, shipAddress, orderTime, deliveryTime, receiptTime, status, voucherCode, userId)
    VALUES (p_consignee, p_phone,p_email, p_shipAddress, now(), null, null, 1, p_voucherCode, p_userId);

    -- Trả về ID của order vừa mới được insert
    SELECT LAST_INSERT_ID() AS newOrderID;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE insertOrderDetail(
    IN p_price FLOAT,
    IN p_quantity INT,
    IN p_orderID INT,
    IN p_productDetailID INT
)
BEGIN
    -- Insert dữ liệu vào bảng `orderDetail`
    INSERT INTO orderDetail (price, quantity, orderID, productDetailID)
    VALUES (p_price, p_quantity, p_orderID, p_productDetailID);

    -- Trả về ID của order detail vừa mới được insert
    SELECT LAST_INSERT_ID() AS newOrderDetailID;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE insertProduct(
    IN p_name TEXT,
    IN p_ingredient TEXT,
    IN p_description TEXT,
    IN p_status INT,
    IN p_categoryID INT
)
BEGIN
    -- Insert dữ liệu vào bảng `product`
    INSERT INTO product (name, ingredient, description, status, createdAt, updatedAt, categoryID)
    VALUES (p_name, p_ingredient, p_description, p_status, now(), now(), p_categoryID);

    -- Trả về ID của sản phẩm vừa mới được insert
    SELECT LAST_INSERT_ID() AS newProductID;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE insertCart(
    IN p_userID INT
)
BEGIN
    -- Insert dữ liệu vào bảng `cart`
    INSERT INTO cart (userID)
    VALUES (p_userID);

    -- Trả về ID của giỏ hàng vừa mới được insert
    SELECT LAST_INSERT_ID() AS newCartID;
END$$
DELIMITER ;

