package Singleton_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import Observer_Pattern_class.Notice;
import Observer_Pattern_class.Observer;
import Observer_Pattern_class.Subject;

import java.util.ArrayList;
import java.util.List;

public class Singleton implements Subject {
    private static List<ItemProduct> sell_item_list;
    private static  Singleton singleton_instance = new Singleton();
    //옵저버 관리를 위한 리스트
    public List<Observer> observer_list = new ArrayList<>();
    private Singleton(){
        sell_item_list = new ArrayList<ItemProduct>();
    }

    @Override
    public void subscribe(Observer observer) {
        observer_list.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        observer_list.remove(observer);
    }

    @Override
    public void notifyObserver(String msg) {
        for(Observer o: observer_list){
            o.update(msg);
        }
    }

    /**LazyHolder*/
    private static class LazyHolder{
        public static final Singleton instance = new Singleton();
    }

    /**싱글톤 객체를 생성, 반환하는 메서드*/
    public static Singleton getInstance() {
        return LazyHolder.instance;
    }

    /**물품 객체 리스트를 출력하는 메서드*/
    public void showItem(){
        if(sell_item_list.size() == 0){
            System.out.println("저장된 물품이 없습니다.");
        }
        else {
            System.out.println("Singleton에서 호출되어 출력되는 중입니다.");
            for (ItemProduct item : sell_item_list) {
                System.out.println(item.getTitle() + "\n" + item.getPrice() + "원\n" + item.getCount() + "개\n" + "설명 : " + item.getDescription() + "\n" + "판매자 : " + item.getUserID() + "\n\n");
            }
        }
    }

    /**물품객체를 리스트에 추가하는 메서드*/
    public void addItem(ItemProduct item){
        sell_item_list.add(item);
    }

    /**현재 저장된 물품의 개수를 반환*/
    public int getSize(){
        return sell_item_list.size();
    }
    
    /**입력된 제목의 물품을 삭제한다*/
    public void removeItem(String title){
        for(int i = 0; i < sell_item_list.size(); i++){
            if(sell_item_list.get(i).getTitle().equals(title)){
                sell_item_list.remove(i);
                return;
            }
        }
    }

    /**index 위치의 ItemProduct 반환*/
    public ItemProduct getItemProduct(int index){
        return sell_item_list.get(index);
    }

    /**index 위치의 ItemProduct 물품 개수 1개 감소*/
    public void decreaseItemCount(int index){
        sell_item_list.get(index).decreaseItemCount();
        notifyObserver(sell_item_list.get(index).getTitle() + "가 1개 판매되었습니다.");
        System.out.println("남은 물품 개수" + sell_item_list.get(index).getCount());
    }

    /**위치의 ItemProduct 물품을 삭제*/
    public void deleteItem(int index){
        System.out.println(sell_item_list.get(index).getTitle() + "가 모두 팔려 삭제되었습니다.");
        notifyObserver(sell_item_list.get(index).getTitle() + "가 모두 팔려 삭제되었습니다.");
        sell_item_list.remove(index);
    }
}
