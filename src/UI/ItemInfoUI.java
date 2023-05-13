package UI;

import Dao_Pattern_Class.ItemDao;
import Dao_Pattern_Class.ItemDaoImpl;
import Facade_Pattern_Class.DatabaseFacade;
import Factory_Pattern_Class.GeneralItemCreator;
import Factory_Pattern_Class.ItemProduct;
import Singleton_Pattern_Class.Singleton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ItemInfoUI implements ActionListener {
    private JPanel item_info_panel;
    private int index;
    private Singleton item_list;

    JButton setButton;//상품 등록 버튼

    //상품의 제목 컴포넌트
    JPanel item_title_panel;
    JTextField item_title_field;
    JLabel item_title_label;


    //상품의 가격을 입력받을 객체
    JPanel item_price_panel;
    JTextField item_price_field;
    JLabel item_price_label;


    //상품의 개수를 입력받을 객체
    JPanel item_count_panel;
    JTextField item_count_field;
    JLabel item_count_label;

    //상품의 사진을 입력받을 객체
    JFileChooser item_image_file;
    FileNameExtensionFilter image_filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
    JPanel item_image_panel;
    JButton item_image_button;
    JTextField item_imagePath_field;
    JLabel item_image_label;
    JLabel item_imagePath_label;
    String item_imagePath;


    //상품의 설명을 입력받을 객체
    JPanel item_description_panel;
    JLabel item_description_label;
    JTextArea item_description_textarea;

    //팩토리 메서드에 입력될 정보들
    File selectedFile;//이미지 파일
    String item_title;//상품의 제목
    String item_description;//상품의 설명
    int item_price = -1;//상품의 가격
    int item_count = -1;//상품의 개수

    PopupDialog topUI;
    public ItemInfoUI(int index, PopupDialog topUI) {
        this.topUI = topUI;
        this.item_list = Singleton.getInstance();
        this.index = index;
        item_info_panel = new JPanel(null);
        item_info_panel.setBounds(-70, 20, 700, 600);

        //제목 공간
        item_title_field = new JTextField(20);
        item_title_label = new JLabel("제목");
        item_title_panel = new JPanel(new BorderLayout());
        item_title_panel.add(item_title_field, BorderLayout.CENTER);
        item_title_panel.add(item_title_label, BorderLayout.WEST);
        item_title_field.setText(item_list.getItemProduct(index).getTitle());
        item_title_panel.setBounds(250, 20, 300, 25);
        item_info_panel.add(item_title_panel);


        //가격 공간
        item_price_panel = new JPanel(new BorderLayout());
        item_price_field = new JTextField(10);
        item_price_label = new JLabel("가격");
        item_price_panel.add(item_price_label, BorderLayout.WEST);
        item_price_panel.add(item_price_field, BorderLayout.CENTER);
        item_price_field.setText(String.valueOf(item_list.getItemProduct(index).getPrice()));
        item_price_panel.setBounds(250, 50, 300, 25);
        item_info_panel.add(item_price_panel);


        //물품 개수 공간
        item_count_panel = new JPanel(new BorderLayout());
        item_count_field = new JTextField(10);
        item_count_label = new JLabel("물품 개수");
        item_count_panel.add(item_count_label, BorderLayout.WEST);
        item_count_panel.add(item_count_field, BorderLayout.CENTER);
        item_count_panel.setBounds(223,80,327,25);
        item_count_field.setText(String.valueOf(item_list.getItemProduct(index).getCount()));
        item_info_panel.add(item_count_panel);


        //상품 사진을 입력받을 공간
        item_image_panel = new JPanel(new BorderLayout());
        item_imagePath_field = new JTextField(20);
        item_imagePath_field.setEditable(false);
        item_image_button = new JButton("상품 이미지 등록");

        try {
            BufferedImage image = ImageIO.read(item_list.getItemProduct(index).getImageFile());
            // 이미지의 크기를 150x150으로 변경
            Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            item_image_label = new JLabel(icon);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        item_imagePath_label = new JLabel("이미지 경로");
        item_info_panel.add(item_image_label);
        item_image_label.setBounds(320,110,150,150);
        item_image_panel.add(item_imagePath_label, BorderLayout.WEST);
        item_image_panel.add(item_imagePath_field, BorderLayout.CENTER);
        item_image_panel.add(item_image_button, BorderLayout.EAST);
        item_image_file = new JFileChooser();
        item_image_file.setFileFilter(image_filter);
        item_info_panel.add(item_image_panel);
        item_image_panel.setBounds(190,270, 480, 25);
        item_image_button.addActionListener(this);

        selectedFile = item_list.getItemProduct(index).getImageFile();

        //상품 정보를 입력받을 공간
        item_description_panel = new JPanel(new BorderLayout());
        item_description_label = new JLabel("상품 설명");
        item_description_textarea = new JTextArea();
        item_description_panel.add(item_description_label, BorderLayout.WEST);
        item_description_panel.add(item_description_textarea, BorderLayout.CENTER);
        item_info_panel.add(item_description_panel);
        item_description_textarea.setText(item_list.getItemProduct(index).getDescription());
        item_description_panel.setBounds(200, 300, 350, 150);


        //수정 버튼 공간
        setButton = new JButton("수정");
        item_info_panel.add(setButton);
        setButton.setBounds(350, 460, 100, 30);
        setButton.addActionListener(this);

    }

    /**입력값을 체크하고 저장하는 메서드*/
    public boolean checkInputData(){
        //입력되어야하는 값이 비어있을때 알림 후 함수를 빠져나간다.
        if("".equals(item_title_field.getText())){
            //제목칸이 공백이라면
            System.out.println("제목을 입력해주세요!");
            return false;
        }
        try {//가격이 공백, 문자, 음수나 0이 아니라면
            //물품의 가격을 저장
            item_price = Integer.parseInt(item_price_field.getText());
            if(item_price < 1){
                return false;
            }
        } catch (NumberFormatException er) {
            System.out.println("가격을 제대로 입력해주세요!");
            return false;
        }
        try {//물품의 개수가 공백, 문자, 음수나 0이 아니라면
            //물픔의 개수를 저장
            item_count = Integer.parseInt(item_count_field.getText());
            if(item_count < 1){
                System.out.println("물품의 개수를 제대로 입력해주세요!");
                return false;
            }
        } catch (NumberFormatException er) {
            System.out.println("물품의 개수를 제대로 입력해주세요!");
            return false;
        }
        if(selectedFile == null){
            System.out.println("파일을 등록해주세요!");
            return false;
        }
        else if(item_description_textarea.getText().equals("")){
            System.out.println("설명을 입력해주세요!");
            return false;
        }

        //물품의 제목을 저장
        item_title = item_title_field.getText();
        //물품의 설명을 저장
        item_description = item_description_textarea.getText();

        //이미지파일 저장
        //saveImageFile();

        return true;
    }
    public JPanel getItem_info_panel() {
        return item_info_panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//이미지 파일을 등록하는 버튼을 클릭하였을때
        if(e.getSource() == item_image_button){
            int result = item_image_file.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = item_image_file.getSelectedFile();
                item_imagePath = selectedFile.getAbsolutePath();
                item_imagePath_field.setText(item_imagePath);
                try {
                    BufferedImage image = ImageIO.read(selectedFile);
                    // 이미지의 크기를 150x150으로 변경
                    Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(resizedImage);
                    item_image_label.setIcon(icon);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getSource() == setButton){
            //입력값을 체크한다.
            if(!checkInputData())   return;
            
            //정보 수정
            item_list.upDateItem(index, item_title, item_count, item_description, item_price, selectedFile);
            item_list.dbUpload(index);
            topUI.TopUI.deletePanel();
            topUI.TopUI.resetFrame();
            topUI.popup_frame.dispose();
        }
    }
}
