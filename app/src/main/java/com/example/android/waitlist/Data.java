package com.example.android.waitlist;

import android.widget.SimpleAdapter;

public class Data {
    private static String Color = "Blue";

    public static String getColor() {
        return Color;
    }

    public static void setColor(String Color) {
        Data.Color = Color;
    }


//    private  static SimpleAdapter adapter;
//
//    public  static  SimpleAdapter getAdapter(){return adapter;}
//
//    public  static  void  setAdapter(SimpleAdapter adapter){Data.adapter = adapter;}
}
