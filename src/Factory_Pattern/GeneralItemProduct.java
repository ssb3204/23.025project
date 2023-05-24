package Factory_Pattern;

import CartState.NotInCart;

import java.io.File;

public class GeneralItemProduct extends ItemProduct  {
    public GeneralItemProduct(String item_title, int item_price, int item_count, String item_description, File item_imageFile, String userID, int itemID){
        this.item_title = item_title;
        this.item_price = item_price;
        this.item_count = item_count;
        this.item_description = item_description;
        this.item_imageFile = item_imageFile;
        this.userID = userID;
        this.item_type = "일반";
        this.itemID = itemID;
        this.cartState = new NotInCart();
    }

    @Override
    public String toString() {
        return "물품 : " + getTitle() + ", " + "가격 : " + getPrice();
    }
}
