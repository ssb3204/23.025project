package CartState;

import javax.swing.*;

public class InCart extends Cart{
    @Override
    public CartState storeToCart() {
        //선택한 물건을 장바구니에서 삭제하는 매소드 호출
        JOptionPane.showMessageDialog(null,"장바구니에서 삭제했습니다");
        return new NotInCart();
    }

    @Override
    public String getCart() {
        return "포함";
    }

    @Override
    public CartState changeCartState() {
        return new NotInCart();
    }


}
