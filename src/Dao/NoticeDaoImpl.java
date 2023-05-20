package Dao;

import DatabaseConnect.DatabaseConect;
import Observer_Pattern.NoticeObj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao{
    private DatabaseConect databaseConect;
    public NoticeDaoImpl(DatabaseConect databaseConect) {
        this.databaseConect = databaseConect;
    }

    @Override
    public List<NoticeObj> getAllNotices(String user) {
        List<NoticeObj> objList = new ArrayList<>();
        try {
            databaseConect.connect();
            String query = "select * from notice where userid = ? order by no desc";
            PreparedStatement pstmt = databaseConect.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                String ID = rs.getString("userid");
                String msg = rs.getString("msg");
                objList.add(new NoticeObj(ID, msg));
            }
            pstmt.close();
            rs.close();
            databaseConect.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return objList;
    }

    @Override
    public void addNotice(NoticeObj notice) {
        try {
            databaseConect.connect();
            String query = "INSERT INTO notice (userid, msg, no) VALUES (?, ?, (SELECT MAX(no) + 1 FROM notice))";
            PreparedStatement pstmt = databaseConect.getConn().prepareStatement(query);
            pstmt.setString(1, notice.getUser());
            pstmt.setString(2, notice.getMsg());
            pstmt.executeQuery();

            pstmt.close();
            databaseConect.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNotice(String id) {
        try {
            databaseConect.connect();
            String query = "DELETE FROM notice WHERE id = ?";
            PreparedStatement pstmt = databaseConect.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeQuery();
            pstmt.close();
            databaseConect.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
