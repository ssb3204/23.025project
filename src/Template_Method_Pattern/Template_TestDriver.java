package Template_Method_Pattern;

import Factory_Pattern.ItemProduct;

import java.util.List;

public class Template_TestDriver {
    public static void main(String[] args) {
       /* Singleton singleton = Singleton.getInstance();
        singleton.dbLoadItem();
        AbstractItemSorter itemSorter;

        System.out.println("정렬 전");
        display(Singleton.getSell_item_list());
        System.out.println();

        System.out.println("이름 정렬");
        itemSorter = new TitleSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        System.out.println();

        System.out.println("이름 역정렬");
        itemSorter = new TitleReversedSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        System.out.println();

        System.out.println("가격 정렬");
        itemSorter = new PriceSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        System.out.println();

        System.out.println("가격 역정렬");
        itemSorter = new PriceReversedSorter();
        itemSorter.sort(Singleton.getSell_item_list());
        System.out.println();*/
    }

    public static void display(List<ItemProduct> items){
        for(ItemProduct item : items){
            System.out.println(item);
        }
    }
}
