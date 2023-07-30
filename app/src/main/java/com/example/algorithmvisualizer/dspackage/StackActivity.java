package com.example.algorithmvisualizer.dspackage;

import androidx.appcompat.app.AppCompatActivity;

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

public class StackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stack);
        GraphicView stack=findViewById(R.id.stack);
        stack.initialize(0,0,1080,720);
        Spinner spinner=(Spinner)findViewById(R.id.query);
        final String[] querylist=getResources().getStringArray(R.array.stackquery);
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
        li.draw();
        final int[] top = {0};
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
                        if(top[0] ==6)
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "stack is full", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        li.set(top[0] +1,v);
                        top[0]++;
                        break;
                    case 1:
                        if(top[0]==0)
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "stack is empty", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        li.set(top[0]-1,0);
                        top[0]--;
                        break;
                    case 2:
                        if(top[0]==-1)
                        {
                            Toast t=Toast.makeText(getApplicationContext(), "stack is empty", Toast.LENGTH_SHORT);
                            t.show();
                            break;
                        }
                        Toast t=Toast.makeText(getApplicationContext(), "Top element is "+li.get(top[0]), Toast.LENGTH_SHORT);
                        t.show();
                        break;
                    case 3:
                        t = Toast.makeText(getApplicationContext(), "Size of the stack is " + (top[0] + 1), Toast.LENGTH_SHORT);
                        t.show();
                        break;
                }
                if(top[0]>=0)
                {
                    li.addCheckMark(top[0]);
                }
                li.draw();
                stack.render();
            }
        });
    }
}