package Dao;

import DatabaseConnect.DatabaseConect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private DatabaseConect database;

    public UserDAOImpl(DatabaseConect database) {
        this.database = database;
    }
    @Override
    public void addUserinfo(String id, String password, String name, String address){
        try {
            database.connect();
            String query = "INSERT INTO userinfo (id, pw, name, address) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, address);
            pstmt.executeUpdate();

            pstmt.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean userInfoExist(String id){
        boolean result = false;
        try {
            database.connect();
            String query = "SELECT * FROM userinfo WHERE id = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                result = true;
            }
            pstmt.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public void saveUserinfo(String id, String password, String name, String address) {
        try {
            database.connect();
            String query = "UPDATE userinfo SET pw = ?, name = ?, address = ? WHERE id = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, password);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, id);
            pstmt.executeUpdate();

            pstmt.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUserinfo(String id, String password){
        boolean result = false;
        try {
            database.connect();
            String query = "SELECT * FROM userinfo WHERE id = ? AND pw = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                result = true;
            }

            pstmt.close();
            rs.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public boolean isFirstPayment(String id) {
        boolean result = false;
        try {
            database.connect();
            String query = "SELECT first_payment FROM userinfo WHERE ID = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                int temp = rs.getInt(1);
                if(temp == 0){
                    result = false;
                }
                else{
                    result = true;
                }
            }
            pstmt.close();
            rs.close();

            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public void firstPayment(String id) {
        try {
            database.connect();
            String query = "UPDATE userinfo SET first_payment = 1 WHERE id = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeUpdate();

            pstmt.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAddress(String id) {
        String address = null;
        try {
            database.connect();
            String query = "SELECT address FROM userinfo WHERE ID = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                address = rs.getString("address");
            }

            pstmt.close();
            rs.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  address;
    }


}