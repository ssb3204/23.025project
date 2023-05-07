package Observer_Pattern_class;

public interface Observer {
    /**주제의 상태가 바뀌었을때 호출되는 메서드*/
    void update(String msg);
}
