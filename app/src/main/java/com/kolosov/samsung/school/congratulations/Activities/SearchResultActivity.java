package com.kolosov.samsung.school.congratulations.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.Congratulation;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;
import com.kolosov.samsung.school.congratulations.R;

public class SearchResultActivity extends AppCompatActivity {

    private TextView congratulation;
    private BottomNavigationView bottomNavigationView;

    private TextView smallCounterTextView;
    private TextView countTextView;

    private Button forwardArrowImageButton;
    private Button backArrowImageButton;

    private ImageButton backImageButton;

    private Button copyButton;

    private Button shareButton;

    private static int currentCongratulationNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        //initialization
        congratulation = findViewById(R.id.congratulation_text);
        smallCounterTextView = findViewById(R.id.small_count_text_view);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        copyButton = findViewById(R.id.button_copy);
        shareButton = findViewById(R.id.button_share);
        forwardArrowImageButton = findViewById(R.id.forward_arrow_image_button);
        backArrowImageButton = findViewById(R.id.back_arrow_image_button);
        countTextView = findViewById(R.id.favorite_to_main_button);
        backImageButton = findViewById(R.id.back_button);
        currentCongratulationNumber = 1;
        //initialization

        Bundle arguments = getIntent().getExtras();
        String holidayName = arguments.get("holidayName").toString();
        String humanName = arguments.get("humanName").toString();

        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        Congratulation receivedCongratulation = db.congratulationDao().getCongratulation(holidayName);

        String[] strings = receivedCongratulation.congratulationText.split("\\|");



        
        smallCounterTextView.setText("Найдено: " + strings.length);

        congratulation.setText(humanName + "!\n" + strings[currentCongratulationNumber-1]);

        countTextView.setText( currentCongratulationNumber + "/" + strings.length);
        congratulation.setMovementMethod(new ScrollingMovementMethod());


        loadMenu();


        forwardArrowImageButton.setOnClickListener(v -> {

            if(currentCongratulationNumber != strings.length) {
                currentCongratulationNumber++;
            }
            congratulation.setText(humanName + "!\n" + strings[currentCongratulationNumber - 1]);

            countTextView.setText( currentCongratulationNumber + "/" + strings.length);
        });

        backArrowImageButton.setOnClickListener(v -> {

            if(currentCongratulationNumber > 1) {
                currentCongratulationNumber--;
            }

            congratulation.setText(humanName + "!\n" + strings[currentCongratulationNumber - 1]);

            countTextView.setText( currentCongratulationNumber + "/" + strings.length);

        });

        backImageButton.setOnClickListener(v -> SearchResultActivity.this.finish());


        copyButton.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("your_text_to_be_copied" , congratulation.getText().toString());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(SearchResultActivity.this ,"Поздравление скопировано",Toast.LENGTH_SHORT).show();
        });



        shareButton.setOnClickListener(v -> {

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, congratulation.getText().toString());
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
            startActivity(Intent.createChooser(sharingIntent, "Поделиться поздравлением"));

        });


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