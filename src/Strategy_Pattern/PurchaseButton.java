package Strategy_Pattern;

public class PurchaseButton {
    private PricingStrategy strategy;

    public PurchaseButton(PricingStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public int click(int price, int quantity){
        return strategy.calculatePrice(price, quantity);
    }
}
