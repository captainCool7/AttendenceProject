package com.captaincool.attendenceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class b1List extends AppCompatActivity {
    private static final String TAG = "Rapp";
    ArrayList<String> names,divs;
    ImageButton present,absent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_list);
        Intent i = getIntent();
        names = i.getStringArrayListExtra("names");
        divs = i.getStringArrayListExtra("divs");
        Log.d(TAG,"List is: "+names);
        present = findViewById(R.id.b1present);
        absent = findViewById(R.id.b1absent);
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
        Intent i = new Intent(b1List.this,b1a.class);
        i.putExtra("names",names);
        i.putExtra("divs",divs);
        startActivity(i);
    }

    void showlist()
    {
        Intent i = new Intent(b1List.this,b1p.class);
        i.putExtra("names",names);
        i.putExtra("divs",divs);
        startActivity(i);
    }
}
