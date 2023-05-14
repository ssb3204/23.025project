package Strategy_Pattern;

import Factory_Pattern_Class.ItemProduct;

import java.util.List;

public interface ItemSorter {
    void sort(List<ItemProduct> items);
}
