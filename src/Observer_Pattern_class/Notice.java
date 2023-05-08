package Observer_Pattern_class;

import java.util.ArrayList;
import java.util.List;

public class Notice implements Observer {
    //private List<String> notice_list = new ArrayList<String>();
    public List<NoticeObj> notice_list = new ArrayList<NoticeObj>();

    @Override
    public void update(String user, String msg) {
        notice_list.add(new NoticeObj(user, msg));
    }

    public List<NoticeObj> getNotice_list(){
        return notice_list;
    }

    /**DB에 저장된 유저 이름과 같은 알림을 받아온다.*/
    public void db_get_notice(String user) {

    }
}
