package com.kolosov.samsung.school.congratulations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private Button searchButton;

    private CongratulationDataBase db;

    private String date;
    private static int currentDatesNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //initialization
        countDescriptionTextView = findViewById(R.id.add_still_button);
        backImageButton = findViewById(R.id.back_description_arrow_image_button);
        forwardImageButton = findViewById(R.id.forward_description_arrow_image_button);
        resultText = findViewById(R.id.calendar_result_text);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        searchButton = findViewById(R.id.search_button_on_calendar);
        currentDatesNumber = 1;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        String firstDate = getCurrentDate();
        //initialization



        loadMenu();

        db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        List<Congratulation> congratulationsOnDate = db.congratulationDao().getAllCongratulationByDate(firstDate);

        List<String> description = new ArrayList<>();

        for (Congratulation congratulation : congratulationsOnDate) {
            description.add(congratulation.description);
        }

        countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());


        setResultText(congratulationsOnDate , description);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, SearchActivity.class);
                intent.putExtra("holidayName", congratulationsOnDate.get(currentDatesNumber-1).name);
                Toast.makeText(CalendarActivity.this, congratulationsOnDate.get(currentDatesNumber-1).name , Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        forwardImageButton.setOnClickListener(v -> {

            if (currentDatesNumber != description.size()) {
                currentDatesNumber++;
            }

            setResultText(congratulationsOnDate , description);
            //resultText.setText(congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" + description.get(currentDatesNumber - 1));

            countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());


        });

        backImageButton.setOnClickListener(v -> {

            if (currentDatesNumber > 1) {
                currentDatesNumber--;
            }

            setResultText(congratulationsOnDate , description);
            //resultText.setText(congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" +description.get(currentDatesNumber - 1));

            countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());
        });


        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            try {

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


                List<Congratulation> congratulationsOnDate1 = db.congratulationDao().getAllCongratulationByDate(date);


                List<String> description1 = new ArrayList<>();

                for (Congratulation congratulation : congratulationsOnDate1) {
                    description1.add(congratulation.description);
                }

                countDescriptionTextView.setText(currentDatesNumber + "/" + description1.size());

                setResultText(congratulationsOnDate1 , description1);

//                resultText.setText(congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" + description1.get(0));
//                resultText.setTextColor(getResources().getColor(R.color.primary_2));

                forwardImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (currentDatesNumber != description1.size()) {
                            currentDatesNumber++;
                        }
                        setResultText(congratulationsOnDate1 , description1);
                       // resultText.setText(congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" + description1.get(currentDatesNumber - 1));

                        countDescriptionTextView.setText(currentDatesNumber + "/" + description1.size());


                    }
                });

                backImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (currentDatesNumber > 1) {
                            currentDatesNumber--;
                        }

                        setResultText(congratulationsOnDate1 , description1);
//                        resultText.setText(congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" + description1.get(currentDatesNumber - 1));

                        countDescriptionTextView.setText(currentDatesNumber + "/" + description1.size());
                    }
                });

                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CalendarActivity.this, SearchActivity.class);
                        intent.putExtra("holidayName", congratulationsOnDate1.get(currentDatesNumber-1).name);
                        Toast.makeText(CalendarActivity.this, congratulationsOnDate1.get(currentDatesNumber-1).name , Toast.LENGTH_SHORT).show();
                         startActivity(intent);
                    }
                });


            } catch (Exception e) {
                resultText.setText("Праздник не найден");
            }


        });

    }

    void setResultText(List<Congratulation> congratulationsOnDate , List<String> description){

        int sizeStartIndex =  congratulationsOnDate.get(currentDatesNumber - 1).date.length();
        int sizeEndIndex = congratulationsOnDate.get(currentDatesNumber - 1).name.length()+ 1 + congratulationsOnDate.get(currentDatesNumber - 1).date.length();
        int colorEndIndex = congratulationsOnDate.get(currentDatesNumber - 1).date.length();
        SpannableString ss =  new SpannableString(congratulationsOnDate.get(currentDatesNumber - 1).date + "\n"+ congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" + description.get(0));
        ss.setSpan(new RelativeSizeSpan(1.5f), sizeStartIndex , sizeEndIndex , Spanned.SPAN_EXCLUSIVE_INCLUSIVE); // устанавливаем размер
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary_2)), 0, colorEndIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE); // устанавливаем цвет

        resultText.setText(ss);
    }

    void loadMenu() {
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {


                case R.id.search:
                    Intent intent = new Intent(CalendarActivity.this, SearchActivity.class);
                    startActivity(intent);
                    break;


                case R.id.home:
                    Intent intent1 = new Intent(CalendarActivity.this, HomeActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.favorite:
                    Intent intent2 = new Intent(CalendarActivity.this, FavoriteActivity.class);
                    startActivity(intent2);
                    break;
            }

            return false;
        });
    }


    public String getCurrentDate() {
        // Текущее время
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

}