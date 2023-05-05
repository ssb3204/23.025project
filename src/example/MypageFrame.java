package example;

import javax.swing.*;

public class MypageFrame extends JFrame {
    public static String id;
    public static String password;
    public static String name;
    public static String email;

    public MypageFrame(String id, String password){
        this.id = id;
        this.password = password;

        setTitle("Mypage");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,80,80,25);
        add(changeButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(180,80,80,25);
        add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10,120,80,25);
        add(backButton);



        setVisible(true);

        changeButton.addActionListener(e -> {
            //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
            new ChangeUser(id,password);
        });

        deleteButton.addActionListener(e -> {
            //회원탈퇴 버튼 누르면 DeleteUserFrame 띄우기
            new DeleteUse();
        });

        backButton.addActionListener(e -> {
            //뒤로가기 버튼 누르면 메인 프레임 출력
            dispose();
        });
    }
    //마이페이지에는 현재 자신의 아이디 비밀번호를 보여주고
    //아이디 비밀번호 변경 버튼과 회원탈퇴 버튼
    //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
    //회원탈퇴 버튼 누르면 DeleteUserFrame 띄우기
    MypageFrame(){
        setTitle("Mypage");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,80,80,25);
        add(changeButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(180,80,80,25);
        add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10,120,80,25);
        add(backButton);



        setVisible(true);

        changeButton.addActionListener(e -> {
            //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
            new ChangeUser(id,password);
        });

        deleteButton.addActionListener(e -> {
            //회원탈퇴 버튼 누르면 DeleteUserFrame 띄우기
            new DeleteUse();
        });

        backButton.addActionListener(e -> {
            //뒤로가기 버튼 누르면 메인 프레임 출력
            dispose();
        });
    }

}
