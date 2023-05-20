package Strategy_Pattern;

public class VolumeDiscountPricingStrategy implements PricingStrategy{
    /**많이 구매시*/
    @Override
    public int calculatePrice(int price, int quantity) {
        return (int)((price * quantity) * 0.9); // 10% 할인
    }
}
