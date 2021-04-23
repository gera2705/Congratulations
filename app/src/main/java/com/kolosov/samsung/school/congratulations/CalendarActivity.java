package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CalendarActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private CalendarView calendarView;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.search:
                        Intent intent = new Intent(CalendarActivity.this , SearchActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.home:
                        Intent intent1 = new Intent(CalendarActivity.this , HomeActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.favorite:
                        Intent intent2 = new Intent(CalendarActivity.this , FavoriteActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });




        resultText = findViewById(R.id.calendar_result_text);


//        calendarView = (CalendarView) findViewById(R.id.calendar);

//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                Toast.makeText(CalendarActivity.this , "ok" , Toast.LENGTH_LONG).show();
//            }
//        });
    }
}