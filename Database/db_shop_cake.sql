use [master]

go 

drop database [shopcake]

go 

use [master]

go

create database [shopcake]

go

use [shopcake]

go

CREATE TABLE [voucher] (
    [code] NVARCHAR(6) PRIMARY KEY,
    [value] INT NOT NULL,
    [quantity] INT NOT NULL,
    [remaining] INT NOT NULL,
    [status] INT,
    [createdAt] DATE,
    [updatedAt] DATE  
)

GO

CREATE TABLE [authority] (
    [code] INT PRIMARY KEY,
    [name] NVARCHAR(50)
)

GO

CREATE TABLE [user] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [username] NVARCHAR (max),
    [password] NVARCHAR (max),
    [fName] NVARCHAR(max),
    [gender] BIT,
    [email] NVARCHAR(max),
    [phone] NVARCHAR(13),
    [dob] DATE,
    [avatar] NVARCHAR(max),
    [address] NVARCHAR(max),
    [status] INT,
    [createdAt] DATE,
    [updatedAt] DATE,
    [role] INT,
    CONSTRAINT FK_Authority_User FOREIGN KEY ([role]) REFERENCES [authority]([code])
)

GO

CREATE TABLE [order] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [consignee] NVARCHAR(max),
    [phone] NVARCHAR(13),
    [shipAddress] NVARCHAR(max),
    [orderTime] DATETIME,
    [deliveryTime] DATETIME,
    [receiptTime] DATETIME,
    [status] INT,
    [voucherCode] NVARCHAR(6),
    CONSTRAINT FK_Voucher_Order FOREIGN KEY ([voucherCode]) REFERENCES [voucher] ([code]),
    [userId] INT,
    CONSTRAINT FK_User_Order FOREIGN KEY ([userId]) REFERENCES [user]([id])
)

GO

CREATE TABLE [cart] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [userID] INT UNIQUE,
    CONSTRAINT FK_User_Cart FOREIGN KEY ([userID]) REFERENCES [user]([id])
)

GO

CREATE TABLE [category] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [name] NVARCHAR(max),
    [status] INT,
    [createdAt] DATE,
    [updatedAt] DATE,
)

GO

CREATE TABLE [product] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [name] NVARCHAR(max),
    [ingredient] NVARCHAR(max),
    [description] NVARCHAR(max),
    [status] INT,
    [createdAt] DATE,
    [updatedAt] DATE,
    [categoryID] INT,
    CONSTRAINT FK_Category_Product FOREIGN KEY ([categoryID]) REFERENCES [category]([id])
)

GO

CREATE TABLE [productDetail] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [price] FLOAT,
    [size] NVARCHAR(30),
    [productID] INT,
    CONSTRAINT FK_Product_ProductDetail FOREIGN KEY ([productID]) REFERENCES [product]([id])
)

GO

CREATE TABLE [cartDetail] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [quantity] INT,
    [status] INT,
    [cartID] INT,
    CONSTRAINT FK_Cart_CartDetail FOREIGN KEY ([cartID]) REFERENCES [cart]([id]),
    [productDetailID] INT,
    CONSTRAINT FK_ProductDetail_CartDetail FOREIGN KEY ([productDetailID]) REFERENCES [productDetail]([id])
)

GO

CREATE TABLE [post] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [title] NVARCHAR(max),
    [content] NVARCHAR(max),
    [status] INT,
    [createdAt] DATE,
    [updatedAt] DATE,
	[userID] INT,
    CONSTRAINT FK_User_Post FOREIGN KEY ([userID]) REFERENCES [user]([id])
)

GO 

CREATE TABLE [media] (
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [image] NVARCHAR(max),
    [postID] INT,
    [productID] INT,
    CONSTRAINT FK_Post_Media FOREIGN KEY ([postID]) REFERENCES [post]([id]),
    CONSTRAINT FK_Product_Media FOREIGN KEY ([productID]) REFERENCES [product]([id])
)

