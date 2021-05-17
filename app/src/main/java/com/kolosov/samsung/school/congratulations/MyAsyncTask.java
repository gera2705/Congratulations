package com.kolosov.samsung.school.congratulations;

import android.content.Context;
import android.os.AsyncTask;

import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;

import java.util.ArrayList;
import java.util.List;

public class MyAsyncTask extends AsyncTask<Context, Void , ArrayList<String>> {


//    @Override
//    protected ArrayList<String> doInBackground(CongratulationDataBase... congratulationDataBases) {
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList = congratulationDataBases.congratulationDao().getAllNames();
//        return null;
//    }

    @Override
    protected ArrayList<String> doInBackground(Context... contexts) {
        CongratulationDataBase db = CongratulationDataBase.getDbInstance(contexts[0]);
        //        arrayList = db.congratulationDao().getAllNames();
        return (ArrayList<String>) db.congratulationDao().getAllNames();
    }
}
