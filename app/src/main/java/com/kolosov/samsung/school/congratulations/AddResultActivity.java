package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddResultActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button toHomeButton;
    private Button addStillButton;
    private ImageButton stylusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        toHomeButton = findViewById(R.id.favorite_found_button);
        addStillButton = findViewById(R.id.favorite_to_main_button);
        stylusButton = findViewById(R.id.stylus_button);

        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddResultActivity.this , HomeActivity.class);
                startActivity(intent);
            }
        });

        addStillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });

        stylusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        loadMenu();
    }

    void loadMenu(){


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }
}