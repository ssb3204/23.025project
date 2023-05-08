package UI;

import Singleton_Pattern_Class.Singleton;

import javax.swing.*;

public class ItemInfoUI {
    private JPanel item_info_panel;
    private int index;
    private Singleton item_list;

    public ItemInfoUI(Singleton item_list, int index) {
        this.item_list = item_list;
        this.index = index;
        item_info_panel = new JPanel();

        item_info_panel.setBounds(0, 0, 100, 100);

    }

    public JPanel getItem_info_panel() {
        return item_info_panel;
    }
}
