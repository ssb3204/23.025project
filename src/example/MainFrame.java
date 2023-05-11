package example;

import javax.swing.*;

public class MainFrame extends JFrame {
    public static String id;
    public static String password;

    public static String name;
    public static String email;
    public String state;

    MainFrame(String id, String password){
        this.id = id;
        this.password = password;

        setTitle("Main");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
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
            //마이페이지 버튼 누르면 마이페이지 창 띄우기
            new MypageFrame(id,password,state);
        });

        logoutButton.addActionListener(e -> {
            //로그아웃 버튼 누르면 로그인 창 띄우기
            new LoginFrame();
            dispose();
        });
    }
}
