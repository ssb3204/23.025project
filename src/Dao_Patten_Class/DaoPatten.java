package Dao_Patten_Class;

public interface DaoPatten {

    void addUserinfo(String id, String password, String name, String email);
    boolean Userinfoexist(String id);
    void saveUserinfo(String id, String password, String name, String email);
    boolean checkUserinfo(String id, String password);
    void deleteUserinfo(String id);


}
