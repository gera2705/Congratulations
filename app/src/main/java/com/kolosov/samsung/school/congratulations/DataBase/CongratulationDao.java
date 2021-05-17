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

    @Query("SELECT * FROM congratulation WHERE name = :arg0")
    Congratulation getCongratulation(String arg0);

    @Query("SELECT * FROM congratulation WHERE date= :arg0")
    Congratulation getCongratulationByDate(String arg0);

    @Query("SELECT * FROM congratulation WHERE date= :arg0")
    List<Congratulation> getAllCongratulationByDate(String arg0);

    @Insert
    void insertCongratulation(Congratulation... congratulations);

    @Delete
    void delete(Congratulation congratulation);
}
