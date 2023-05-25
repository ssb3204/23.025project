package Observer_Pattern;

public interface DisplaySubject {
    void displaySubscribe(DisplayObserver observer);
    /**구독 해지*/
    void displayUnsubscribe(DisplayObserver observer);
    /**상태가 변했음을  알리는 메서드*/
    void displayNotifyObserver();
}
