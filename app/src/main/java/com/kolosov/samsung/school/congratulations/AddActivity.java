package com.kolosov.samsung.school.congratulations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    private TextView calendarSpinner;
    private BottomNavigationView bottomNavigationView;
    private Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        calendarSpinner = findViewById(R.id.calendar_spinner);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        addButton = findViewById(R.id.add_still_button);

        addButton.setOnClickListener(v -> {


            Intent intent = new Intent(AddActivity.this , AddResultActivity.class);
            startActivity(intent);
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        calendarSpinner.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddActivity.this , R.style.DialogTheme, (view, year1, month1, dayOfMonth) -> {
                        month1 = month1 +1;
                        String date = dayOfMonth + "." + month1 + "." + year1;
                        calendarSpinner.setText(date);
                    },year,month,day);

            datePickerDialog.show();

        });



        loadMenu();

    }



    void loadMenu(){


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
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
        });
    }
}