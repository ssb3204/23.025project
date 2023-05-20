package Command_Pattern_Class;

import Singleton_Pattern_Class.Singleton;

public class UpdateCommand implements Command{
    Singleton singleton;
    UpdateCommand(){
        singleton = Singleton.getInstance();
    }
    @Override
    public void execute(int index) {
        new ItemUpdateUI(singleton.getItemProduct(index), index);
    }
}
