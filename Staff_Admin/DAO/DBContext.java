/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Staff_Admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author FPT University - PRJ30X
 */
public class DBContext {

    protected Connection connection;




        private static final String URL = "jdbc:mysql://localhost:3306/shopcake";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "12345";
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

        public static Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }



//    public DBContext() {
//        //@Students: You are allowed to edit user, pass, url variables to fit
//        //your system configuration
//        //You can also add more methods for Database Interaction tasks.
//        //But we recommend you to do it in another class
//        // For example : StudentDBContext extends DBContext ,
//        //where StudentDBContext is located in dal package,
//        try {
//            String user = "sa";
//            String pass = "12345";
//            String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=shopcake";
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            connection = DriverManager.getConnection(url, user, pass);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(DBContext.getConnection());
    }
}