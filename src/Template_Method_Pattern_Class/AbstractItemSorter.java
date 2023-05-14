package Template_Method_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import UI.MainUI;

import java.util.List;

public abstract class AbstractItemSorter {
    MainUI UI;

    /**템플릿 패턴의 고정된 로직*/
    public final void sort(List<ItemProduct> items){
        items.sort(this :: compare);
        /*System.out.println("정렬되었습니다");
        print(items);
        System.out.println();*/
        display();
    }

    protected  abstract  int compare(ItemProduct a, ItemProduct b);

    private void print(List<ItemProduct> items){
        for(ItemProduct item : items){
            System.out.println(item);
        }
    }

    private void display() {
        UI.deletePanel();
        UI.resetFrame();
        UI.reloadUI();
    }
}
