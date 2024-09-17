/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.x70s.sweetshop.controller.Customer;

import com.x70s.sweetshop.entity.Product;
import com.x70s.sweetshop.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "cartlist", value = "/cartlist")
public class CartList extends HttpServlet {

    @Autowired
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.equals("viewCart")) {

            List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("cartlist.jsp").forward(request, response);
        } else if (action.equals("viewDetail")) {

            String productId = request.getParameter("id");
            Product product = productService.getProductById(productId);

            request.setAttribute("product", product);
            request.getRequestDispatcher("cartdetail.jsp").forward(request, response);
        }
    }
}
