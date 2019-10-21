package com.captaincool.attendenceproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>{
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onAbsentClick(int position);
        void onPresentClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ExampleAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        holder.mTextView.setText(currentItem.getTx());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList)
    {
        mExampleList = exampleList;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageButton abtn,pbtn;

        public ExampleViewHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.nameView);
            abtn = (ImageButton) itemView.findViewById(R.id.abtn);
            pbtn = (ImageButton) itemView.findViewById(R.id.pbtn);
            abtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                    {
                        int position= getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onAbsentClick(position);
                        }
                    }
                }
            });
            pbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onPresentClick(position);
                        }
                    }
                }
            });
        }
    }
}
