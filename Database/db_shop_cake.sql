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
    [updatedAt] DATE
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

INSERT INTO [authority] ([code], [name]) VALUES 
(1, 'Customer'),
(2, 'Staff'),
(3, 'Shipper'),
(4, 'Admin');

INSERT INTO [voucher] ([code], [value], [quantity], [remaining], [status], [createdAt], [updatedAt]) VALUES
('VCH001', 100, 50, 50, 1, '2024-09-18', '2024-09-18'),
('VCH002', 200, 30, 30, 1, '2024-09-18', '2024-09-18');

INSERT INTO [user] ([username], [password], [fName], [gender], [email], [phone], [dob], [avatar], [address], [status], [createdAt], [updatedAt], [role]) VALUES
('user1', 'password1', 'John Doe', 1, 'johndoe@example.com', '123456789', '1990-01-01', 'avatar1.png', '123 Street', 1, '2024-09-18', '2024-09-18', 1),
('user2', 'password2', 'Jane Smith', 0, 'janesmith@example.com', '987654321', '1992-02-02', 'avatar2.png', '456 Avenue', 1, '2024-09-18', '2024-09-18', 2);

INSERT INTO [category] ([name], [status], [createdAt], [updatedAt]) VALUES
('Cakes', 1, '2024-09-18', '2024-09-18'),
('Pastries', 1, '2024-09-18', '2024-09-18');

INSERT INTO [product] ([name], [ingredient], [description], [status], [createdAt], [updatedAt], [categoryID]) VALUES
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


INSERT INTO [productDetail] ([price], [size], [productID]) VALUES
(20.0, 'Medium', 1),
(18.0, 'Small', 2),
(22.0, 'Large', 3),
(15.0, 'Small', 4),
(25.0, 'Medium', 5),
(10.0, 'Small', 6),
(12.0, 'Medium', 7),
(30.0, 'Large', 8),
(28.0, 'Large', 9),
(8.0, 'Small', 10);

INSERT INTO [productDetail] ([price], [size], [productID]) VALUES
(20.0, 'Large', 1),
(18.0, 'Large', 2),
(10.0, 'Small', 1)


INSERT INTO [cart] ([userID]) VALUES
(1),
(2);

INSERT INTO [cartDetail] ([quantity], [status], [cartID], [productDetailID]) VALUES
(2, 1, 1, 1),
(3, 1, 2, 2);

INSERT INTO [order] ([consignee], [phone], [shipAddress], [orderTime], [deliveryTime], [receiptTime], [status], [voucherCode], [userId]) VALUES
('John Doe', '123456789', '123 Street', '2024-09-18 10:00:00', '2024-09-20 12:00:00', '2024-09-20 15:00:00', 1, 'VCH001', 1),
('Jane Smith', '987654321', '456 Avenue', '2024-09-18 11:00:00', '2024-09-21 13:00:00', '2024-09-21 16:00:00', 1, 'VCH002', 2);

INSERT INTO [orderDetail] ([price], [codeVoucher], [quantity], [orderID], [productDetailID]) VALUES
(20.0, 'VCH001', 2, 1, 1),
(5.0, 'VCH002', 3, 2, 2);

INSERT INTO [post] ([title], [content], [status], [createdAt], [updatedAt]) VALUES
('New Cake Recipe', 'Check out our new cake recipe!', 1, '2024-09-18', '2024-09-18'),
('Special Pastry', 'Special offer on our croissants!', 1, '2024-09-18', '2024-09-18');


INSERT INTO [media] ([image], [postID], [productID]) VALUES
('145688445-chup-hinh-banh-ngot_08.jpg', 1, null),
('145688445-chup-hinh-banh-ngot_08.jpg', 2, null);


INSERT INTO [media] ([image], [postID], [productID]) VALUES
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

