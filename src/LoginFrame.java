import javax.swing.*;

public class LoginFrame extends JFrame{
    LoginFrame(){
        setTitle("Login");
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10,80,80,25);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180,80,80,25);
        add(registerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(10,120,80,25);
        add(exitButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(180,120,80,25);
        add(resetButton);

        setVisible(true);

        loginButton.addActionListener(e -> {
            String id = idText.getText();
            String password = passwordText.getText();
            Userinfocheck userinfocheck = new Userinfocheck(id,password);
            if(userinfocheck.checkUserinfo()){
                JOptionPane.showMessageDialog(null,"Login Success");

                //로그인 성곻하면 아이디와 비밀번호를 다른 프레임에서도 사용할수 있게 해주는 코드
                MainFrame mainFrame = new MainFrame();
                mainFrame.id = idText.getText();
                mainFrame.password = passwordText.getText();
                mainFrame.setVisible(true);

                //메인창 띄우고 현재창 닫기
                dispose();

            }
            else{
                JOptionPane.showMessageDialog(null,"Login Failed");
            }
        });

        registerButton.addActionListener(e -> {
            new RegisterFrame();
            dispose();
        });


        //reset 버튼 누르면 입력한 정보 초기화
        resetButton.addActionListener(e -> {
            idText.setText("");
            passwordText.setText("");
        });

        //exit 버튼 누르면 모두 종료
        exitButton.addActionListener(e -> {
            System.exit(0);
        });

    }
}
