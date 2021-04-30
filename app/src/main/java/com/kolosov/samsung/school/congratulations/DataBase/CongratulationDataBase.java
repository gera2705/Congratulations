package com.kolosov.samsung.school.congratulations.DataBase;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
//                                   List<User> userList = new ArrayList<>();

//                                    String stringDate="01/12/1995";
//                                    Date date=new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
//                                    System.out.println("Date is : "+date);

                                    Congratulation congratulation = new Congratulation();

                                    congratulation.name="День дня";
                                    congratulation.date= "2021.04.30";
//                                    List<String> strings = new ArrayList<>();
//                                    strings.add("С новым годом");
                                    congratulation.congratulationText = "С новым годом!";

                                    getDbInstance(context).congratulationDao().insertCongratulation(congratulation);

                                }
                            });
                        }
                    })
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;

    }

}
