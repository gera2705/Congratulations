package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddResultActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
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