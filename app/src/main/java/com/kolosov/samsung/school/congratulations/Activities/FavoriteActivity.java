package com.kolosov.samsung.school.congratulations.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kolosov.samsung.school.congratulations.R;
import com.kolosov.samsung.school.congratulations.Utils.RecyclerViewAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private ImageButton stylusButton;
    private TextView favoriteDescriptionTextView;
    private Button favoriteFoundButton;
    private Button favoriteToMainButton;

    public static void setFavorites(ArrayList<String> favorites) {
        FavoriteActivity.favorites = favorites;
    }

    private static ArrayList<String> favorites;

    public static ArrayList<String> getFavorites() {
        return favorites;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        init();

        loadDate();

        loadMenu();

    }

    private void init(){
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        stylusButton = findViewById(R.id.stylus_button);
        favoriteDescriptionTextView = findViewById(R.id.favorite_description_text_view_2);
        favoriteToMainButton = findViewById(R.id.result_counter);
        favoriteFoundButton = findViewById(R.id.favorite_found_button);

        favoriteToMainButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(FavoriteActivity.this , HomeActivity.class);
            startActivity(intent2);

        });

        favoriteFoundButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteActivity.this , SearchActivity.class);
            startActivity(intent);
        });
    }

    private void loadMenu(){
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.search:
                    Intent intent = new Intent(FavoriteActivity.this , SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    break;

                case R.id.calendar:
                    Intent intent1 = new Intent(FavoriteActivity.this , CalendarActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    break;
                case R.id.home:
                    Intent intent2 = new Intent(FavoriteActivity.this , HomeActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    break;
            }

            return false;
        });
    }

    private void loadDate() {

        SharedPreferences sharedPreferences = getSharedPreferences("favorites" , MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("list" , null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        favorites = gson.fromJson(json, type);

        if(favorites == null){
            stylusButton.setVisibility(View.VISIBLE);
            favoriteToMainButton.setVisibility(View.VISIBLE);
            favoriteFoundButton.setVisibility(View.VISIBLE);

        }else {
            RecyclerView recyclerView = findViewById(R.id.recycle_view);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            RecyclerView.Adapter adapter = new RecyclerViewAdapter(favorites, FavoriteActivity.this);

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

    }
}