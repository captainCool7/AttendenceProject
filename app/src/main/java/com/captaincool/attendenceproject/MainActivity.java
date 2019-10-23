package com.captaincool.attendenceproject;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Rapp";
    public ArrayList<String> nameList,divList,nlist,plist,alist;
    Button show,sbyd;

    private ImageButton b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseUser user = ParseUser.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, Login.class));
        }
        nameList = new ArrayList<>();
        divList = new ArrayList<>();
        nlist = new ArrayList<>();
        plist = new ArrayList<>();
        alist = new ArrayList<>();
        b1= findViewById(R.id.b1);
        b2= findViewById(R.id.b2);
        sbyd = findViewById(R.id.sbyd);
        show = findViewById(R.id.show);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchB1();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchB2();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });
        sbyd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,dateChoose.class));
            }
        });
    }

    private void showData() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Present");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null)
                {
                    if(objects.size()>0)
                    {
                        for(ParseObject obj : objects)
                        {
                            Log.d(TAG,"Name is: "+obj.getString("studname"));
                            Log.d(TAG,"Name is: "+obj.getNumber("Present"));
                            Log.d(TAG,"Name is: "+obj.getNumber("Absent"));
                            nlist.add(obj.getString("studname"));
                            plist.add(obj.getNumber("Present").toString());
                            alist.add(obj.getNumber("Absent").toString());
                        }
                    }
                }
                Intent i = new Intent(MainActivity.this,b1showData.class);
                i.putExtra("nlist",nlist);
                i.putExtra("plist",plist);
                i.putExtra("alist",alist);
                startActivity(i);
            }
        });
    }

    private void searchB2() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("batchid",2);
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
                            Log.d(TAG, "Division is: "+obj.getString("division"));
                            nameList.add(obj.getString("studname"));
                            divList.add(obj.getString("division"));
                        }
                    }
                }
                Intent i = new Intent(MainActivity.this,b2List.class);
                i.putExtra("names",nameList);
                i.putExtra("divs",divList);
                startActivity(i);
            }
        });
    }

    private void searchB1() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("batchid",1);
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
                            Log.d(TAG, "Division is: "+obj.getString("division"));
                            nameList.add(obj.getString("studname"));
                            divList.add(obj.getString("division"));
                        }
                    }
                }
                Intent i = new Intent(MainActivity.this,b1List.class);
                i.putExtra("names",nameList);
                i.putExtra("divs",divList);
                startActivity(i);
            }
        });
        }
        public void Logout(View v)
        {
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Loading.....");
            progress.show();
            ParseUser.logOut();
            Intent intent = new Intent(MainActivity.this,loginAct.class);
            startActivity(intent);
            finish();
            progress.dismiss();
        }
}

