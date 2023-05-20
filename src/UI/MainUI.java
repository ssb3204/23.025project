package UI;

import Command_Pattern_Class.AdminUI;
import Factory_Pattern_Class.ItemProduct;
import Observer_Pattern_class.Notice;
import Observer_Pattern_class.NoticeUI;
import Observer_Pattern_class.PushNotice;
import OrderHistory.OrderHistoryUI;
import Singleton_Pattern_Class.Singleton;
import Template_Method_Pattern_Class.*;
import example.LoginFrame;
import example.MypageFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainUI implements ActionListener {

    public Singleton sell_item_list;//물품 리스트 객체
    public JFrame mainframe;//메인프레임
    JButton create_item;//물품 생성 버튼
    
    JButton orderHistory_button;//구매기록
    JButton Mypage_button;//마이페이지 버튼
    JButton Logout_button;//로그아웃 버튼

    //페이지 번호
    JPanel page_number_panel;
    int total_page = 0;//총 페이지 수
    JLabel page_number_label;
    JButton next_page_button;//다음 페이지 버튼
    JButton prev_page_button;//이전 페이지 버튼

    //메인화면 로고
    JLabel logo_label;
    String logoPath = "resource/logo.jpg";

    //물품 목록을 보여주기 위한 속성
    int page_number = 1;//현재 페이지 번호
    List<JPanel> panel_list;

    //물품 검색 컴포넌트
    JPanel search_panel;//검색창 패널
    JTextField search_field;//검색창 텍스트 필드
    JButton search_button;//검색버튼
    List<JPanel> search_item_list;//검색된 물품의 패널을 관리할 리스트
    int search_page_number;//검색 모드에서의 현재 페이지
    int search_total_page;//검색된 물품의 총 페이지
    boolean search_mode;//검색 모드 F: 일반, T: 검색 모드

    //알림 기능
    JButton notice_button;
    Notice notice; //알림을 관리할 객체
    PushNotice pushNotice;//전송될 알림을 관리하는 객체

    //정렬선택 드롭다운리스트
    JComboBox<String> sortComboBox;
    
    //로그인한 유저의 아이디 및 비밀번호 정보
    private final String id;
    private final String password;

    public static String state;

    boolean isTurnedOff = false; // 알림 상태 변수

    public MainUI (String id , String password,String state) {
        //유저 정보를 저장한다.
        this.id = id;
        this.password = password;
        this.state=state;

        //메인프레임 생성
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

        //물품 리스트를 관리하기 위한 객체 생성
        sell_item_list = Singleton.getInstance();
        sell_item_list.observer_list.clear();
        sell_item_list.setUser(id);
        sell_item_list.dbLoadItem();

        //알림 버튼
        notice_button = new JButton("\uD83D\uDD14");
        mainframe.add(notice_button);
        notice_button.setBounds(920,20, 50, 50);
        
        //알림 저장 객체
        notice_button.addActionListener(this);
        notice = new Notice(id);
        sell_item_list.subscribe(notice);

        //보내질 알림을 관리하는 객체
        pushNotice = new PushNotice(id);
        sell_item_list.subscribe(pushNotice);

        //로고 추가
        // 이미지 아이콘 생성
        ImageIcon icon = new ImageIcon(logoPath);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImg);
        logo_label = new JLabel(resizedIcon);
        logo_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                search_mode = false;
                resetFrame();
            }
        });
        mainframe.add(logo_label);
        logo_label.setBounds(30,10,150,100);


        //물품 검색
        search_panel = new JPanel(new BorderLayout());
        search_field = new JTextField();
        search_panel.add(search_field, BorderLayout.CENTER);
        search_button = new JButton("검색");
        search_panel.add(search_button, BorderLayout.EAST);
        search_button.addActionListener(this);
        mainframe.add(search_panel);
        search_panel.setBounds(275, 20, 450, 30);

        search_item_list = new ArrayList<JPanel>();
        search_mode = false;//검색모드를 일반모드로 한다.

        //물품 등록 버튼
        create_item = new JButton("물품생성");
        mainframe.add(create_item);
        create_item.setBounds(730, 600, 100,50);
        create_item.addActionListener(this);
        if(state=="admin"|| state=="user"){
            create_item.setEnabled(true);
        }
        else{
            create_item.setEnabled(false);
        }

        
        //현재 저장된 물품 목록을 보기 위한 버튼
        orderHistory_button = new JButton("구매기록");
        orderHistory_button.setBounds(850, 600, 100, 50);
        mainframe.add(orderHistory_button);
        orderHistory_button.addActionListener(this);

        
        //마이페이지에 접속하기 위한 버튼
        Mypage_button = new JButton("마이페이지");
        Mypage_button.setBounds(850, 500, 100, 50);
        mainframe.add(Mypage_button);
        Mypage_button.addActionListener(this);
        if(state=="admin"){
            //Mypage_button.setEnabled(false);
            Mypage_button.setText("관리자화면");
        }
        
        
        //로그아웃 버튼
        Logout_button = new JButton("로그아웃");
        Logout_button.setBounds(730, 500, 100, 50);
        mainframe.add(Logout_button);
        Logout_button.addActionListener(this);


        //판매 물품 화면을 띄울 공간
        panel_list = new ArrayList<JPanel>();

        for(int i = 0; i < sell_item_list.getSize(); i++) {
            panel_list.add(createItemPanel(sell_item_list.getItemProduct(i), i));
        }
        
        //페이지 번호 공간
        if(total_page == 0) total_page = 1;
        page_number_panel = new JPanel(new BorderLayout());
        page_number_label = new JLabel(page_number + " / " + total_page);
        page_number_panel.add(page_number_label, BorderLayout.CENTER);

        //다음 페이지 버튼
        next_page_button = new JButton("다음");
        next_page_button.addActionListener(this);
        next_page_button.setEnabled(false);
        page_number_panel.add(next_page_button, BorderLayout.EAST);

        //이전 페이지 버튼
        prev_page_button = new JButton("이전");
        prev_page_button.addActionListener(this);
        page_number_panel.add(prev_page_button, BorderLayout.WEST);
        prev_page_button.setEnabled(false);
        calcTotalPage();

        //페이지의 개수가 1개가 넘지 않는다면 다음 페이지 버튼 작동X
        if(total_page == 1){
            next_page_button.setEnabled(false);
        }
        mainframe.add(page_number_panel);
        page_number_panel.setBounds(400, 640, 155, 20);
        
        //정렬 방법 선택을 위한 드롭다운리스트
        String sortList[] = {"이름순", "이름역순", "가격순", "가격역순"};
        sortComboBox = new JComboBox<String>(sortList);
        sortComboBox.addActionListener(this);
        mainframe.add(sortComboBox);
        sortComboBox.setBounds(800,20,100,20);


        //화면에 물품을 로드 한다.
        loadUI(panel_list, page_number);
        reloadUI();
        
        //메인프레임 중앙 및 보이기 설정
        mainframe.setResizable(false);
        mainframe.setVisible(true);
    }

    /**처음 화면으로 초기화한다.*/
    public void resetFrame() {
        clearFrame();
        page_number = 1;
        checkPage(page_number, total_page);
        loadUI(panel_list, page_number);
        updatePageNumber();
        reloadUI();
    }

    /**인덱스 위치의 패널을 제거한다.*/
    public void deletePanel(){
        clearFrame();
        resetAndAddPanels();
    }

    /**패널 리스트를 다시 생성한다*/
    public void resetAndAddPanels(){
        //System.out.println("패널 리스트를 삭제 후 다시 추가 중 입니다." + sell_item_list.getSize());
        panel_list.clear();
        for(int i = 0; i < sell_item_list.getSize(); i++) {
            panel_list.add(createItemPanel(sell_item_list.getItemProduct(i), i));
        }
        //System.out.println("패널 리스트 개수 : " + panel_list.size());
    }

    /**메인프레임 화면에 있는 패널들을 모두 제거한다.*/
    public void clearFrame(){
        if(panel_list.size() >= 1) {
            int i = 1;
            int range = page_number * 8;//범위 = 페이지 번호 * 페이지에 출력되는 물품 수
            for (JPanel jp : panel_list) {//개수만큼 반복한다
                if ((range - 8) < i && i <= (range)) {
                    mainframe.remove(panel_list.get(i - 1));
                }
                i++;
            }
        }
        if(search_item_list.size() >= 1){
            int i = 1;
            int range = search_page_number * 8;
            for(JPanel jp : search_item_list) {
                if ((range - 8) < i && i <= (range)) {
                    mainframe.remove(search_item_list.get(i - 1));
                }
                i++;
            }
        }
    }

    /**화면 리로드*/
    public void reloadUI(){
       // System.out.println("화면을 리로드합니다." + " 현재 등록된 물품 개수 : " + panel_list.size());
        mainframe.validate();
        mainframe.repaint();
    }


    /**panel_list 에 물품 패널을 추가한다*/
    public void addPanel(){
        int i = sell_item_list.getSize() - 1;
        //패널로 만들 물품을 보낸 후 패널을 반환 받는다
        panel_list.add(createItemPanel(sell_item_list.getItemProduct(i), i));
    }

    /**매개변수로 전달받은 ItemProduct 와 Singleton 에서의 index 를 전달받아 물품 패널로 만들어 반환한다.*/
    public JPanel createItemPanel(ItemProduct item, int index){
        //이미지 라벨 생성
        JLabel image_label = new JLabel();
        try {
            BufferedImage image = ImageIO.read(item.getImageFile());
            // 이미지의 크기를 150x150으로 변경
            Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImage);
            image_label.setIcon(icon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //제목 라벨 생성
        JLabel title_label = new JLabel(item.getTitle());
        //패널 생성
        JPanel item_panel = new JPanel(new BorderLayout());
        item_panel.add(image_label, BorderLayout.CENTER);
        item_panel.add(title_label, BorderLayout.SOUTH);
        item_panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PopupDialog(MainUI.this, index);
            }
        });

        return item_panel;
    }

    /**메인화면에 등록된 물품들을 로드한다*/
    public void loadUI(List<JPanel> list, int page){
        clearFrame();
        int x = 100;
        int y = 100;
        int i = 1;
        int range = page * 8;//범위 = 페이지 번호 * 페이지에 출력되는 물품 수
        for(JPanel jp : list){
            if((range - 8) < i && i <= (range)) {
                mainframe.add(jp);
                jp.setBounds(x, y, 200, 200);
                if (i % 4 == 0) {
                    x = 100;
                    y += 200;
                } else if (i >= range) {
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
            //물품 생성버튼
            new SellerProductUploadUI(this);
        }
        else if(e.getSource()==Logout_button) {
            mainframe.dispose();
            new LoginFrame();
        }else if (e.getSource() == orderHistory_button) {
            //출력버튼
            new OrderHistoryUI(mainframe, getId());
        }
        else if(e.getSource() == Mypage_button) {
            if(state=="plane"){
                JOptionPane.showMessageDialog(null,"회원가입이 필요합니다");
                return;
            }
            else if(state.equals("admin")){
                new AdminUI(this);
            }
            else {
                //마이페이지 버튼
                new MypageFrame(id, password, state);
            }
        }
        else if(e.getSource() == next_page_button){
            //다음 페이지 버튼
            clearFrame();
            if(search_mode == false) {
                page_number++;
                updatePageNumber();
                checkPage(page_number, total_page);
                loadUI(panel_list, page_number);
            }
            else{
                search_page_number++;
                updateSearchPageNumber();
                checkPage(search_page_number, search_total_page);
                loadUI(search_item_list, search_page_number);
            }
            reloadUI();
        }
        else if(e.getSource() == prev_page_button){
            //이전 페이지 버튼
            clearFrame();
            if(search_mode == false) {
                page_number--;
                updatePageNumber();
                checkPage(page_number, total_page);
                loadUI(panel_list, page_number);
            }
            else{
                search_page_number--;
                updateSearchPageNumber();
                checkPage(search_page_number, search_total_page);
                loadUI(search_item_list, search_page_number);
            }
            reloadUI();
        }
        else if (e.getSource() == search_button){
            //검색버튼
            clearFrame();
            searchItem(search_field.getText());
            search_mode = true;
            if(search_item_list.size() != 0){
               // System.out.println("검색된 물품 개수 : " + search_page_number);
                loadUI(search_item_list, search_page_number);
                updateSearchPageNumber();
                checkPage(search_page_number, search_total_page);
                reloadUI();
            }
            else{
                search_mode = false;
                JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "검색 실패", JOptionPane.WARNING_MESSAGE);
            }
        }
        else if(e.getSource() == notice_button){
            //알림버튼
            new NoticeUI(this, notice, id);
        }
        else if(e.getSource() == sortComboBox){
            AbstractItemSorter itemSorter;
            int index = sortComboBox.getSelectedIndex();
            switch (index){
                case 0:{
                    itemSorter = new TitleSorter(this);
                    itemSorter.sort(Singleton.getSell_item_list());
                    break;
                }
                case 1:{
                    itemSorter = new TitleReversedSorter(this);
                    itemSorter.sort(Singleton.getSell_item_list());
                    break;
                }
                case 2:{
                    itemSorter = new PriceSorter(this);
                    itemSorter.sort(Singleton.getSell_item_list());
                    break;
                }
                case 3:{
                    itemSorter = new PriceReversedSorter(this);
                    itemSorter.sort(Singleton.getSell_item_list());
                    break;
                }
                default:{
                    System.out.println("에러");
                    break;
                }
            }
        }
    }

    /**검색어에 맞는 물품을 검색한다.*/
    private void searchItem(String text) {
        //만약 검색한 물품 리스트에 내용 존재시 리스트를 비운다.
        if(search_item_list.size() != 0){
            clearFrame();
            search_item_list.clear();
            //System.out.println("검색 리스트 초기화");
        }

        //현재 등록된 물품을 순회하며
        for(int i = 0; i < sell_item_list.getSize(); i++){
            //검색어와 같은 물품을 찾는다
            if(text.equals(sell_item_list.getItemProduct(i).getTitle())){
                search_item_list.add(createItemPanel(sell_item_list.getItemProduct(i), i));
            }
        }
        search_page_number = 1;
        search_total_page = search_item_list.size() / 8;
        if(search_item_list.size() == 0 || (search_item_list.size() % 8 != 0)){
            search_total_page++;
        }
    }

    /**상품 리스트에 새로운 상품을 추가하는 메서드*/
    public void add_item_list(ItemProduct item){
        //싱글톤 객체에 아이템을 추가하는 코드
        sell_item_list.addItem(item);
        addPanel();
    }

    /**토탈 페이지 수를 계산*/
    public void calcTotalPage(){
        total_page = sell_item_list.getSize() / 8;
        if(total_page == 0 || (sell_item_list.getSize() % 8 != 0)) total_page++;
        if(total_page >= 2) next_page_button.setEnabled(true);
        updatePageNumber();
    }

    /**페이지 번호 업데이트*/
    public void updatePageNumber(){
        if(page_number_label != null) {
            page_number_label.setText(page_number + " / " + total_page);
        }
    }

    /**검색후 페이지 번호로 업데이트한다*/
    public void updateSearchPageNumber(){
        page_number_label.setText(search_page_number + " / " + search_total_page);
    }

    /**페이지 번호를 검사 후 페이지 버튼의 활성화 여부를 결정*/
    public void checkPage(int page, int total){
        if(page == total && page != 1){//최대 페이지라면
            prev_page_button.setEnabled(true);
            next_page_button.setEnabled(false);
        }
        else if(page == 1 && total != 1){//최소 페이지이며 최대 페이지가 1이 아니라면
            prev_page_button.setEnabled(false);
            next_page_button.setEnabled(true);
        }
        else if(page == 1 && total == 1){//최소 페이지이며 최대 페이지가 1이라면
            prev_page_button.setEnabled(false);
            next_page_button.setEnabled(false);
        }
        else{//중간 페이지라면
            prev_page_button.setEnabled(true);
            next_page_button.setEnabled(true);
        }
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTurnedOff() {
        return isTurnedOff;
    }

    public void setTurnedOff(boolean turnedOff) {
        isTurnedOff = turnedOff;
    }
}