go 

create table [orderDetail] (
	[id] int identity(1,1) primary key,
	[price] float,
	[codeVoucher] nvarchar(6),
	[quantity] int,
	[orderID] int,
	[productDetailID] int,
	CONSTRAINT FK_Order_OrderDetail FOREIGN KEY ([orderID]) REFERENCES [order]([id]),
	CONSTRAINT FK_ProductDetail_OrderDetail FOREIGN KEY ([productDetailID]) REFERENCES [productDetail]([id])
)
------------------------------------------------------


INSERT INTO [voucher] ([code], [value], [quantity], [remaining], [status], [createdAt], [updatedAt]) VALUES
('ABC123', 10, 100, 80, 1, '2024-09-01', '2024-09-10'),
('XYZ456', 20, 50, 45, 1, '2024-09-02', '2024-09-11'),
('LMN789', 15, 200, 180, 1, '2024-09-03', '2024-09-12'),
('DEF321', 25, 75, 60, 1, '2024-09-04', '2024-09-13'),
('GHI654', 30, 100, 70, 1, '2024-09-05', '2024-09-14'),
('JKL987', 50, 150, 140, 1, '2024-09-06', '2024-09-15'),
('QWE123', 5, 90, 85, 1, '2024-09-07', '2024-09-16'),
('RTY456', 35, 60, 40, 1, '2024-09-08', '2024-09-17'),
('UIO789', 40, 120, 100, 1, '2024-09-09', '2024-09-18'),
('PAS123', 20, 80, 60, 1, '2024-09-10', '2024-09-19');


INSERT INTO [authority] ([code], [name]) VALUES 
(1, 'Customer'),
(2, 'Staff'),
(3, 'Shipper'),
(4, 'Admin');

