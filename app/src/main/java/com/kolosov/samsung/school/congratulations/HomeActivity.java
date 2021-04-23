package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageButton addButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);


        addButton = findViewById(R.id.right_button);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this , AddActivity.class);
            startActivity(intent);
        });



        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.search:
                        Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.calendar:
                        Intent intent1 = new Intent(HomeActivity.this , CalendarActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.favorite:
                        Intent intent2 = new Intent(HomeActivity.this , FavoriteActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });


    }
}