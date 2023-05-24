package Facade_Pattern;


import CartState.Protuct;
import Dao.ItemDao;
import Dao.ItemDaoImpl;
import DatabaseConnect.DatabaseConect;
import Factory_Pattern.GeneralItemCreator;
import Factory_Pattern.ItemCreator;
import Factory_Pattern.ItemProduct;
import Observer_Pattern.DisplayObserver;
import Observer_Pattern.DisplaySubject;
import Observer_Pattern.Observer;
import Observer_Pattern.Subject;
import OrderHistory.OrderHistoryDao;
import OrderHistory.OrderHistoryDaoImpl;
import OrderHistory.OrderHistoryObj;
import Singleton_Pattern.Singleton;
import UI.MainUI;
import CartState.NotInCart;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ItemFacade implements Subject, DisplaySubject {
    Singleton singleton;
    ItemDao itemDao;
    OrderHistoryDao orderHistoryDao;
    MainUI mainUI;

    public List<Observer> observer_list = new ArrayList<>();
    public List<DisplayObserver> display_list = new ArrayList<>();

    private ItemFacade() {
        singleton = Singleton.getInstance();
        itemDao = new ItemDaoImpl(new DatabaseConect());
        orderHistoryDao = new OrderHistoryDaoImpl(new DatabaseConect());
    }



    private static class Lazy{
        public static final ItemFacade instance = new ItemFacade();
    }

    public static ItemFacade getItemFacade(){
        return Lazy.instance;
    }

    public void setMainUI(MainUI mainUI) {
        this.mainUI = mainUI;
    }

    //CRUD
    public void createItem(ItemProduct item){
        singleton.addItem(item);
        int index = singleton.getSize() - 1;
        notifyObserver("생성", singleton.getItemProduct(index));
        displayNotifyObserver();
        mainUI.addPanel(item, index);
        mainUI.resetFrame();
    }

    public void buyItem(int index, int price, int count, String customer){
        String userID = singleton.getItemProduct(index).getUserID();
        String title = singleton.getItemProduct(index).getTitle();
        orderHistoryDao.addHistory(new OrderHistoryObj(title, String.valueOf(price), userID, customer, count));

        if(singleton.decreaseItemCount(index, count) != 0){
            /**물품 구매*/
            notifyObserver("판매", singleton.getItemProduct(index));
            itemDao.updateItem(singleton.getItemProduct(index));
        }
        else{
            /**물품 전부 팔림*/
            itemDao.deleteItem(singleton.getItemProduct(index).getItemID());
            notifyObserver("매진", singleton.getItemProduct(index));
            singleton.remove(index);
        }
        displayNotifyObserver();
        mainUI.clearFrame();
        mainUI.resetAndAddPanels();
        mainUI.resetFrame();
    }

    public void updateItem(int index, String title, int count, String description, int price, File image){
        singleton.upDateItem(index, title, count, description, price, image);
        itemDao.updateItem(singleton.getItemProduct(index));
        mainUI.clearFrame();
        mainUI.resetAndAddPanels();
        mainUI.resetFrame();
        notifyObserver("수정", singleton.getItemProduct(index));
        displayNotifyObserver();
    }

    public void deleteItem(int index){
        itemDao.deleteItem(singleton.getItemProduct(index).getItemID());
        ItemProduct temp = singleton.getItemProduct(index);
        mainUI.clearFrame();
        singleton.deleteItem(index);
        notifyObserver("삭제", temp);
        displayNotifyObserver();
        mainUI.resetAndAddPanels();
        mainUI.resetFrame();
    }

    public ItemProduct copyItem(int index){
        ItemCreator itemCreator = new GeneralItemCreator();
        String title = singleton.getItemProduct(index).getTitle();
        int price = singleton.getItemProduct(index).getPrice();
        int count = singleton.getItemProduct(index).getCount();
        String description = singleton.getItemProduct(index).getDescription();
        File image = singleton.getItemProduct(index).getImageFile();
        String userID = singleton.getItemProduct(index).getUserID();
        int itemID = singleton.getItemProduct(index).getItemID();

        return  itemCreator.createItemProduct(title, price, count, description, image, userID, itemID);
    }

    public void undoDelete(ItemProduct item){
        itemDao.createItem(item);
        singleton.addItem(item);
        int index = singleton.getSize() - 1;
        mainUI.addPanel(item, index);
        mainUI.resetFrame();
        notifyObserver(null, item);
        displayNotifyObserver();
    }

    public void check(int index){
        singleton.getItemProduct(index).changeCartState();
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
    public void notifyObserver(String action, ItemProduct item) {
        for(Observer o: observer_list){
            o.update(action, item);
        }
    }
    @Override
    public void displaySubscribe(DisplayObserver observer) {
        display_list.add(observer);
    }
    @Override
    public void displayUnsubscribe(DisplayObserver observer) {
        display_list.remove(observer);
    }
    @Override
    public void displayNotifyObserver() {
        for(DisplayObserver o: display_list){
            System.out.println("화면 옵저버 작동");
            o.DisplayUpdate();
        }
    }
}
