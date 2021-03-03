package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cupagroup.controlcalidad.db.entity.Espesor;

import java.util.List;

@Dao
public interface EspesorDao {
    @Query("SELECT grosor FROM espesor ORDER BY LOWER(espesor_id)")
    List<String> getAll();

    @Insert
    long insert(Espesor espesor);

    @Update
    void update(Espesor espesor);

    @Delete
    void delete(Espesor espesor);

    @Query("DELETE FROM espesor")
    void deleteAll();
}