package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class b1a extends AppCompatActivity {
    private static final String TAG = "Rapp";
    ArrayList<String> names,divs,selected;
    ListView listView;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1a);
        Intent intent = getIntent();
        names = intent.getStringArrayListExtra("names");
        divs = intent.getStringArrayListExtra("divs");
        listView = findViewById(R.id.b1alistView);
        selected = new ArrayList<>();
        submit = findViewById(R.id.b1asubmit);
        Log.d(TAG,"Divs is: "+divs);
        setArrayAdapter();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeAwayList();
            }
        });
    }
    private void takeAwayList() {
        Intent i = new Intent(b1a.this,storeb1a.class);
        Log.d("Rapp","name is: "+selected);
        i.putExtra("snames",selected);
        startActivity(i);
    }

    public void setArrayAdapter()
    {
        CustomeAdapter adapter = new b1a.CustomeAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG,"Selected: "+names.get(i));
                selected.add(names.get(i));
                Toast.makeText(b1a.this,"Selected : "+names.get(i),Toast.LENGTH_SHORT).show();
            }
        });
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
            View cview = getLayoutInflater().inflate(R.layout.name_layout,null);
            TextView vname = cview.findViewById(R.id.nameView);
            TextView vdiv = cview.findViewById(R.id.divView);
            vname.setText(names.get(i));
            vdiv.setText(divs.get(i));
            return cview;
        }
    }
}
