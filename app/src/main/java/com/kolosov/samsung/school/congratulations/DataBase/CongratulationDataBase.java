package com.kolosov.samsung.school.congratulations.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Executors;

@Database(entities = {Congratulation.class} , version = 1 , exportSchema = false)
public abstract class CongratulationDataBase extends RoomDatabase {

    public abstract CongratulationDao congratulationDao();

    private static CongratulationDataBase INSTANCE;

    public static CongratulationDataBase getDbInstance(Context context){

        if(INSTANCE == null){
            Log.d("DBcreate" , "create");
            INSTANCE = Room.databaseBuilder(context.getApplicationContext() , CongratulationDataBase.class , "CONGRATULATIONS_BD")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> {

                                String json = "";
                                try {

                                    InputStream is = context.getAssets().open("testCong.json");
                                    int size = is.available();
                                    byte[] buffer = new byte[size];
                                    is.read(buffer);
                                    is.close();
                                    json = new String(buffer, StandardCharsets.UTF_8);

                                } catch (IOException ex) {
                                    ex.printStackTrace();

                                }


                                Log.d("STASTTIME1" , "1");
                                Gson gson = new Gson();
                                CongratulationJson congratulationJson = gson.fromJson(json, CongratulationJson.class);


                                 ArrayList<CongratulationModel> congratulations = congratulationJson.getHolidays();

                                Log.d("ENDTTIME1" , "2");

                                Log.d("STASTTIME2" , "1");
                                for (CongratulationModel c: congratulations) {
                                    try {

                                        getDbInstance(context).congratulationDao().insertCongratulation(new Congratulation(c.getName() , c.getDate() , c.getDescription() , c.getText()));
                                    }catch (SQLiteException e){
                                        Log.d("DBex" ,  c.getName());
                                    }
                                }
                                Log.d("ENDTTIME2" , "2");

                            });

                        }
                    })
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;

    }


}
