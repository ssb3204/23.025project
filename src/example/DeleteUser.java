package example;

import DatabaseConnect.DatabaseConect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
    DatabaseConect database;
    String user;
    public DeleteUser(DatabaseConect database) {
        this.database = database;
    }

    public void deleteStart(String user){
        this.user = user;
        deleteU();
        deleteNotice();
        deleteHistory();
        deleteItem();
    }
    
    /**회원탈퇴*/
    public void deleteU(){
        try {
            database.connect();

            String query = "DELETE FROM USERINFO WHERE ID = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.executeUpdate();
            pstmt.close();

            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    /**회원탈퇴시 알림삭제*/
    public void deleteNotice() {
        try {
            database.connect();

            String query = "DELETE FROM notice WHERE userID = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.executeUpdate();

            pstmt.close(); // PreparedStatement 객체 닫기

            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    /**구매기록 삭제*/
    public void deleteHistory(){
        try {
            database.connect();
            String query = "DELETE FROM OrderHistory WHERE customerID = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            pstmt.executeUpdate();
            pstmt.close();

            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(){
        try {
            database.connect();

            String query = "DELETE FROM item WHERE userid = ?";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
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
