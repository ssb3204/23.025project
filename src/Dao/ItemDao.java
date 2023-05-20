package Dao;

import Factory_Pattern.ItemProduct;

import java.util.List;

public interface ItemDao {
    /**아이템을 DB에 추가하기위한 메서드*/
    void createItem(ItemProduct item);
    /**
     * 아이템을 DB 에서 읽어오기위한 메서드
     */
    List<ItemProduct> readItem();
    /**아이템을 변경을 업데이트하기 위한 메서드*/
    void updateItem(ItemProduct item);
    /**아이템을 삭제하기위한 메서드*/
    void deleteItem(int id);
    /**아이템 추가시 고유한 아이템 번호를 할당받는다*/;
    int getID();
}
