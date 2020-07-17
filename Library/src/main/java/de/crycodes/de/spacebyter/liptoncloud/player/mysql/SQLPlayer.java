package de.crycodes.de.spacebyter.liptoncloud.player.mysql;

import de.crycodes.de.spacebyter.liptoncloud.player.meta.LiptonPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Coded By CryCodes
 * Class: SQLPlayer
 * Date : 17.07.2020
 * Time : 13:35
 * Project: LiptonCloud
 */

public class SQLPlayer {

    private final LiptonPlayer liptonPlayer;
    private final SQLDB mySQL;

    public SQLPlayer(LiptonPlayer liptonPlayer, SQLDB mySQL) {
        this.liptonPlayer = liptonPlayer;
        this.mySQL = mySQL;
        addEntry();
    }

    public boolean hasEntry() {
        try {
            PreparedStatement ps = mySQL.getConnection().prepareStatement("SELECT * FROM Player WHERE uuid = ?");
            ps.setString(1, liptonPlayer.getUuid().toString());
            ResultSet rs = ps.executeQuery();
            boolean var = rs.next();
            rs.close();
            ps.close();
            return var;
        }catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void addEntry() {
        if(!hasEntry()) {
            try {
                PreparedStatement ps = mySQL.getConnection().prepareStatement("INSERT INTO Player(name,uuid,login_first,login_last) VALUES (?,?,?,?)");
                ps.setString(1, liptonPlayer.getName());
                ps.setString(2, liptonPlayer.getUuid().toString());
                ps.setLong(3, liptonPlayer.getFirst_login());
                ps.setLong(4, liptonPlayer.getLast_login());
                ps.executeUpdate();
                ps.close();
            }catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void updateLastLogin(long lastLogin) {
         try {
             PreparedStatement ps = mySQL.getConnection().prepareStatement("UPDATE Players SET login_last = ? WHERE uuid = ?");
             ps.setLong(1, lastLogin);
             ps.setString(2, liptonPlayer.getUuid().toString());
             ps.executeUpdate();
             ps.close();
         }catch (SQLException exception) {
             exception.printStackTrace();
         }
    }

    public void updateName(String name) {
        try {
            PreparedStatement ps = mySQL.getConnection().prepareStatement("UPDATE Players SET name = ? WHERE uuid = ?");
            ps.setString(1, name);
            ps.setString(2, liptonPlayer.getUuid().toString());
            ps.executeUpdate();
            ps.close();
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
