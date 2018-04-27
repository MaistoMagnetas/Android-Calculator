package com.example.pc.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PC on 4/27/2018.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> idList;
    ArrayList<String> inputsList;
    ArrayList<String> answersList;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext,ArrayList<String> idList,ArrayList<String> inputsList,ArrayList<String> answersList){
        this.context = applicationContext;
        this.idList = idList;
        this.inputsList = inputsList;
        this.answersList = answersList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return idList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.listviewitem,null);
        TextView idText = (TextView) convertView.findViewById(R.id.listview_id);
        TextView inputText = (TextView) convertView.findViewById(R.id.listview_inputtext);
        TextView answerText = (TextView) convertView.findViewById(R.id.listview_answertext);
        idText.setText(idList.get(position));
        inputText.setText(inputsList.get(position));
        answerText.setText(answersList.get(position));
        return convertView;
    }
}
