package Strategy_Pattern;

import Factory_Pattern_Class.ItemProduct;
import Singleton_Pattern_Class.Singleton;

import java.util.List;

public class StrategySorterTestDriver {
    public static void main(String[] args) {
        //초기화
        Singleton singleton = Singleton.getInstance();
        singleton.dbLoadItem();
        ItemSorter itemSorter;

        System.out.println("정렬전");
        display(singleton.getSell_item_list());
        System.out.println();

        System.out.println("이름순 정렬");
        itemSorter = new TitleSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        display(singleton.getSell_item_list());
        System.out.println();

        System.out.println("이름순 역정렬");
        itemSorter = new TitleReversedSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        display(singleton.getSell_item_list());
        System.out.println();

        System.out.println("가격순 정렬");
        itemSorter = new PriceSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        display(singleton.getSell_item_list());
        System.out.println();

        System.out.println("가격순 역정렬");
        itemSorter = new PriceReversedSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        display(singleton.getSell_item_list());
        System.out.println();
    }

    public static void display(List<ItemProduct> items){
        for(ItemProduct item : items){
            System.out.println(item);
        }
    }
}
