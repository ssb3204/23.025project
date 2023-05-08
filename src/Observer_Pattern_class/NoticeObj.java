package Observer_Pattern_class;

public class NoticeObj {
    String user;
    String msg;

    public NoticeObj(String user, String msg) {
        this.user = user;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public String getUser() {
        return user;
    }
}
