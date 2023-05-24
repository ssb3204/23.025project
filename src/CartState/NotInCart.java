package CartState;

import javax.swing.*;

public class NotInCart extends Cart {
    @Override
    public CartState storeToCart() {
        //선택한 물건을 장바구니에 담는 매소드호출
        JOptionPane.showMessageDialog(null,"장바구니에 담았습니다");
        return new InCart();
    }
    @Override
    public String getCart() {
        return "미포함";
    }
    @Override
    public CartState changeCartState() {
        return new InCart();
    }
}
