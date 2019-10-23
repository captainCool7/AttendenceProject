package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class storeB2p extends AppCompatActivity {
    ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        names = i.getStringArrayListExtra("snames");
            for(String value : names)
            {
                Log.d("Rapp","name is: "+value);
                updatePresent(value);
            }
            startActivity(new Intent(storeB2p.this,MainActivity.class));
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
