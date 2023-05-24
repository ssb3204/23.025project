package CartHistory;

import java.util.List;


public interface CartHistoryDao {
    void addHistory(CartHistoryObj obj) ;
    void deleteHistory(CartHistoryObj obj);
    List<CartHistoryObj> readHistory(String user);


}
