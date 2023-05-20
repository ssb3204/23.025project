package Template_Method_Pattern;

import Factory_Pattern.ItemProduct;
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
