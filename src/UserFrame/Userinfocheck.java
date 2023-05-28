package UserFrame;

import Dao.UserDAO;
import Dao.UserDAOImpl;
import DatabaseConnect.DatabaseConect;

import javax.swing.*;
import java.sql.*;

public class Userinfocheck {
    //입력 받은 아이디와 비밀번호가 파일에 있는지 확인
    //파일에 저장된 정보와 입력받은 정보가 같으면 true
    //다르면 false
    private String id;
    private String password;

    private String name;

    private String address;
    UserDAO userDAO;
    Userinfocheck(String id, String password){
        this.id = id;
        this.password = password;
        userDAO = new UserDAOImpl(new DatabaseConect());
    }

    Userinfocheck(String id, String password, String name, String address){
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        userDAO = new UserDAOImpl(new DatabaseConect());
    }
    
    //회원 등록
    public void addUserinfo(){
        userDAO.addUserinfo(id, password, name, address);
    }

    //회원정보 있는지 확인
    public boolean userInfoExist(){
        return userDAO.userInfoExist(id);
    }

    //회원정보 변경사항 저장
    public void saveUserinfo(){
        userDAO.saveUserinfo(id, password, name, address);
    }

    //로그인시 회원 확인
    public boolean checkUserinfo(){
        return userDAO.checkUserinfo(id, password);
    }

}
