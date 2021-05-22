package com.kolosov.samsung.school.congratulations.Utils;

import android.content.Context;
import android.os.AsyncTask;

import com.kolosov.samsung.school.congratulations.DataBase.CongratulationDataBase;

import java.util.ArrayList;
import java.util.List;

public class MyAsyncTask extends AsyncTask<Context, Void , ArrayList<String>> {

    @Override
    public ArrayList<String> doInBackground(Context... contexts) {
        CongratulationDataBase db = CongratulationDataBase.getDbInstance(contexts[0]);
        return (ArrayList<String>) db.congratulationDao().getAllNames();
    }
}
