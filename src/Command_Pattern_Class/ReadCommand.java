package Command_Pattern_Class;

import Singleton_Pattern_Class.Singleton;

public class ReadCommand implements Command{
    Singleton singleton;
    ReadCommand() {
        singleton = Singleton.getInstance();
    }
    @Override
    public void execute(int index) {
        new ReadItem(singleton.getItemProduct(index));
    }
}