package com.cupagroup.controlcalidad.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cupagroup.controlcalidad.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_NAVES)
public class Naves implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long naves_id;

    @ColumnInfo(name = "name")
    private String name;

    public Naves(long naves_id, String name) {
        this.naves_id = naves_id;
        this.name = name;
    }

    public Naves(){
        //Empty
    }

    public long getNaves_id() {
        return naves_id;
    }

    public void setNaves_id(long naves_id) {
        this.naves_id = naves_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
