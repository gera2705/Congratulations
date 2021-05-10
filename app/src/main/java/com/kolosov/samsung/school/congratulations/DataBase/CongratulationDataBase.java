package com.kolosov.samsung.school.congratulations.DataBase;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executors;

@Database(entities = {Congratulation.class} , version = 1 , exportSchema = false)
public abstract class CongratulationDataBase extends RoomDatabase {

    public abstract CongratulationDao congratulationDao();

    private static CongratulationDataBase INSTANCE;

    public static CongratulationDataBase getDbInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext() , CongratulationDataBase.class , "CONGRATULATIONS_BD")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void run() {


                                    String json = "";
                                    try {
//                                        InputStream is = getAssets()
                                        InputStream is = context.getAssets().open("testCong.json");
                                        int size = is.available();
                                        byte[] buffer = new byte[size];
                                        is.read(buffer);
                                        is.close();
                                        json = new String(buffer, "UTF-8");
                                    } catch (IOException ex) {
                                        ex.printStackTrace();

                                    }

//
//                                    StringBuilder fileData = new StringBuilder();
//                                    try(BufferedReader br = new BufferedReader(new FileReader("testCong.json"))){
//                                        String readLine = "";
//                                        while((readLine = br.readLine()) != null){
//                                            fileData.append(readLine);
//                                        }
//                                    }catch(IOException ex){
//                                        ex.printStackTrace();
//                                    }
////
//                                    Log.d("JSON_DATA" , fileData.toString());

//                                    GsonBuilder builder = new GsonBuilder();
//                                    Gson gson = builder.create();
//                                    Congratulation congratulation = gson.fromJson()
//                                    Cat murzik = gson.fromJson(jsonText, Cat.class);
//                                    Log.i("GSON", "Имя: " + murzik.name + "\nВозраст: " + murzik.age);

                                   // ArrayList<CongratulationJson> congratulations = new ArrayList<>();


                                    Gson gson = new Gson();
                                    CongratulationJson congratulationJson = gson.fromJson(json, CongratulationJson.class);


                                     ArrayList<CongratulationModel> congratulations = congratulationJson.getHolidays();


                                    for (CongratulationModel c: congratulations) {
                                        Congratulation congratulation = new Congratulation();
                                        congratulation.name = c.getName();
                                        congratulation.description = c.getDescription();
                                        congratulation.date = c.getDate();
                                        congratulation.congratulationText = c.getText();
                                        getDbInstance(context).congratulationDao().insertCongratulation(congratulation);
                                    }
//
//                                    Congratulation congratulation = new Congratulation();
//
//                                    congratulation.name="День дня";
//                                    congratulation.date= "2021.4.30";
//                                    congratulation.description="";
////
//                                    congratulation.congratulationText = "С новым годом!";
//
//                                    getDbInstance(context).congratulationDao().insertCongratulation(congratulation);

                                }
                            });
                        }
                    })
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;

    }


//    public String readJSONFromAsset() {
//        String json = "";
//        try {
//            InputStream is = getAssets()
////            InputStream is = getAssets().open("yourFile.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }

}
