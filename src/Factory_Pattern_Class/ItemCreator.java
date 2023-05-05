package Factory_Pattern_Class;

import java.io.File;

public abstract class ItemCreator {
    /**매개변수를 이용하여 일반물품 객체 생성 후 DB에 저장작업 후 객체를 리턴한다.*/
    public ItemProduct createItem(String title, int price, int count, String description, File imageFile, String userID) {
        ItemProduct item = createItemProduct(title, price, count, description, imageFile, userID);
        registerItem(item);
        return item;
    }

    public abstract ItemProduct createItemProduct(String title, int price, int count, String description, File imageFile, String userID);

    public abstract void registerItem(ItemProduct item);
}
