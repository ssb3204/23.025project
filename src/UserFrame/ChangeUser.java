package UserFrame;

import UI.MainUI;

import javax.swing.*;

public class ChangeUser extends JFrame {
    String id;
    String password;
    String name;
    String address;

    String state;


    //아이디 비밀번호 변경 버튼 누르면 아이디 비밀번호 변경할 수 있는 창 띄우기
    //아이디 비밀번호 변경 후 확인 버튼 누르면
    //아이디 비밀번호 변경

    ChangeUser(String id, String password,String state){
        this.id = id;
        this.password = password;
        this.state=state;


        setTitle("Change User");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel changeLabel = new JLabel("Change your ID and password");
        changeLabel.setBounds(10,160,280,25);
        add(changeLabel);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10,10,100,25);
        add(idLabel);

        JLabel idText = new JLabel(this.id);
        idText.setBounds(100,10,80,25);
        add(idText);

        JLabel passwordLabel = new JLabel("PW");
        passwordLabel.setBounds(10,40,100,25);
        add(passwordLabel);

        JTextField passwordText = new JTextField(30);
        passwordText.setBounds(100,40,80,25);
        add(passwordText);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,70,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField(30);
        nameText.setBounds(100,70,80,25);
        add(nameText);

        JLabel AddressLabel = new JLabel("Address");
        AddressLabel.setBounds(10,100,80,25);
        add(AddressLabel);

        JTextField addressText = new JTextField(30);
        addressText.setBounds(100,100,80,25);
        add( addressText);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,180,80,25);
        add(changeButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180,180,80,25);
        add(cancelButton);

        setVisible(true);

        changeButton.addActionListener(e -> {

            String pass = passwordText.getText();
            String name = nameText.getText();
            String  address =  addressText.getText();

            if(pass.equals("")||name.equals("")||address.equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
                return;
            }else if(pass.equals(this.password)){
                JOptionPane.showMessageDialog(null,"Please enter a different password with the current password");
                passwordText.setText("");
                return;
            }
            else if(pass.contains(" ")){
                JOptionPane.showMessageDialog(null,"Please don't enter space in password");
                passwordText.setText("");
                return;
            }else if(name.contains(" ")){
                JOptionPane.showMessageDialog(null,"Please don't enter space in name");

                nameText.setText("");
                return;
            }else if(address.contains(" ")){
                JOptionPane.showMessageDialog(null,"Please don't enter space in address");
                addressText.setText("");
                return;
            }
            else if( pass.length()<4||pass.length()>10){
                JOptionPane.showMessageDialog(null,"Password must be at least 4 characters or less than 10 characters");
                passwordText.setText("");
                nameText.setText("");
                addressText.setText("");
                return;
            }
            Userinfocheck userinfocheck = new Userinfocheck(this.id,pass,name,address);
            userinfocheck.saveUserinfo();
            JOptionPane.showMessageDialog(null,"Change complete");
            new MainUI(this.id,pass,this.state);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            //new MainUI(this.id,this.password,this.state);
            //취소 버튼 누르면 마이페이지 프레임 출력
            dispose();
        });
    }

}
