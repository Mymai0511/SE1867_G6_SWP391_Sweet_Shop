package controller.shipperController;

import dal.shipper.ShipperProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Shipper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetShipperController", value = {"/findallshipper"})
public class GetShipperController extends HttpServlet {
    private List<Shipper> shipperList = new ArrayList<>();


    @Override
    public void init() {
        shipperList = ShipperProcess.Instance.getAllShipper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("shippers", shipperList);
        request.getRequestDispatcher("page/admin/shipper_list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}