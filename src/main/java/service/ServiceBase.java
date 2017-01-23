package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public class ServiceBase {

    private static Connection conn;

    private static String hostname;
    private static String username;
    private static String password;
    private static String database;
    private static String testDatabase;

    public ServiceBase() {
        if (hostname == null) {
            Properties props = new Properties();
            try {
                System.out.println(this.getClass().getClassLoader().getResource("."));
                InputStream a = this.getClass().getClassLoader().getResourceAsStream("credentials.properties");
                props.load(a);
                hostname = props.getProperty("hostname");
                username = props.getProperty("username");
                password = props.getProperty("password");
                database = props.getProperty("database");
                testDatabase = props.getProperty("testDatabase");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Connection getDbInternal(String driverClass, String connectionString) {
        try {
            Class.forName(driverClass).newInstance();
            return DriverManager.getConnection(connectionString);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection getDb() {
        if (conn == null) {
            conn = getDbInternal("com.mysql.cj.jdbc.Driver", String.format("jdbc:mysql://%s/%s?user=%s&password=%s", hostname, database, username, password));
        }
        return conn;
    }

    public Connection getTestDb() throws FileNotFoundException, SQLException {
        if (conn == null) {
            conn = getDbInternal("com.mysql.cj.jdbc.Driver", String.format("jdbc:mysql://%s/%s?user=%s&password=%s", hostname, testDatabase, username, password));
        }
        return conn;
    }
}
