package Facade_Pattern;

import Dao.ItemDao;
import Dao.ItemDaoImpl;
import DatabaseConnect.DatabaseConect;
import Factory_Pattern.ItemProduct;
import Observer_Pattern.Observer;
import Observer_Pattern.Subject;
import OrderHistory.OrderHistoryDao;
import OrderHistory.OrderHistoryDaoImpl;
import OrderHistory.OrderHistoryObj;
import Singleton_Pattern.Singleton;
import UI.MainUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ItemFacade implements Subject {
    Singleton singleton;
    ItemDao itemDao;
    OrderHistoryDao orderHistoryDao;
    MainUI mainUI;
    public List<Observer> observer_list = new ArrayList<>();


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
        notifyObserver(singleton.getItemProduct(index).getUserID() ,"생성", singleton.getItemProduct(index));
        mainUI.addPanel(item, index);
        mainUI.resetFrame();
    }

    public void buyItem(int index, int price, int count, String customer){
        String userID = singleton.getItemProduct(index).getUserID();
        String title = singleton.getItemProduct(index).getTitle();
        orderHistoryDao.addHistory(new OrderHistoryObj(title, String.valueOf(price), userID, customer, count));

        if(singleton.decreaseItemCount(index, count) != 0){
            /**물품 구매*/
            notifyObserver(singleton.getItemProduct(index).getUserID(), "판매", singleton.getItemProduct(index));
            itemDao.updateItem(singleton.getItemProduct(index));
        }
        else{
            /**물품 전부 팔림*/
            itemDao.deleteItem(singleton.getItemProduct(index).getItemID());
            notifyObserver(singleton.getItemProduct(index).getUserID() ,"매진", singleton.getItemProduct(index));
            singleton.remove(index);
        }
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
        notifyObserver(singleton.getItemProduct(index).getUserID() ,"수정", singleton.getItemProduct(index));
    }

    public void deleteItem(int index){
        itemDao.deleteItem(singleton.getItemProduct(index).getItemID());
        ItemProduct temp = singleton.getItemProduct(index);
        mainUI.clearFrame();
        singleton.deleteItem(index);
        notifyObserver(temp.getUserID() ,"삭제", temp);
        mainUI.resetAndAddPanels();
        mainUI.resetFrame();
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
    public void notifyObserver(String user, String action, ItemProduct item) {
        for(Observer o: observer_list){
            o.update(user ,action, item);
        }
    }
}
