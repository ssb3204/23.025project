package Strategy_Pattern;

public class DefaultPricingStrategy implements PricingStrategy{
    /**기본 구매*/
    @Override
    public int calculatePrice(int price, int quantity) {
        return price * quantity;
    }
}
