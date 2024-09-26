package dal.orderDetail;

import dal.DAO;
import model.OrderDetail;

public class OrderDetailProcess extends DAO {
    public static OrderDetailProcess INSTANCE = new OrderDetailProcess();

    private OrderDetailProcess() {}

    public String create(OrderDetail orderDetail) {
        String id = null;
        String sql = "{Call insertOrderDetail(?,?,?,?,?)}";

        return null;
    }
}
