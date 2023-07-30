package com.example.algorithmvisualizer.dspackage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.cit.algorithmVisualize.dataStructure.CGraph;
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
            init(0,n-1,1);
            view.draw();
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
            int ret = qry(1,0,n-1,l,r);
            view.clearColor();
            view.draw();
            return ret;
        }
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segtree);
    }
}
