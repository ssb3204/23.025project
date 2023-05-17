package Dao_Pattern_Class;

import Facade_Pattern_Class.DatabaseFacade;
import Factory_Pattern_Class.GeneralItemCreator;
import Factory_Pattern_Class.ItemCreator;
import Factory_Pattern_Class.ItemProduct;
import Observer_Pattern_class.NoticeObj;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao{
    private DatabaseFacade databaseFacade;
    public NoticeDaoImpl(DatabaseFacade databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    @Override
    public List<NoticeObj> getAllNotices(String user) {
        List<NoticeObj> objList = new ArrayList<>();
        try {
            databaseFacade.connect();
            String query = "select * from notice where userid = ? order by no desc";
            PreparedStatement pstmt = databaseFacade.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                String ID = rs.getString("userid");
                String msg = rs.getString("msg");
                objList.add(new NoticeObj(ID, msg));
            }
            pstmt.close();
            rs.close();
            databaseFacade.closeConnect();
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
            databaseFacade.connect();
            String query = "INSERT INTO notice (userid, msg, no) VALUES (?, ?, (SELECT MAX(no) + 1 FROM notice))";
            PreparedStatement pstmt = databaseFacade.getConn().prepareStatement(query);
            pstmt.setString(1, notice.getUser());
            pstmt.setString(2, notice.getMsg());
            pstmt.executeQuery();

            pstmt.close();
            databaseFacade.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteNotice(String id) {
        try {
            databaseFacade.connect();
            String query = "DELETE FROM notice WHERE id = ?";
            PreparedStatement pstmt = databaseFacade.getConn().prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.executeQuery();
            pstmt.close();
            databaseFacade.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
