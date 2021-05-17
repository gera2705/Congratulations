package com.kolosov.samsung.school.congratulations.DataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Congratulation {

    public Congratulation(){
    }

    @Ignore
    public Congratulation(@NonNull String name , String date , String description , String congratulationText){
        this.name = name;
        this.date = date;
        this.description = description;
        this.congratulationText = congratulationText;
    }

    @NonNull
    @PrimaryKey
    public String name;


    @ColumnInfo(name = "date")
    public String date;
    
    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "text")
    public String congratulationText;

}
