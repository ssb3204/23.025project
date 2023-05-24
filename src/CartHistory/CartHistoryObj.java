package CartHistory;

public class CartHistoryObj {

    String title;
    String price;
    String user;
    String custumer;
    int count;

    public CartHistoryObj(String title, String price, String user, String custumer, int count) {
        this.title = title;
        this.price = price;
        this.user = user;
        this.custumer = custumer;
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
    public String getCustumer() {
        return custumer;
    }
    public int getCount() {
        return count;
    }

}
