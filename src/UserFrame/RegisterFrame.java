package UserFrame;

import javax.swing.*;

public class RegisterFrame extends JFrame {
    //회원가입 창
    //Register 버튼을 누르면 json 파일에 회원정보 저장하고
    //회원가입 창 닫기
    RegisterFrame(){
        setTitle("회원가입");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel idLabel = new JLabel("아이디(4-10자)");
        idLabel.setBounds(10,10,90,25);
        add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(150,10,160,25);
        add(idText);

        JLabel passwordLabel = new JLabel("비밀번호(4-10자)");
        passwordLabel.setBounds(10,40,100,25);
        add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150,40,160,25);
        add(passwordText);

        //이름과 이메일 입력란 추가
        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(10,70,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(150,70,160,25);
        add(nameText);

        JLabel addressLabel = new JLabel("주소");
        addressLabel.setBounds(10,100,80,25);
        add(addressLabel);

        JTextField addressText = new JTextField(20);
        addressText.setBounds(150,100,160,25);
        add(addressText);

        JButton registerButton = new JButton("회원가입");
        registerButton.setBounds(10,180,85,25);
        add(registerButton);

        JButton exitButton = new JButton("나가기");
        exitButton.setBounds(180,180,85,25);
        add(exitButton);

        JButton resetButton = new JButton("초기화");
        resetButton.setBounds(10,220,85,25);
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
            if(userinfocheck.userInfoExist()){
                JOptionPane.showMessageDialog(null,"존재하는 아이디 입니다");
            }else if(id.equals("")||password.equals("")||name.equals("")||address.equals("")){
                JOptionPane.showMessageDialog(null,"공백이 있습니다");
            }else if(id.length()<4||id.length()>10||password.length()<4||password.length()>10) {
                JOptionPane.showMessageDialog(null, "아이디와 비밀번호는 4-10자 사이입니다");
            }else if(id.contains(" ")||password.contains(" ")||name.contains(" ")||address.contains(" ")){
                JOptionPane.showMessageDialog(null,"띄어쓰기가 포함되어 있습니다");
            } else if (id.contains("!") || id.contains("@") || id.contains("#") || id.contains("$")||id.contains("%") || id.contains("^") || id.contains("&") || id.contains("*")||id.contains("(") || id.contains(")") || id.contains("_") || id.contains("-")||id.contains("+")|id.contains("=")) {
                JOptionPane.showMessageDialog(null, "아이디에 특수문자 사용불가 입니다");
            }else if(id.contains("{")||id.contains("}")||id.contains("[")||id.contains("]")||id.contains(":")||id.contains(";")||id.contains("'")||id.contains("\"")||id.contains("<")||id.contains(">")||id.contains(",")||id.contains(".")||id.contains("?")||id.contains("/")){
                JOptionPane.showMessageDialog(null, "아이디에 특수문자 사용불가 입니다");
            }
            else {
                JOptionPane.showMessageDialog(null, "회워가입 성공!");
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
