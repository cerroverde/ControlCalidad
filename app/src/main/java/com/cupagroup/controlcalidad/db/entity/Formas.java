package com.cupagroup.controlcalidad.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.cupagroup.controlcalidad.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_FORMA)
public class Formas implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long forma_id;

    @ColumnInfo(name = "forma_name")
    private String forma_name;

    //Constructor
    public Formas(Long forma_id, String forma_name)
    {
        this.forma_id = forma_id;
        this.forma_name = forma_name;
    }

    @Ignore
    public Formas(){
        //Empty
    }

    public Long getForma_id() {
        return forma_id;
    }
    public void setForma_id(Long forma_id) {
        this.forma_id = forma_id;
    }

    public String getForma_name() {
        return forma_name;
    }
    public void setForma_name(String name) {
        this.forma_name = forma_name;
    }
}

