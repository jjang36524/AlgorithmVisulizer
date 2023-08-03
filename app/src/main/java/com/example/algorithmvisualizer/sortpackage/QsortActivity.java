package com.example.algorithmvisualizer.sortpackage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
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
public class QsortActivity extends Activity {
    protected void qsort(CList<Integer> t,int l,int r)
    {
        if(l>=r)
            return;
        int pi=t.get(l);
        int i=l;
        int j=r;
        t.clearMarks();
        t.addLineMark(l,"Left");
        t.addLineMark(r+1,"Right");
        t.setColor(l,Color.parseColor("#FF0000"));
        t.draw();
        while(i<j)
        {
            while(pi<t.get(j))
            {
                j--;
            }
            while(i<j&&pi>=t.get(i))
                i++;
            if(i!=j)
            {
                t.clearColors();
                t.setColor(l,Color.parseColor("#FF0000"));
                t.setColor(i,Color.parseColor("#FFFF00"));
                t.setColor(j,Color.parseColor("#FFFF00"));
                int temp=t.get(i);
                t.set(i,t.get(j));
                t.set(j,temp);
                t.draw();
            }
        }
        t.clearColors();
        t.setColor(l,Color.parseColor("#FFFF00"));
        t.setColor(i,Color.parseColor("#FFFF00"));
        int temp=t.get(l);
        t.set(l,t.get(i));
        t.set(i,temp);
        t.draw();
        qsort(t,l,i-1);
        qsort(t,i+1,r);
    }
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
                qsort(li,0,li.size()-1);
            }
        });

    }
}
