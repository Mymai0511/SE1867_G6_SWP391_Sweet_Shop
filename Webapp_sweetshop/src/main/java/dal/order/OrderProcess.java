package dal.order;

import dal.DAO;
import model.Order;
import model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProcess extends DAO {
    public static OrderProcess INSTANCE = new OrderProcess();

    private OrderProcess() {}

    /**
     * create new order of user
     *
     * @param name
     * @param phone
     * @param email
     * @param shipAddress
     * @param voucher
     * @param userId
     * @return
     */
    public String create(String name, String phone, String email, String shipAddress, String voucher, String userId) {
        String id = null;
        String sql = "CALL insertOrder(?,?,?,?,?,?)";  // Remove parentheses around the CALL statement
        try {
            CallableStatement cs = this.connection.prepareCall(sql);
            cs.setString(1, name);
            cs.setString(2, phone);
            cs.setString(3, email);
            cs.setString(4, shipAddress);
            cs.setString(5, voucher);
            cs.setString(6, userId);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                id = rs.getString("newOrderID");
            }
            cs.close();
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return id;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order`";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setConsignee(rs.getString("consignee"));
                order.setPhone(rs.getString("phone"));
                order.setEmail(rs.getString("email"));
                order.setShipAddress(rs.getString("shipAddress"));
                order.setOrderTime(rs.getTimestamp("orderTime"));
                order.setDeliveryTime(rs.getTimestamp("deliveryTime"));
                order.setReceiptTime(rs.getTimestamp("receiptTime"));
                order.setStatus(rs.getInt("status"));
                order.setVoucherCode(rs.getString("voucherCode"));
                order.setUserId(rs.getInt("userId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Order> getOrdersByStatus(int status) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE status = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setConsignee(rs.getString("consignee"));
                    order.setPhone(rs.getString("phone"));
                    order.setEmail(rs.getString("email"));
                    order.setShipAddress(rs.getString("shipAddress"));
                    order.setOrderTime(rs.getTimestamp("orderTime"));
                    order.setDeliveryTime(rs.getTimestamp("deliveryTime"));
                    order.setReceiptTime(rs.getTimestamp("receiptTime"));
                    order.setStatus(rs.getInt("status"));
                    order.setVoucherCode(rs.getString("voucherCode"));
                    order.setUserId(rs.getInt("userId"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM orderDetail WHERE orderID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(rs.getInt("id"));
                    orderDetail.setPrice(rs.getFloat("price"));
                    orderDetail.setQuantity(rs.getInt("quantity"));
                    orderDetail.setOrderID(rs.getInt("orderID"));
                    orderDetail.setProductDetailID(rs.getInt("productDetailID"));
                    orderDetails.add(orderDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public List<Order> searchOrders(String keyword) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM `order` WHERE consignee LIKE ? OR phone LIKE ? OR email LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setConsignee(rs.getString("consignee"));
                    order.setPhone(rs.getString("phone"));
                    order.setEmail(rs.getString("email"));
                    order.setShipAddress(rs.getString("shipAddress"));
                    order.setOrderTime(rs.getTimestamp("orderTime"));
                    order.setDeliveryTime(rs.getTimestamp("deliveryTime"));
                    order.setReceiptTime(rs.getTimestamp("receiptTime"));
                    order.setStatus(rs.getInt("status"));
                    order.setVoucherCode(rs.getString("voucherCode"));
                    order.setUserId(rs.getInt("userId"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }


    public boolean addOrderWithDetails(Order order, List<OrderDetail> orderDetails) {
        String orderSql = "INSERT INTO `order` (consignee, phone, email, shipAddress, orderTime, deliveryTime, receiptTime, status, voucherCode, userId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String orderDetailSql = "INSERT INTO orderDetail (price, quantity, orderID, productDetailID) VALUES (?, ?, ?, ?)";

        try {
            // Bắt đầu một giao dịch
            connection.setAutoCommit(false);

            // Chèn bản ghi order
            try (PreparedStatement psOrder = connection.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS)) {
                psOrder.setString(1, order.getConsignee());
                psOrder.setString(2, order.getPhone());
                psOrder.setString(3, order.getEmail());
                psOrder.setString(4, order.getShipAddress());
                psOrder.setTimestamp(5, new java.sql.Timestamp(order.getOrderTime().getTime()));
                psOrder.setTimestamp(6, new java.sql.Timestamp(order.getDeliveryTime().getTime()));
                psOrder.setTimestamp(7, new java.sql.Timestamp(order.getReceiptTime().getTime()));
                psOrder.setInt(8, order.getStatus());
                psOrder.setString(9, order.getVoucherCode());
                psOrder.setInt(10, order.getUserId());

                int orderRowsAffected = psOrder.executeUpdate();

                // Kiểm tra nếu chèn order thành công
                if (orderRowsAffected == 0) {
                    connection.rollback();
                    return false;
                }

                // Lấy ID của order vừa được chèn
                ResultSet generatedKeys = psOrder.getGeneratedKeys();
                int orderId;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } else {
                    connection.rollback();
                    return false;
                }

                // Chèn các chi tiết order
                try (PreparedStatement psOrderDetail = connection.prepareStatement(orderDetailSql)) {
                    for (OrderDetail orderDetail : orderDetails) {
                        psOrderDetail.setFloat(1, orderDetail.getPrice());
                        psOrderDetail.setInt(2, orderDetail.getQuantity());
                        psOrderDetail.setInt(3, orderId);  // Sử dụng ID của order vừa được tạo làm khóa ngoại
                        psOrderDetail.setInt(4, orderDetail.getProductDetailID());

                        psOrderDetail.addBatch();  // Thêm vào batch để thực thi hiệu quả hơn
                    }
                    psOrderDetail.executeBatch();
                }

                // Commit giao dịch nếu mọi thứ thành công
                connection.commit();
                return true;

            } catch (SQLException e) {
                connection.rollback();  // Rollback nếu có lỗi
                e.printStackTrace();
                return false;
            } finally {
                connection.setAutoCommit(true);  // Khôi phục hành vi commit mặc định
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderStatus(int orderId, int newStatus) {
        String sql = "UPDATE `order` SET status = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, newStatus);
            ps.setInt(2, orderId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }

    public boolean updateOrder(Order order) {
        String sql = "UPDATE `order` SET consignee = ?, phone = ?, email = ?, shipAddress = ?, " +
                "orderTime = ?, deliveryTime = ?, receiptTime = ?, status = ?, voucherCode = ?, userId = ? " +
                "WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, order.getConsignee());
            ps.setString(2, order.getPhone());
            ps.setString(3, order.getEmail());
            ps.setString(4, order.getShipAddress());
            ps.setTimestamp(5, new java.sql.Timestamp(order.getOrderTime().getTime()));
            ps.setTimestamp(6, new java.sql.Timestamp(order.getDeliveryTime().getTime()));
            ps.setTimestamp(7, new java.sql.Timestamp(order.getReceiptTime().getTime()));
            ps.setInt(8, order.getStatus());
            ps.setString(9, order.getVoucherCode());
            ps.setInt(10, order.getUserId());
            ps.setInt(11, order.getId());  // Điều kiện để tìm đúng đơn hàng

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }






    public static void main(String[] args) {
        System.out.println(
                OrderProcess.INSTANCE.create("12", "12", "123@gmail.com", "12",null,"27")
        );

//        for () {
//            System.out.println();
//        }
    }


}
