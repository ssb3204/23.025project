package Singleton_Pattern;

import Dao.ItemDao;
import Dao.ItemDaoImpl;
import DatabaseConnect.DatabaseConect;
import Factory_Pattern.ItemProduct;
import Observer_Pattern.Observer;
import Observer_Pattern.Subject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Singleton {

    private static List<ItemProduct> sell_item_list;
    ItemDao itemDao;

    private Singleton(){
        sell_item_list = new ArrayList<ItemProduct>();
    }


    /**LazyHolder*/
    private static class LazyHolder{
        public static final Singleton instance = new Singleton();
    }

    /**싱글톤 객체를 생성, 반환하는 메서드*/
    public static Singleton getInstance() {
        return LazyHolder.instance;
    }

    /**물품객체를 리스트에 추가하는 메서드*/
    public void addItem(ItemProduct item){
        sell_item_list.add(item);
    }

    /**현재 저장된 물품의 개수를 반환*/
    public int getSize(){
        return sell_item_list.size();
    }

    /**index 위치의 ItemProduct 반환*/
    public ItemProduct getItemProduct(int index){
        return sell_item_list.get(index);
    }

    /**index 위치의 ItemProduct 물품 개수 1개 감소*/
    public int decreaseItemCount(int index, int count){
        sell_item_list.get(index).decreaseItemCount(count);
        return sell_item_list.get(index).getCount();
    }

    /**위치의 ItemProduct 물품을 삭제*/
    public void deleteItem(int index){
        /**물품이 삭제됨*/
        sell_item_list.remove(index);
    }

    /**DB에 저장된 아이템을 받아오는 코드*/
    public void dbLoadItem() {
        itemDao = new ItemDaoImpl(new DatabaseConect());
        sell_item_list = itemDao.readItem();
    }

    public void remove(int index){
        sell_item_list.remove(index);
    }

    public void upDateItem(int index, String item, int count, String description, int price, File image){
        sell_item_list.get(index).setItem_title(item);
        sell_item_list.get(index).setItem_count(count);
        sell_item_list.get(index).setItem_description(description);
        sell_item_list.get(index).setItem_price(price);
        sell_item_list.get(index).setItem_imageFile(image);
    }

    public static List<ItemProduct> getSell_item_list() {
        return sell_item_list;
    }
}
