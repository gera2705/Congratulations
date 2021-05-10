package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.Congratulation;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private CalendarView calendarView;
    private TextView resultText;

    private TextView countDescriptionTextView;
    private ImageButton backImageButton;
    private ImageButton forwardImageButton;

    String date ;
    private static int currentDatesNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        countDescriptionTextView = findViewById(R.id.count_description_text_view);
        backImageButton = findViewById(R.id.back_description_arrow_image_button);
        forwardImageButton = findViewById(R.id.forward_description_arrow_image_button);
        resultText = findViewById(R.id.calendar_result_text);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        currentDatesNumber = 1;

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);


        loadMenu();

        String firstDate = getCurrentDate();

       // Log.d("TIME" , timeStamp);

        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        List<Congratulation> congratulationsOnDate = db.congratulationDao().getAllCongratulationByDate(firstDate);

        List<String> description = new ArrayList<>();

        for (Congratulation congratulation : congratulationsOnDate){
            description.add(congratulation.description);
        }

        countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());


        resultText.setText(description.get(0));

        forwardImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentDatesNumber != description.size()) {
                    currentDatesNumber++;
                }
                resultText.setText(description.get(currentDatesNumber - 1));

                countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());


            }
        });

        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentDatesNumber > 1) {
                    currentDatesNumber--;
                }

                resultText.setText(description.get(currentDatesNumber - 1));

                countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());
            }
        });






        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                try {

//                    Congratulation congratulation = db.congratulationDao().getCongratulationByDate(date);
//                    resultText.setText(congratulation.description);

                    String day = String.valueOf(dayOfMonth);
                    String m = String.valueOf(month);

                    if (dayOfMonth < 10) {
                        day = "0" + dayOfMonth;
                    }

                    if ((month + 1) < 10) {
                        m = "0" + (month + 1);
                    }

                    date = day + "." + m + "." + year;

                    currentDatesNumber = 1;


                    List<Congratulation> congratulationsOnDate = db.congratulationDao().getAllCongratulationByDate(date);


                    List<String> description = new ArrayList<>();

                    for (Congratulation congratulation : congratulationsOnDate) {
                        description.add(congratulation.description);
                    }

                    countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());

                    resultText.setText(description.get(0));

                    forwardImageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (currentDatesNumber != description.size()) {
                                currentDatesNumber++;
                            }
                            resultText.setText(description.get(currentDatesNumber - 1));

                            countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());


                        }
                    });

                    backImageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (currentDatesNumber > 1) {
                                currentDatesNumber--;
                            }

                            resultText.setText(description.get(currentDatesNumber - 1));

                            countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());
                        }
                    });

                } catch (Exception e) {
                    resultText.setText("Праздник не найден");
                }


            }
        });

    }

        void loadMenu(){
            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.getItem(2);
            menuItem.setChecked(true);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){


                        case R.id.search:
                            Intent intent = new Intent(CalendarActivity.this , SearchActivity.class);
                            startActivity(intent);
                            break;


                        case R.id.home:
                            Intent intent1 = new Intent(CalendarActivity.this , HomeActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.favorite:
                            Intent intent2 = new Intent(CalendarActivity.this , FavoriteActivity.class);
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
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

}