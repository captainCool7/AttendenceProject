package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class dateChoose extends AppCompatActivity {
    private static final String TAG = "Rapp";
    ArrayList<String> names;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Button bad,ssdata;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_choose);
        names = new ArrayList<>();
        bad= findViewById(R.id.bad);
        ssdata = findViewById(R.id.ssdata);
        bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        dateChoose.this,
                        mDateSetListener,
                        year, month, day
                );
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 += 1;
                String month = String.format("%02d", i1);
                String day = String.format("%02d", i2);
                s =  i +""+month+""+day;
                Log.i("Rapp", "date is :" + s);
            }
        };
        ssdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getList(s);
            }
        });
    }

    private void getList(String s) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dpresent");
        query.whereEqualTo("Ivalue",s);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null)
                {
                    if(objects.size() > 0)
                    {
                        for(ParseObject obj : objects )
                        {
                            Log.d(TAG, "Name is: "+obj.getString("studname"));
                            names.add(obj.getString("studname"));
                        }
                    }
                }
                Intent i = new Intent(dateChoose.this,showByDate.class);
                i.putExtra("names",names);
//                i.putExtra("divs",divList);
                startActivity(i);
            }
        });
    }
}