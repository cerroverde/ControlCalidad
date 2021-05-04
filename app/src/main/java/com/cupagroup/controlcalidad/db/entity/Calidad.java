package com.cupagroup.controlcalidad.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cupagroup.controlcalidad.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_CALIDAD)
public class Calidad implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long calidad_id;

    @ColumnInfo(name = "calidad_name")
    private String calidad_name;

    public Calidad(Long calidad_id, String calidad_name) {
        this.calidad_name = calidad_name;
    }

    @Ignore
    public Calidad(){
        //Empty
    }

    public long getCalidad_id() {
        return calidad_id;
    }
    public void setCalidad_id(long calidad_id) {
        this.calidad_id = calidad_id;
    }

    public String getCalidad_name() {
        return calidad_name;
    }
    public void setName(String calidad_name) {
        this.calidad_name = calidad_name;
    }
}