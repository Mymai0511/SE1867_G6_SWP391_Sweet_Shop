package dal.order;

import dal.DAO;
import model.Order;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public static void main(String[] args) {
        System.out.println(
                OrderProcess.INSTANCE.create("12", "12", "123@gmail.com", "12",null,"27")
        );
    }


}
