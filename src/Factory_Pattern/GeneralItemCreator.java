package Factory_Pattern;

import Dao.ItemDao;
import Dao.ItemDaoImpl;
import DatabaseConnect.DatabaseFacade;

import java.io.File;

public class GeneralItemCreator extends ItemCreator {
    
    /**아이템 객체를 반환*/
    @Override
    public ItemProduct createItemProduct(String title, int price, int count, String description, File imageFile, String userID, int itemID) {
        return new GeneralItemProduct(title, price, count, description, imageFile, userID, itemID);
    }

    /**생성한 객체를 DB에 등록하는 메서드*/
    @Override
    public void registerItem(ItemProduct item) {
        // 아이템을 데이터베이스나 다른 저장소에 등록하는 로직을 구현
        // 이 예제에서는 간단히 콘솔에 출력만 함
        ItemDao itemDao = new ItemDaoImpl(new DatabaseFacade());
        itemDao.createItem(item);
        //System.out.println("DB에 상품을 저장하였습니다.  저장된 상품명 : " + item.getTitle() + "\n");
    }
}
