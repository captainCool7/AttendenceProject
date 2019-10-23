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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;

public class b2p extends AppCompatActivity {
    private static final String TAG = "Rapp";
    ArrayList<String> names,divs,selected;
    ListView listView;
    Button submit;
    Calendar c;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2p);
        Intent intent = getIntent();
        names = intent.getStringArrayListExtra("names");
        divs = intent.getStringArrayListExtra("divs");
        listView = findViewById(R.id.listView);
        c= Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day =c.get(Calendar.DAY_OF_MONTH);
        month++;
        s = year+""+month+""+day;
        Log.d(TAG,"Date is: "+year+"/"+month+"/"+day);
        selected = new ArrayList<>();
        submit = findViewById(R.id.submit);
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
        Intent i = new Intent(b2p.this,storeB2p.class);
        Log.d("Rapp","name is: "+selected);
        for(String value : selected)
        {
            Log.d("Rapp","name is: "+value);
            updateDpresent(value,s);
        }
        i.putExtra("snames",selected);
        startActivity(i);
    }
    private void updateDpresent(final String value,final String s)
    {
        ParseObject data = new ParseObject("Dpresent");
        data.put("studname",value);
        data.put("Ivalue",s);
        data.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null)
                {
                    Log.d("Rapp","Successful: ");
                }
            }
        });
    }

    public void setArrayAdapter()
    {
        CustomeAdapter adapter = new CustomeAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG,"Selected: "+names.get(i));
                selected.add(names.get(i));
                Toast.makeText(b2p.this,"Selected : "+names.get(i),Toast.LENGTH_SHORT).show();
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
