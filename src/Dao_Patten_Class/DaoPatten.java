package Dao_Patten_Class;

import java.sql.SQLException;

public interface DaoPatten {

    void addUserinfo(String id, String password, String name, String email) throws SQLException;
    Boolean UserinfoExist(String id) throws SQLException;
    void saveUserinfo(String id, String password, String name, String email) throws SQLException;
    boolean checkUserinfo(String id, String password) throws SQLException;
    void deleteUserinfo(String id) throws SQLException;



}
