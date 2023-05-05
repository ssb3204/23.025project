package example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SearchFrame extends JFrame {
    //검색창이라는 텍스트 출력
    //검색창에 검색어 입력
    //검색 버튼 누르면 검색어가 포함된 파일 출력
    //검색어가 포함된 파일이 없다면 "No file" 출력
    SearchFrame(){
        setTitle("Search");
        //화면 중앙에 배치
        Dimension frameSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);

        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel searchLabel = new JLabel("Search");
        searchLabel.setBounds(10,10,80,25);
        add(searchLabel);

        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(100,10,160,25);
        add(searchTextField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(10,50,80,25);
        add(searchButton);

        setVisible(true);

        searchButton.addActionListener(e -> {
            //검색 버튼 누르면 검색어가 포함된 파일 출력
            String search = searchTextField.getText();
            File file = new File("C:\\Users\\user\\Desktop\\Java\\JavaProject\\src");
            File[] fileList = file.listFiles();
            for(int i = 0; i < fileList.length; i++){
                if(fileList[i].getName().contains(search)){
                    System.out.println(fileList[i].getName());
                }
            }
        });
        //뒤로가기 버튼을 누르면 현재창을 닫고
        //메인창을 띄우기
        JButton backButton = new JButton("Back");
        backButton.setBounds(180,50,80,25);
        add(backButton);

        backButton.addActionListener(e -> {
            //뒤로가기 버튼 누르면 메인 프레임 출력
            dispose();
        });


    }

}
