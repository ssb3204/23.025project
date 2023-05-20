package Strategy_Pattern;

import Factory_Pattern_Class.GeneralItemCreator;
import Factory_Pattern_Class.ItemCreator;
import Factory_Pattern_Class.ItemProduct;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestDrive {
    public static void main(String[] args) {
        JDialog mainframe = new JDialog(null, "메인 화면", null);
        mainframe.setSize(700, 600);
        mainframe.setLayout(null);
        mainframe.setLocationRelativeTo(null);
        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        mainframe.setVisible(true);

        ItemCreator itemCreator = new GeneralItemCreator();
        ItemProduct item = itemCreator.createItemProduct("test", 10000, 100, "테스트", null, "admin", 10);

        PurchaseUI dialog = new PurchaseUI(mainframe, item, "id");
        int price = 0;
        if(dialog.getResultValue() != -1) {
            price = dialog.getResultValue();
        }

        System.out.println(price);

        // 기본 가격 계산 버튼
        PurchaseButton button1 = new PurchaseButton(new DefaultPricingStrategy());
        button1.click(100, 5);

        // 양적 할인 가격 계산 버튼
        PurchaseButton button2 = new PurchaseButton(new VolumeDiscountPricingStrategy());
        button2.click(100, 5);

        // 회원 할인 가격 계산 버튼
        PurchaseButton button3 = new PurchaseButton(new FirstPaymentPricingStrategy());
        button3.click(100, 5);
    }
}
