package Observer_Pattern;

import Factory_Pattern.ItemProduct;

public interface Observer {
    /**주제의 상태가 바뀌었을때 호출되는 메서드
     * 대상 유저, 활동, 물품인덱스*/
    void update(String action, ItemProduct item);
}
