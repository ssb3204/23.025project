package Command_Pattern;

import Singleton_Pattern.Singleton;

import javax.swing.*;

public class ReadCommand implements Command{
    Singleton singleton;
    ReadCommand() {
        singleton = Singleton.getInstance();
    }
    @Override
    public void execute(int index) {
        new ReadItem(singleton.getItemProduct(index));
    }

    @Override
    public void undo() {
        JOptionPane.showMessageDialog(null, "취소할 작업이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
    }
}