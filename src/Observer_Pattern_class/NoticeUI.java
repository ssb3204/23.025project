package Observer_Pattern_class;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NoticeUI {
    Notice notice;
    JDialog notice_frame;
    JList<String> notice_list;
    public NoticeUI(JFrame Top, Notice notice) {
        this.notice = notice;
        notice_frame = new JDialog(Top,"알림창", true);
        notice_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String n : notice.getNotice_list()){
            model.addElement(n);
        }
        notice_list = new JList<>(model);
        notice_frame.add(notice_list);

        notice_frame.setSize(500, 800);
        notice_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        notice_frame.setLocationRelativeTo(null);
        notice_frame.setResizable(false);
        notice_frame.setVisible(true);
    }
}
