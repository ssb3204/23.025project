package UserFrame;

import DatabaseConnect.DatabaseConect;
import UI.MainUI;

import javax.swing.*;

public class DeleteUse extends JFrame{
    //회원탈퇴 버튼을 누르면 회원탈퇴하시겠습니까 문구 출력후 확인 버튼 누르면
    //로그인 되어있는 회원 아이디 비밀번호 삭제
    //삭제 후 로그인 창 띄우기
    String id;
    String password;
    String state;
    DeleteUse(String id, String password,String state){
        this.id = id;
        this.password = password;
        this.state=state;


        setTitle("회원탈퇴");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel deleteLabel = new JLabel("정말로 계정을 삭제하시겠습니까?");
        deleteLabel.setBounds(10,10,280,25);
        add(deleteLabel);

        JButton deleteButton = new JButton("회원탈퇴");
        deleteButton.setBounds(10,50,85,25);
        add(deleteButton);

        JButton cancelButton = new JButton("나가기");
        cancelButton.setBounds(10,80,85,25);
        add(cancelButton);

        setVisible(true);

        deleteButton.addActionListener(e -> {

            DeleteUser deleteUse = new DeleteUser(new DatabaseConect());
            deleteUse.deleteStart(MypageFrame.id);
            new LoginFrame();
            dispose();
        });

        cancelButton.addActionListener(e -> {
            //마이페이지 프레임 출력
            new MainUI(this.id,this.password,this.state);
            dispose();
        });
    }
}
