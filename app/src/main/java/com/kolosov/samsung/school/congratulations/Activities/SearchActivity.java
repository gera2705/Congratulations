package com.kolosov.samsung.school.congratulations.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;
import com.kolosov.samsung.school.congratulations.Utils.MyAsyncTask;
import com.kolosov.samsung.school.congratulations.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private TextView holidayNameTextView;
    private TextView genderTextView;
    private TextView humanTextView;
    private static List<String> holidaysNames;
    private ArrayList<String> genderName;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        loadMenu();

        loadNameSpinner();

        loadGenderSpinner();


    }

    private void init(){
        holidayNameTextView = findViewById(R.id.holiday_name_text_view);
        genderTextView = findViewById(R.id.gender);
        Button search = findViewById(R.id.result_counter);
        humanTextView = findViewById(R.id.human_name);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

        Bundle arguments = getIntent().getExtras();

        if(arguments != null) {
            String name = arguments.get("holidayName").toString();

            holidayNameTextView.setText(name);
        }

        ImageButton questionButton = findViewById(R.id.question);

        questionButton.setOnClickListener(v -> {
            Toast.makeText(this, "Раздел находится в разработке.", Toast.LENGTH_SHORT).show();
        });

        CongratulationDataBase db = CongratulationDataBase.getDbInstance(this.getApplicationContext());

        holidaysNames = new ArrayList<>();

        holidaysNames = new MyAsyncTask().doInBackground(this.getApplicationContext());

        genderName = new ArrayList<>();

        genderName.add("Не важно");
        genderName.add("Мужской");
        genderName.add("Женский");

        search.setOnClickListener(v -> {

            if (holidayNameTextView.getText().toString().equals("") || genderTextView.getText().toString().equals("") || humanTextView.getText().toString().equals("") ) {

                Toast.makeText(SearchActivity.this, "Заполните все поля!", Toast.LENGTH_SHORT).show();

            }else {

                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra("flag" , "1");
                intent.putExtra("holidayName", holidayNameTextView.getText());
                intent.putExtra("humanName" , humanTextView.getText());
                startActivity(intent);
            }
        });
    }

    private void loadMenu(){
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.home:
                    Intent intent = new Intent(SearchActivity.this , HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    break;

                case R.id.calendar:
                    Intent intent1 = new Intent(SearchActivity.this , CalendarActivity.class);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    break;
                case R.id.favorite:
                    Intent intent2 = new Intent(SearchActivity.this , FavoriteActivity.class);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    break;
            }

            return false;
        });
    }

    private void loadGenderSpinner(){
        genderTextView.setOnClickListener(v -> {

            dialog = new Dialog(SearchActivity.this);
            dialog.setContentView(R.layout.dialog_search_spinner);
            dialog.getWindow().setLayout(1000 , 1200);
            dialog.show();
            EditText editText = dialog.findViewById(R.id.spinner_edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchActivity.this , android.R.layout.simple_list_item_1, genderName);
            listView.setAdapter(adapter);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            listView.setOnItemClickListener((parent, view, position, id) -> {
                genderTextView.setText(adapter.getItem(position));
                genderTextView.setTextColor(getResources().getColor(R.color.dark_blue , null));
                dialog.dismiss();
            });

        });
    }

    private void loadNameSpinner(){
        holidayNameTextView.setOnClickListener(v -> {

            dialog = new Dialog(SearchActivity.this);
            dialog.setContentView(R.layout.dialog_search_spinner);
            dialog.getWindow().setLayout(1000 , 1200);
            dialog.show();

            EditText editText = dialog.findViewById(R.id.spinner_edit_text);
            ListView listView = dialog.findViewById(R.id.list_view);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchActivity.this , android.R.layout.simple_list_item_1, holidaysNames);
            listView.setAdapter(adapter);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            listView.setOnItemClickListener((parent, view, position, id) -> {
                holidayNameTextView.setText(adapter.getItem(position));
                holidayNameTextView.setTextColor(getResources().getColor(R.color.dark_blue , null));
                dialog.dismiss();
            });

        });
    }
}

