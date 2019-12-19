package com.example.oasisverde.Helpers;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "oasisverde.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE planta(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, nombreTipico TEXT, descripcion TEXT);");
        db.execSQL("CREATE TABLE favorito(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, nombreTipico TEXT, descripcion TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
