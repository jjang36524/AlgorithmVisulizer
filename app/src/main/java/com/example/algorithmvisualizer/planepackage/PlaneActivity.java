package com.example.algorithmvisualizer.planepackage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.algorithmvisualizer.ListItem;
import com.example.algorithmvisualizer.MyAdapter;
import com.example.algorithmvisualizer.R;
import com.example.algorithmvisualizer.dspackage.DsAdapter;

import java.util.ArrayList;

public class PlaneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview=findViewById(R.id.listView);
        ArrayList<ListItem> items=new ArrayList<>();
        items.add(new ListItem("볼록 껍질",""));
        items.add(new ListItem("로테이팅 캘리퍼스",""));
        items.add(new ListItem("최소 외접원",""));
        MyAdapter adapter=new PlaneAdapter(items,getApplicationContext());
        listview.setAdapter(adapter);
    }
}