package com.example.algorithmvisualizer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.cit.algorithmVisualize.GraphicView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview=findViewById(R.id.listView);
        ArrayList<ListItem>items=new ArrayList<>();
        items.add(new ListItem("정렬","배열에 있는 수들을 오름차순으로 정렬하는 알고리즘입니다."));
        items.add(new ListItem("배열 알고리즘","배열에서 동작하는 여러 알고리즘입니다"));
        items.add(new ListItem("자료구조","자료들을 효율적인 방식으로 저장할 수 있습니다"));
        items.add(new ListItem("기하학","평면 상에서 동작하는 기하 알고리즘들입니다."));
        items.add(new ListItem("그래프","그래프 상에서 동작하는 여러 알고리즘들입니다"));
        MyAdapter adapter=new MyAdapter(items,getApplicationContext());
        listview.setAdapter(adapter);
    }
}