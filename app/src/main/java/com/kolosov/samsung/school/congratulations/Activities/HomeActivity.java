package com.kolosov.samsung.school.congratulations.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.Congratulation;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;
import com.kolosov.samsung.school.congratulations.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private String date;
//    private TextView name;
    private String bigDate;

    private Button smallButton1;
    private Button smallButton2;
    private Button smallButton3;
    private Button smallButton4;

    private String currentDate;
    SliderView sliderView;
    List<String> strings ;


    private static final String[] month = {"января" , "февраля" , "марта" , "апреля" , "мая"
            , "июня" , "июля" , "августа" , "сентября" , "октября" , "ноября" , "декабря" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        init();

        sliderView = findViewById(R.id.image_slider);


//        strings.add("1");
//        strings.add("2");
//        strings.add("3");

        SliderAdapter sliderAdapter = new SliderAdapter((ArrayList<String>) strings , date , bigDate, this);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();


//        Button foundBlackButton = findViewById(R.id.home_black_found_button);
//
//        foundBlackButton.setOnClickListener(v -> {
//            Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
////            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
////            intent.putExtra("holidayName", name.getText());
////            startActivity(intent);
//
//        });


        loadMenu();





    }

    private void init(){
        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());



        smallButton1 = findViewById(R.id.small_button_1);
        smallButton2 = findViewById(R.id.small_button_2);
        smallButton3 = findViewById(R.id.small_button_3);
        smallButton4 = findViewById(R.id.small_button_4);
        currentDate = getCurrentDate();
//        date = findViewById(R.id.date);
//        name = findViewById(R.id.name);
//        bigDateTextView = findViewById(R.id.bigDateTextView);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
//        Button foundBlackButton = findViewById(R.id.home_black_found_button);
        ImageButton addButton = findViewById(R.id.right_button);
        ImageButton foundButton = findViewById(R.id.left_button);

        String[] dateArray = currentDate.split("/");

        showDate(dateArray);

        try {

//            Congratulation congratulation = db.congratulationDao().getCongratulationByDate(currentDate); //currentDate
//            name.setText(congratulation.name);
           strings = db.congratulationDao().getAllCongratulationNameByDate(currentDate);
//            name.setText(congratulations.get(0).name);
            //Log.d("LIST" , String.valueOf(congratulations));
        }catch (NullPointerException e){
//            name.setText("Сегодня нет никакого праздника!\nДобавьте свой!");
        }

        ImageButton questionButton = findViewById(R.id.question);

        questionButton.setOnClickListener(v -> {
            Toast.makeText(this, "Раздел находится в разработке.", Toast.LENGTH_SHORT).show();
        });

        smallButton1.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
            intent.putExtra("holidayName", smallButton1.getText());
            startActivity(intent);
        });

        smallButton2.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
            intent.putExtra("holidayName", smallButton2.getText());
            startActivity(intent);
        });

        smallButton3.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
            intent.putExtra("holidayName", smallButton3.getText());
            startActivity(intent);
        });

        smallButton4.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
            intent.putExtra("holidayName", smallButton4.getText());
            startActivity(intent);
        });

//        foundBlackButton.setOnClickListener(v -> {
//            Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
////            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
////            intent.putExtra("holidayName", name.getText());
////            startActivity(intent);
//
//        });

        foundButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this , AddActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }


    private void showDate(String[] dateArray){

        if (Integer.parseInt(dateArray[1]) < 10) {
            dateArray[1] = "0" + dateArray[1];
        }

        if (Integer.parseInt(dateArray[0]) < 10) {
            dateArray[0] = "0" + dateArray[0];
        }

        date = getString(R.string.date, dateArray[1], getMonth(Integer.parseInt(dateArray[0])));
         bigDate = getString(R.string.big_date, dateArray[1] , dateArray[0]);



    }

    void loadMenu(){

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.search:
                    Intent intent = new Intent(HomeActivity.this , SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    break;

                case R.id.calendar:
                    Intent intent1 = new Intent(HomeActivity.this , CalendarActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    break;
                case R.id.favorite:
                    Intent intent2 = new Intent(HomeActivity.this , FavoriteActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    break;
            }

            return false;
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