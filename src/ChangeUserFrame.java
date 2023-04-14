import javax.swing.*;

public class ChangeUserFrame extends JFrame {
    private String ID;
    private String PW;

    private String Name;

    private String Email;

    protected ChangeUserFrame(){
        setTitle("ChangeUser");
        setSize(500,400);
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

        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(100,40,160,25);
        add(passwordText);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10,70,80,25);
        add(nameLabel);

        JTextField nameText = new JTextField(20);
        nameText.setBounds(100,70,160,25);
        add(nameText);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10,100,80,25);
        add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100,100,160,25);
        add(emailText);

        JButton changeButton = new JButton("Change");
        changeButton.setBounds(10,150,80,25);
        add(changeButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(190,150,80,25);
        add(backButton);

        setVisible(true);

        changeButton.addActionListener(e -> {
            //아이디 비밀번호 변경 버튼 누르면 ChangeUserFrame 띄우기
            Userinfocheck userinfocheck = new Userinfocheck(ID,PW);
            this.ID = idText.getText();
            this.PW = passwordText.getText();

            String id = idText.getText();
            String password = passwordText.getText();







            dispose();
            });

        backButton.addActionListener(e -> {

            dispose();
        });
    }
}
