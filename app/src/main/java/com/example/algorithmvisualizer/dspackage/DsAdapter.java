package com.example.algorithmvisualizer.dspackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.algorithmvisualizer.ListItem;
import com.example.algorithmvisualizer.MyAdapter;
import com.example.algorithmvisualizer.R;
import com.example.algorithmvisualizer.dspackage.DsActivity;
import com.example.algorithmvisualizer.graphpackage.GraphActivity;
import com.example.algorithmvisualizer.planepackage.PlaneActivity;
import com.example.algorithmvisualizer.sortpackage.SortActivity;

import java.util.ArrayList;

public class DsAdapter extends MyAdapter {
    public DsAdapter(ArrayList<ListItem> items, Context applicationContext) {
        super(items, applicationContext);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.listnode, viewGroup, false);
        TextView name = view.findViewById(R.id.title);
        name.setText(sample.get(i).getname());
        TextView desc = view.findViewById(R.id.desc);
        desc.setText(sample.get(i).getdesc());
        Button button = view.findViewById(R.id.execute);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                switch(i)
                {
                    case 0:
                        intent = new Intent(mContext, StackActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, QueueActivity.class);
                        break;
                    case 2:
                        intent = new Intent(mContext, DequeActivity.class);
                        break;
                    case 3:
                        intent = new Intent(mContext, HeapActivity.class);
                        break;
                    case 4:
                        intent = new Intent(mContext, SegtreeActivity.class);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + i);
                }

                mContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
        return view;
    }
}
