import java.sql.*;

public class Database {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl";
    private static final String user = "a20193116";
    private static final String pw = "20193116";

    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;

        ResultSet rs = null;
        try{
            Class.forName(JDBC_DRIVER);

            conn= DriverManager.getConnection(DB_URL, user, pw);

            stmt = conn.createStatement();

            String query = "SELECT STU_ID FROM STUDENT";
            System.out.println(query);

            PreparedStatement psmt = conn.prepareStatement(query);
            //psmt.setString(1, "20193116");
            //stmt.executeUpdate(query);
            rs = stmt.executeQuery(query);
            //String query2 = "SELECT * FROM TAKES";
            //System.out.println(query2);
            //rs = stmt.executeQuery(query2);
            while(rs.next()){
                String name = rs.getString("STU_ID");
                System.out.println("stu_id : " + name);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}