package example;

import Facade_Pattern_Class.DatabaseFacade;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

public class DeleteUse extends JFrame{
    //회원탈퇴 버튼을 누르면 회원탈퇴하시겠습니까 문구 출력후 확인 버튼 누르면
    //로그인 되어있는 회원 아이디 비밀번호 삭제
    //삭제 후 로그인 창 띄우기
    DeleteUse(){
        setTitle("Delete User");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel deleteLabel = new JLabel("Are you sure you want to delete your account?");
        deleteLabel.setBounds(10,10,280,25);
        add(deleteLabel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(10,50,80,25);
        add(deleteButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(10,80,80,25);
        add(cancelButton);

        setVisible(true);

        deleteButton.addActionListener(e -> {
            //Userinfocheck userinfocheck = new Userinfocheck(MypageFrame.id,MypageFrame.password);
            //userinfocheck.deleteUserinfo();
            DeleteUser deleteUse = new DeleteUser(new DatabaseFacade());
            deleteUse.deleteStart(MypageFrame.id);
        });

        cancelButton.addActionListener(e -> {
            //마이페이지 프레임 출력
            dispose();
        });
    }
}
