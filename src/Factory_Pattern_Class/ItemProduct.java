package Factory_Pattern_Class;

import java.io.File;

public interface ItemProduct {
    /**물품의 이름을 반환*/
    public String getTitle();
    /**물품의 가격을 반환*/
    public int getPrice();
    /**물품의 설명을 반환*/
    public String getDescription();
    /**물품의 이미지 파일을 반환*/
    public File getImageFile();
    /**물품의 남은 개수를 반환*/
    public int getCount();
    /**유저의 ID을 반환*/
    public String getUserID();
    /**물품의 갯수를 1줄인다*/
    public void decreaseItemCount();
}
