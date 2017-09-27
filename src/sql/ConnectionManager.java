package sql;
//Created by Ehsan Zand on 7/28/2017

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {


    private static final String URL = "jdbc:mysql://localhost:3308/db_paint";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection conn = getConnection();

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Console.write("Connecting to the Data Base...");
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } else
                return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}