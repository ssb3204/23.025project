package OrderHistory;

import Facade_Pattern_Class.DatabaseFacade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryDaoImpl implements OrderHistoryDao{
    DatabaseFacade database;
    public OrderHistoryDaoImpl(DatabaseFacade database) {
        this.database = database;
    }

    @Override
    public void addHistory(OrderHistoryObj obj) {
        try {
            database.connect();

            String query = "insert into OrderHistory (no, userID, title, price, time) values ((SELECT MAX(no) + 1 FROM OrderHistory), ?, ?, ?, ?)";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, obj.getUser());
            pstmt.setString(2, obj.getTitle());
            pstmt.setString(3, String.valueOf(obj.getPrice()));
            pstmt.setString(4, obj.getTime());
            pstmt.executeQuery();

            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<OrderHistoryObj> readHistory(String user) {
        List<OrderHistoryObj> order_list = new ArrayList<OrderHistoryObj>();
        try {
            database.connect();

            String query = "SELECT userID, title, price, time FROM OrderHistory WHERE userID = ? ORDER BY no DESC";
            PreparedStatement pstmt = database.getConn().prepareStatement(query);
            pstmt.setString(1, user);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                String title = rs.getString("title");
                String price = rs.getString("price");
                String userID = rs.getString("userID");
                String time = rs.getString("time");
                order_list.add(new OrderHistoryObj(title, price, userID, time));
            }
            rs.close();
            database.closeConnect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return order_list;
    }
}
