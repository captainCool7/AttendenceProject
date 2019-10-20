package com.captaincool.attendenceproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<items> mExampleList;
    private AdapterView.OnItemClickListener mListener;

    @NonNull
    @Override
    public ExampleAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.ExampleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    
    public ExampleAdapter(ArrayList<items> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        items currentItem = mExampleList.get(position);

        holder..setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

