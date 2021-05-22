package com.kolosov.samsung.school.congratulations.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      CongratulationDataBase.getDbInstance(this.getApplicationContext());


      Log.d("Splash" , "if");
      Intent intent = new Intent(this, HomeActivity.class);
      startActivity(intent);
      finish();

    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
}