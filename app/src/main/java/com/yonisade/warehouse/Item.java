package com.yonisade.warehouse;


import java.util.Arrays;

public class Item {

    private int item_id;
    private String item_name;
    private String item_amount;
    private byte[] item_img;

    //<editor-fold desc="Constructor">

    public Item(){

    }

    public Item(Item i)
    {
        this.item_name = i.getItem_name();
        this.item_amount = i.getItem_amount();
        this.item_img = i.getItem_img();
    }

    public Item(String item_name, String item_amount) {
        this.item_name = item_name;
        this.item_amount = item_amount;
    }


    public Item(String item_name, String item_amount, byte[] item_img) {
        this.item_name = item_name;
        this.item_amount = item_amount;
        this.item_img = item_img;
    }

    //</editor-fold>

    //<editor-fold desc="Getters & Setters">

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_amount() {
        return item_amount;
    }

    public void setItem_amount(String item_amount) {
        this.item_amount = item_amount;
    }

    public byte[] getItem_img() {
        return item_img;
    }

    public void setItem_img(byte[] item_img) {
        this.item_img = item_img;
    }

    //</editor-fold>

    //<editor-fold desc="toString">

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_amount='" + item_amount + '\'' +
                ", item_img=" + Arrays.toString(item_img) +
                '}';
    }



    //</editor-fold>

}

