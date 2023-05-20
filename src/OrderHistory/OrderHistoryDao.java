package OrderHistory;

import java.util.List;

public interface OrderHistoryDao {
    /**DB에 구매기록을 저장*/
    void addHistory(OrderHistoryObj obj);
    /**DB 에서 구매기록을 불러온다*/
    List<OrderHistoryObj> readHistory(String user);
    /**DB 에서 판매기록을 불러온다*/
    List<OrderHistoryObj> readSalesRecord(String user);
}
