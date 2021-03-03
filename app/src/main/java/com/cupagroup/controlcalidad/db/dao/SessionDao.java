package com.cupagroup.controlcalidad.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cupagroup.controlcalidad.db.entity.Session;

@Dao
public interface SessionDao {
    @Query("SELECT status FROM session ORDER BY UPPER(session_id) LIMIT 1")
    Boolean getLastSessionStatus();

    @Query("SELECT session_id FROM session WHERE status = 1")
    Long getIdFromActiveSession();

    @Query("UPDATE session SET status = 0 WHERE session_id = :session_id")
    int disableActiveSession(Long session_id);

    @Insert
    long insert(Session session);

    @Delete
    void delete(Session session);

}