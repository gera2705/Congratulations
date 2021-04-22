package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextView textView2;
    private TextView textView3;
    private ArrayList<String> holidaysNames;
    private ArrayList<String> genderName;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        textView2 = findViewById(R.id.text_view_2);
        textView3 = findViewById(R.id.gender);



        holidaysNames = new ArrayList<>();

        holidaysNames.add("День рождения");
        holidaysNames.add("Новый год");
        holidaysNames.add("Пасха");
        holidaysNames.add("День защитника отечества");
        holidaysNames.add("День рождения");
        holidaysNames.add("Новый год");
        holidaysNames.add("Пасха");
        holidaysNames.add("День защитника отечества");

        genderName = new ArrayList<>();

        genderName.add("Не важно");
        genderName.add("Мужской");
        genderName.add("Женский");

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        loadMenu();

        loadNameSpinner();

        loadGenderSpinner();




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
                        Intent intent = new Intent(SearchActivity.this , HomeActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.calendar:
                        Intent intent1 = new Intent(SearchActivity.this , CalendarActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.favorite:
                        Intent intent2 = new Intent(SearchActivity.this , FavoriteActivity.class);
                        startActivity(intent2);
                        break;
                }

                return false;
            }
        });
    }

    void loadGenderSpinner(){
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog = new Dialog(SearchActivity.this);

                dialog.setContentView(R.layout.dialog_search_spinner);

                dialog.getWindow().setLayout(1000 , 1200);

//                Rect displayRectangle = new Rect();
//                dialog.getWindow().setLayout((int) ( displayRectangle.width() * 0.5f) , (int) ( displayRectangle.height() * 0.5f));

                dialog.show();

                EditText editText = dialog.findViewById(R.id.spinner_edit_text);

                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this , android.R.layout.simple_list_item_1, genderName);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        textView3.setText(adapter.getItem(position));
                        textView3.setTextColor(getResources().getColor(R.color.dark_blue , null));
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    void loadNameSpinner(){
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = new Dialog(SearchActivity.this);

                dialog.setContentView(R.layout.dialog_search_spinner);

                dialog.getWindow().setLayout(1000 , 1200);

//                Rect displayRectangle = new Rect();
//                dialog.getWindow().setLayout((int) ( displayRectangle.width() * 0.5f) , (int) ( displayRectangle.height() * 0.5f));

                dialog.show();

                EditText editText = dialog.findViewById(R.id.spinner_edit_text);

                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.this , android.R.layout.simple_list_item_1, holidaysNames);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        textView2.setText(adapter.getItem(position));
                        textView2.setTextColor(getResources().getColor(R.color.dark_blue , null));

                        dialog.dismiss();
                    }
                });

            }
        });

    }
}