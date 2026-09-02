package com.senati.appmantenimientoalumnos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.senati.appmantenimientoalumnos.entidades.Alumnos;

import java.util.ArrayList;

public class DbAlumnos extends DbHelper {

    Context context;

    public DbAlumnos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarAlumno(String nombre, String dni, String codigo_id, String telefono, 
                              String correo, String carrera, String semestre, String zonal, 
                              String fecha, String promedio) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("dni", dni);
            values.put("codigo_id", codigo_id);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo);
            values.put("carrera", carrera);
            values.put("semestre", semestre);
            values.put("zonal_sede", zonal);
            values.put("fecha_ingreso", fecha);
            values.put("promedio", promedio);

            id = db.insert(TABLE_ALUMNOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Alumnos> mostrarAlumnos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Alumnos> listaAlumnos = new ArrayList<>();
        Alumnos alumno;
        Cursor cursorAlumnos;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS, null);

        if (cursorAlumnos.moveToFirst()) {
            do {
                alumno = new Alumnos();
                alumno.setId(cursorAlumnos.getInt(0));
                alumno.setNombre(cursorAlumnos.getString(1));
                alumno.setDni(cursorAlumnos.getString(2));
                alumno.setTelefono(cursorAlumnos.getString(4));
                alumno.setCorreo(cursorAlumnos.getString(5));
                listaAlumnos.add(alumno);
            } while (cursorAlumnos.moveToNext());
        }

        cursorAlumnos.close();

        return listaAlumnos;
    }

    public Alumnos verAlumno(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Alumnos alumno = null;
        Cursor cursorAlumnos;

        cursorAlumnos = db.rawQuery("SELECT * FROM " + TABLE_ALUMNOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorAlumnos.moveToFirst()) {
            alumno = new Alumnos();
            alumno.setId(cursorAlumnos.getInt(0));
            alumno.setNombre(cursorAlumnos.getString(1));
            alumno.setDni(cursorAlumnos.getString(2));
            alumno.setCodigo_id(cursorAlumnos.getString(3));
            alumno.setTelefono(cursorAlumnos.getString(4));
            alumno.setCorreo(cursorAlumnos.getString(5));
            alumno.setCarrera(cursorAlumnos.getString(6));
            alumno.setSemestre(cursorAlumnos.getString(7));
            alumno.setZonal(cursorAlumnos.getString(8));
            alumno.setFecha(cursorAlumnos.getString(9));
            alumno.setPromedio(cursorAlumnos.getString(10));
        }

        cursorAlumnos.close();

        return alumno;
    }

    public boolean editarAlumno(int id, String nombre, String dni, String codigo_id, String telefono, 
                               String correo, String carrera, String semestre, String zonal, 
                               String fecha, String promedio) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("dni", dni);
            values.put("codigo_id", codigo_id);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo);
            values.put("carrera", carrera);
            values.put("semestre", semestre);
            values.put("zonal_sede", zonal);
            values.put("fecha_ingreso", fecha);
            values.put("promedio", promedio);

            db.update(TABLE_ALUMNOS, values, "id=?", new String[]{String.valueOf(id)});
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarAlumno(int id) {
        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ALUMNOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
