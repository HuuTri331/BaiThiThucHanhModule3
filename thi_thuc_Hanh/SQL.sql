CREATE DATABASE QL_thong_tin_sanPham;

USE QL_thong_tin_sanPham;

DROP database QL_thong_tin_sanPham;

CREATE TABLE Products (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(255) NOT NULL,
    Price double,
    Quantity INT NOT NULL,
    Color VARCHAR(50),
    Description VARCHAR(255) NOT NULL,
    CategoryID INT NOT NULL,
    FOREIGN KEY (CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE Category (
    CategoryID INT PRIMARY KEY AUTO_INCREMENT,
    CategoryName VARCHAR(100) NOT NULL
);

INSERT INTO Products (ProductName, Price, Quantity, Color, Description, CategoryID) 
VALUES 
('iPhone 12', 1200.00, 100, 'Đen', 'iPhone 12 - Thiết kế mới, màn hình OLED, 5G.', 1),
('Samsung Galaxy S21', 1100.00, 200, 'Xanh', 'Samsung Galaxy S21 - Màn hình Dynamic AMOLED 2X, 5G.', 1),
('Xiaomi Mi 11', 900.00, 300, 'Xanh lá', 'Xiaomi Mi 11 - Snapdragon 888, màn hình AMOLED.', 1),
('Google Pixel 5', 800.00, 400, 'Đen', 'Google Pixel 5 - Thiết kế đơn giản, camera chất lượng.', 1),
('OnePlus 9 Pro', 1000.00, 500, 'Đỏ', 'OnePlus 9 Pro - Màn hình Fluid AMOLED 120Hz, camera Hasselblad.', 1),
('iPhone SE', 400.00, 600, 'Trắng', 'iPhone SE - Chip A13 Bionic, Touch ID.', 1),
('Samsung Galaxy A52', 450.00, 700, 'Hồng', 'Samsung Galaxy A52 - Màn hình Super AMOLED, camera đa nhiệm.', 1),
('Tủ lạnh LG', 1000.00, 100, 'Trắng', 'Tủ lạnh LG - Dung tích lớn, tiết kiệm năng lượng.', 2),
('Máy giặt Samsung', 1200.00, 200, 'Xanh', 'Máy giặt Samsung - Công nghệ tiên tiến, giặt sạch.', 3),
('Tivi Sony', 1500.00, 300, 'Đen', 'Tivi Sony - Màn hình 4K, âm thanh sống động.', 4);

INSERT INTO Category (CategoryName) 
VALUES 
('Phone'),
('Tủ lạnh'),
('Máy giặt'),
('Tivi');

SELECT 
    p.ProductID, 
    p.ProductName, 
    p.Price, 
    p.Quantity, 
    p.Color, 
    p.Description, 
    c.CategoryName
FROM 
    Products p
JOIN 
    Category c ON p.CategoryID = c.CategoryID;
    
    
    UPDATE Products 
SET 
    ProductName = 'Tên sản phẩm mới',
    Price = 120.00,
    Quantity = 60,
    Color = 'Màu sắc mới',
    Description = 'Mô tả sản phẩm mới',
    CategoryID = 2
WHERE 
    ProductID = 1;
    
    DELETE FROM Products 
WHERE ProductID = 1;