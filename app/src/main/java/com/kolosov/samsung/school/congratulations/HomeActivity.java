package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.Congratulation;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageButton addButton;
    private ImageButton foundButton;

    private Button foundBlackButton;

    private TextView date;
    private TextView name;

    private String currentDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        currentDate = getCurrentDate();

        date = findViewById(R.id.date);

        name = findViewById(R.id.name);

        Log.d("MY_DATE" , currentDate);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        foundBlackButton = findViewById(R.id.home_black_found_button);

        foundBlackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                startActivity(intent);
            }
        });

        foundButton = findViewById(R.id.left_button);

        foundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                startActivity(intent);
            }
        });


        addButton = findViewById(R.id.right_button);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this , AddActivity.class);
            startActivity(intent);
        });



        loadMenu();

        date.setText("Сегодня, " + currentDate);

        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        Congratulation congratulation = db.congratulationDao().getCongratulationByDate("2021.04.30"); //currentDate

//        if(congratulation == null){
//            String[] date = currentDate.split("\\.");
//            String day = date[2];
//            int iDay = Integer.parseInt(day);
//            iDay += 1;
//            String newDate = (date[0]+"."+date[1]+"."+iDay);
//            congratulation = db.congratulationDao().getCongratulationByDate(newDate);
//        }

        name.setText(congratulation.name);



    }

    void loadMenu(){


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

    public String getCurrentDate (){
        // Текущее время
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        return dateFormat.format(currentDate);
    }
}