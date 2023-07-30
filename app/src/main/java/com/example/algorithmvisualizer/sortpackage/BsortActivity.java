package com.example.algorithmvisualizer.sortpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cit.algorithmVisualize.GraphicView;
import com.cit.algorithmVisualize.dataStructure.CList;
import com.example.algorithmvisualizer.R;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BsortActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorts);
        GraphicView sort = findViewById(R.id.sort);
        sort.initialize(0,0,1080,720);
        CList<Integer> li=new CList<>(sort, Arrays.asList(4,1,7,2,5,6,3));
        EditText index=findViewById(R.id.index);
        EditText value=findViewById(R.id.value);
        Button chval=findViewById(R.id.change);
        Button exec=findViewById(R.id.exec);
        Button shuffle=findViewById(R.id.shuffle);
        Context mContext=getApplicationContext();
        Field field=null;
        SortFuncs funcs = new SortFuncs();
        try {
            field = sort.getClass().getDeclaredField("mRenderQueue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);


        li.draw();
        Field finalField = field;
        chval.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( sort,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try{
                    int i=Integer.parseInt(index.getText().toString());
                    int v=Integer.parseInt(value.getText().toString());
                    funcs.chval(sort,li,mContext,i,v);
                }
                catch(Exception e) {
                    Toast t=Toast.makeText(mContext, "invalid index or value", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( sort,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                funcs.shuffle(sort,li);
            }
        });
        exec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( sort,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                int i,j;
                for(i=0;i<li.size();i++)
                {
                    for(j=0;j<li.size()-1;j++)
                    {
                        li.clearMarks();
                        li.clearColors();
                        li.addCheckMark(j);
                        li.addCheckMark(j+1);
                        if(li.get(j)>li.get(j+1))
                        {
                            li.setColor(j, Color.parseColor("#FF0000"));
                            li.setColor(j+1, Color.parseColor("#FF0000"));
                            int temp=li.get(j);
                            li.set(j,li.get(j+1));
                            li.set(j+1,temp);
                        }
                        li.draw();
                    }
                }
            }
        });

    }
}
