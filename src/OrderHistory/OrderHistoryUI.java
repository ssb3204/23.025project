package OrderHistory;

import DatabaseConnect.DatabaseFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class OrderHistoryUI implements ActionListener{
    private JDialog orderHistoryFrame;
    private JTable orderHistoryTable;
    List<OrderHistoryObj> list;
    DefaultTableModel model;
    List<OrderHistoryObj> list2;
    DefaultTableModel model2;
    JButton changeButton;
    boolean mode;
    OrderHistoryDao orderDao;
    public OrderHistoryUI(JFrame TOP, String user) {

        mode = false;
        orderHistoryFrame = new JDialog(TOP, "구매기록", true);
        orderHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderHistoryFrame.setSize(500, 500);
        orderHistoryFrame.setLayout(new BorderLayout());
        orderHistoryFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                orderHistoryFrame.dispose();
            }
        });

        orderDao = new OrderHistoryDaoImpl(new DatabaseFacade());

        //구매기록
        model = new DefaultTableModel(new String[]{"판매자", "물품", "가격", "개수", "구매일자"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 편집 불가능하도록 설정
            }
        };
        //판매기록
        model2 = new DefaultTableModel(new String[]{"구매자", "물품", "가격", "개수", "판매일자"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 편집 불가능하도록 설정
            }
        };

        //구매기록
        list = orderDao.readHistory(user);
        for(OrderHistoryObj obj : list){
            String userID = obj.getUser();
            String title = obj.getTitle();
            String price = obj.getPrice();
            String time = obj.getTime();
            int count = obj.getCount();
            model.addRow(new Object[]{userID, title, price, count, time});
        }

        //판매기록
        list2 = orderDao.readSalesRecord(user);
        for(OrderHistoryObj obj : list2){
            String customer = obj.getCustomer();
            String title = obj.getTitle();
            String price = obj.getPrice();
            String time = obj.getTime();
            int count = obj.getCount();
            model2.addRow(new Object[]{customer, title, price, count, time});
        }

        orderHistoryTable = new JTable(model);
        TableColumnModel columnModel = orderHistoryTable.getColumnModel();
        columnModel.getColumn(4).setPreferredWidth(110);

        JScrollPane scrollPane = new JScrollPane(orderHistoryTable);
        orderHistoryFrame.add(scrollPane, BorderLayout.CENTER);

        changeButton = new JButton("판매기록");
        changeButton.addActionListener(this);
        orderHistoryFrame.add(changeButton, BorderLayout.SOUTH);

        orderHistoryFrame.setLocationRelativeTo(null);
        orderHistoryFrame.setResizable(false);
        orderHistoryFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeButton){
            if(mode){
                changeButton.setText("판매기록");
                mode = false;
                orderHistoryTable.setModel(model);
                orderHistoryFrame.setTitle("구매기록");
                TableColumnModel columnModel = orderHistoryTable.getColumnModel();
                columnModel.getColumn(4).setPreferredWidth(110);
            }
            else{
                changeButton.setText("구매기록");
                mode = true;
                orderHistoryTable.setModel(model2);
                orderHistoryFrame.setTitle("판매기록");
                TableColumnModel columnModel = orderHistoryTable.getColumnModel();
                columnModel.getColumn(4).setPreferredWidth(110);
            }
        }
    }

}
