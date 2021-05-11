package com.kolosov.samsung.school.congratulations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextView holidayNameTextView;
    private TextView genderTextView;
    private TextView humanTextView;
    private List<String> holidaysNames;
    private ArrayList<String> genderName;
    private Dialog dialog;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        holidayNameTextView = findViewById(R.id.holiday_name_text_view);
        genderTextView = findViewById(R.id.gender);
        search = findViewById(R.id.add_still_button);
        humanTextView = findViewById(R.id.human_name);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);


        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            String name = arguments.get("holidayName").toString();

            holidayNameTextView.setText(name);
        }

        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        holidaysNames = new ArrayList<>();
        holidaysNames = db.congratulationDao().getAllNames();

        genderName = new ArrayList<>();

        genderName.add("Не важно");
        genderName.add("Мужской");
        genderName.add("Женский");



        loadMenu();

        loadNameSpinner();

        loadGenderSpinner();

        search.setOnClickListener(v -> {

            if (holidayNameTextView.getText().toString().equals("") || genderTextView.getText().toString().equals("") || humanTextView.getText().toString().equals("") ) {

                Toast.makeText(SearchActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();

            }else {

                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("holidayName", holidayNameTextView.getText());
                startActivity(intent);
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
        genderTextView.setOnClickListener(v -> {


            dialog = new Dialog(SearchActivity.this);

            dialog.setContentView(R.layout.dialog_search_spinner);

            dialog.getWindow().setLayout(1000 , 1200);


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
                    genderTextView.setText(adapter.getItem(position));
                    genderTextView.setTextColor(getResources().getColor(R.color.dark_blue , null));
                    dialog.dismiss();
                }
            });

        });
    }

    void loadNameSpinner(){
        holidayNameTextView.setOnClickListener(v -> {

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

            listView.setOnItemClickListener((parent, view, position, id) -> {
                holidayNameTextView.setText(adapter.getItem(position));
                holidayNameTextView.setTextColor(getResources().getColor(R.color.dark_blue , null));

                dialog.dismiss();
            });

        });

    }
}