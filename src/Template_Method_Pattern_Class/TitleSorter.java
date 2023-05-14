package Template_Method_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import UI.MainUI;

public class TitleSorter extends AbstractItemSorter{

    public TitleSorter(MainUI UI) {
        this.UI = UI;
    }

    @Override
    protected int compare(ItemProduct a, ItemProduct b) {
        return a.getTitle().compareTo(b.getTitle());
    }
}
