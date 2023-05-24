package Template_Method_Pattern;

import Factory_Pattern.ItemProduct;
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
