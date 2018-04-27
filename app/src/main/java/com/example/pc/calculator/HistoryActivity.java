package com.example.pc.calculator;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    DatabaseHelper db = new DatabaseHelper(this);
    ArrayList<String> idList = new ArrayList<String>();
    ArrayList<String> inputsList = new ArrayList<String>();
    ArrayList<String> answersList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Cursor res = db.getAllHistoryData();
        if(res.getCount() == 0){
            Toast.makeText(this, "Sorry. No data available.", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while (res.moveToNext()){
                idList.add(res.getString(0)+"."); //EX: 1. 2. etc
                inputsList.add(res.getString(1));
                answersList.add(res.getString(2));
            }
        }

        //Populate liistview with DB data
        listView = (ListView) findViewById(R.id.hisotry_listview);
        CustomAdapter adapter = new CustomAdapter(this,idList,inputsList,answersList);
        listView.setAdapter(adapter);
    }


}
