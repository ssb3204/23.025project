package Factory_Pattern;

import Dao.ItemDao;
import Dao.ItemDaoImpl;
import DatabaseConnect.DatabaseConect;

import java.io.File;

public abstract class ItemCreator {
    /**매개변수를 이용하여 일반물품 객체 생성 후 DB에 저장작업 후 객체를 리턴한다.*/
    public ItemProduct createItem(String title, int price, int count, String description, File imageFile, String userID) {
        ItemDao itemDao = new ItemDaoImpl(new DatabaseConect());
        int itemID = itemDao.getID();
        ItemProduct item = createItemProduct(title, price, count, description, imageFile, userID, itemID);
        registerItem(item);
        return item;
    }

    public abstract ItemProduct createItemProduct(String title, int price, int count, String description, File imageFile, String userID, int itemID);

    public abstract void registerItem(ItemProduct item);
}
