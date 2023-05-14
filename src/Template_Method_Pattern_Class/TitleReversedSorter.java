package Template_Method_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import UI.MainUI;

public class TitleReversedSorter extends AbstractItemSorter {

    public TitleReversedSorter(MainUI UI) {
        this.UI = UI;
    }

    @Override
    protected int compare(ItemProduct a, ItemProduct b) {
        return b.getTitle().compareTo(a.getTitle());
    }
}
