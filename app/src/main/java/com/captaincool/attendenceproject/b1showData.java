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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class b1showData extends AppCompatActivity {
    private static final String TAG = "Rapp";
    ArrayList<String> names,pList,aList;
    ListView b1ListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1show_data);
        Intent i = getIntent();
        names = i.getStringArrayListExtra("nlist");
        pList = i.getStringArrayListExtra("plist");
        aList = i.getStringArrayListExtra("alist");
        b1ListView = findViewById(R.id.b1slistView);
        setArrayAdapter();

//        startActivity(new Intent(b1showData.this, MainActivity.class));

    }
    public void setArrayAdapter()
    {
        CustomeAdapter adapter = new CustomeAdapter();
        b1ListView.setAdapter(adapter);
        b1ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG,"Selected: "+names.get(i));
                Toast.makeText(b1showData.this,"Selected : "+names.get(i),Toast.LENGTH_SHORT).show();
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
            View cview = getLayoutInflater().inflate(R.layout.show_all,null);
            TextView vname = cview.findViewById(R.id.NameView);
            TextView vpresent = cview.findViewById(R.id.PresentView);
            TextView vabsent = cview.findViewById(R.id.AbsentView);
            vname.setText(names.get(i));
            vpresent.setText(pList.get(i));
            vabsent.setText(aList.get(i));
            return cview;
        }
    }

    private void sname(String value) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Present");
        query.whereEqualTo("studname",value);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null)
                {
                    if(objects.size() > 0)
                    {
                        for(ParseObject obj:objects)
                        {
                            Log.d("Rapp","Present value is: "+obj.getNumber("Present"));
                            Log.d("Rapp","Absent value is: "+obj.getNumber("Absent"));
                            pList.add(obj.getNumber("Present").toString());
                            aList.add(obj.getNumber("Absent").toString());
                        }
                    }
                }
                setArrayAdapter();
            }
        });
    }


}
