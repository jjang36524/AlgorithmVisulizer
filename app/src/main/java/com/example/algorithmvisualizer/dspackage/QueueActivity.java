package com.example.algorithmvisualizer.dspackage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cit.algorithmVisualize.GraphicView;
import com.cit.algorithmVisualize.dataStructure.CList;
import com.example.algorithmvisualizer.R;

import java.util.Arrays;

public class QueueActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        GraphicView stack=findViewById(R.id.stack);
        stack.initialize(0,0,1080,720);
        Spinner spinner=(Spinner)findViewById(R.id.query);
        final String[] querylist=getResources().getStringArray(R.array.queuequery);
        ArrayAdapter<String> queryAdapter=new ArrayAdapter<String>(this,R.layout.query_layout, querylist);
        spinner.setAdapter(queryAdapter);
        final int[] qp = {0};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                qp[0] =i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                qp[0]=-1;
            }
        });
        Button exec=findViewById(R.id.change);
        CList<Integer> li=new CList<>(stack, Arrays.asList(1,0,0,0,0,0,0));

        final int[] top = {0,1};
        li.addLineMark(top[0],"front");
        li.addLineMark(top[1],"back");
        li.draw();
        EditText t=findViewById(R.id.value);
        exec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                li.clearMarks();
                switch(qp[0])
                {
                    case 0:
                        int v=Integer.parseInt(t.getText().toString());
                        if(v<=0)
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "invalid index or value", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        if((top[1] +1)%7==top[0])
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "queue is full", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        li.set(top[1],v);
                        top[1]++;
                        top[1]%=7;
                        break;
                    case 1:
                        if(top[0]==top[1])
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "queue is empty", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        li.set(top[0]+1,0);
                        top[0]++;
                        top[0]%=7;
                        break;
                    case 2:
                        if(top[0]==top[1])
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "queue is empty", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        Toast t=Toast.makeText(getApplicationContext(), "Front element is "+li.get(top[0]), Toast.LENGTH_SHORT);
                        t.show();
                        break;
                    case 3:
                        t = Toast.makeText(getApplicationContext(), "Size of the queue is " + (top[1]-top[0] + 7)%7, Toast.LENGTH_SHORT);
                        t.show();
                        break;
                }
                li.addLineMark(top[0],"front");
                li.addLineMark(top[1],"back");
                li.draw();
                stack.render();
            }
        });
    }
}
