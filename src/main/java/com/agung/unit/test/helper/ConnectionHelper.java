package com.agung.unit.test.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ConnectionHelper.class);

   /* private static final String DB_URL = "jdbc:mysql://localhost:3306/unittest?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDattimeCode=false&serverTimezone=UTC&verifyServerCertificate=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "admin";
*/
    private static final String DB_URL = "jdbc:h2:~/unittest";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection connect(){
        Connection con = null;
        try {
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
            return con;
        } catch (SQLException e) {
            logger.error("Koneksi Gagal {}",e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.error("Koneksi tidak ditemukan {}",e.getMessage());
            e.printStackTrace();
        }
        return con;
    }

    public static void disconnect(Connection con){
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}