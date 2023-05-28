package UserFrame;

import UI.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends JFrame{
    JTextField idText;
    JPasswordField passwordText;

    JButton loginButton;
    JButton registerButton;
    JButton exitButton;
    JButton resetButton;

    
    //로그인 화면
    public LoginFrame(){
        setTitle("로그인");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLocationRelativeTo(null);
        JLabel idLabel = new JLabel("아이디");
        idLabel.setBounds(280,100,80,25);
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

        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setBounds(280,140,80,25);
        add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(330,140,160,25);
        add(passwordText);

        loginButton = new JButton("로그인");
        loginButton.setBounds(300,180,85,25);
        add(loginButton);

        registerButton = new JButton("회원가입");
        registerButton.setBounds(400,180,85,25);
        add(registerButton);

        exitButton = new JButton("나가기");
        exitButton.setBounds(400,220,85,25);
        add(exitButton);

        resetButton = new JButton("초기화");
        resetButton.setBounds(300,220,85,25);
        add(resetButton);

        JButton PlaneButton = new JButton("비회원");
        PlaneButton.setBounds(300,260,85,25);
        add(PlaneButton);

        setVisible(true);

        loginButton.addActionListener(e -> {
            String id = idText.getText();
            String password = passwordText.getText();

            if(id.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호가 입력되지 않았습니다.");
                idText.setText("");
                passwordText.setText("");
                return;
            }
            if(id.length() > 10 || password.length() > 10){
                JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호가 10자를 초과합니다.");
                idText.setText("");
                passwordText.setText("");
                return;
            }
            if(id.contains(" ") || password.contains(" ")){
                JOptionPane.showMessageDialog(null,"아이디 또는 비밀번호에 띄어쓰기가 있습니다.");
                idText.setText("");
                passwordText.setText("");
                return;
            }

            Userinfocheck userinfocheck = new Userinfocheck(id,password);
            if(userinfocheck.checkUserinfo()&&idText.getText().equals("admin")){
                JOptionPane.showMessageDialog(null,"관리자 로그인 성공");
                //로그인 성공하면 메인창 띄우기
                new MainUI(id,password,"admin");
                //메인창 띄우고 현재창 닫기
                dispose();
            }
            //사용자 로그인
            else if(userinfocheck.checkUserinfo()){

                JOptionPane.showMessageDialog(null, "로그인 성공");
                //로그인 성공하면 메인창 띄우기
                new MainUI(id, password, "user");
                //메인창 띄우고 현재창 닫기
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"로그인 실패");
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
            new MainUI("viewonly",null,"plane");
            dispose();
        });
    }
}
