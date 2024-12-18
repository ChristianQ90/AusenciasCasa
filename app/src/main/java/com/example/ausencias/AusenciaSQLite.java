package com.example.ausencias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AusenciaSQLite extends SQLiteOpenHelper {
    String sqlCreacion = "CREATE DATABASE gestionAsistencia";
    String sqlCreacionTablaAusencias = "CREATE TABLE ausencias (nombre TEXT, fecha TEXT, hora INT)";
    String sqlBorradoTablaAusencias = "DROP TABLE IF EXISTS ausencias";
    public AusenciaSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreacionTablaAusencias);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlBorradoTablaAusencias);
        db.execSQL(sqlCreacionTablaAusencias);
    }
}
