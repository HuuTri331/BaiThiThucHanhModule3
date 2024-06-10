package org.example.thi_thuc_hanh.module.products;

import org.example.thi_thuc_hanh.database.DBConnect;
import org.example.thi_thuc_hanh.entity.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements  IProductDAO{
    private Connection connection;

    public ProductDAO() {
        DBConnect dbConnection = new DBConnect();
        connection = dbConnection.getConnection();
    }
    @Override
    public void insertInto(Products products) throws SQLException {
        String query = "INSERT INTO Products (ProductName, Price, Quantity, Color, Description, CategoryID) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, products.getProductName());
            statement.setDouble(2, products.getPrice());
            statement.setInt(3, products.getQuantity());
            statement.setString(4, products.getColor());
            statement.setString(5, products.getDescription());
            statement.setInt(6, products.getCategoryId());
            statement.executeUpdate();
        }
    }

    @Override
    public Products selectById(int id) throws SQLException {
        String sql = "SELECT * FROM Products WHERE ProductID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapToProducts(resultSet);
            }
        }

        return null;
    }

    public Products mapToProducts(ResultSet resultSet) throws SQLException {
        Products product = new Products();
        product.setProductId(resultSet.getInt("ProductID"));
        product.setProductName(resultSet.getString("ProductName"));
        product.setPrice(resultSet.getDouble("Price"));
        product.setQuantity(resultSet.getInt("Quantity"));
        product.setColor(resultSet.getString("Color"));
        product.setDescription(resultSet.getString("Description"));
        product.setCategoryId(resultSet.getInt("CategoryID"));
        return product;
    }

    @Override
    public List<Products> selectAll() throws SQLException {
        List<Products> productsList = new ArrayList<>();
        String sql = "SELECT * FROM Products";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("productId");
                String productName = rs.getString("productName");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int categoryID = rs.getInt("categoryId");

                Products product = new Products(productID, productName, price, quantity, color, description, categoryID);
                productsList.add(product);
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi truy xuất danh sách sản phẩm", e);
        }
        return productsList;
    }

    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        try {
            List<Products> productsList = productDAO.selectAll();
            for (Products products : productsList) {
                System.out.println(products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Products WHERE ProductID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Products products) throws SQLException {
        String sql = "UPDATE Products SET ProductName = ?, Price = ?, Quantity = ?, Color = ?, Description = ?, CategoryID = ? WHERE ProductID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, products.getProductName());
            preparedStatement.setDouble(2, products.getPrice());
            preparedStatement.setInt(3, products.getQuantity());
            preparedStatement.setString(4, products.getColor());
            preparedStatement.setString(5, products.getDescription());
            preparedStatement.setInt(6, products.getCategoryId());
            preparedStatement.setInt(7, products.getProductId());

            return preparedStatement.executeUpdate() > 0;
        }
    }
}
