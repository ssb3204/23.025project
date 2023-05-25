package Template_Method_Pattern;

import Factory_Pattern.ItemProduct;
import UI.MainUI;

import java.util.List;

public abstract class AbstractItemSorter {
    MainUI UI;
    /**템플릿 패턴의 고정된 로직*/
    public final void sort(List<ItemProduct> items){
        items.sort(this :: compare);
        display();
    }
    protected  abstract  int compare(ItemProduct a, ItemProduct b);

    private void display() {
        UI.clearFrame();
        UI.resetAndAddPanels();
        UI.resetFrame();
    }
}
