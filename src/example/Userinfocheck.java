package example;

import java.io.*;
import java.sql.*;

public class Userinfocheck {
    //입력 받은 아이디와 비밀번호가 파일에 있는지 확인
    //파일에 저장된 정보와 입력받은 정보가 같으면 true
    //다르면 false

    private String id;
    private String password;

    private String name;

    private String email;
    private String idFile;
    private String passwordFile;
    private String[] userinfo;
    private String[] userinfoFile;
    private boolean check;
    Userinfocheck(String id, String password){
        this.id = id;
        this.password = password;
        check = false;
    }

    Userinfocheck(String id, String password, String name, String email){
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        check = false;
    }
    
    //회원 등록
    public void addUserinfo(){
        String id="";
        String pass="";
        String name="";
        String email="";
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl","a20193116","20193116");
            stmt = conn.createStatement();
            String query = "INSERT INTO USERINFO VALUES('"+this.id+"','"+this.password+"','"+this.name+"','"+this.email+"')";
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //회원정보 있는지 확인
    public boolean Userinfoexist(){

        Statement stmt=null;
        ResultSet rs=null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl","a20193116","20193116");
            stmt = conn.createStatement();
            String query = "SELECT * FROM USERINFO WHERE ID='"+this.id+"'";
            rs = stmt.executeQuery(query);
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //회원정보 변경사항 저장
    public void saveUserinfo(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn= DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl","a20193116","20193116");
            stmt = conn.createStatement();

            String sql_query= String.format("UPDATE USERINFO SET PW='"+this.password+"', NAME='"+this.name+"', EMAIL='"+this.email+"' WHERE ID='"+this.id+"'");
            stmt.executeUpdate(sql_query);


            rs=stmt.executeQuery(sql_query);
            rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //로그인시 회원 확인
    public boolean checkUserinfo(){
        String id= this.id;
        String pass = this.password;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl","a20193116","20193116");
            Statement stmt =conn.createStatement();

            String sql_query=String.format("SELECT pw FROM userinfo WHERE id='%s' AND pw='%s'",id,pass);
            ResultSet rs = stmt.executeQuery(sql_query);

            PreparedStatement psmt = conn.prepareStatement(sql_query);
            rs=stmt.executeQuery(sql_query);
            rs.next();

            if(pass.equals(rs.getString("pw"))){
                check=true;
            }
            else{
                check=false;
            }

            rs.close();
            stmt.close();
            conn.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    //회원정보 삭제
    public void deleteUserinfo() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@sedb.deu.ac.kr:1521/orcl", "a20193116", "20193116");
            stmt = conn.createStatement();

            String sql_query = String.format("DELETE FROM USERINFO WHERE ID='" + this.id + "'");
            stmt.executeUpdate(sql_query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
