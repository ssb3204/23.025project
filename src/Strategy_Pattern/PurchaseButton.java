package Strategy_Pattern;

public class PurchaseButton {
    private PricingStrategy strategy;

    public PurchaseButton(){}
    public int click(int price, int quantity){
        return strategy.calculatePrice(price, quantity);
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

}
