package Observer_Pattern;

import Dao.NoticeDao;
import Dao.NoticeDaoImpl;
import DatabaseConnect.DatabaseConect;
import Factory_Pattern.ItemProduct;
import Singleton_Pattern.Singleton;

public class PushNotice implements Observer {
    private NoticeDao noticeDao;
    private String ID;
    private Singleton singleton;

    public PushNotice(String ID) {
        this.ID = ID;
        this.noticeDao = new NoticeDaoImpl(new DatabaseConect());
        singleton = Singleton.getInstance();
    }

    @Override
    public void update(String action, ItemProduct item) {
        if(!ID.equals(item.getUserID()) && action != null) {
            String target = item.getUserID();
            NoticeObj noticeObj = null;

            /*System.out.println("상대 알림 작동");
            System.out.println("접속 계정 : " + ID + "   대상 계정 : " + userID);*/
            if (action.equals("판매")) {//접속자 대상이 아니라서 무조건 판매된것
                noticeObj = new NoticeObj(target, getComleteWordByJongsung(item.getTitle(), "이", "가") + " 판매되었습니다.");
            } 
            else if (action.equals("매진")) {//자기가 자기것은 구매가 불가능하기 때문에 무조건 다른사람의 것을 구매한것
                noticeObj = new NoticeObj(target, getComleteWordByJongsung(item.getTitle(), "이", "가") + " 모두 판매되었습니다.");
            } 
            else if (action.equals("삭제")) {//
                noticeObj = new NoticeObj(target, getComleteWordByJongsung(item.getTitle(), "이", "가") + " 관리자에게 삭제되었습니다.");
            }
            else if (action.equals("수정")){
                noticeObj = new NoticeObj(target, getComleteWordByJongsung(item.getTitle(), "이", "가") + " 관리자에게 수정되었습니다.");
            }
            /*System.out.println(noticeObj.getUser() + "  " + noticeObj.getMsg());
            System.out.println();*/
            noticeDao.addNotice(noticeObj);
        }
    }

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
