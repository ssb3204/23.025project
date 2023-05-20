package sort2;

import Factory_Pattern.ItemProduct;

import java.util.Comparator;
import java.util.List;

public class PriceReversedSorter implements ItemSorter{
    /**물품의 가격 순으로 역정렬*/
    @Override
    public void sort(List<ItemProduct> items) {
        //ItemProduct::getPrice => 메서드 참조 (정렬시에 자동으로 해당 메서드의 반환값을 참조한다.)
        items.sort(Comparator.comparing(ItemProduct::getPrice).reversed());
    }
}