INSERT INTO [user] ([username], [password], [fName], [gender], [email], [phone], [dob], [avatar], [address], [status], [createdAt], [updatedAt], [role]) VALUES
('customer', '12345', 'John Doe', 1, 'john@example.com', '0123456789', '1990-01-01', 'john_avatar.png', '123 Main St', 1, '2024-09-01', '2024-09-10', 1),
('staff', '12345', 'Jane Smith', 0, 'jane@example.com', '0123456798', '1992-02-02', 'jane_avatar.png', '456 Park Ave', 1, '2024-09-02', '2024-09-11', 2),
('shipper', '12345', 'Alice Jones', 0, 'alice@example.com', '0123456797', '1993-03-03', 'alice_avatar.png', '789 Oak St', 1, '2024-09-03', '2024-09-12', 3),
('admin', '12345', 'Bob Brown', 1, 'bob@example.com', '0123456796', '1994-04-04', 'bob_avatar.png', '321 Pine St', 1, '2024-09-04', '2024-09-13', 4),
('charlie_black', 'password654', 'Charlie Black', 1, 'charlie@example.com', '0123456795', '1995-05-05', 'charlie_avatar.png', '654 Cedar St', 1, '2024-09-05', '2024-09-14', 1),
('david_green', 'password987', 'David Green', 1, 'david@example.com', '0123456794', '1996-06-06', 'david_avatar.png', '987 Birch St', 1, '2024-09-06', '2024-09-15', 2),
('emily_white', 'password111', 'Emily White', 0, 'emily@example.com', '0123456793', '1997-07-07', 'emily_avatar.png', '111 Elm St', 1, '2024-09-07', '2024-09-16', 3),
('frank_silver', 'password222', 'Frank Silver', 1, 'frank@example.com', '0123456792', '1998-08-08', 'frank_avatar.png', '222 Maple St', 1, '2024-09-08', '2024-09-17', 4),
('george_gold', 'password333', 'George Gold', 1, 'george@example.com', '0123456791', '1999-09-09', 'george_avatar.png', '333 Willow St', 1, '2024-09-09', '2024-09-18', 1),
('hannah_blue', 'password444', 'Hannah Blue', 0, 'hannah@example.com', '0123456790', '2000-10-10', 'hannah_avatar.png', '444 Ash St', 1, '2024-09-10', '2024-09-19', 2),
('sam_adams', 'password555', 'Sam Adams', 1, 'sam@example.com', '0123456701', '1991-01-01', 'sam_avatar.png', '101 Liberty St', 1, '2024-09-01', '2024-09-10', 1),
('mia_clark', 'password666', 'Mia Clark', 0, 'mia@example.com', '0123456702', '1990-02-01', 'mia_avatar.png', '202 Freedom Ave', 1, '2024-09-02', '2024-09-11', 2),
('liam_taylor', 'password777', 'Liam Taylor', 1, 'liam@example.com', '0123456703', '1992-03-01', 'liam_avatar.png', '303 Unity Rd', 1, '2024-09-03', '2024-09-12', 3),
('sophia_hill', 'password888', 'Sophia Hill', 0, 'sophia@example.com', '0123456704', '1991-04-01', 'sophia_avatar.png', '404 Harmony St', 1, '2024-09-04', '2024-09-13', 4),
('noah_jones', 'password999', 'Noah Jones', 1, 'noah@example.com', '0123456705', '1993-05-01', 'noah_avatar.png', '505 Victory Ln', 1, '2024-09-05', '2024-09-14', 1),
('olivia_johnson', 'password101', 'Olivia Johnson', 0, 'olivia@example.com', '0123456706', '1990-06-01', 'olivia_avatar.png', '606 Equality Ave', 1, '2024-09-06', '2024-09-15', 2),
('logan_brown', 'password102', 'Logan Brown', 1, 'logan@example.com', '0123456707', '1994-07-01', 'logan_avatar.png', '707 Justice St', 1, '2024-09-07', '2024-09-16', 3),
('emma_thomas', 'password103', 'Emma Thomas', 0, 'emma@example.com', '0123456708', '1992-08-01', 'emma_avatar.png', '808 Prosperity Blvd', 1, '2024-09-08', '2024-09-17', 4),
('jacob_garcia', 'password104', 'Jacob Garcia', 1, 'jacob@example.com', '0123456709', '1991-09-01', 'jacob_avatar.png', '909 Serenity Ln', 1, '2024-09-09', '2024-09-18', 1),
('amelia_martin', 'password105', 'Amelia Martin', 0, 'amelia@example.com', '0123456710', '1990-10-01', 'amelia_avatar.png', '1010 Paradise St', 1, '2024-09-10', '2024-09-19', 2),
('ethan_lee', 'password106', 'Ethan Lee', 1, 'ethan@example.com', '0123456711', '1993-11-01', 'ethan_avatar.png', '1111 Liberty Rd', 1, '2024-09-11', '2024-09-20', 3),
('ava_clarkson', 'password107', 'Ava Clarkson', 0, 'ava@example.com', '0123456712', '1992-12-01', 'ava_avatar.png', '1212 Freedom Ln', 1, '2024-09-12', '2024-09-21', 4),
('mason_wright', 'password108', 'Mason Wright', 1, 'mason@example.com', '0123456713', '1991-11-01', 'mason_avatar.png', '1313 Unity Ave', 1, '2024-09-13', '2024-09-22', 1),
('isabella_hall', 'password109', 'Isabella Hall', 0, 'isabella@example.com', '0123456714', '1990-01-01', 'isabella_avatar.png', '1414 Victory Blvd', 1, '2024-09-14', '2024-09-23', 2),
('james_turner', 'password110', 'James Turner', 1, 'james@example.com', '0123456715', '1994-02-01', 'james_avatar.png', '1515 Equality St', 1, '2024-09-15', '2024-09-24', 3);


