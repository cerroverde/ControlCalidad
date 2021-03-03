package com.cupagroup.controlcalidad.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cupagroup.controlcalidad.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_ESPESOR)
public class Espesor implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long espesor_id;

    @ColumnInfo(name = "grosor")
    private String grosor;

    public Espesor(String grosor) {
        this.grosor = grosor;
    }

    public long getEspesor_id() {
        return espesor_id;
    }

    public void setEspesor_id(long espesor_id) {
        this.espesor_id = espesor_id;
    }

    public String getGrosor() {
        return grosor;
    }

    public void setName(String grosor) {
        this.grosor = grosor;
    }
}