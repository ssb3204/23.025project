package Command_Pattern;

import Factory_Pattern.GeneralItemCreator;
import Factory_Pattern.ItemCreator;
import Factory_Pattern.ItemProduct;
import Singleton_Pattern.Singleton;
import UI.MainUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI implements ActionListener {
    JFrame mainframe;
    Button button;
    JButton read;
    JButton delete;
    JButton update;
    JTable item_table;
    ItemProduct item;
    DefaultTableModel model;
    MainUI TOP;
    public AdminUI(MainUI TOP)  {
        this.TOP = TOP;
        ItemCreator itemCreator = new GeneralItemCreator();
        item = itemCreator.createItemProduct("테스트", 10000, 100, "테스트설명", null, "tester", 100);

        mainframe = new JFrame("관리자 화면");
        mainframe.setSize(700, 500);
        mainframe.setLayout(new BorderLayout());
        mainframe.setLocationRelativeTo(null);
        //메인프레임 중앙 및 보이기 설정
        mainframe.setResizable(false);


        read = new JButton("읽기");
        read.addActionListener(this);
        delete = new JButton("삭제");
        delete.addActionListener(this);
        update = new JButton("수정");
        update.addActionListener(this);
        JPanel button_panel= new JPanel(new FlowLayout());

        button_panel.add(read);
        button_panel.add(delete);
        button_panel.add(update);

        mainframe.add(button_panel, BorderLayout.SOUTH);

        Command readCommand = new ReadCommand();
        Command deleteCommand = new DeleteCommand();
        Command updateCommand = new UpdateCommand();

        button = new Button();
        button.setButton(0, readCommand);
        button.setButton(1, deleteCommand);
        button.setButton(2, updateCommand);
        item_table = new JTable();
        // 편집 비활성화
        item_table.setDefaultEditor(Object.class, null);
        // 드래그 비활성화
        item_table.setDragEnabled(false);
        // 선택 모드 설정
        item_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        updateData();
        JScrollPane jScrollPane = new JScrollPane(item_table);
        mainframe.add(jScrollPane, BorderLayout.CENTER);

        mainframe.setVisible(true);
    }

    public void updateData(){
        model = new DefaultTableModel(new String[]{"순서", "물품", "가격", "판매자"}, 0);
        int i = 1;
        for(ItemProduct item : Singleton.getSell_item_list()){
            String title = item.getTitle();
            String price = String.valueOf(item.getPrice());
            String seller = item.getUserID();
            model.addRow(new Object[]{i, title, price, seller});
            i++;
        }
        item_table.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(item_table.getSelectedRow() == -1){
            JOptionPane.showMessageDialog(mainframe, "항목이 선택되지 않았습니다.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        else if(e.getSource() == read){
            button.click(0, item_table.getSelectedRow());
        }
        else if(e.getSource() == delete){
            button.click(1, item_table.getSelectedRow());
        }
        else if(e.getSource() == update){
            button.click(2, item_table.getSelectedRow());
        }
    }
}
