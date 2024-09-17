package com.x70s.sweetshop;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
//1. authority (code [PK], name)
//2. user (id [PK], idAuthority [FK], fullName, userName, password, email, gender, dateOfBirth, avatar, address, createdAt, updatedAt, status)
//3. voucher (code [PK], value, quantity, remaining, createdAt, updatedAt, status)
//4. order (id [PK], idCustomer [FK], idShipper [FK], idVoucher [FK] phone, district, commune, specificAddress, orderTime, deliveryTime, receiptTime, status)
//5. orderDetail (id [PK], idOrder [FK], idProductDetail [FK], quantity)
//6. category (id (PK), name,  createdAt, updatedAt, status)
//7. product (id (PK), idCategory (FK), name, ingredient, description,  createdAt, updatedAt, status)
//8. ProductDetail (id (PK), idProduct (FK), size, price)
//9. Cart (id (PK), idCustomer [FK])
//10. CartDetail (id (PK), idCart (FK), idProductDetail (FK), quantity, status)
//11. Media (id (PK), idProduct (FK), idPost (FK), image)
//12. Post (id (PK), title, content, createdAt, updatedAt, status)
