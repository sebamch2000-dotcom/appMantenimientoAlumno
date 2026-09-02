package com.senati.appmantenimientoalumnos.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "alumnos.db";
    public static final String TABLE_ALUMNOS = "t_alumnos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALUMNOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "dni TEXT NOT NULL," +
                "codigo_id TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo_electronico TEXT," +
                "carrera TEXT," +
                "semestre TEXT," +
                "zonal_sede TEXT," +
                "fecha_ingreso TEXT," +
                "promedio TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ALUMNOS);
        onCreate(sqLiteDatabase);
    }
}
