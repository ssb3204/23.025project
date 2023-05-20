package sort2;

import Factory_Pattern_Class.ItemProduct;

import java.util.Comparator;
import java.util.List;

public class TitleSorter implements ItemSorter {
    /**물품의 이름 순으로 정렬*/
    @Override
    public void sort(List<ItemProduct> items) {
        //ItemProduct::getTitle => 메서드 참조 (정렬시에 자동으로 해당 메서드의 반환값을 참조한다.)
        items.sort(Comparator.comparing(ItemProduct::getTitle));
    }
}
