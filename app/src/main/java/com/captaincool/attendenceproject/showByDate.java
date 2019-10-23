package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class showByDate extends AppCompatActivity {
    ArrayList<String> names;
    ListView sbyd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_by_date);
        Intent i = getIntent();
        names = i.getStringArrayListExtra("names");
        sbyd  = findViewById(R.id.sbydlistview);
        setArrayAdapter();
    }
    public void setArrayAdapter()
    {
        CustomeAdapter adapter = new CustomeAdapter();
        sbyd.setAdapter(adapter);

    }

    class CustomeAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View cview = getLayoutInflater().inflate(R.layout.date_view,null);
            TextView vname = cview.findViewById(R.id.sbydname);            vname.setText(names.get(i));
            return cview;
        }
    }
}
