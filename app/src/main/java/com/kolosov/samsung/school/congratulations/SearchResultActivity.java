package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchResultActivity extends AppCompatActivity {

    private TextView congratulation;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        congratulation = findViewById(R.id.congratulation_text);

        congratulation.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sapien adipiscing condimentum sed sem leo morbi tellus orci. Feugiat a, orci mauris id egestas mi.Nec leo quam vel magna cursus tortor nunc tincidunt. Mauris arcu in eu nam natoque. Vel augue nunc nisi turpis eget quis. Mattis sed felis nisi, pellentesque mauris, cursus amet sagittis. Sed morbi cras feugiat facilisi egestas fringilla. Vitae fusce et nulla amet, felis. At in imperdiet nascetur eu, enim vulputate et.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sapien adipiscing condimentum sed sem leo morbi tellus orci. Feugiat a, orci mauris id egestas mi. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sapien adipiscing condimentum sed sem leo morbi tellus orci. Feugiat a, orci mauris id egestas mi.\n" +
                "Nec leo quam vel magna cursus tortor nunc tincidunt. Mauris arcu in eu nam natoque. Vel augue nunc nisi turpis eget quis. Mattis sed felis nisi, pellentesque mauris, cursus amet sagittis. Sed morbi cras feugiat facilisi egestas fringilla. Vitae fusce et nulla amet, felis. At in imperdiet nascetur eu, enim vulputate et.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sapien adipiscing condimentum sed sem leo morbi tellus orci. Feugiat a, orci mauris id egestas mi.");

        congratulation.setMovementMethod(new ScrollingMovementMethod());

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);


        loadMenu();
    }

    void loadMenu(){
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.home:
                        Intent intent = new Intent(SearchResultActivity.this , HomeActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.calendar:
                        Intent intent1 = new Intent(SearchResultActivity.this , CalendarActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.favorite:
                        Intent intent2 = new Intent(SearchResultActivity.this , FavoriteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.search:
                        Intent intent4 = new Intent(SearchResultActivity.this , SearchActivity.class);
                        startActivity(intent4);
                        break;

                }

                return false;
            }
        });
    }
}