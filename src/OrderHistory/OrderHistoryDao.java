package OrderHistory;

import Factory_Pattern_Class.ItemProduct;
import Observer_Pattern_class.NoticeObj;

import java.util.List;

public interface OrderHistoryDao {
    /**DB에 구매기록을 저장*/
    void addHistory(OrderHistoryObj obj);
    /**DB 에서 구매기록을 불러온다*/
    List<OrderHistoryObj> readHistory(String user);
}
