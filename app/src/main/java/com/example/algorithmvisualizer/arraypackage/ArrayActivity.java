package com.example.algorithmvisualizer.arraypackage;

import android.os.Bundle;
import android.widget.ListView;

import com.example.algorithmvisualizer.ListItem;
import com.example.algorithmvisualizer.MyAdapter;
import com.example.algorithmvisualizer.R;
import com.example.algorithmvisualizer.sortpackage.SortAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ArrayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview=findViewById(R.id.listView);
        ArrayList<ListItem> items=new ArrayList<>();
        items.add(new ListItem("이분 탐색","배열의 특정 원소를 빠르게 찾을 수 있습니다"));
        items.add(new ListItem("삼분 탐색","증가하다 감소하는 배열에서 최댓값을 빠르게 찾을 수 있습니다."));
        items.add(new ListItem("부분합","어떤 배열 구간의 합을 빠르게 구할 수 있습니다."));
        MyAdapter adapter=new ArrayAdapter(items,getApplicationContext());
        listview.setAdapter(adapter);
    }
}