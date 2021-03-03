package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cupagroup.controlcalidad.db.entity.Naves;

import java.util.List;

@Dao
public interface NavesDao {
    @Query("SELECT name FROM naves ORDER BY LOWER(naves_id)")
    List<String> getAll();

    @Insert
    long insert(Naves naves);

    @Update
    void update(Naves naves);

    @Delete
    void delete(Naves naves);

    @Query("DELETE FROM naves")
    void deleteAll();
}