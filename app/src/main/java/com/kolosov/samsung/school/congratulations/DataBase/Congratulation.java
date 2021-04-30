package com.kolosov.samsung.school.congratulations.DataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Congratulation {

    @NonNull
    @PrimaryKey
    public String name;


    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "text")
    public String congratulationText;

}
