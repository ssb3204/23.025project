import javax.swing.*;

public class MypageFrame extends JFrame {
    public String id;
    public String password;

    //로그인 프레임에서 입력된 아이디와 비밀번호를 받아옴
    MypageFrame(String id, String password){
        this.id = id;
        this.password = password;
    }

    //마이페이지에는 현재 자신의 아이디 비밀번호를 보여주고
    //아이디 비밀번호 변경 버튼과 회원탈퇴 버튼
    //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
    //회원탈퇴 버튼 누르면 DeleteUserFrame 띄우기
    MypageFrame(){
        setTitle("Mypage");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,80,80,25);
        add(changeButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(100,80,80,25);
        add(deleteButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(190,80,80,25);
        add(backButton);

        setVisible(true);

        changeButton.addActionListener(e -> {
            //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
            new ChangeUserFrame();
            dispose();
        });

        deleteButton.addActionListener(e -> {
            //회원탈퇴 버튼 누르면 DeleteUserFrame 띄우기
            new DeleteUse();
            dispose();
        });

        backButton.addActionListener(e -> {

            dispose();
        });
    }

}
