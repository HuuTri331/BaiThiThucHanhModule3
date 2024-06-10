package org.example.thi_thuc_hanh.service;

import org.example.thi_thuc_hanh.entity.Products;
import org.example.thi_thuc_hanh.module.products.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public void renderPageListProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IOException {
        List<Products> products = this.productDAO.selectAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/productsView/list.jsp");
        dispatcher.forward(request, response);
    }


    public void renderPageAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Products> categories = this.productDAO.selectAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/productsView/productCreate.jsp");
        dispatcher.forward(request, response);
    }

    public void addNewProduct(HttpServletRequest req) throws ServletException, IOException, SQLException {
        Products newProduct = mapReqToProduct(req);
        this.productDAO.insertInto(newProduct);
    }

    public Products mapReqToProduct(HttpServletRequest req) {
        Products product = new Products();
        product.setProductName(req.getParameter("productName"));
        product.setPrice(Double.parseDouble(req.getParameter("price")));
        product.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        product.setColor(req.getParameter("color"));
        product.setDescription(req.getParameter("description"));
        product.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
        return product;
    }

    public void updateProduct(HttpServletRequest req) throws ServletException, IOException, SQLException {
        Products updatedProduct = mapReqToProduct(req);
        updatedProduct.setProductId(Integer.parseInt(req.getParameter("ProductId")));
        this.productDAO.update(updatedProduct);
    }

    public void deleteProductById(int id) throws ServletException, IOException, SQLException {
        this.productDAO.deleteById(id);
    }

}
