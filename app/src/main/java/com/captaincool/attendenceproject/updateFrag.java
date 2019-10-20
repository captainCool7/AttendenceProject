package com.captaincool.attendenceproject;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class updateFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("batchid",1);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject player, ParseException e) {
                if (e == null) {
                    String name = player.getString("studname");
                    int std = player.getInt("standard");
                    String div =  player.getString("division");
                    Log.d("Rapp","Value is: "+name+"dob "+std+"div "+div);
                } else {
                    e.printStackTrace();
                    Log.d("Rapp","Error is: "+e);
                    // Something is wrong
                }
            }
        });
        return view;
    }

}
