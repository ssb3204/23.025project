package Observer_Pattern;

import Facade_Pattern.ItemFacade;
import UI.MainUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NoticeUI implements ActionListener {
    private Notice notice;
    private JDialog notice_frame;
    private JTable notice_list;
    JButton turnOffButton;
    MainUI TOP;

    public NoticeUI(MainUI Top, Notice notice, String user) {
        TOP = Top;
        this.notice = notice;
        notice_frame = new JDialog(Top.mainframe,"알림창", true);
        notice_frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        notice_frame.setSize(500, 600);
        notice_frame.setLayout(new BorderLayout());
        turnOffButton = new JButton();

        if(TOP.isTurnedOff()){
            turnOffButton.setText("알림 켜기");
        }
        else {
            turnOffButton.setText("알림 끄기");
        }

        turnOffButton.addActionListener(this);
        notice_frame.add(turnOffButton, BorderLayout.SOUTH);

        notice_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                notice_frame.dispose();
            }
        });

        DefaultTableModel model = new DefaultTableModel(new String[]{"순서", "메세지"}, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 편집 불가능하도록 설정
            }
        };

        int index = 1;
        for(NoticeObj o : notice.getNotice_list()){
             String msg = o.getMsg();
             String i = "["+ String.valueOf(index) +"]";
             index++;
             model.addRow(new Object[]{i, msg});
        }

        notice_list = new JTable(model);
        TableColumnModel columnModel = notice_list.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(-10);
        columnModel.getColumn(1).setPreferredWidth(380);
        JScrollPane scrollPane = new JScrollPane(notice_list);
        //notice_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        notice_frame.add(scrollPane, BorderLayout.CENTER);


        notice_frame.setLocationRelativeTo(null);
        notice_frame.setResizable(false);
        notice_frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ItemFacade itemFacade = ItemFacade.getItemFacade();
        if (e.getSource() == turnOffButton){
            if (TOP.isTurnedOff()) {
                System.out.println("알림이 켜집니다.");
                turnOffButton.setText("알림 끄기"); // 버튼 이름 변경
                TOP.setTurnedOff(false);
                itemFacade.subscribe(notice);
                // 알림을 켜는 동작 처리
            } else {
                System.out.println("알림이 꺼집니다.");
                turnOffButton.setText("알림 켜기"); // 버튼 이름 변경
                TOP.setTurnedOff(true);
                itemFacade.unsubscribe(notice);
                // 알림을 끄는 동작 처리
            }
        }
    }
}
