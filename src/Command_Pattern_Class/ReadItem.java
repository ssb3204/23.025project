package Command_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import Singleton_Pattern_Class.Singleton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**물품의 정보를 볼 수 있는 클래스*/
public class ReadItem{
    JFrame popup_frame;
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
    Singleton singleton;
    public ReadItem(ItemProduct item) {
        //값 초기화
        singleton = Singleton.getInstance();

        //팝업화면 설정
        popup_frame = new JFrame("물품 상세정보");
        popup_frame.setSize(700, 600);
        popup_frame.setLayout(null);
        popup_frame.setLocationRelativeTo(null);
        popup_frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                popup_frame.dispose();
            }
        });


        //패널
        item_panel = new JPanel(null);
        item_panel.setBounds(0, 0, 800, 600);

        //제목
        item_title = new JLabel("상품명 : " + item.getTitle());
        Font font = item_title.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, 20);
        item_title.setFont(boldFont);
        item_panel.add(item_title);
        item_title.setBounds(350, 80, 400, 20);


        //가격
        item_price = new JLabel(String.valueOf("가격  : " + item.getPrice()) + "원");
        item_panel.add(item_price);
        font = item_price.getFont();
        Font PriceFont = new Font(font.getFontName(), Font.BOLD, 20);
        item_price.setFont(PriceFont);
        item_price.setForeground(Color.RED);
        item_price.setBounds(350, 110, 200,20);


        //개수
        item_count = new JLabel("수량  : " + String.valueOf(item.getPrice()) + "개");
        font = item_count.getFont();
        Font countFont = new Font(font.getFontName(), Font.PLAIN, 20);
        item_count.setFont(countFont);
        item_panel.add(item_count);
        item_count.setBounds(350, 140, 200, 20);


        //판매자
        item_seller = new JLabel(item.getUserID());
        item_panel.add(item_seller);
        item_seller.setBounds(350, 60, 100, 20);

        //이미지
        item_image = new JLabel();
        try {
            BufferedImage image = ImageIO.read(item.getImageFile());
            // 이미지의 크기를 150x150으로 변경
            Image resizedImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
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
        item_description.setText(item.getDescription());
        item_description.setEditable(false);
        item_description.setBounds(40, 320, 600, 200);


        //패널을 메인프레임에 추가한다.
        popup_frame.add(item_panel);

        popup_frame.setResizable(false);
        popup_frame.setVisible(true);
    }
}
