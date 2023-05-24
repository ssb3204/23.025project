package UserFrame;

import UI.MainUI;

import javax.swing.*;

public class MypageFrame extends JFrame {
    public static String id;
    public static String password;
    public static String state;

    //마이페이지 UI
    public MypageFrame(String id, String password,String state){
        this.id = id;
        this.password = password;
        this.state=state;


        setTitle("Mypage");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,80,80,25);
        add(changeButton);

        JLabel changeLabel = new JLabel("Change your Imformation");
        changeLabel.setBounds(100,80,280,25);
        add(changeLabel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(10,40,80,25);
        add(deleteButton);

        JLabel deleteLabel = new JLabel("Delete your account");
        deleteLabel.setBounds(100,40,280,25);
        add(deleteLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10,120,80,25);
        add(backButton);

        setVisible(true);

        changeButton.addActionListener(e -> {
            //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
            new ChangeUser(id,password,state);
            dispose();
        });

        deleteButton.addActionListener(e -> {
            //회원탈퇴 버튼 누르면 DeleteUserFrame 띄우기
            new DeleteUse();
            dispose();
        });

        backButton.addActionListener(e -> {
            //뒤로가기 버튼 누르면 메인 프레임 출력
            new MainUI(id, password, state);
            dispose();
        });
    }
}
