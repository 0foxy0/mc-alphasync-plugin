package foxy.main.events;

import java.sql.*;

public class DBConnection {
    public Boolean MySQL(String cmd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://45.13.227.52:3306/alphadcsync", "rootmc", "Crafting20");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Boolean res;
        try {
            res = stmt.execute(cmd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}
