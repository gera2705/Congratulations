package com.kolosov.samsung.school.congratulations.DataBase;

import java.util.ArrayList;

public class CongratulationJson{

    public ArrayList<CongratulationModel> holidays;

    public ArrayList<CongratulationModel> getHolidays() {
        return holidays;
    }

    public void setDictionaryWords(ArrayList<CongratulationModel> holidays) {
        this.holidays = holidays;
    }
}


class CongratulationModel {

    public String name;

    public String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String description;

    public String text;
}
