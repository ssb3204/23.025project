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
    public void addUserinfo(String id, String password, String name, String email) throws SQLException {
        try (Connection conn = database.getConn();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, password, name, email) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, id);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean UserinfoExist(String id) throws SQLException{
        try (Connection conn = database.getConn();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            }
        }
    }


    @Override
    public void saveUserinfo(String id, String password, String name, String email) throws SQLException {
        try (Connection conn = database.getConn();
             PreparedStatement stmt = conn.prepareStatement("UPDATE users SET password = ?, name = ?, email = ? WHERE id = ?")) {
            stmt.setString(1, password);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkUserinfo(String id, String password) throws SQLException {
        try (Connection conn = database.getConn();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ? AND password = ?")) {
            stmt.setString(1, id);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserinfo(String id) throws SQLException {
        try (Connection conn = database.getConn();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}