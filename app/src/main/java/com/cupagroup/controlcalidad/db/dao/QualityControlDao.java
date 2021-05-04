package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cupagroup.controlcalidad.db.entity.QualityControl;

import java.util.List;

@Dao
public interface QualityControlDao {
    @Query("SELECT COUNT(quality_id) FROM quality WHERE session_id LIKE :session_id")
    Integer getCountControls(Long session_id);

    @Query("SELECT ensayo_id FROM quality WHERE session_id LIKE :session_id")
    Integer getLastEnsayoIdOfSession(Long session_id);

    @Query("SELECT * FROM quality WHERE session_id LIKE :session_id")
    List<QualityControl> getAllSessionById(Long session_id);

    @Delete
    void delete(QualityControl qualityControl);

    @Insert
    long insert(QualityControl qualityControl);

}
