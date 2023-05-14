package Template_Method_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import UI.MainUI;

public class PriceReversedSorter extends AbstractItemSorter {
    public PriceReversedSorter(MainUI UI) {
        this.UI = UI;
    }

    @Override
    protected int compare(ItemProduct a, ItemProduct b) {
        return Integer.compare(b.getPrice(), a.getPrice());
    }
}
