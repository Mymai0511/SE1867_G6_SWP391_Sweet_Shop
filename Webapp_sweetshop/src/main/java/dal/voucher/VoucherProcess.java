package dal.voucher;

import dal.DAO;
import model.Voucher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoucherProcess extends DAO {
    public static VoucherProcess INSTANCE = new VoucherProcess();

    private VoucherProcess() {}

    public List<Voucher> read() {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "select * from shopcake.voucher where `status` = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setCode(rs.getString("code"));
                voucher.setValue(rs.getInt("value"));
                voucher.setQuantity(rs.getInt("quantity"));
                voucher.setRemaining(rs.getInt("remaining"));
                voucher.setStatus(rs.getInt("status"));
                voucher.setCreatedAt(rs.getDate("createdAt"));
                voucher.setUpdatedAt(rs.getDate("updatedAt"));
                vouchers.add(voucher);
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return vouchers;
    }

    public static void main(String[] args) {
        String codeVoucher = "|";
        for (Voucher voucher : VoucherProcess.INSTANCE.read()) {
            codeVoucher += voucher.getCode() + "|";
        }
        System.out.println(codeVoucher);
    }
}
