package com.kolosov.samsung.school.congratulations.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      CongratulationDataBase.getDbInstance(this.getApplicationContext());
      Intent intent = new Intent(this, HomeActivity.class);
      startActivity(intent);
      finish();
    }

}