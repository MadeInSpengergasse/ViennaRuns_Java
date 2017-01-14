package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public class ServiceBase {

    private static Connection conn;

    public Connection getDb() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//                conn = DriverManager.getConnection("jdbc:mysql://hostname/database?user=user&password=password");
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return conn;
    }
}
