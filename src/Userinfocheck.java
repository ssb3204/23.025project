import java.io.*;

public class Userinfocheck {
    //입력 받은 아이디와 비밀번호가 파일에 있는지 확인
    //파일에 저장된 정보와 입력받은 정보가 같으면 true
    //다르면 false

    private String id;
    private String password;

    private String Name;

    private String Email;
    private String idFile;
    private String passwordFile;
    private String[] userinfo;
    private String[] userinfoFile;
    private boolean check;
    Userinfocheck(String id, String password){
        this.id = id;
        this.password = password;
        check = false;
    }

    Userinfocheck(String id, String password, String name, String email){
        this.id = id;
        this.password = password;
        this.Name = name;
        this.Email = email;
        check = false;
    }


    //입력한 아이디와 비밀번호가 userinfo.txt에 있다면 그 라인뺴고 다시 저장

    public void deleteUserinfo(){
        try{
            File file = new File("userinfo.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                userinfo = line.split(" ");
                idFile = userinfo[0];
                passwordFile = userinfo[1];
                if(id.equals(idFile) && password.equals(passwordFile)){
                    continue;
                }
                else{
                    FileWriter fileWriter = new FileWriter("userinfo.txt",true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(idFile + " " + passwordFile);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                }
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveUserinfo(){
        try{
            FileWriter fileWriter = new FileWriter("userinfo.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(id + " " + password);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public boolean checkUserinfo(){
        try{
            File file = new File("userinfo.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                userinfoFile = line.split(" ");
                idFile = userinfoFile[0];
                passwordFile = userinfoFile[1];
                if(id.equals(idFile) && password.equals(passwordFile)){
                    check = true;
                    break;
                }
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return check;
    }

    public void changeUserinfo(String id, String password){
        try{
            File file = new File("userinfo.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                userinfo = line.split(" ");
                idFile = userinfo[0];
                passwordFile = userinfo[1];
                if(id.equals(idFile) && password.equals(passwordFile)){
                    continue;
                }
                else{
                    FileWriter fileWriter = new FileWriter("userinfo.txt",true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(idFile + " " + passwordFile);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                }
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
