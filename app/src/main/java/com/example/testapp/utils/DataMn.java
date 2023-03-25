package com.example.testapp.utils;

import com.example.testapp.Item;

import java.util.ArrayList;

public class DataMn {

    private static DataMn dataMn;
    private ArrayList<Item> itemList;
    private DataMn() {
       this.itemList= new ArrayList<>();
    }
    public static DataMn getInstance() {
        if(dataMn == null){
            dataMn = new DataMn();
        }
        return dataMn;
    }

}
