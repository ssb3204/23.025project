package OrderHistory;

import Facade_Pattern_Class.DatabaseFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryUI {
    private JDialog orderHistoryFrame;
    private JTable orderHistoryTable;

    public OrderHistoryUI(JFrame TOP, String user) {
        orderHistoryFrame = new JDialog(TOP, "구매기록", true);
        orderHistoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderHistoryFrame.setSize(500, 500);
        DefaultTableModel model = new DefaultTableModel(new String[]{"판매자", "물품", "가격", "구매일자"}, 0);

        List<OrderHistoryObj> list = new ArrayList<OrderHistoryObj>();
        OrderHistoryDao orderDao = new OrderHistoryDaoImpl(new DatabaseFacade());
        list = orderDao.readHistory(user);

        for(OrderHistoryObj obj : list){
            String userID = obj.getUser();
            String title = obj.getTitle();
            String price = obj.getPrice();
            String time = obj.getTime();
            model.addRow(new Object[]{userID, title, price, time});
        }

        orderHistoryTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(orderHistoryTable);
        orderHistoryFrame.add(scrollPane);

        orderHistoryFrame.setLocationRelativeTo(null);
        orderHistoryFrame.setResizable(false);
        orderHistoryFrame.setVisible(true);
    }
}
