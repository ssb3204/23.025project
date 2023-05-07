package Observer_Pattern_class;

import java.util.ArrayList;
import java.util.List;

public class Notice implements Observer {
    private List<String> notice_list = new ArrayList<String>();

    @Override
    public void update(String msg) {
        notice_list.add(msg);
    }

    public void showNotice(){
        for(String s: notice_list){
            System.out.println(s);
        }
    }

    public List<String> getNotice_list(){
        return notice_list;
    }
}
