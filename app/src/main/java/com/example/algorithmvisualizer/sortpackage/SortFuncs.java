package com.example.algorithmvisualizer.sortpackage;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.cit.algorithmVisualize.GraphicView;
import com.cit.algorithmVisualize.dataStructure.CList;
import com.example.algorithmvisualizer.R;

import java.util.Random;

public class SortFuncs {
    public void chval(GraphicView gview, CList<Integer> li, Context c, int i, int v)
    {
        gview.renderClear();
        li.clearMarks();
        int ii;
        for(ii=0;ii<100;ii++)
        {
            gview.render();
        }
        try{
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
        gview.render();
    }
    public void shuffle(GraphicView gview, CList<Integer> li)
    {
        gview.renderClear();
        li.clearMarks();
        int ii;
        for(ii=0;ii<100;ii++)
        {
            gview.render();
        }
        int i;
        Random rd=new Random();
        for(i=1;i<li.size();i++)
        {
            int j=rd.nextInt(i);
            int temp=li.get(j);
            li.set(j,li.get(i));
            li.set(i,temp);
        }
        li.draw();
        gview.render();
    }
}
