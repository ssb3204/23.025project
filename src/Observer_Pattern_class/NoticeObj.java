package Observer_Pattern_class;

public class NoticeObj {
    String user;
    String msg;
    /**알림의 대상, 메세지*/
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
