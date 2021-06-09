package com.kolosov.samsung.school.congratulations.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kolosov.samsung.school.congratulations.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> arrayList;
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> arrayList , Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row , parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
       return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = arrayList.get(position);
        holder.cardTextView.setText(s);
        holder.cardTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchResultActivity.class);
                intent.putExtra("flag" , "2");
                intent.putExtra("text", s);
                context.startActivity(intent);
                Toast.makeText(context, s , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cardTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cardTextView = itemView.findViewById(R.id.card_text_view);
        }
    }
}
