package controller.checkoutController;

import dal.product.ProductProcess;
import dal.productDetail.ProductDetailProcess;
import dal.voucher.VoucherProcess;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductDetail;
import model.Voucher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "checkout", value = "/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idPd = request.getParameter("idPd");
        String[] idProductDetails = idPd.split(",");
        String[] locations = {
                "Bình Phú", "Bình Yên", "Cẩm Yên", "Cần Kiệm", "Canh Nậu",
                "Chàng Sơn", "Đại Đồng", "Dị Nậu", "Đồng Trúc", "Hạ Bằng",
                "Hương Ngải", "Hữu Bằng", "Kim Quan", "Lại Thượng", "Phú Kim",
                "Phùng Xá", "Tân Xã", "Thạch Hòa", "Thạch Xá"
        };
        List<ProductDetail> listProductDetails = ProductDetailProcess.INSTANCE.getPDByListPDId(idProductDetails);
        List<Voucher> voucherList = VoucherProcess.INSTANCE.read();
        String codeVoucher = "|";
        for (Voucher voucher : voucherList) {
            codeVoucher += voucher.getCode() + "|";
        }
        request.setAttribute("codeVoucher", codeVoucher);
        request.setAttribute("product", ProductProcess.INSTANCE);
        request.setAttribute("listPD", listProductDetails);
        request.setAttribute("location", locations);
        request.getRequestDispatcher("/page/cakeMain/Checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String location = request.getParameter("location");
        String district = request.getParameter("district");
        String country = request.getParameter("country");
        String voucher = request.getParameter("voucher");

    }
}
