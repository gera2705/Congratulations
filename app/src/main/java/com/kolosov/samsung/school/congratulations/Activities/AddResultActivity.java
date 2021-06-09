package com.kolosov.samsung.school.congratulations.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.R;

public class AddResultActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        init();

        loadMenu();
    }

    private void init(){
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        Button toHomeButton = findViewById(R.id.favorite_found_button);
        Button addStillButton = findViewById(R.id.result_counter);
        ImageButton stylusButton = findViewById(R.id.stylus_button);

        toHomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddResultActivity.this , HomeActivity.class);
            startActivity(intent);
        });

        addStillButton.setOnClickListener(v -> finish());

        stylusButton.setOnClickListener(v -> finish());
    }

    private void loadMenu(){

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){


                case R.id.home:
                    Intent intent = new Intent(AddResultActivity.this , HomeActivity.class);
                    startActivity(intent);
                    break;


                case R.id.calendar:
                    Intent intent1 = new Intent(AddResultActivity.this , CalendarActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.favorite:
                    Intent intent2 = new Intent(AddResultActivity.this , FavoriteActivity.class);
                    startActivity(intent2);
                    break;

                case R.id.search:
                    Intent intent3 = new Intent(AddResultActivity.this , SearchActivity.class);
                    startActivity(intent3);
                    break;

            }

            return false;
        });
    }
}