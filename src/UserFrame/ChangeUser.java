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


        setTitle("수정");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel changeLabel = new JLabel("정보를 수정하세요");
        changeLabel.setBounds(10,160,280,25);
        add(changeLabel);

        JLabel idLabel = new JLabel("아이디");
        idLabel.setBounds(10,10,100,25);
        add(idLabel);

        JLabel idText = new JLabel(this.id);
        idText.setBounds(100,10,80,25);
        add(idText);

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(10,40,100,25);
        add(passwordLabel);

        JTextField passwordText = new JTextField(30);
        passwordText.setBounds(100,40,80,25);
        add(passwordText);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(10,70,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField(30);
        nameText.setBounds(100,70,80,25);
        add(nameText);

        JLabel AddressLabel = new JLabel("주소");
        AddressLabel.setBounds(10,100,80,25);
        add(AddressLabel);

        JTextField addressText = new JTextField(30);
        addressText.setBounds(100,100,80,25);
        add( addressText);

        JButton changeButton = new JButton("수정하기");
        changeButton.setBounds(10,180,85,25);
        add(changeButton);

        JButton cancelButton = new JButton("나가기");
        cancelButton.setBounds(180,180,85,25);
        add(cancelButton);

        setVisible(true);

        changeButton.addActionListener(e -> {

            String pass = passwordText.getText();
            String name = nameText.getText();
            String  address =  addressText.getText();

            if(pass.equals("")||name.equals("")||address.equals("")) {
                JOptionPane.showMessageDialog(null, "빈칸을 채워주세요");
                return;
            }else if(pass.equals(this.password)){
                JOptionPane.showMessageDialog(null,"이전 비밀번호와 동일합니다. 다른 비밀번호를 입력해주세요");
                passwordText.setText("");
                return;
            }
            else if(pass.contains(" ")){
                JOptionPane.showMessageDialog(null,"비밀번호에 띄어쓰기가 포함되어 있습니다");
                passwordText.setText("");
                return;
            }else if(name.contains(" ")){
                JOptionPane.showMessageDialog(null,"이름에 띄어쓰기가 포함되어 있습니다");

                nameText.setText("");
                return;
            }else if(address.contains(" ")){
                JOptionPane.showMessageDialog(null,"주소에 띄어쓰기가 포함되어 있습니다");
                addressText.setText("");
                return;
            }
            else if( pass.length()<4||pass.length()>10){
                JOptionPane.showMessageDialog(null,"비밀번호는 4-10자 사이로 입력해주세요");
                passwordText.setText("");
                nameText.setText("");
                addressText.setText("");
                return;
            }
            Userinfocheck userinfocheck = new Userinfocheck(this.id,pass,name,address);
            userinfocheck.saveUserinfo();
            JOptionPane.showMessageDialog(null,"수정 완료");
            new MainUI(this.id,pass,this.state);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            new MainUI(this.id,this.password,this.state);
            //취소 버튼 누르면 마이페이지 프레임 출력
            dispose();
        });
    }

}
