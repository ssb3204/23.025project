package Observer_Pattern_class;

import Dao_Pattern_Class.NoticeDao;
import Dao_Pattern_Class.NoticeDaoImpl;
import Facade_Pattern_Class.DatabaseFacade;
import Singleton_Pattern_Class.Singleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Notice implements Observer {
    //private List<String> notice_list = new ArrayList<String>();
    Singleton singleton;
    public List<NoticeObj> notice_list = new ArrayList<NoticeObj>();
    NoticeDao noticeDao;
    private String ID;

    public Notice(String ID) {
        this.ID = ID;
        singleton = Singleton.getInstance();
        noticeDao = new NoticeDaoImpl(new DatabaseFacade());
        db_get_notice(ID);
    }

    @Override
    public void update(String userID, String action, int index) {
        NoticeObj noticeObj = null;
        /*System.out.println("개인 알림 작동");
        System.out.println("접속 계정 : " + ID + "   대상 계정 : " + userID);*/
        if (action.equals("판매") && !userID.equals(ID)) {//자기가 올린것은 구매가 불가능하기 때문에 무조건 다른사람의 것을 구매한것
            noticeObj = new NoticeObj(ID, getComleteWordByJongsung(singleton.getItemProduct(index).getTitle(), "을", "를") + " 구매하셨습니다.");
        }
        else if (action.equals("매진")){//자기가 자기것은 구매가 불가능하기 때문에 무조건 다른사람의 것을 구매한것
            noticeObj = new NoticeObj(ID, getComleteWordByJongsung(singleton.getItemProduct(index).getTitle(), "을", "를") + " 구매하셨습니다.");
        }
        else if (action.equals("삭제")){
            noticeObj = new NoticeObj(ID, getComleteWordByJongsung(singleton.getItemProduct(index).getTitle(), "을", "를") + " 삭제하셨습니다.");
        }
        else if (action.equals("수정")){
            noticeObj = new NoticeObj(ID, getComleteWordByJongsung(singleton.getItemProduct(index).getTitle(), "이", "가") + " 수정되었습니다.");
        }
        else if (action.equals("생성")){
            noticeObj = new NoticeObj(ID, singleton.getItemProduct(index).getTitle() + "의" + " 판매가 시작되었습니다.");
        }
        /*System.out.println(noticeObj.getUser() + "  " + noticeObj.getMsg());
        System.out.println();*/
        noticeDao.addNotice(noticeObj);
        notice_list.add(0, noticeObj);
    }

    public List<NoticeObj> getNotice_list(){
        return notice_list;
    }

    /**DB에 저장된 유저 이름과 같은 알림을 받아온다.*/
    public void db_get_notice(String userID) {
        notice_list = noticeDao.getAllNotices(ID);
    }

    /**을/를, 이/가, 은/는 판단기*/
    public static final String getComleteWordByJongsung(String name, String firstValue, String secondValue) {

        char lastName = name.charAt(name.length() - 1);

        // 한글의 제일 처음과 끝의 범위밖일 경우는 오류
        if (lastName < 0xAC00 || lastName > 0xD7A3) {
            return name;
        }


        String seletedValue = (lastName - 0xAC00) % 28 > 0 ? firstValue : secondValue;

        return name+seletedValue;
    }

}
