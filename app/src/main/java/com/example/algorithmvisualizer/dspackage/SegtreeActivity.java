package com.example.algorithmvisualizer.dspackage;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cit.algorithmVisualize.GraphicView;
import com.cit.algorithmVisualize.dataStructure.CGraph;
import com.cit.algorithmVisualize.mathStructure.Edge;
import com.example.algorithmvisualizer.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SegtreeActivity extends Activity {
    static class STree
    {
        private int n;
        private CGraph view;
        private ArrayList<Integer> arr = new ArrayList<>();
        private ArrayList<String> arr2=new ArrayList<>();
        public STree(int n, CGraph view)
        {
            this.n = n;
            this.view = view;
            arr=new ArrayList<>(2*n);
            arr2=new ArrayList<>(2*n);
            for (int i = 0; i < 2*n; i++) {
                arr.add(0);
            }
            for (int i = 0; i < 2*n; i++) {
                arr2.add("");
            }
            init(0,n-1,1);
        }
        public void init(int s, int e, int i)
        {
            arr2.set(i,"["+s+","+e+"] ");
            view.setVertText(i, "["+s+","+e+"] "+arr.get(i));
            if(s == e)
            {
                return;
            }
            init(s,(s+e)/2,i*2);
            init((s+e)/2+1,e,i*2+1);
        }
        public void upd(int i,int v){
            i+=n;
            view.setVertColor(i, Color.GREEN);
            arr.set(i,v);
            view.setVertText(i,arr2.get(i)+arr.get(i));
            view.draw();
            view.setVertColor(i,Color.WHITE);
            while(i>1)
            {
                i/=2;
                view.setVertColor(i, Color.GREEN);
                arr.set(i,arr.get(i*2)+arr.get(i*2+1));
                view.setVertText(i,arr2.get(i)+arr.get(i));
                view.draw();
                view.setVertColor(i,Color.WHITE);
            }
        }
        public int qry(int s, int e, int l, int r,int i){
            if(r < s || e< l) return 0;
            if(l <= s && e <= r){
                view.setVertColor(i, Color.BLUE);
                view.draw();
                return arr.get(i);
            }
            view.setVertColor(i, Color.GREEN);
            view.draw();
            int ret = qry(s,(s+e)/2,l,r,2*i)+qry((s+e)/2+1,e,l,r,2*i+1);
            view.setVertColor(i, Color.WHITE);
            return ret;
        }
        public int qry(int l, int r)
        {
            view.clearColor();
            view.draw();
            int ret = qry(0,n-1,l,r,1);
            view.clearColor();
            view.draw();
            return ret;
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segtree);
        GraphicView segtree=findViewById(R.id.segtree);
        segtree.initialize(0,0,1080,720);
        CGraph graph=new CGraph(segtree);
        int i;
        graph.add_vert(new Point(0,0));

        graph.setVertSize(60);
        graph.setTextSize(30);
        for(i=1;i<=4;i++)
        {
            int j;
            for(j=(1<<(i-1));j<(1<<i);j++) {
                graph.add_vert(new Point((((j - (1 << (i - 1))) * 2000 + 1000) / (1 << i)), i * 140 + 50));
                if(i!=1)
                    graph.add_edge(new Edge(j/2,j,0,j-2), android.R.color.holo_blue_dark);
            }
        }
        graph.setVertActive(0,false);
        STree seg=new STree(8,graph);
        Button change=findViewById(R.id.change);
        graph.draw();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    EditText idx=findViewById(R.id.index);
                    int i=Integer.parseInt(idx.getText().toString());
                    EditText val=findViewById(R.id.value);
                    int v=Integer.parseInt(val.getText().toString());
                    if(i<0||i>7)
                    {
                        throw new Exception();
                    }
                    seg.upd(i,v);
                }
                catch(Exception e)
                {
                    Toast t=Toast.makeText(getApplicationContext(), "invalid index or value", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        Button query=findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    EditText qstart=findViewById(R.id.qstart);
                    int s=Integer.parseInt(qstart.getText().toString());
                    EditText qend=findViewById(R.id.qend);
                    int e=Integer.parseInt(qend.getText().toString());
                    if(s<0||s>7||e<0||e>7)
                    {
                        throw new Exception();
                    }
                    Toast t=Toast.makeText(getApplicationContext(), "The sum is "+seg.qry(s,e), Toast.LENGTH_SHORT);
                    t.show();
                }
                catch(Exception e)
                {
                    Toast t=Toast.makeText(getApplicationContext(), "invalid index or value", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
}
