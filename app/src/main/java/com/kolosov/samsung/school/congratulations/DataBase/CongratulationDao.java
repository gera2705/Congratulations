package com.kolosov.samsung.school.congratulations.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CongratulationDao {

    @Query("SELECT * FROM congratulation")
    List<Congratulation> getAllCongratulation();

    @Query("SELECT name FROM congratulation")
    List<String> getAllNames();

    @Query("SELECT * FROM congratulation WHERE name = :congName")
    Congratulation getCongratulation(String congName);

    @Query("SELECT * FROM congratulation WHERE date= :congDate")
    Congratulation getCongratulationByDate(String congDate);

    @Query("SELECT * FROM congratulation WHERE date= :congDate")
    List<Congratulation> getAllCongratulationByDate(String congDate);

    @Insert
    void insertCongratulation(Congratulation... congratulations);

    @Delete
    void delete(Congratulation congratulation);
}
