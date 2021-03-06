package com.kolosov.samsung.school.congratulations.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.Congratulation;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;
import com.kolosov.samsung.school.congratulations.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
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

        init();
        loadMenu();
    }

    private void init(){

        //initialization
        countDescriptionTextView = findViewById(R.id.result_counter);
        backImageButton = findViewById(R.id.back_description_arrow_image_button);
        forwardImageButton = findViewById(R.id.forward_description_arrow_image_button);
        resultText = findViewById(R.id.calendar_result_text);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        searchButton = findViewById(R.id.search_button_on_calendar);
        currentDatesNumber = 1;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        String firstDate = getCurrentDate();
        //initialization

        db = CongratulationDataBase.getDbInstance(this.getApplicationContext());
        List<Congratulation> congratulationsOnDate = db.congratulationDao().getAllCongratulationByDate(firstDate);
        List<String> description = new ArrayList<>();

        for (Congratulation congratulation : congratulationsOnDate) {
            description.add(congratulation.description);
        }

        //countDescriptionTextView.setText(currentDatesNumber + "/" + description.size());
        countDescriptionTextView.setText(getString(R.string.counter_text, currentDatesNumber , description.size()));

        setResultText(congratulationsOnDate , description);

        ImageButton questionButton = findViewById(R.id.question);

        questionButton.setOnClickListener(v -> {
            Toast.makeText(this, "???????????? ?????????????????? ?? ????????????????????.", Toast.LENGTH_SHORT).show();
        });

        searchButton.setOnClickListener(v -> {
            Intent intent = new Intent(CalendarActivity.this, SearchActivity.class);
            intent.putExtra("holidayName", congratulationsOnDate.get(currentDatesNumber-1).name);
            startActivity(intent);
        });

        forwardImageButton.setOnClickListener(v -> {

            if (currentDatesNumber != description.size()) {
                currentDatesNumber++;
            }
            setResultText(congratulationsOnDate , description);
            countDescriptionTextView.setText(getString(R.string.counter_text, currentDatesNumber , description.size()));

        });

        backImageButton.setOnClickListener(v -> {

            if (currentDatesNumber > 1) {
                currentDatesNumber--;
            }

            setResultText(congratulationsOnDate , description);
            countDescriptionTextView.setText(getString(R.string.counter_text, currentDatesNumber , description.size()));
        });



        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {

            try {
                date = (month + 1) + "/" + dayOfMonth + "/" + String.valueOf(year).substring(2);
                currentDatesNumber = 1;
                List<Congratulation> congratulationsOnDate1 = db.congratulationDao().getAllCongratulationByDate(date);
                List<String> description1 = new ArrayList<>();

                for (Congratulation congratulation : congratulationsOnDate1) {
                    description1.add(congratulation.description);
                }

                countDescriptionTextView.setText(getString(R.string.counter_text, currentDatesNumber , description1.size()));
                setResultText(congratulationsOnDate1 , description1);

                forwardImageButton.setOnClickListener(v -> {

                    if (currentDatesNumber != description1.size()) {
                        currentDatesNumber++;
                    }
                    setResultText(congratulationsOnDate1 , description1);
                    countDescriptionTextView.setText(getString(R.string.counter_text, currentDatesNumber , description1.size()));
                });

                backImageButton.setOnClickListener(v -> {

                    if (currentDatesNumber > 1) {
                        currentDatesNumber--;
                    }

                    setResultText(congratulationsOnDate1 , description1);
                    countDescriptionTextView.setText(getString(R.string.counter_text, currentDatesNumber , description1.size()));
                });

                searchButton.setOnClickListener(v -> {
                    Intent intent = new Intent(CalendarActivity.this, SearchActivity.class);
                    intent.putExtra("holidayName", congratulationsOnDate1.get(currentDatesNumber-1).name);
                    startActivity(intent);
                });

            } catch (Exception e) {
                resultText.setText("???????????????? ???? ????????????");
            }

        });

    }

    private void setResultText(List<Congratulation> congratulationsOnDate , List<String> description){

        String[] dates = congratulationsOnDate.get(currentDatesNumber - 1).date.split("/");
        String date = dates[1] + "." + dates[0] + "." + dates[2];
        int sizeStartIndex =  congratulationsOnDate.get(currentDatesNumber - 1).date.length();
        int sizeEndIndex = congratulationsOnDate.get(currentDatesNumber - 1).name.length()+ 1 + congratulationsOnDate.get(currentDatesNumber - 1).date.length();
        int colorEndIndex = congratulationsOnDate.get(currentDatesNumber - 1).date.length();
        SpannableString ss =  new SpannableString(date + "\n"+ congratulationsOnDate.get(currentDatesNumber - 1).name + "\n" + description.get(currentDatesNumber - 1));
        ss.setSpan(new RelativeSizeSpan(1.5f), sizeStartIndex , sizeEndIndex , Spanned.SPAN_EXCLUSIVE_INCLUSIVE); // ?????????????????????????? ????????????
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.primary_2)), 0, colorEndIndex, Spanned.SPAN_EXCLUSIVE_INCLUSIVE); // ?????????????????????????? ????????
        resultText.setText(ss);
    }

    private void loadMenu() {
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {


                case R.id.search:
                    Intent intent = new Intent(CalendarActivity.this, SearchActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    break;


                case R.id.home:
                    Intent intent1 = new Intent(CalendarActivity.this, HomeActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    break;
                case R.id.favorite:
                    Intent intent2 = new Intent(CalendarActivity.this, FavoriteActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    break;
            }

            return false;
        });
    }


    private String getCurrentDate (){
        // ?????????????? ??????????
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("M/d/yy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

}