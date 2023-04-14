import javax.swing.*;

public class MainFrame extends JFrame {
    public String id;
    public String password;

    //로그인 프레임에서 입력된 아이디와 비밀번호를 받아옴
    MainFrame(String id, String password){
        this.id = id;
        this.password = password;
    }

    //로그인 성공시 메인창 출력
    //메인 창에는 검색 ,마이페이지, 로그아웃 버튼
    //검색 버튼 누르면 검색창 띄우기
    //마이페이지 버튼 누르면 마이페이지 창 띄우기
    //로그아웃 버튼 누르면 로그인 창 띄우기
    MainFrame(){
        setTitle("Main");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(10,10,80,25);
        add(searchButton);

        JButton mypageButton = new JButton("Mypage");
        mypageButton.setBounds(180,10,80,25);
        add(mypageButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(10,50,80,25);
        add(logoutButton);

        setVisible(true);

        searchButton.addActionListener(e -> {
            //검색 버튼 누르면 검색창 띄우기
            new SearchFrame();
        });

        mypageButton.addActionListener(e -> {
            //아이디와 비밀번호를 마이페이지 프레임에 넘겨주기
            MypageFrame mypageFrame = new MypageFrame();
            mypageFrame.id = id;
            mypageFrame.password = password;
            mypageFrame.setVisible(true);

        });

        logoutButton.addActionListener(e -> {
            //로그아웃 버튼 누르면 로그인 창 띄우기
            new LoginFrame();
            dispose();
        });
    }
}
