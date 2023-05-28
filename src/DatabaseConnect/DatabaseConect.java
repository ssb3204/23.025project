package DatabaseConnect;

import java.sql.*;

public class DatabaseConect {
    private String url = "jdbc:mariadb://113.198.235.241:9090/shoppingmalldb";
    private String username =  "sonb";
    private String password = "20193116";
    private Connection conn = null;
    private Statement stmt = null;

    /**DB주소 및 아이디, 비밀번호 입력 후 연결*/
    public DatabaseConect(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /**기본값으로 DB연결*/
    public DatabaseConect() {}

    public void connect() throws SQLException, ClassNotFoundException {
        // 데이터베이스 연결 설정
        Class.forName("org.mariadb.jdbc.Driver");
        conn = DriverManager.getConnection(url, username, password);
        stmt = conn.createStatement();
    }

    public Connection getConn() {
        return conn;
    }

    public void closeConnect() throws SQLException {
        stmt.close();
        conn.close();
    }
}
