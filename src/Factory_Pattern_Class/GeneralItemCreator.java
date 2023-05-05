package Factory_Pattern_Class;

import java.io.File;

public class GeneralItemCreator extends ItemCreator {
    @Override
    public ItemProduct createItemProduct(String title, int price, int count, String description, File imageFile, String userID) {
        return new GeneralItemProduct(title, price, count, description, imageFile, userID);
    }

    /**생성한 객체를 DB에 등록하는 메서드*/
    @Override
    public void registerItem(ItemProduct item) {
        // 아이템을 데이터베이스나 다른 저장소에 등록하는 로직을 구현
        // 이 예제에서는 간단히 콘솔에 출력만 함
        System.out.println("DB에 상품을 저장하였습니다.  저장된 상품명 : " + item.getTitle() + "\n");
    }
}
