package CartState;

public abstract class Cart implements CartState {
    @Override
    public CartState storeToCart() {
        //선택한 물건을 장바구니에 담는다
        return new InCart();
    }
    public abstract String getCart();
}
