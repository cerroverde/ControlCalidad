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

    @ColumnInfo(name = "name")
    private String name;

    public Calidad(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}