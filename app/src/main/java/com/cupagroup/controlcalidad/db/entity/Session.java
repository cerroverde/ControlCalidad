package com.cupagroup.controlcalidad.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.cupagroup.controlcalidad.utils.Constants;

import java.io.Serializable;

@Entity(tableName = Constants.TABLE_NAME_SESSIONS)
public class Session implements Serializable {
    @PrimaryKey(autoGenerate = false)
    private long session_id;

    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name = "status")
    private boolean status;

    public Session(int user_id, Boolean status){
        this.user_id = user_id;
        this.status = status;
    }

    public long getSession_id() {
        return session_id;
    }
    public void setSession_id(long session_id) {
        this.session_id = session_id;
    }

    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
