package com.example.algorithmvisualizer.dspackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.algorithmvisualizer.ListItem;
import com.example.algorithmvisualizer.MyAdapter;
import com.example.algorithmvisualizer.R;
import com.example.algorithmvisualizer.arraypackage.ArrayAdapter;

import java.util.ArrayList;

public class DsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview=findViewById(R.id.listView);
        ArrayList<ListItem> items=new ArrayList<>();
        items.add(new ListItem("스택","나중에 넣은 것이 가장 먼저 빠지는 자료구조입니다."));
        items.add(new ListItem("큐","먼저 넣은 것이 먼저 빠지는 자료구조입니다."));
        items.add(new ListItem("덱","스택과 큐의 기능을 동시에 할 수 있습니다."));
        items.add(new ListItem("힙","가장 큰 원소를 빨리 찾고 뺄 수 있습니다."));
        items.add(new ListItem("세그먼트 트리","변하는 배열에서 구간의 합을 빠르게 찾을 수 있습니다."));
        MyAdapter adapter=new DsAdapter(items,getApplicationContext());
        listview.setAdapter(adapter);
    }
}