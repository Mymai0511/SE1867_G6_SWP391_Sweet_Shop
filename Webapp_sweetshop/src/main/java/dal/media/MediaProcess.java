package dal.media;

import dal.DAO;
import model.Media;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MediaProcess extends DAO {
    public static MediaProcess INSTANCE = new MediaProcess();

    private MediaProcess() {};

    private final List<Media> mediaList = new ArrayList<Media>();

    public void add(Media media) {}

    public void remove(Media media) {}

    /**
     * get all media from database
     *
     * @return list media
     */
    public List<Media> read() {
        String sql = "select * from [media]";
        try{
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Media media = new Media();
                media.setId(rs.getInt("id"));
                media.setImage(rs.getString("image"));
                media.setPostID(rs.getInt("postID"));
                media.setProductID(rs.getInt("productID"));
                mediaList.add(media);
            }
        }catch (SQLException e) {
            status = e.getMessage();
        }
        return mediaList;
    }

    public Media getTop1MediaByProductID(String productID) {
        String sql = "select top 1 *  from [media] where productID = ?";
        Media media = new Media();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1,productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                media.setId(rs.getInt("id"));
                media.setImage(rs.getString("image"));
                media.setPostID(rs.getInt("postID"));
                media.setProductID(rs.getInt("productID"));
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return media;
    }

    public static void main(String[] args) {
            System.out.println(MediaProcess.INSTANCE.getTop1MediaByProductID("1").getImage());
    }
}
