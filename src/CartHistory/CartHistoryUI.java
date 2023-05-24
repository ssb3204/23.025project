package CartHistory;

import DatabaseConnect.DatabaseConect;
import Facade_Pattern.ItemFacade;
import Factory_Pattern.ItemProduct;
import Singleton_Pattern.Singleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CartHistoryUI implements ActionListener {
    private JDialog cartHistoryFrame;
    private JTable cartHistoryTable;
    List<ItemProduct>  list;
    DefaultTableModel model;
    JButton buyAll;
    public String user;
    Singleton singleton;
    ItemFacade itemFacade;
    boolean mode;

    public CartHistoryUI(JFrame TOP, String user) {
        this.user=user;
        mode = false;
        cartHistoryFrame = new JDialog(TOP, "장바구니", true);
        cartHistoryFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cartHistoryFrame.dispose();
            }
        });

        cartHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartHistoryFrame.setSize(500, 500);
        cartHistoryFrame.setLocationRelativeTo(null);
        cartHistoryFrame.setLayout(new BorderLayout());
        cartHistoryFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cartHistoryFrame.dispose();
            }
        });


        //장바구니
        model = new DefaultTableModel(new String[]{"판매자", "물품", "가격", "개수"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 편집 불가능하도록 설정
            }
        };

        //장바구니
        singleton = Singleton.getInstance();
        itemFacade = ItemFacade.getItemFacade();
        list = singleton.getSell_item_list();

        for(ItemProduct obj : list){
            if(obj.getCartState().equals("포함")){
                String userID = obj.getUserID();
                String title = obj.getTitle();
                int price = obj.getPrice();
                int count = obj.getCount();
                model.addRow(new Object[]{userID, title, price, count});
            }
        }

        buyAll= new JButton("일괄 구매");
        buyAll.addActionListener(this);
        JPanel button_panel= new JPanel(new FlowLayout());
        cartHistoryFrame.add(buyAll, BorderLayout.SOUTH);
        cartHistoryTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(cartHistoryTable);
        cartHistoryFrame.add(scrollPane, BorderLayout.CENTER);

        cartHistoryFrame.setVisible(true);
    }

    public void update(){
        model.setRowCount(0);
        for(ItemProduct obj : list){
            if(obj.getCartState().equals("포함")){
                String userID = obj.getUserID();
                String title = obj.getTitle();
                int price = obj.getPrice();
                int count = obj.getCount();
                model.addRow(new Object[]{userID, title, price, count});
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buyAll){
            int i=0;
            for(ItemProduct obj : list){
                if(obj.getCartState().equals("포함")){
                    itemFacade.buyItem(i, obj.getPrice(),1,user);
                    obj.ChangeCartState();
                }
                i++;
            }
            JOptionPane.showMessageDialog(null, "일괄 구매 완료");
            update();
            cartHistoryFrame.validate();
            cartHistoryFrame.repaint();
        }
    }
}
