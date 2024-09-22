DROP DATABASE IF EXISTS shopcake;

CREATE DATABASE shopcake;

USE shopcake;

CREATE TABLE voucher (
    code VARCHAR(6) PRIMARY KEY,
    value INT NOT NULL,
    quantity INT NOT NULL,
    remaining INT NOT NULL,
    status INT,
    createdAt DATE,
    updatedAt DATE
);

CREATE TABLE authority (
    code INT PRIMARY KEY,
    name VARCHAR(50)
);

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
    FOREIGN KEY (role) REFERENCES authority(code)
);

CREATE TABLE `order` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consignee TEXT,
    phone VARCHAR(13),
    shipAddress TEXT,
    orderTime DATETIME,
    deliveryTime DATETIME,
    receiptTime DATETIME,
    status INT,
    voucherCode VARCHAR(6),
    FOREIGN KEY (voucherCode) REFERENCES voucher(code),
    userId INT,
    FOREIGN KEY (userId) REFERENCES user(id)
);

CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    userID INT UNIQUE,
    FOREIGN KEY (userID) REFERENCES user(id)
);

CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name TEXT,
    status INT,
    createdAt DATE,
    updatedAt DATE
);

CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name TEXT,
    ingredient TEXT,
    description TEXT,
    status INT,
    createdAt DATE,
    updatedAt DATE,
    categoryID INT,
    FOREIGN KEY (categoryID) REFERENCES category(id)
);

CREATE TABLE productDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    price FLOAT,
    size VARCHAR(30),
    productID INT,
    FOREIGN KEY (productID) REFERENCES product(id)
);

CREATE TABLE cartDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    status INT,
    cartID INT,
    FOREIGN KEY (cartID) REFERENCES cart(id),
    productDetailID INT,
    FOREIGN KEY (productDetailID) REFERENCES productDetail(id)
);

CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title TEXT,
    content TEXT,
    status INT,
    createdAt DATE,
    updatedAt DATE
);

CREATE TABLE media (
    id INT AUTO_INCREMENT PRIMARY KEY,
    image TEXT,
    postID INT,
    productID INT,
    FOREIGN KEY (postID) REFERENCES post(id),
    FOREIGN KEY (productID) REFERENCES product(id)
);

CREATE TABLE orderDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    price FLOAT,
    codeVoucher VARCHAR(6),
    quantity INT,
    orderID INT,
    productDetailID INT,
    FOREIGN KEY (orderID) REFERENCES `order`(id),
    FOREIGN KEY (productDetailID) REFERENCES productDetail(id)
);

INSERT INTO authority (code, name) VALUES 
(1, 'Customer'),
(2, 'Staff'),
(3, 'Shipper'),
(4, 'Admin');

INSERT INTO voucher (code, value, quantity, remaining, status, createdAt, updatedAt) VALUES
('VCH001', 100, 50, 50, 1, '2024-09-18', '2024-09-18'),
('VCH002', 200, 30, 30, 1, '2024-09-18', '2024-09-18');

INSERT INTO user (username, password, fName, gender, email, phone, dob, avatar, address, status, createdAt, updatedAt, role) VALUES
('user1', 'password1', 'John Doe', 1, 'johndoe@example.com', '123456789', '1990-01-01', 'avatar1.png', '123 Street', 1, '2024-09-18', '2024-09-18', 1),
('user2', 'password2', 'Jane Smith', 0, 'janesmith@example.com', '987654321', '1992-02-02', 'avatar2.png', '456 Avenue', 1, '2024-09-18', '2024-09-18', 2);

INSERT INTO category (name, status, createdAt, updatedAt) VALUES
('Cakes', 1, '2024-09-18', '2024-09-18'),
('Pastries', 1, '2024-09-18', '2024-09-18');

INSERT INTO product (name, ingredient, description, status, createdAt, updatedAt, categoryID) VALUES
('Chocolate Cake', 'Chocolate, Flour, Sugar, Eggs', 'Rich chocolate cake with creamy frosting', 1, '2024-09-18', '2024-09-18', 1),
('Strawberry Tart', 'Strawberries, Flour, Sugar, Butter', 'Fresh strawberry tart with a crispy crust', 1, '2024-09-18', '2024-09-18', 1),
('Lemon Pie', 'Lemon, Flour, Sugar, Butter', 'Tangy lemon pie with a golden crust', 1, '2024-09-18', '2024-09-18', 1),
('Vanilla Cupcake', 'Vanilla, Flour, Sugar, Eggs', 'Soft vanilla cupcakes with creamy frosting', 1, '2024-09-18', '2024-09-18', 1),
('Caramel Pudding', 'Caramel, Milk, Sugar, Eggs', 'Smooth caramel pudding topped with whipped cream', 1, '2024-09-18', '2024-09-18', 1),
('Blueberry Muffin', 'Blueberries, Flour, Sugar, Eggs', 'Moist muffin packed with fresh blueberries', 1, '2024-09-18', '2024-09-18', 2),
('Croissant', 'Butter, Flour, Yeast', 'Flaky croissant with a buttery texture', 1, '2024-09-18', '2024-09-18', 2),
('Cheesecake', 'Cream Cheese, Sugar, Eggs', 'Rich cheesecake with a graham cracker crust', 1, '2024-09-18', '2024-09-18', 1),
('Apple Pie', 'Apples, Flour, Sugar, Cinnamon', 'Classic apple pie with a flaky crust', 1, '2024-09-18', '2024-09-18', 1),
('Macaron', 'Almond Flour, Sugar, Egg Whites', 'French macarons in assorted flavors', 1, '2024-09-18', '2024-09-18', 2);

INSERT INTO productDetail (price, size, productID) VALUES
(20.0, 'Medium', 1),
(18.0, 'Small', 2),
(22.0, 'Large', 3),
(15.0, 'Small', 4),
(25.0, 'Medium', 5),
(10.0, 'Small', 6),
(12.0, 'Medium', 7),
(30.0, 'Large', 8),
(28.0, 'Large', 9),
(8.0, 'Small', 10),
(20.0, 'Large', 1),
(18.0, 'Large', 2),
(10.0, 'Small', 1);

INSERT INTO cart (userID) VALUES
(1),
(2);

INSERT INTO cartDetail (quantity, status, cartID, productDetailID) VALUES
(2, 1, 1, 1),
(3, 1, 2, 2);

INSERT INTO `order` (consignee, phone, shipAddress, orderTime, deliveryTime, receiptTime, status, voucherCode, userId) VALUES
('John Doe', '123456789', '123 Street', '2024-09-18 10:00:00', '2024-09-20 12:00:00', '2024-09-20 15:00:00', 1, 'VCH001', 1),
('Jane Smith', '987654321', '456 Avenue', '2024-09-18 11:00:00', '2024-09-21 13:00:00', '2024-09-21 16:00:00', 1, 'VCH002', 2);

INSERT INTO orderDetail (price, codeVoucher, quantity, orderID, productDetailID) VALUES
(20.0, 'VCH001', 2, 1, 1),
(5.0, 'VCH002', 3, 2, 2);

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
