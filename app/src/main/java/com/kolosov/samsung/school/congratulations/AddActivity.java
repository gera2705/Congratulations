package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddActivity extends AppCompatActivity {

    private TextView calendarSpinner;
    private Dialog dialog;
    private CalendarView calendarView;
    private BottomNavigationView bottomNavigationView;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        calendarSpinner = findViewById(R.id.calendar_spinner);

        addButton = findViewById(R.id.count_description_text_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this , AddResultActivity.class);
                startActivity(intent);
            }
        });




        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        loadCalendarSpinner();
        loadMenu();

    }

    void loadCalendarSpinner(){
        calendarSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog = new Dialog(AddActivity.this);

                dialog.setContentView(R.layout.dialog_calendar_spinner);

                dialog.getWindow().setLayout(1000 , 1200);

                dialog.show();


                calendarView = findViewById(R.id.c);

//                calendarView = (CalendarView) findViewById(R.id.calendar);
//
//
//
//                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//                    @Override
//                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//
//                        String date = year + "."  + (month + 1) + "." + dayOfMonth;
//
////               resultText.setText(date);
//
//                        try {
//                           calendarSpinner.setText(date);
//                        }catch (NullPointerException e){
//                           calendarSpinner.setText("e");
//                        }
//
//
//
//
//                    }
//                });




            }
        });


    }


    void loadMenu(){


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.home:
                        Intent intent = new Intent(AddActivity.this , HomeActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.calendar:
                        Intent intent1 = new Intent(AddActivity.this , CalendarActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.favorite:
                        Intent intent2 = new Intent(AddActivity.this , FavoriteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.search:
                        Intent intent3 = new Intent(AddActivity.this , SearchActivity.class);
                        startActivity(intent3);
                        break;

                }

                return false;
            }
        });
    }
}