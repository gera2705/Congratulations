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
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
    private TextView bigDateTextView;

    private Button smallButton1;
    private Button smallButton2;
    private Button smallButton3;
    private Button smallButton4;

    private String currentDate;

    private static final String[] month = {"января" , "февраля" , "марта" , "апреля" , "мая"
            , "июня" , "июля" , "августа" , "сентября" , "октября" , "ноября" , "декабря" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        smallButton1 = findViewById(R.id.small_button_1);
        smallButton2 = findViewById(R.id.small_button_2);
        smallButton3 = findViewById(R.id.small_button_3);
        smallButton4 = findViewById(R.id.small_button_4);
        currentDate = getCurrentDate();
        date = findViewById(R.id.date);
        name = findViewById(R.id.name);
        bigDateTextView = findViewById(R.id.bigDateTextView);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        foundBlackButton = findViewById(R.id.home_black_found_button);

        try {

            Congratulation congratulation = db.congratulationDao().getCongratulationByDate(currentDate); //currentDate
            name.setText(congratulation.name);
        }catch (NullPointerException e){
            name.setText("Сегодня нет никакого праздника!\nДобавьте свой!");
        }

        smallButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                intent.putExtra("holidayName", smallButton1.getText());
                startActivity(intent);
            }
        });

        smallButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                intent.putExtra("holidayName", smallButton2.getText());
                startActivity(intent);
            }
        });

        smallButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                intent.putExtra("holidayName", smallButton3.getText());
                startActivity(intent);
            }
        });

        smallButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                intent.putExtra("holidayName", smallButton4.getText());
                startActivity(intent);
            }
        });


        foundBlackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                intent.putExtra("holidayName", name.getText());
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




//        String[] dateArray = currentDate.split("\\.");
//        date.setText("Сегодня, " + dateArray[2] + " " + getMonth(Integer.parseInt(dateArray[1])));
//        bigDateTextView.setText(dateArray[2] + "\n" + dateArray[1]);



        String[] dateArray = currentDate.split("/");

        showDate(dateArray);

        //String date = dateArray[2] + "." + dateArray[1] + "." + dateArray[0];




    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }



    void showDate(String[] dateArray){

        if (Integer.parseInt(dateArray[1]) < 10) {
            dateArray[1] = "0" + dateArray[1];
        }

        if (Integer.parseInt(dateArray[0]) < 10) {
            dateArray[0] = "0" + dateArray[0];
        }

        date.setText("Сегодня, " + dateArray[1] + " " + getMonth(Integer.parseInt(dateArray[0])));
        bigDateTextView.setText(dateArray[1] + "\n" + dateArray[0]);
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

    public String getMonth(int monthNumber){
        return month[monthNumber-1];
    }

    public String getCurrentDate (){
        // Текущее время
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("M/d/yy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }
}