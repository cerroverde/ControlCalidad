package com.cupagroup.controlcalidad.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cupagroup.controlcalidad.db.dao.CalidadDao;
import com.cupagroup.controlcalidad.db.dao.CanterasDao;
import com.cupagroup.controlcalidad.db.dao.EspesorDao;
import com.cupagroup.controlcalidad.db.dao.FormasDao;
import com.cupagroup.controlcalidad.db.dao.NavesDao;
import com.cupagroup.controlcalidad.db.dao.QualityControlDao;
import com.cupagroup.controlcalidad.db.dao.SessionDao;
import com.cupagroup.controlcalidad.db.dao.UserDao;
import com.cupagroup.controlcalidad.db.entity.Calidad;
import com.cupagroup.controlcalidad.db.entity.Canteras;
import com.cupagroup.controlcalidad.db.entity.Espesor;
import com.cupagroup.controlcalidad.db.entity.Formas;
import com.cupagroup.controlcalidad.db.entity.Naves;
import com.cupagroup.controlcalidad.db.entity.QualityControl;
import com.cupagroup.controlcalidad.db.entity.Session;
import com.cupagroup.controlcalidad.db.entity.User;
import com.cupagroup.controlcalidad.utils.Constants;

@Database(entities = {
        Calidad.class,
        Espesor.class,
        Naves.class,
        QualityControl.class,
        Session.class,
        User.class,
        Formas.class,
        Canteras.class
}, version = 7, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase appDB;
    static final Migration MIGRATION_6_7 = new Migration(6,7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };

    public static AppDatabase getInstance(Context context){
        if (null == appDB){
            appDB = buildDatabaseInstance(context);
        }
        return appDB;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_6_7)
                .build();
    }

    public abstract CalidadDao getCalidad();
    public abstract EspesorDao getEspesor();
    public abstract NavesDao getNaves();
    public abstract QualityControlDao getQualityControl();
    public abstract SessionDao getSession();
    public abstract UserDao getPreferenceUser();
    public abstract FormasDao getFormas();
    public abstract CanterasDao getCanteras();
}