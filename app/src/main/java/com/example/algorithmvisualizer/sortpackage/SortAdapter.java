package com.example.algorithmvisualizer.sortpackage;

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
import com.example.algorithmvisualizer.arraypackage.BsearchActivity;
import com.example.algorithmvisualizer.arraypackage.PrefixActivity;
import com.example.algorithmvisualizer.arraypackage.TsearchActivity;

import java.util.ArrayList;

public class SortAdapter extends MyAdapter {
    public SortAdapter(ArrayList<ListItem> items, Context applicationContext) {
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
                        intent = new Intent(mContext, BsortActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, SsortActivity.class);
                        break;
                    case 2:
                        intent = new Intent(mContext, IsortActivity.class);
                        break;
                    case 3:
                        intent = new Intent(mContext, QsortActivity.class);
                        break;
                    case 4:
                        intent = new Intent(mContext, MsortActivity.class);
                        break;
                    case 5:
                        intent = new Intent(mContext, HsortActivity.class);
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
