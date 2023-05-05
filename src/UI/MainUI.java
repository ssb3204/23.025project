package UI;

import Factory_Pattern_Class.ItemProduct;
import Singleton_Pattern_Class.Singleton;
import example.MypageFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainUI implements ActionListener {

    Singleton sell_item_list;//물품 리스트 객체
    JFrame mainframe;//메인프레임
    JButton create_item;//물품 생성 버튼
    JButton output_button;

    JButton Mypage_button;//마이페이지 버튼

    JButton Logout_button;//로그아웃 버튼

    //페이지 번호
    JPanel page_number_panel;

    int total_page = 0;//총 페이지 수
    int page_number = 1;//현재 페이지 번호
    List<JPanel> panel_list;

    String id;
    String password;

    public MainUI (String id , String password) {
        this.id=id;
        this.password=password;

        //물품 리스트를 관리하기 위한 객체 생성
        sell_item_list = Singleton.getInstance();

        mainframe = new JFrame("메인화면");
        mainframe.setSize(1000, 700);
        mainframe.setLayout(null);
        mainframe.setLocationRelativeTo(null);
        mainframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        create_item = new JButton("물품생성");
        mainframe.add(create_item);
        create_item.setBounds(730, 600, 100,50);
        create_item.addActionListener(this);

        output_button = new JButton("출력");
        output_button.setBounds(850, 600, 100, 50);
        mainframe.add(output_button);
        output_button.addActionListener(this);

        Mypage_button = new JButton("마이페이지");
        Mypage_button.setBounds(850, 500, 100, 50);
        mainframe.add(Mypage_button);
        Mypage_button.addActionListener(this);

        Logout_button = new JButton("로그아웃");
        Logout_button.setBounds(730, 500, 100, 50);
        mainframe.add(Logout_button);
        Logout_button.addActionListener(this);


        //판매 물품 화면을 띄울 공간
        panel_list = new ArrayList<JPanel>();

        
        //페이지 번호 공간
        page_number_panel = new JPanel(new FlowLayout());
        mainframe.add(page_number_panel);
        page_number_panel.setBounds(0, 680, 600, 20);
        
        mainframe.setResizable(false);
        mainframe.setVisible(true);
    }



    /**화면 리로드*/
    public void reloadUI(){
        loadUI();
        System.out.println("화면을 리로드합니다." + " 현재 등록된 물품 개수 : " + panel_list.size());
        mainframe.validate();
        mainframe.repaint();
    }


    /**물품 패널을 추가한다*/
    public void addPanel(){
        int i = sell_item_list.getSize() - 1;
        //이미지 라벨 생성
        JLabel image_label = new JLabel();
        try {
            BufferedImage image = ImageIO.read(sell_item_list.getItemProduct(i).getImageFile());
            // 이미지의 크기를 150x150으로 변경
            Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            image_label.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //제목 라벨 생성
        JLabel title_label = new JLabel(sell_item_list.getItemProduct(i).getTitle());

        //패널 생성
        JPanel item_panel = new JPanel(new BorderLayout());

        item_panel.add(image_label, BorderLayout.CENTER);
        item_panel.add(title_label, BorderLayout.SOUTH);
        item_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PopupDialog(MainUI.this, i);
                mainframe.setVisible(false);
            }
        });
        panel_list.add(item_panel);

    }


    /**메인화면에 등록된 물품들을 로드한다*/
    public void loadUI(){
        int x = 100;
        int y = 100;
        int i = 1;
        int range = page_number * 8;//범위 = 페이지 번호 * 페이지에 출력되는 물품 수
        for(JPanel jp : panel_list){
            if((range - 8) < i && i <= (range)) {
                mainframe.add(jp);
                jp.setBounds(x, y, 200, 200);
                if (i % 4 == 0) {
                    x = 100;
                    y += 200;
                } else if (i >= 8) {
                    return;
                } else {
                    x += 200;
                }
            }
            i++;
        }
    }

    /**버튼의 이벤트 처리 메소드*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create_item){
            mainframe.setVisible(false);
            new SellerProductUploadUI(this);
        }
        else if (e.getSource() == output_button) {
            showItemList();
        }else if(e.getSource() == Mypage_button) {
            new MypageFrame(id, password);
        }

    }

    /**상품 리스트에 새로운 상품을 추가하는 메서드*/
    public void add_item_list(ItemProduct item){
        //싱글톤 객체에 아이템을 추가하는 코드
        sell_item_list.addItem(item);
        addPanel();
    }

    /**상품 리스트의 내용물을 출력하는 메서드*/
    public void showItemList(){
        //싱글톤 객체의 내용을 보여주는 메서드
        sell_item_list.showItem();
    }
}
