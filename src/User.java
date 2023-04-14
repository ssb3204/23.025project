public class User {
    protected String name;

    private String ID;
    private String PW;
    boolean nowLogin; //현재 로그인 상태인지 아닌지

    public User(String id, String password){

    }
    public User(String name,String ID ,String PW){
        this.name = name;
        this.ID=ID;
        this.PW = PW;

        nowLogin = false;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }


    public boolean getNowLogin() {
        return nowLogin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPW(String PW) {
        this.PW = PW;
    }

    public void setNowLogin(boolean nowLogin) {
        this.nowLogin = nowLogin;
    }


}
