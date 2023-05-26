package CartState_Pattern;

public interface CartState {
    CartState storeToCart();
    String getCart();
    CartState changeCartState();
}
