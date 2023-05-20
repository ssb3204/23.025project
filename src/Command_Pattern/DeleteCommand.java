package Command_Pattern;

import Facade_Pattern.ItemFacade;
import Singleton_Pattern.Singleton;

import javax.swing.*;

public class DeleteCommand implements Command {
    ItemFacade itemFacade;
    DeleteCommand(){
       this.itemFacade = ItemFacade.getItemFacade();
    }
    @Override
    public void execute(int index) {
        itemFacade.deleteItem(index);
        JOptionPane.showMessageDialog(null, "삭제되었습니다!", "알림", JOptionPane.INFORMATION_MESSAGE);
    }
}
