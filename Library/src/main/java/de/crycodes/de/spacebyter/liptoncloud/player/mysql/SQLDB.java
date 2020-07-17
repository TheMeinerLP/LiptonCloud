package de.crycodes.de.spacebyter.liptoncloud.player.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Coded By CryCodes
 * Class: MySQL
 * Date : 17.07.2020
 * Time : 12:36
 * Project: LiptonCloud
 */

public class SQLDB {

    private Connection connection;

    private final String host;
    private final Integer port;
    private final String user;
    private final String password;

    public SQLDB(String host, Integer port, String user, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;

        if(!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/LiptonCloud", user, password);
                PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Player(id INT AUTO_INCREMENT PRIMARY KEY NOT NULL, name VARCHAR(64), uuid VARCHAR(64), login_first LONG, login_last LONG)");
                ps.executeUpdate();
                ps.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void sql(String sql) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            }catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public boolean isConnected(){
        return connection != null;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
