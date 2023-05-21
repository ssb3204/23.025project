package Dao;

import java.sql.SQLException;

public interface UserDAO {

    void addUserinfo(String id, String password, String name, String email) throws SQLException;
    Boolean UserinfoExist(String id) throws SQLException;
    void saveUserinfo(String id, String password, String name, String email) throws SQLException;
    boolean checkUserinfo(String id, String password) throws SQLException;
    void deleteUserinfo(String id) throws SQLException;
    /**첫결제 유무*/
    boolean isFirstPayment(String id);
    void firstPayment(String id);

    String getAddress(String id);
}