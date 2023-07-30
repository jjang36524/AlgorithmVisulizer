package com.example.algorithmvisualizer.sortpackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.algorithmvisualizer.ListItem;
import com.example.algorithmvisualizer.MyAdapter;
import com.example.algorithmvisualizer.R;
import com.example.algorithmvisualizer.arraypackage.ArrayAdapter;

import java.util.ArrayList;

public class SortActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview=findViewById(R.id.listView);
        ArrayList<ListItem> items=new ArrayList<>();
        items.add(new ListItem("버블 정렬","."));
        items.add(new ListItem("선택 정렬","."));
        items.add(new ListItem("삽입 정렬","."));
        items.add(new ListItem("퀵 정렬","."));
        items.add(new ListItem("힙 정렬","."));
        items.add(new ListItem("병합 정렬","."));
        MyAdapter adapter=new SortAdapter(items,getApplicationContext());
        listview.setAdapter(adapter);
    }
}