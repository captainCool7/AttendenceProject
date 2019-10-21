package com.captaincool.attendenceproject;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Rapp";
    private BottomNavigationView mBottomNavigationView;
    private updateFrag updateFrag;
    private addFrag addFrag;
    private showFrag showFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseUser user = ParseUser.getCurrentUser();
        if(user == null)
        {
            startActivity(new Intent(this,Login.class));
        }
        Log.d("Rapp","User is:"+user.getUsername());
        mBottomNavigationView = findViewById(R.id.bnav);
        updateFrag = new updateFrag();
        addFrag = new addFrag();
        showFrag = new showFrag();
        Log.e(TAG, "working");
        setFragment(updateFrag);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frag_con, new updateFrag()).commit();
        Log.e(TAG, "working1");
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.add:
                        setFragment(addFrag);
                        return true;
                    case R.id.update:
                        setFragment(updateFrag);
                        return true;
                    case R.id.show:
                        setFragment(showFrag);
                        return true;
                        default:
                            return false;
                }
            }
        });
        Log.e(TAG, "working3");

    }
    private  void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frag_con,fragment);
        fragmentTransaction.commit();
    }
}

