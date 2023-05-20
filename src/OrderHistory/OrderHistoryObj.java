package OrderHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderHistoryObj {
    String title;
    String price;
    String user;
    String time;
    String customer;
    int count;

    public OrderHistoryObj(String title, String price, String user, String customer, int count) {
        this.title = title;
        this.price = price;
        this.user = user;
        LocalDateTime now = LocalDateTime.now(); // 현재 시간을 가져옴
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 출력할 형식을 지정
        this.time =  now.format(formatter); // 형식에 맞게 시간을 문자열로 변환
        this.customer = customer;
        this.count = count;
    }

    public OrderHistoryObj(String title, String price, String user, String time, String customer, int count) {
        this.title = title;
        this.price = price;
        this.user = user;
        this.time = time;
        this.customer =customer;
        this.count = count;
    }

    public String getUser() {
        return user;
    }
    public String getTitle() {
        return title;
    }
    public String getPrice() {
        return price;
    }
    public String getTime() {
        return time;
    }
    public String getCustomer() {
        return customer;
    }
    public int getCount() {
        return count;
    }
}
