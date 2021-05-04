package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cupagroup.controlcalidad.activities.CanterasListClass;
import com.cupagroup.controlcalidad.db.entity.Canteras;
import com.cupagroup.controlcalidad.db.entity.Naves;

import java.util.List;

@Dao
public interface CanterasDao {
    @Query("SELECT id, name FROM canteras")
    public List<CanterasListClass> getAllCanteras();

    @Insert
    long insert(Canteras canteras);

    @Update
    void update(Canteras canteras);

    @Delete
    void delete(Canteras canteras);

    @Query("DELETE FROM canteras")
    void deleteAll();
}
