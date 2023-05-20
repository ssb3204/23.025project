package Strategy_Pattern;

import Dao_Pattern_Class.UserDAOImpl;
import Facade_Pattern_Class.DatabaseFacade;
import Factory_Pattern_Class.ItemProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PurchaseUI implements ActionListener {
    JDialog frame;
    int result;
    int count = -1;
    String id;
    JComboBox comboBox;
    JButton buy_button;
    PurchaseButton button;
    JTextField textField;
    ItemProduct item;
    JLabel result_label;

    public PurchaseUI(JDialog TOP, ItemProduct item, String id) {
        this.item = item;
        result = 0;
        this.id = id;

        frame = new JDialog(TOP , "결제 화면", true);
        frame.setSize(400, 350);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                result = 0;
                count = -1;
                frame.dispose();
            }
        });

        //제목
        JLabel item_title = new JLabel(item.getTitle());
        Font font = item_title.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
        item_title.setFont(boldFont);
        Dimension labelSize = item_title.getPreferredSize();
        int x = (frame.getWidth() - labelSize.width) / 2;
        int y = 20; // 원하는 y 좌표 값
        item_title.setBounds(x, y, labelSize.width, labelSize.height);
        frame.add(item_title);


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label  = new JLabel("결제 방법     ");
        String sortList[] = {"일반구매", "대량구매(-10%)", "첫결제(-20%)"};
        comboBox = new JComboBox<String>(sortList);
        comboBox.setSelectedIndex(-1);
        comboBox.addActionListener(this);

        panel.add(label, BorderLayout.WEST);
        panel.add(comboBox, BorderLayout.CENTER);
        frame.add(panel);
        panel.setBounds(50,160,250,20);

        String address = "테스트 주소입니다";
        JLabel address_label  = new JLabel("주소              " + address);
        frame.add(address_label);
        address_label.setBounds(50, 80, 300, 20);

        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel customer_label  = new JLabel("개수              ");
        customer_label.setBounds(0,0,100,20);
        panel2.add(customer_label, BorderLayout.WEST);
        textField = new JTextField();
        textField.setText("1");
        panel2.add(textField, BorderLayout.CENTER);
        frame.add(panel2);
        panel2.setBounds(50, 120, 130, 20);

        result_label = new JLabel("총 결제액 : ");
        frame.add(result_label);
        result_label.setBounds(x, 200, 200, 20);

        buy_button = new JButton("구매");
        frame.add(buy_button);
        buy_button.addActionListener(this);
        buy_button.setBounds(130, 240, 130, 30);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Integer.parseInt(textField.getText()) > item.getCount()){
            JOptionPane.showMessageDialog(null, "물품의 수량이 부족합니다.\n" + "현재 남은 개수 : " + item.getCount(), "알림", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(e.getSource() == comboBox){
            switch (comboBox.getSelectedIndex()){
                case 0:{
                    button = new PurchaseButton(new DefaultPricingStrategy());
                    result = button.click(item.getPrice(), Integer.parseInt(textField.getText()));
                    result_label.setText("총 결제액 : " + result);
                    break;
                }
                case 1:{
                    if(Integer.parseInt(textField.getText()) >= 10) {
                        button = new PurchaseButton(new VolumeDiscountPricingStrategy());
                        result = button.click(item.getPrice(), Integer.parseInt(textField.getText()));
                        result_label.setText("총 결제액 : " + result);
                    }
                    else{
                        comboBox.setSelectedIndex(-1);
                        result = 0;
                        result_label.setText("총 결제액 : ");
                        JOptionPane.showMessageDialog(null, "구매 개수가 10개 이상일 때 선택 가능합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }
                case 2:{
                    UserDAOImpl dao = new UserDAOImpl(new DatabaseFacade());
                    if(dao.isFirstPayment(id)){
                        result = 0;
                        result_label.setText("총 결제액 : ");
                        comboBox.setSelectedIndex(-1);
                        JOptionPane.showMessageDialog(null, "이미 첫 결제할인을 사용하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        button = new PurchaseButton(new FirstPaymentPricingStrategy());
                        result = button.click(item.getPrice(), Integer.parseInt(textField.getText()));
                        result_label.setText("총 결제액 : " + result);
                        dao.firstPayment(id);
                    }
                    break;
                }
            }
        }
        else if(e.getSource() == buy_button){
            if(result != 0) {
                count = Integer.parseInt(textField.getText());
                frame.dispose();
            }
            else{
                comboBox.setSelectedIndex(-1);
                JOptionPane.showMessageDialog(null, "결제방법을 선택해 주세요.", "경고", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public int getCount() {
        return count;
    }

    public int getResultValue(){
        return result;
    }
}
