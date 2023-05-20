package Strategy_Pattern;

public class FirstPaymentPricingStrategy implements PricingStrategy {
    @Override
    public int calculatePrice(int price, int quantity) {
        return (int)((price * quantity) * 0.8); // 20% 할인
    }
}
