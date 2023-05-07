package Factory_Pattern_Class;

import Observer_Pattern_class.Observer;
import Observer_Pattern_class.Subject;

import java.io.File;

public class GeneralItemProduct extends ItemProduct  {
    public GeneralItemProduct(String item_title, int item_price, int item_count, String item_description, File item_imageFile, String userID){
        this.item_title = item_title;
        this.item_price = item_price;
        this.item_count = item_count;
        this.item_description = item_description;
        this.item_imageFile = item_imageFile;
        this.userID = userID;
        this.item_type = "일반";
    }
}
