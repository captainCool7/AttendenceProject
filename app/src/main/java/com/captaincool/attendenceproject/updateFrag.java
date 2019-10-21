package com.captaincool.attendenceproject;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class updateFrag extends Fragment {
     private static final String TAG = "Rapp";
    private mydb db;
    private ArrayList<ExampleItem> names;
    private ArrayList<String> n;
    private RecyclerView rec;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);
        Log.d(TAG, "User is: "+ ParseUser.getCurrentUser().getUsername());
        rec= view.findViewById(R.id.rec_view);
        createList();
        buildRecyclerView();
        return view;
    }
    void createList()
    {
        names = new ArrayList<ExampleItem>();
        db = new mydb(getContext());
        db.createdb();
        names = db.show();
        n = db.getName();
    }

    public void buildRecyclerView()
    {
        rec.setHasFixedSize(true);
        mAdapter = new ExampleAdapter(names);
        mLayoutManager = new LinearLayoutManager(getContext());
        rec.setAdapter(mAdapter);
        rec.setLayoutManager(mLayoutManager);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onAbsentClick(int position) {
                Log.i("Rapp","Here"+n.get(position));
                Toast.makeText(getContext(),"name is: "+n.get(position),Toast.LENGTH_SHORT).show();
                db.markAbsent(n.get(position));
            }

            @Override
            public void onPresentClick(int position)
            {
                Log.i("Rapp","Here"+n.get(position));
                Toast.makeText(getContext(),"name is: "+n.get(position),Toast.LENGTH_SHORT).show();
                db.markPresent(n.get(position));
            }
        });

    }
}
