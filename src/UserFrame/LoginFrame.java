package UserFrame;

import UI.MainUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame{
    JTextField idText;
    JPasswordField passwordText;

    JButton loginButton;
    JButton registerButton;
    JButton exitButton;
    JButton resetButton;



    public LoginFrame(){
        setTitle("Login");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(300,100,80,25);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                /**DB 에 sell_item_list 객체들을 저장하는 코드*/
                System.exit(0);
            }
        });
        add(idLabel);

        idText = new JTextField(10);
        idText.setBounds(330,100,160,25);
        add(idText);

        JLabel passwordLabel = new JLabel("PW");
        passwordLabel.setBounds(300,140,80,25);
        add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(330,140,160,25);
        add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(300,180,80,25);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(400,180,80,25);
        add(registerButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(400,220,80,25);
        add(exitButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(300,220,80,25);
        add(resetButton);

        JButton PlaneButton = new JButton("Plane");
        PlaneButton.setBounds(300,260,80,25);
        add(PlaneButton);

        setVisible(true);

        loginButton.addActionListener(e -> {
            String id = idText.getText();
            String password = passwordText.getText();

            if(id.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(null,"ID or Password is empty");
                idText.setText("");
                passwordText.setText("");
                return;
            }
            if(id.length() > 10 || password.length() > 10){
                JOptionPane.showMessageDialog(null,"ID or Password is too long");
                idText.setText("");
                passwordText.setText("");
                return;
            }
            if(id.contains(" ") || password.contains(" ")){
                JOptionPane.showMessageDialog(null,"ID or Password cannot contain space");
                idText.setText("");
                passwordText.setText("");
                return;
            }

            Userinfocheck userinfocheck = new Userinfocheck(id,password);
            if(userinfocheck.checkUserinfo()&&idText.getText().equals("admin")){
                JOptionPane.showMessageDialog(null,"Admin Login Success");
                //로그인 성공하면 메인창 띄우기
                new MainUI(id,password,"admin");
                //메인창 띄우고 현재창 닫기
                dispose();
            }
            //사용자 로그인
            else if(userinfocheck.checkUserinfo()){

                JOptionPane.showMessageDialog(null, "User Login Success");
                //로그인 성공하면 메인창 띄우기
                new MainUI(id, password, "user");
                //메인창 띄우고 현재창 닫기
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Login Failed");
                idText.setText("");
                passwordText.setText("");
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

        PlaneButton.addActionListener(e -> {
            new MainUI(null,null,"plane");
            dispose();
        });
    }
}
