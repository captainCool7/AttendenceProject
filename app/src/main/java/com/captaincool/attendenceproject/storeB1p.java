package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class storeB1p extends AppCompatActivity {
    ArrayList<String> names;
    private static final String TAG = "Rapp";
    Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        names = i.getStringArrayListExtra("snames");
        c = Calendar.getInstance();
        for(String value : names)
        {
            Log.d("Rapp","name is: "+value);
            updatePresent(value);
            //updateDpresent(value);
        }
        startActivity(new Intent(storeB1p.this,MainActivity.class));
    }

    private void updateDpresent(String value) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dpresent");
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
                            obj.put("2019/10/23",1);
                            obj.saveInBackground();
                        }
                    }
                }
            }
        });
    }

    private void updatePresent(String value) {
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
                            int present = (int) obj.getNumber("Present");
                            present++;
                            obj.put("Present",present);
                            obj.saveInBackground();
                        }
                    }
                }
            }
        });
    }
}
