package Factory_Pattern_Class;

import java.io.File;

public class GeneralItemProduct implements ItemProduct{
    private String item_title;//물품 명
    private int item_price;//물품 가격
    private String item_description;//물품 설명
    private File item_imageFile;//물품 파일
    private int item_count;//물품 개수
    private  String userID;//판매자 명
    private String type = "일반";//물품 타입

    public GeneralItemProduct(String item_title, int item_price, int item_count, String item_description, File item_imageFile, String userID){
        this.item_title = item_title;
        this.item_price = item_price;
        this.item_count = item_count;
        this.item_description = item_description;
        this.item_imageFile = item_imageFile;
        this.userID = userID;
    }

    @Override
    public String getTitle() {
        return item_title;
    }

    @Override
    public int getPrice() {
        return item_price;
    }

    @Override
    public String getDescription() {
        return item_description;
    }

    @Override
    public File getImageFile() {
        return item_imageFile;
    }

    @Override
    public int getCount() {
        return item_count;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public void decreaseItemCount() {
        item_count--;
    }
    @Override
    public String getType() {
        return type;
    }
}
