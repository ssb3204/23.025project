package UserFrame;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    //회원가입 창
    //Register 버튼을 누르면 json 파일에 회원정보 저장하고
    //회원가입 창 닫기

    RegisterFrame(){
        setTitle("Register");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(10,10,80,25);
        add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(100,10,160,25);
        add(idText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,40,80,25);
        add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,40,160,25);
        add(passwordText);

        //이름과 이메일 입력란 추가
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,70,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100,70,160,25);
        add(nameText);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(10,100,80,25);
        add(addressLabel);

        JTextField addressText = new JTextField(20);
        addressText.setBounds(100,100,160,25);
        add(addressText);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10,180,80,25);
        add(registerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(180,180,80,25);
        add(exitButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(10,220,80,25);
        add(resetButton);

        setVisible(true);

        registerButton.addActionListener(e -> {
            //회원가입 버튼 누르면 파일에 회원정보 저장하고
            //회원가입 창 닫기
            String id = idText.getText();
            String password = passwordText.getText();
            String name = nameText.getText();
            String address = addressText.getText();
            //회원정보 저장
            Userinfocheck userinfocheck = new Userinfocheck(id,password,name,address);
            //입력한 정보와 기존 파일에 저장되어있는 정보 비교
            //비교해서 같은 정보가 있으면 회원가입 실패
            //없으면 회원가입 성공
            if(userinfocheck.Userinfoexist()){
                JOptionPane.showMessageDialog(null,"Register Failed,your imformation is already exist");
            }else if(id.equals("")||password.equals("")||name.equals("")||address.equals("")){
                JOptionPane.showMessageDialog(null,"Register Failed,please fill in the blank");
            }
            else if(id.contains(" ")||password.contains(" ")||name.contains(" ")||address.contains(" ")){
                JOptionPane.showMessageDialog(null,"Register Failed,please don't use space");
            } else if (id.contains("!") || id.contains("@") || id.contains("#") || id.contains("$")||id.contains("%") || id.contains("^") || id.contains("&") || id.contains("*")||id.contains("(") || id.contains(")") || id.contains("_") || id.contains("-")||id.contains("+")|id.contains("=")) {
                JOptionPane.showMessageDialog(null, "Register Failed,please don't use special character");
            }else if(id.contains("{")||id.contains("}")||id.contains("[")||id.contains("]")||id.contains(":")||id.contains(";")||id.contains("'")||id.contains("\"")||id.contains("<")||id.contains(">")||id.contains(",")||id.contains(".")||id.contains("?")||id.contains("/")){
                JOptionPane.showMessageDialog(null, "Register Failed,please don't use special character");
            }
            else {
                JOptionPane.showMessageDialog(null, "Register Success");
                //성공하면 입력한 정보 파일에 저장
                userinfocheck.addUserinfo();
                //로그인 창 띄우기
                new LoginFrame();
                dispose();
            }
        });

        exitButton.addActionListener(e -> {
            new LoginFrame();
            dispose();
        });

        resetButton.addActionListener(e -> {
            idText.setText("");
            passwordText.setText("");
            nameText.setText("");
            addressText.setText("");
        });
    }
}
