package com.example.oasisverde.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.oasisverde.DTO.Planta;
import com.example.oasisverde.Helpers.DatabaseHelper;

import java.util.ArrayList;

public class PlantaDAL {

    private DatabaseHelper dbHelper;
    private Planta planta;

    public PlantaDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.planta = new Planta();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public PlantaDAL(Context context, Planta planta) {
        this.dbHelper = new DatabaseHelper(context);
        this.planta = planta;
    }

    public ArrayList<Planta> seleccionar()
    {
        ArrayList<Planta> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM planta", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String nombre = consulta.getString(1);
                String nombreTipico = consulta.getString(2);
                String descripcion = consulta.getString(3);

                Planta planta = new Planta(id,nombre,nombreTipico,descripcion);
                lista.add(planta);
            } while(consulta.moveToNext());
        }
        return lista;
    }
    public Planta getPlanta()
    {
        return this.planta;
    }

}
