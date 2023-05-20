package Command_Pattern;

import Singleton_Pattern.Singleton;

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