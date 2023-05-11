package example;

import UI.MainUI;

import javax.swing.*;

public class ChangeUser extends JFrame {
    String id;
    String password;
    String name;
    String email;

    String state;

    ChangeUser(String id, String password, String name, String email){
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    //아이디 비밀번호 변경 버튼 누르면 아이디 비밀번호 변경할 수 있는 창 띄우기
    //아이디 비밀번호 변경 후 확인 버튼 누르면
    //아이디 비밀번호 변경

    ChangeUser(String id, String password,String state){
        this.id = id;
        this.password = password;
        this.state=state;


        setTitle("Change User");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel changeLabel = new JLabel("Change your ID and password");
        changeLabel.setBounds(100,10,280,25);
        add(changeLabel);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(100,40,80,25);
        add(idLabel);

        JLabel idText = new JLabel(this.id);
        idText.setBounds(150,40,165,25);
        add(idText);

        JLabel passwordLabel = new JLabel("PW");
        passwordLabel.setBounds(100,70,80,25);
        add(passwordLabel);

        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(150,70,165,25);
        add(passwordText);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(100,100,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150,100,165,25);
        add(nameText);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(100,130,80,25);
        add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(150,130,165,25);
        add(emailText);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,100,80,25);
        add(changeButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180,100,80,25);
        add(cancelButton);

        setVisible(true);

        changeButton.addActionListener(e -> {

            String pass = passwordText.getText();
            String name = nameText.getText();
            String email = emailText.getText();

            Userinfocheck userinfocheck = new Userinfocheck(this.id,pass,name,email);
            userinfocheck.saveUserinfo();
            dispose();
        });

        cancelButton.addActionListener(e -> {
            new MainUI(this.id,this.password,this.state);
            //취소 버튼 누르면 마이페이지 프레임 출력
            dispose();
        });
    }

}
