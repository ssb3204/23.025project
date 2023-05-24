package CartState;

public interface CartState {
    CartState storeToCart();
    String getCart();

    CartState changeCartState();
}
