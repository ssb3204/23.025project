package CartState;

public class Protuct {
    private CartState cartState;

    public Protuct(CartState cartState) {
        this.cartState = cartState;
    }

    public void StoreToCart() {
        cartState.storeToCart();
    }

}
