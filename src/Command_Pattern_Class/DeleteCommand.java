package Command_Pattern_Class;

import Singleton_Pattern_Class.Singleton;

public class DeleteCommand implements Command {
    Singleton singleton;
    DeleteCommand(){
       singleton = Singleton.getInstance();
    }
    @Override
    public void execute(int index) {
        singleton.deleteItem(index);
        //System.out.println(singleton.getItemProduct(index).getTitle() + "를 삭제합니다");
    }
}
