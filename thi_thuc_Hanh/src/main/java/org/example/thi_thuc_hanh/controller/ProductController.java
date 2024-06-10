package org.example.thi_thuc_hanh.controller;

import org.example.thi_thuc_hanh.service.ProductService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProductController", urlPatterns = "/product/*")
public class ProductController extends HttpServlet {
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/list":
                try {
                    this.productService.renderPageListProducts(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/create":
                try {
                    this.productService.renderPageAddProduct(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/edit":
                try {
                    int id = Integer.parseInt(req.getParameter("ProductId"));
                    this.productService.renderPageAddProduct(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getPathInfo();
        switch (url) {
            case "/create":
                try {
                    this.productService.addNewProduct(req);
                    resp.sendRedirect(req.getContextPath() + "/product/list");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/delete":
                try {
                    int id = Integer.parseInt(req.getParameter("ProductId"));
                    this.productService.deleteProductById(id);
                    resp.sendRedirect(req.getContextPath() + "/product/list");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/edit":
                try {
                    this.productService.updateProduct(req);
                    resp.sendRedirect(req.getContextPath() + "/product/list");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
