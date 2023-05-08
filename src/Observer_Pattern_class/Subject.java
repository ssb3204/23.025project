package Observer_Pattern_class;

public interface Subject {
    /**구독*/
    void subscribe(Observer observer);

    /**구독 해지*/
    void unsubscribe(Observer observer);
    /**상태가 변했음을  알리는 메서드*/
    void notifyObserver(String user, String msg);
}
