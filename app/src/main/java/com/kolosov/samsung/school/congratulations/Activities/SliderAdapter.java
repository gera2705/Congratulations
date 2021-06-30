package com.kolosov.samsung.school.congratulations.Activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kolosov.samsung.school.congratulations.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>{

   // int[] images;
    ArrayList<String> names;
    String bigDate;
    String date;


    public SliderAdapter(ArrayList<String> names , String date , String bigDate){

        this.names = names;
        this.date = date;
        this.bigDate = bigDate;
       // this.images = images;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        //viewHolder.imageView.setImageResource(images[position]);
        viewHolder.name.setText(names.get(position));
        viewHolder.date.setText(date);
        viewHolder.bigDateTextView.setText(bigDate);

    }

    @Override
    public int getCount() {
        return names.size();
    }

    public class Holder extends  SliderViewAdapter.ViewHolder{

        //ImageView imageView;
        TextView name;
        TextView date;

        TextView bigDateTextView;

        public Holder(View itemView){
            super(itemView);
            //imageView = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);

            bigDateTextView = itemView.findViewById(R.id.bigDateTextView);

        }
    }

}
