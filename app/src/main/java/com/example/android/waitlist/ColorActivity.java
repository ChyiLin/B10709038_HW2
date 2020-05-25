package com.example.android.waitlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorActivity extends AppCompatActivity {

    ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private SimpleAdapter adapter;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        final SharedPreferences sharedPreferences = getSharedPreferences("User" , MODE_PRIVATE);

        ActionBar actionBar=getSupportActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);


//        listView = (ListView) findViewById(R.id.list);
//        ListAdapter adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_2 ,values);
//        listView.setAdapter(adapter);

        listView = (ListView)findViewById(R.id.list);

        final String[] values = new String[]{
                "Color"
        };
        final String[] valueSet = new String[]{
                sharedPreferences.getString("Color","")
        };

        //把資料加入ArrayList中
        for(int i=0; i<values.length; i++){
            HashMap<String,String> item = new HashMap<String,String>();
            item.put( "Values", values[i]);
            item.put( "ValueSet",valueSet[i] );
            list.add( item );}

        adapter = new SimpleAdapter(
                this,
                list,
                android.R.layout.simple_list_item_2,
                new String[] { "Values","ValueSet" },
                new int[] { android.R.id.text1, android.R.id.text2 } );
        final Context adp = this.getBaseContext();



        //ListActivity設定adapter
        listView.setAdapter( adapter );

        //啟用按鍵過濾功能，這兩行資料都會進行過濾
        listView.setTextFilterEnabled(true);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PopupMenu popupMenu = new PopupMenu(ColorActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_color,popupMenu.getMenu());

                //彈出式菜單的菜單項點擊事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem itemX) {
                        switch (itemX.getItemId()) {
                            case R.id.item1:
                                sharedPreferences.edit().putString("Color", "Blue").apply();
                                valueSet[0] = "Blue";
                                break;
                            case R.id.item2:
                                sharedPreferences.edit().putString("Color", "Red").apply();
                                valueSet[0] = "Red";
                                break;
                            case R.id.item3:
                                sharedPreferences.edit().putString("Color", "Green").apply();
                                valueSet[0] = "Green";
                                break;
                        }
                        sharedPreferences.edit().commit();
                        return false;
            }
        });
                //彈出式菜單的菜單的關閉事件
                popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
                    @Override
                    public void onDismiss(PopupMenu menu) {
                        list.clear();
                        for(int i=0; i<values.length; i++){
                            HashMap<String,String> item = new HashMap<String,String>();
                            item.put( "Values", values[i]);
                            item.put( "ValueSet",valueSet[i] );
                            list.add( item );}

                        adapter.notifyDataSetChanged();
                        adapter = new SimpleAdapter(
                                adp,
                                list,
                                android.R.layout.simple_list_item_2,
                                new String[] { "Values","ValueSet" },
                                new int[] { android.R.id.text1, android.R.id.text2 } );
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);

                        Toast.makeText(ColorActivity.this, "menu close.", Toast.LENGTH_SHORT).show();
                    }
                });
                popupMenu.show();

            }


        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(ColorActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回鍵
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(ColorActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
