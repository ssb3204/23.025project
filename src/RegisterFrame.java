import javax.swing.*;

public class RegisterFrame extends JFrame {
    //회원가입 창
    //Register 버튼을 누르면 json 파일에 회원정보 저장하고
    //회원가입 창 닫기

    RegisterFrame(){
        setTitle("Register");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

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

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10,80,80,25);
        add(registerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(180,80,80,25);
        add(exitButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(10,120,80,25);
        add(resetButton);

        setVisible(true);

        registerButton.addActionListener(e -> {
            //회원가입 버튼 누르면 파일에 회원정보 저장하고
            //회원가입 창 닫기
            String id = idText.getText();
            String password = passwordText.getText();

            //회원정보 저장
            Userinfocheck userinfocheck = new Userinfocheck(id,password);
            //입력한 정보와 기존 파일에 저장되어있는 정보 비교
            //비교해서 같은 정보가 있으면 회원가입 실패
            //없으면 회원가입 성공
            if(userinfocheck.checkUserinfo()){
                JOptionPane.showMessageDialog(null,"Register Failed");
            }
            else{
                JOptionPane.showMessageDialog(null,"Register Success");
                //성공하면 입력한 정보 파일에 저장
                userinfocheck.saveUserinfo();
                User user = new User(id,password);
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
        });
    }
}
