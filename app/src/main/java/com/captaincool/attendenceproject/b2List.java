package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class b2List extends AppCompatActivity {
    private static final String TAG = "Rapp";
    ArrayList<String> names,divs;
    ImageButton present,absent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2_list);
        Intent i = getIntent();
        names = i.getStringArrayListExtra("names");
        divs = i.getStringArrayListExtra("divs");
        Log.d(TAG,"List is: "+names);
        present = findViewById(R.id.present);
        absent = findViewById(R.id.absent);
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showlist();
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forAbsent();
            }
        });
    }
    private void forAbsent() {
        Intent i = new Intent(b2List.this,b1a.class);
        i.putExtra("names",names);
        i.putExtra("divs",divs);
        startActivity(i);
    }
    void showlist()
    {
        Intent i = new Intent(b2List.this,b2p.class);
        i.putExtra("names",names);
        i.putExtra("divs",divs);
        startActivity(i);
    }
}
