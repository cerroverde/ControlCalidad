package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cupagroup.controlcalidad.db.entity.QualityControl;

@Dao
public interface QualityControlDao {
    @Query("SELECT COUNT(quality_id) FROM quality WHERE create_at LIKE :create_at")
    Integer getCountControls(String create_at);

    @Delete
    void delete(QualityControl qualityControl);

    @Insert
    long insert(QualityControl qualityControl);

}
