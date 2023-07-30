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
import com.example.algorithmvisualizer.sortpackage.SortFuncs;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BsearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsearch);
        GraphicView bsearch = findViewById(R.id.bsearch);
        Context c=this.getApplicationContext();
        bsearch.initialize(0,0,1080, 700);
        CList<Integer> li=new CList<>(bsearch, Arrays.asList(1,2,3,4,5,6,7));
        Button stop=findViewById(R.id.stop);
        Field field=null;
        try {
            field = bsearch.getClass().getDeclaredField("mRenderQueue");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        field.setAccessible(true);
        Field finalField = field;
        stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( bsearch,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                li.clearMarks();
                li.clearColors();
                li.draw();
                bsearch.render();
            }
        });
        final int[] fval = {3};
        Button ch=findViewById(R.id.change);
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( bsearch,viviz);

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
        exec.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                EditText sva=findViewById(R.id.svalue);
                fval[0] =Integer.parseInt(sva.getText().toString());
                try {
                    Queue<Queue<Consumer<Canvas>>> viviz=new LinkedList<>();
                    finalField.set( bsearch,viviz);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                int s=0;
                int e=li.size()-1;
                int t= fval[0];
                li.clearColors();
                li.clearMarks();
                li.addRectMarks(s,e);
                while(s<e)
                {
                    int m=(s+e)/2;
                    li.addCheckMark(m);
                    li.draw();
                    if(li.get(m)==t) {
                        li.removeCheckMark(m);
                        li.setColor(m, Color.parseColor("#FF0000"));
                        li.draw();
                        break;
                    }
                    else if(li.get(m)<t)
                    {
                        li.removeRectMark(s,e);
                        s=m+1;
                        li.removeCheckMark(m);
                        li.addRectMarks(s,e);
                        li.draw();
                    }
                    else
                    {
                        li.removeRectMark(s,e);
                        e=m-1;
                        li.removeCheckMark(m);
                        li.addRectMarks(s,e);
                        li.draw();
                    }
                }
                if(s==e&&li.get(s)==t)
                {
                    li.setColor(s, Color.parseColor("#FF0000"));
                    li.draw();
                }
            }
        });
        int s=0;
        int e=li.size()-1;
        int t= fval[0];
        li.clearColors();
        li.addRectMarks(s,e);
        while(s<e)
        {
            int m=(s+e)/2;
            li.addCheckMark(m);
            li.draw();
            if(li.get(m)==t) {
                li.setColor(m, Color.parseColor("#FF0000"));
                li.removeCheckMark(m);
                li.draw();
                break;
            }
            else if(li.get(m)<t)
            {
                li.removeRectMark(s,e);
                s=m+1;
                li.removeCheckMark(m);
                li.addRectMarks(s,e);
                li.draw();
            }
            else
            {
                li.removeRectMark(s,e);
                e=m-1;
                li.removeCheckMark(m);
                li.addRectMarks(s,e);
                li.draw();
            }
        }
        if(s==e&&li.get(s)==t)
        {
            li.setColor(s, Color.parseColor("#FF0000"));
            li.draw();
        }
        li.clearCheckMarks();


    }
}