INSERT INTO [category] ([name], [status], [createdAt], [updatedAt]) VALUES
('Birthday Cakes', 1, '2024-09-01', '2024-09-10'),
('Wedding Cakes', 1, '2024-09-02', '2024-09-11'),
('Cupcakes', 1, '2024-09-03', '2024-09-12'),
('Custom Cakes', 1, '2024-09-04', '2024-09-13'),
('Desserts', 1, '2024-09-05', '2024-09-14'),
('Chocolate Cakes', 1, '2024-09-06', '2024-09-15'),
('Fruit Cakes', 1, '2024-09-07', '2024-09-16'),
('Pastries', 1, '2024-09-08', '2024-09-17'),
('Cheesecakes', 1, '2024-09-09', '2024-09-18'),
('Ice Cream Cakes', 1, '2024-09-10', '2024-09-19');

INSERT INTO [product] ([name], [ingredient], [description], [status], [createdAt], [updatedAt], [categoryID]) VALUES
('Chocolate Cake', 'Chocolate, Flour, Sugar, Eggs', 'Delicious chocolate cake', 1, '2024-09-01', '2024-09-10', 1),
('Vanilla Cupcake', 'Vanilla, Flour, Sugar, Eggs', 'Sweet vanilla cupcake', 1, '2024-09-02', '2024-09-11', 3),
('Strawberry Cheesecake', 'Strawberries, Cheese, Sugar, Eggs', 'Fresh strawberry cheesecake', 1, '2024-09-03', '2024-09-12', 9),
('Wedding Cake', 'Flour, Sugar, Eggs, Butter', 'Elegant wedding cake', 1, '2024-09-04', '2024-09-13', 2),
('Fruit Tart', 'Fruits, Sugar, Cream', 'Delicious fruit tart', 1, '2024-09-05', '2024-09-14', 8),
('Custom Birthday Cake', 'Custom ingredients', 'Personalized birthday cake', 1, '2024-09-06', '2024-09-15', 4),
('Chocolate Cupcake', 'Chocolate, Flour, Sugar, Eggs', 'Rich chocolate cupcake', 1, '2024-09-07', '2024-09-16', 3),
('Ice Cream Cake', 'Ice Cream, Flour, Sugar, Eggs', 'Refreshing ice cream cake', 1, '2024-09-08', '2024-09-17', 10),
('Fruit Cake', 'Mixed Fruits, Flour, Sugar, Eggs', 'Tasty fruit cake', 1, '2024-09-09', '2024-09-18', 7),
('Pastry Delight', 'Flour, Sugar, Butter', 'Assorted pastries', 1, '2024-09-10', '2024-09-19', 8);

INSERT INTO [productDetail] ([price], [size], [productID]) VALUES
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

INSERT INTO [cart] ([userID]) VALUES
(1),
(5),
(9);

INSERT INTO [cartDetail] ([quantity], [status], [cartID], [productDetailID]) VALUES
(2, 1, 1, 1),
(3, 1, 1, 2),
(1, 1, 1, 4),
(4, 1, 2, 5),
(5, 1, 2, 3),
(1, 1, 2, 6),
(2, 1, 2, 8),
(3, 1, 3, 9),
(4, 1, 3, 10),
(5, 1, 3, 7);

