package Command_Pattern;

import Facade_Pattern.ItemFacade;
import Factory_Pattern.ItemProduct;
import Singleton_Pattern.Singleton;

import javax.swing.*;

public class UpdateCommand implements Command{
    Singleton singleton;
    ItemProduct backupItem;
    int backupIndex;
    ItemFacade itemFacade;
    UpdateCommand(){
        singleton = Singleton.getInstance();
        itemFacade = ItemFacade.getItemFacade();
    }
    @Override
    public void execute(int index) {
        this.backupIndex = index;
        backupItem = itemFacade.copyItem(index);
        new ItemUpdateUI(singleton.getItemProduct(index), index);
    }

    @Override
    public void undo() {
        if(backupItem != null) {
            itemFacade.updateItem(backupIndex, backupItem.getTitle(), backupItem.getCount(), backupItem.getDescription(), backupItem.getPrice(), backupItem.getImageFile());
            backupItem = null;
            JOptionPane.showMessageDialog(null, "수정 작업이 취소되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null, "실행할 명령이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
