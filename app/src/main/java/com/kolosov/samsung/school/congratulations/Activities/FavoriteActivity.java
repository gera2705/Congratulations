package com.kolosov.samsung.school.congratulations.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.R;

public class FavoriteActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Button toHomeButton;
    private Button foundButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        toHomeButton = findViewById(R.id.favorite_to_main_button);
        foundButton = findViewById(R.id.favorite_found_button);

        toHomeButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(FavoriteActivity.this , HomeActivity.class);
            startActivity(intent2);

        });

        foundButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteActivity.this , SearchActivity.class);
            startActivity(intent);
        });

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){


                case R.id.search:
                    Intent intent = new Intent(FavoriteActivity.this , SearchActivity.class);
                    startActivity(intent);
                    break;


                case R.id.calendar:
                    Intent intent1 = new Intent(FavoriteActivity.this , CalendarActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.home:
                    Intent intent2 = new Intent(FavoriteActivity.this , HomeActivity.class);
                    startActivity(intent2);
                    break;
            }

            return false;
        });
    }
}