package com.example.algorithmvisualizer.arraypackage;

import androidx.appcompat.app.AppCompatActivity;

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

public class PrefixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefix);
        GraphicView prefix = findViewById(R.id.prefix);
        Context c = this.getApplicationContext();
        prefix.initialize(0, 0, 1080, 700);
        CList<Integer> li = new CList<>(prefix, Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Button ch=findViewById(R.id.change);
        Field field=null;
        try {
            field = prefix.getClass().getDeclaredField("mRenderQueue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        Field finalField = field;
        li.draw();
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( prefix,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                EditText idx=findViewById(R.id.index);
                EditText val=findViewById(R.id.value);
                try{
                    int i=Integer.parseInt(idx.getText().toString());
                    int v=Integer.parseInt(val.getText().toString());
                    if(i<0||i>li.size())
                        throw new Exception();
                    else if(i==li.size())
                    {
                        li.add(v);
                    }
                    else
                    {
                        li.set(i,v);
                        Log.d("1",(i*10+v)+"");
                    }
                }
                catch(Exception e) {
                    Toast t=Toast.makeText(c, "invalid index or value", Toast.LENGTH_SHORT);
                    t.show();
                }
                li.draw();
            }
        });
        Button exec=findViewById(R.id.exec);
        exec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( prefix,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                CList<Integer> nli=li;
                int i;
                for(i=1;i<li.size();i++)
                {
                    li.clearMarks();
                    li.clearColors();
                    li.set(i,li.get(i-1)+li.get(i));
                    li.setColor(i,Color.parseColor("#FFFF00"));
                    li.setColor(i-1,Color.parseColor("#FFFF7F"));
                    li.draw();
                }
                nli=li;
                li.clearMarks();
                li.clearColors();
                li.draw();
            }
        });
    }
}