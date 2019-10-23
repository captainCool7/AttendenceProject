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
import java.util.List;

public class storeb1a extends AppCompatActivity {
    ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        names = i.getStringArrayListExtra("snames");
        for (String value : names) {
            Log.d("Rapp", "name is: " + value);
            updateAbsent(value);
        }
        startActivity(new Intent(storeb1a.this, MainActivity.class));
    }
    private void updateAbsent(String value) {
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
                            Log.d("Rapp","Present value is: "+obj.getNumber("Absent"));
                            int absent = (int) obj.getNumber("Absent");
                            absent++;
                            obj.put("Absent",absent);
                            obj.saveInBackground();
                        }
                    }
                }
            }
        });
    }
}
