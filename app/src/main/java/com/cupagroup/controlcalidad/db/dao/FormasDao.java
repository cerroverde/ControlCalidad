package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cupagroup.controlcalidad.db.entity.Formas;

import java.util.List;

@Dao
public interface FormasDao {
    @Query("SELECT forma_name FROM forma ORDER BY LOWER(forma_id)")
    List<String> getAll();

    @Query("SELECT forma_name FROM forma ORDER BY LOWER(forma_id)")
    List<String> getFormaEntries();

    @Query("SELECT forma_id FROM forma ORDER BY  LOWER(forma_id)")
    List<Long> getFormaEntryValues();

    @Insert
    long insert(Formas formas);

    @Update
    void update(Formas formas);

    @Delete
    void delete(Formas formas);

    @Query("DELETE FROM forma")
    void deleteAll();
}