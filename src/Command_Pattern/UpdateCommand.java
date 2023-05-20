package Command_Pattern;

import Singleton_Pattern.Singleton;

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
