package com.kolosov.samsung.school.congratulations.Activities;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kolosov.samsung.school.congratulations.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>{

    ArrayList<String> names;
    String bigDate;
    String date;
    Context context;



    public SliderAdapter(ArrayList<String> names , String date , String bigDate, Context context){

        this.names = names;
        this.date = date;
        this.bigDate = bigDate;
        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        if (names.get(position).length() > 55){
            viewHolder.name.setTextSize(18);
        }

        viewHolder.name.setText(names.get(position));
        viewHolder.date.setText(date);
        viewHolder.bigDateTextView.setText(bigDate);

        viewHolder.foundBlackButton.setOnClickListener(v -> {
            //Log.d("AA" , "aa");
            Intent intent = new Intent(context , SearchActivity.class);
            intent.putExtra("holidayName", names.get(position));
            context.startActivity(intent);
        });

    }



    @Override
    public int getCount() {
        return names.size();
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{


        TextView name;
        TextView date;

        TextView bigDateTextView;

        Button foundBlackButton ;

        public Holder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            bigDateTextView = itemView.findViewById(R.id.bigDateTextView);
            foundBlackButton = itemView.findViewById(R.id.home_black_found_button);

        }
    }

}
