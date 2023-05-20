package Dao;

import Observer_Pattern.NoticeObj;

import java.util.List;

public interface NoticeDao {
    /**특정 사용자에게 온 알림을 가져오는 메서드*/
    List<NoticeObj> getAllNotices(String user);

    /** 새로운 알림을 추가하는 메서드*/
    void addNotice(NoticeObj notice);

    /** 기존 알림을 삭제하는 메서드*/
    void deleteNotice(String id);
}
