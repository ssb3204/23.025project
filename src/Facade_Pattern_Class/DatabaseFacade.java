package Facade_Pattern_Class;

import java.sql.*;

public class DatabaseFacade {
    private String url = "jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl";
    private String username =  "a20193116";
    private String password = "20193116";
    private Connection conn = null;
    private Statement stmt = null;

    /**DB주소 및 아이디, 비밀번호 입력 후 연결*/
    public DatabaseFacade(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /**기본값으로 DB연결*/
    public DatabaseFacade() {}

    public void connect() throws SQLException, ClassNotFoundException {
        // 데이터베이스 연결 설정
        Class.forName("oracle.jdbc.driver.OracleDriver");
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
