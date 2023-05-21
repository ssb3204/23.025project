package Command_Pattern;

import javax.swing.*;

public class NoCommand implements Command {
    @Override
    public void execute(int index) {
        JOptionPane.showMessageDialog(null, "실행할 명령이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void undo() {
        JOptionPane.showMessageDialog(null, "실행할 명령이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
    }
}
