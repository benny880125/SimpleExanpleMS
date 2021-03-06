package com.example.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.data.User;
import com.example.dbUtil.MySqlConnection;

public class UserDB {

    private static final UserDB userDB = new UserDB();
    private static final MySqlConnection mySqlConnection = new MySqlConnection();

    public static UserDB getInstance() {
        return userDB;
    }

    private UserDB(){

    }


    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from userData";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                User user = new User(id, userPhone, userName);
                users.add(user);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return users;
    }

    public User getUser(int userId) {
        User user = null;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from userData where id=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setInt(1, userId);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                user = new User(id, userPhone, userName);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return user;

    }

    public User getUser(String name) {
        User user = null;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "select * from userData where userName=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            ResultSet rs = preStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userPhone = rs.getString("userPhone");
                String userName = rs.getString("userName");
                user = new User(id, userPhone, userName);
            }
            connection.close();

        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return user;

    }

    public boolean createUser(String name, String userPhone) {

        boolean is_success = false;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "INSERT INTO userData(userPhone,userName) VALUES(?, ?)";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, userPhone);
            preStmt.setString(2, name);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return is_success;
    }

    public boolean deleteUser(String name) {

        boolean is_success = false;
        Connection connection = mySqlConnection.getDBConnection();
        String sqlString = "DELETE FROM `userData` WHERE userName=?";
        try {
            PreparedStatement preStmt = connection.prepareStatement(sqlString);
            preStmt.setString(1, name);
            preStmt.executeUpdate();
            connection.close();
            is_success = true;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return is_success;
    }


}
