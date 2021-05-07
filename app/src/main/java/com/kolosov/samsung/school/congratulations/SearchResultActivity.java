package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SearchResultActivity extends AppCompatActivity {

    private TextView congratulation;
    private BottomNavigationView bottomNavigationView;

    ClipboardManager clipboardManager;
    ClipData clipData;
    ImageButton copyButton;
    TextView congText;

    ImageButton shareButton;

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


        copyButton = findViewById(R.id.button_copy);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("your_text_to_be_copied" , congratulation.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(SearchResultActivity.this ,"Поздравление скопировано",Toast.LENGTH_SHORT).show();
            }
        });

        shareButton = findViewById(R.id.button_share);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, congratulation.getText().toString());
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
                startActivity(Intent.createChooser(sharingIntent, "Share using"));

            }
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