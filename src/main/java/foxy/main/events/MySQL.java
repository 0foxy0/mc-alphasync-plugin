package foxy.main.events;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class MySQL extends Plugin {

    Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));

    public MySQL() throws IOException {
    }

    String conLink = config.getString("mysql-conlink");
    String userName = config.getString("mysql-username");
    String passWord = config.getString("mysql-password");

    public Error Create(String cmd) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = null;

        if (conLink.equalsIgnoreCase("jdbc:mysql://IP:PORT/DATABASE") || userName.equals("") || passWord.equals("")) {
            return new Error("No Data!");
        }

        try {
            conn = DriverManager.getConnection(conLink, userName, passWord);
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
            stmt.executeUpdate(cmd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conn.close();
        return null;
    }

    public ResultSet Find(String cmd) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = null;

        if (conLink.equalsIgnoreCase("jdbc:mysql://IP:PORT/DATABASE") || userName.equals("") || passWord.equals("")) {
            return (ResultSet) new Error("No Data!");
        }

        try {
            conn = DriverManager.getConnection(conLink, userName, passWord);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet res = null;
        try {
            res = stmt.executeQuery(cmd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        conn.close();
        return res;
    }
}
