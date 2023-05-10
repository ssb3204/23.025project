package Observer_Pattern_class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Notice implements Observer {
    //private List<String> notice_list = new ArrayList<String>();
    public List<NoticeObj> notice_list = new ArrayList<NoticeObj>();

    @Override
    public void update(String userID, String msg) {
        notice_list.add(new NoticeObj(userID, msg));
        String DB_URL = "jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl";
        String user = "a20193116";String pw = "20193116";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "a20193116", "20193116")) {
            System.out.println("DB에 성공적으로 연결되었습니다.");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO NOTICE VALUES (?, ?)");
            pstmt.setString(1, userID);
            pstmt.setString(2, msg);
            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<NoticeObj> getNotice_list(){
        return notice_list;
    }

    /**DB에 저장된 유저 이름과 같은 알림을 받아온다.*/
    public void db_get_notice(String userID) {
        String DB_URL = "jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl";
        String user = "a20193116";String pw = "20193116";
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521:orcl", "a20193116", "20193116")) {
            System.out.println("DB에 성공적으로 연결되었습니다.");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM NOTICE");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                 if(rs.getString("userID").equals(userID)) {
                     String u = rs.getString(1);
                     String msg = rs.getString(2);
                     notice_list.add(new NoticeObj(u, msg));
                 }
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
