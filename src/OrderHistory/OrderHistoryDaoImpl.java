package OrderHistory;

import DatabaseConnect.DatabaseConect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDaoImpl implements OrderHistoryDao{
    DatabaseConect database;
    public OrderHistoryDaoImpl(DatabaseConect database) {
        this.database = database;
    }

    @Override
    public void addHistory(OrderHistoryObj obj) {
        int no = -1;
        try {
            database.connect();
            String query = "SELECT MAX(no) FROM OrderHistory";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                no = rs.getInt("MAX(no)");
            }
            no++;
            pstmt.close();
            rs.close();

            query = "INSERT INTO OrderHistory (no, userID, title, price, time, customerID, count) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = database.getConn().prepareStatement(query);
            pstmt.setInt(1, no);
            pstmt.setString(2, obj.getUser());
            pstmt.setString(3, obj.getTitle());
            pstmt.setString(4, String.valueOf(obj.getPrice()));
            pstmt.setString(5, obj.getTime());
            pstmt.setString(6, obj.getCustomer());
            pstmt.setInt(7, obj.getCount());
            pstmt.executeUpdate();

            pstmt.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderHistoryObj> readHistory(String user) {
        //System.out.println("목록 불러옴");
        List<OrderHistoryObj> order_list = new ArrayList<OrderHistoryObj>();
        try {
            database.connect();

            String query = "SELECT userID, title, price, time, customerID, count FROM OrderHistory WHERE customerID = ? ORDER BY no DESC";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String title = rs.getString("title");
                String price = rs.getString("price");
                String userID = rs.getString("userID");
                String time = rs.getString("time");
                String customer = rs.getString("customerID");
                int count = rs.getInt("count");
                order_list.add(new OrderHistoryObj(title, price, userID, time, customer, count));
            }
            pstmt.close();
            rs.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return order_list;
    }

    @Override
    public List<OrderHistoryObj> readSalesRecord(String user) {
        //System.out.println("판매기록 불러옴");
        List<OrderHistoryObj> sales_list = new ArrayList<OrderHistoryObj>();
        try {
            database.connect();

            String query = "SELECT customerID, title, price, time, customerID, count FROM OrderHistory WHERE userID = ? ORDER BY no DESC";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String title = rs.getString("title");
                String price = rs.getString("price");
                String userID = rs.getString("customerID");
                String time = rs.getString("time");
                String customer = rs.getString("customerID");
                int count = rs.getInt("count");
                sales_list.add(new OrderHistoryObj(title, price, userID, time, customer, count));
            }
            pstmt.close();
            rs.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return sales_list;
    }
}
