package dal.media;

import dal.DAO;
import model.Media;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MediaProcess extends DAO {
    public static MediaProcess INSTANCE = new MediaProcess();

    private MediaProcess() {};

    public void add(Media media) {}

    public void remove(Media media) {}

    /**
     * get all media from database
     *
     * @return list media
     */
    public List<Media> read() {
        List<Media> mediaList = new ArrayList<Media>();
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

    /**
     * get first image from an list image of product
     *
     * @param productID id product
     * @return first object media
     */
    public Media getTop1MediaByProductID(String productID) {
        String sql = "select top 1 *  from [media] where productID = ?";
        Media media = null;
        try {
            media = new Media();
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

    public List<Media> getAllMediaByProductID(String productID) {
        String sql = "select * from [media] where productID = ?";
        List<Media> mediaList = new ArrayList<Media>();
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1,productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Media media = new Media();
                media.setId(rs.getInt("id"));
                media.setImage(rs.getString("image"));
                media.setPostID(rs.getInt("postID"));
                media.setProductID(rs.getInt("productID"));
                mediaList.add(media);
            }
        } catch (SQLException e) {
            status = e.getMessage();
        }
        return mediaList;
    }

    public static void main(String[] args) {
        for (Media media : new MediaProcess().getAllMediaByProductID("1")) {
            System.out.println(media.toString());
        }
    }
}
