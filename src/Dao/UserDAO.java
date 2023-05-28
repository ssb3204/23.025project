package Dao;

import java.sql.SQLException;

public interface UserDAO {

    void addUserinfo(String id, String password, String name, String email);
    boolean userInfoExist(String id);
    void saveUserinfo(String id, String password, String name, String email);
    boolean checkUserinfo(String id, String password);
    /**첫결제 유무*/
    boolean isFirstPayment(String id);
    void firstPayment(String id);

    String getAddress(String id);
}