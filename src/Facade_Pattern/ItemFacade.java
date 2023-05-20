package Facade_Pattern;

import Dao.ItemDao;
import Dao.ItemDaoImpl;
import DatabaseConnect.DatabaseConect;
import Singleton_Pattern.Singleton;

public class ItemFacade {
    Singleton singleton;
    ItemDao itemDao;
    public ItemFacade() {
        singleton = Singleton.getInstance();
        itemDao = new ItemDaoImpl(new DatabaseConect());
    }

    //CRUD
    void CreateItem(){

    }
    void ReadItem(){

    }

    void UpdateItem(){

    }

    void DeleteItem(){

    }
}
