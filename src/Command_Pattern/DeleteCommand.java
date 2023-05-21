package Command_Pattern;

import Facade_Pattern.ItemFacade;
import Factory_Pattern.GeneralItemCreator;
import Factory_Pattern.ItemCreator;
import Factory_Pattern.ItemProduct;

import javax.swing.*;

public class DeleteCommand implements Command {
    ItemFacade itemFacade;
    ItemProduct backupItem;

    DeleteCommand(){
       this.itemFacade = ItemFacade.getItemFacade();
    }
    @Override
    public void execute(int index) {
        //copy
        backupItem = itemFacade.copyItem(index);
        itemFacade.deleteItem(index);
        JOptionPane.showMessageDialog(null, "삭제되었습니다!", "알림", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void undo() {
        if(backupItem != null) {
            itemFacade.undoDelete(backupItem);
            backupItem = null;
            JOptionPane.showMessageDialog(null, "삭제 작업이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "실행할 명령이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
