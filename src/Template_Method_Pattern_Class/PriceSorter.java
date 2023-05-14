package Template_Method_Pattern_Class;

import Factory_Pattern_Class.ItemProduct;
import UI.MainUI;

public class PriceSorter extends AbstractItemSorter {
    public PriceSorter(MainUI UI) {
        this.UI =  UI;
    }

    @Override
    protected int compare(ItemProduct a, ItemProduct b) {
        return Integer.compare(a.getPrice(), b.getPrice());
    }
}
