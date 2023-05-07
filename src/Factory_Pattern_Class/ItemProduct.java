package Factory_Pattern_Class;

import Observer_Pattern_class.Observer;
import Observer_Pattern_class.Subject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ItemProduct {
    protected String item_title;//물품 명
    protected int item_price;//물품 가격
    protected String item_description;//물품 설명
    protected File item_imageFile;//물품 파일
    protected int item_count;//물품 개수
    protected  String userID;//판매자 명
    protected String item_type;//물품 타입

    /**물품의 이름을 반환*/
    public String getTitle(){
        return  item_title;
    }
    /**물품의 가격을 반환*/
    public int getPrice(){
        return item_price;
    }
    /**물품의 설명을 반환*/
    public String getDescription(){
        return item_description;
    }
    /**물품의 이미지 파일을 반환*/
    public File getImageFile(){
        return item_imageFile;
    }
    /**물품의 남은 개수를 반환*/
    public int getCount(){
        return item_count;
    }
    /**유저의 ID을 반환*/
    public String getUserID(){
        return userID;
    }
    /**물품 타입을 반환*/
    public String getItemType(){
        return item_type;
    }
    /**물품의 갯수를 1줄인다*/
    public void decreaseItemCount(){
        item_count--;
    }
}