INSERT INTO [order] ([consignee], [phone], [shipAddress], [orderTime], [deliveryTime], [receiptTime], [status], [voucherCode], [userId]) VALUES
('John Doe', '0123456789', '123 Main St', '2024-09-10 14:00:00', '2024-09-12 10:00:00', '2024-09-12 12:00:00', 1, 'ABC123', 1),
('Jane Smith', '0123456798', '456 Park Ave', '2024-09-11 15:00:00', '2024-09-13 11:00:00', '2024-09-13 13:00:00', 1, 'XYZ456', 1),
('Alice Jones', '0123456797', '789 Oak St', '2024-09-12 16:00:00', '2024-09-14 12:00:00', '2024-09-14 14:00:00', 1, 'LMN789', 5),
('Bob Brown', '0123456796', '321 Pine St', '2024-09-13 17:00:00', '2024-09-15 13:00:00', '2024-09-15 15:00:00', 1, 'DEF321', 5),
('Charlie Black', '0123456795', '654 Cedar St', '2024-09-14 18:00:00', '2024-09-16 14:00:00', '2024-09-16 16:00:00', 1, 'GHI654', 9),
('David Green', '0123456794', '987 Birch St', '2024-09-15 19:00:00', '2024-09-17 15:00:00', '2024-09-17 17:00:00', 1, 'JKL987', 9),
('Emily White', '0123456793', '111 Elm St', '2024-09-16 20:00:00', '2024-09-18 16:00:00', '2024-09-18 18:00:00', 1, 'QWE123', 1),
('Frank Silver', '0123456792', '222 Maple St', '2024-09-17 21:00:00', '2024-09-19 17:00:00', '2024-09-19 19:00:00', 1, 'RTY456', 5),
('George Gold', '0123456791', '333 Willow St', '2024-09-18 22:00:00', '2024-09-20 18:00:00', '2024-09-20 20:00:00', 1, 'UIO789', 9),
('Hannah Blue', '0123456790', '444 Ash St', '2024-09-19 23:00:00', '2024-09-21 19:00:00', '2024-09-21 21:00:00', 1, 'PAS123', 1);

INSERT INTO [orderDetail] ([price], [codeVoucher], [quantity], [orderID], [productDetailID]) VALUES
(15.99, 'ABC123', 2, 1, 1),
(25.99, 'XYZ456', 1, 2, 2),
(12.50, 'LMN789', 3, 3, 4),
(35.00, 'DEF321', 1, 4, 6),
(10.00, 'GHI654', 2, 5, 5),
(50.00, 'JKL987', 5, 6, 8),
(5.00, 'QWE123', 4, 7, 9),
(30.00, 'RTY456', 1, 8, 7),
(20.99, 'UIO789', 2, 9, 3),
(25.99, 'PAS123', 3, 10, 10);

INSERT INTO [post] ([title], [content], [status], [createdAt], [updatedAt], [userID]) VALUES
('New Cake Arrival', 'Check out our latest cakes!', 1, '2024-09-01', '2024-09-10', 2),
('Discount on Cupcakes', 'Get 20% off on all cupcakes!', 1, '2024-09-02', '2024-09-11', 2),
('Wedding Cakes Special', 'Exclusive designs for your big day!', 1, '2024-09-03', '2024-09-12', 2),
('Custom Cake Orders', 'Personalize your cake!', 1, '2024-09-04', '2024-09-13', 6),
('Fruit Cakes Season', 'Fresh fruit cakes available now!', 1, '2024-09-05', '2024-09-14', 6),
('Limited Edition Cheesecake', 'Try our special cheesecakes!', 1, '2024-09-06', '2024-09-15', 6),
('Pastry Delight', 'Assorted pastries now on sale!', 1, '2024-09-07', '2024-09-16', 10),
('Birthday Cakes', 'Surprise your loved ones with a cake!', 1, '2024-09-08', '2024-09-17', 10),
('Ice Cream Cakes', 'Perfect for summer parties!', 1, '2024-09-09', '2024-09-18', 10),
('Custom Designs', 'We create your dream cake!', 1, '2024-09-10', '2024-09-19', 10);

INSERT INTO [media] ([image], [postID], [productID]) VALUES
('145688445-chup-hinh-banh-ngot_08.jpg', 1, null),
('145688445-chup-hinh-banh-ngot_08.jpg', 1, null),
('145688445-chup-hinh-banh-ngot_08.jpg', 1, null),
('145688445-chup-hinh-banh-ngot_08.jpg', 2, null),
('145688445-chup-hinh-banh-ngot_08.jpg', 2, null),
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


----------------------------------------------------------------
