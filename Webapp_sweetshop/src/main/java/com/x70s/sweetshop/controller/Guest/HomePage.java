package com.x70s.sweetshop.controller.Guest;

import com.x70s.sweetshop.dto.ProductDTO;
import com.x70s.sweetshop.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "homepage", value = "/home")
public class HomePage extends HttpServlet {
    @Autowired
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDTO> productList = productService.getAllProduct();

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("page/cakeMain/Index.jsp").forward(request,response);
    }
}
