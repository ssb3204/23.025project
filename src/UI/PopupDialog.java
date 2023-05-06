package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PopupDialog implements ActionListener {
    MainUI TopUI;
    JFrame popup_frame;
    int index;

    //패널
    JPanel item_panel;
    //물품의 제목
    JLabel item_title;
    //물품의 가격
    JLabel item_price;
    //물품의 개수
    JLabel item_count;
    //물품의 이미지
    JLabel item_image;
    //판매자
    JLabel item_seller;
    //물품의 설명
    JTextArea item_description;
    //구매 버튼
    JButton item_buy_button;


    public PopupDialog(MainUI TopUI, int index) {
        //값 초기화
        this.TopUI = TopUI;
        this.index = index;

        //팝업화면 설정
        popup_frame = new JFrame("물품 상세정보");
        popup_frame.setSize(700, 600);
        popup_frame.setLayout(null);
        popup_frame.setLocationRelativeTo(null);
        popup_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TopUI.mainframe.setVisible(true);
                popup_frame.dispose();
            }
        });


        //패널
        item_panel = new JPanel(null);
        item_panel.setBounds(0, 0, 800, 600);

        //제목
        item_title = new JLabel("상품명 : " + TopUI.sell_item_list.getItemProduct(index).getTitle());
        Font font = item_title.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
        item_title.setFont(boldFont);
        item_panel.add(item_title);
        item_title.setBounds(350, 80, 200, 20);


        //가격
        item_price = new JLabel(String.valueOf("가격  : " + TopUI.sell_item_list.getItemProduct(index).getPrice()) + "원");
        item_panel.add(item_price);
        font = item_price.getFont();
        Font PriceFont = new Font(font.getFontName(), Font.BOLD, 20);
        item_price.setFont(PriceFont);
        item_price.setForeground(Color.RED);
        item_price.setBounds(350, 110, 200,20);


        //개수
        item_count = new JLabel("수량  : " + String.valueOf(TopUI.sell_item_list.getItemProduct(index).getCount()) + "개");
        font = item_count.getFont();
        Font countFont = new Font(font.getFontName(), Font.PLAIN, 20);
        item_count.setFont(countFont);
        item_panel.add(item_count);
        item_count.setBounds(350, 140, 200, 20);


        //판매자
        item_seller = new JLabel(TopUI.sell_item_list.getItemProduct(index).getUserID());
        item_panel.add(item_seller);
        item_seller.setBounds(350, 60, 100, 20);

        //이미지
        item_image = new JLabel();
        try {
            BufferedImage image = ImageIO.read(TopUI.sell_item_list.getItemProduct(index).getImageFile());
            // 이미지의 크기를 150x150으로 변경
            Image resizedImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            item_image.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        item_panel.add(item_image);
        item_image.setBounds(40,40, 250, 250);

        //설명
        item_description = new JTextArea();
        item_panel.add(item_description);
        item_description.setText(TopUI.sell_item_list.getItemProduct(index).getDescription());
        item_description.setEditable(false);
        item_description.setBounds(40, 320, 600, 200);


        //구매버튼
        item_buy_button = new JButton("구입");
        item_buy_button.addActionListener(this);
        item_buy_button.setBounds(300, 530, 100, 30);
        item_panel.add(item_buy_button);


        //패널을 메인프레임에 추가한다.
        popup_frame.add(item_panel);

        popup_frame.setResizable(false);
        popup_frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == item_buy_button){
            System.out.println("물품을 구매하셨습니다.");
            TopUI.sell_item_list.decreaseItemCount(index);
            //만약 물품의 개수가 0이라면 물품을 제거한다.
            if(TopUI.sell_item_list.getItemProduct(index).getCount() == 0) {
                System.out.println("현재 목록 개수" + TopUI.sell_item_list.getSize());
                TopUI.sell_item_list.removeItem(index);
                TopUI.removePanel();
            }
            TopUI.loadUI();
            TopUI.reloadUI();
            TopUI.mainframe.setVisible(true);
            popup_frame.dispose();
        }
    }
}
