package com.example.oasisverde.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.oasisverde.DTO.Favorito;
import com.example.oasisverde.Helpers.DatabaseHelper;

import java.util.ArrayList;

public class FavoritoDAL {

    /*ATRIBUTOS*/
    private DatabaseHelper dbHelper;
    private Favorito favorito;

    /*CONSTRUCTORES*/
    public FavoritoDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.favorito = new Favorito();
    }

    public FavoritoDAL(Context context, Favorito favorito) {
        this.dbHelper = new DatabaseHelper(context);
        this.favorito = favorito;
    }

    /*MÉTODOS*/
    public boolean insertar(String nombre, String nombreTipico, String descripcion)
    {
        this.favorito.setNombre(nombre);
        this.favorito.setNombreTipico(nombreTipico);
        this.favorito.setDescripcion(descripcion);

        return this.tryInsert();
    }
    public boolean eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filasAfectadas;


        try {
            filasAfectadas = db.delete("favorito","id = ?",
                    new String[] { String.valueOf(id) });
        } catch (Exception e) {
            return false;
        }

        return (filasAfectadas == 1);

    }

    public ArrayList<Favorito> seleccionar()
    {
        ArrayList<Favorito> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM favorito", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String nombre = consulta.getString(1);
                String nombreTipico = consulta.getString(2);
                String descripcion = consulta.getString(3);

                Favorito favorito = new Favorito(id,nombre,nombreTipico,descripcion);
                lista.add(favorito);
            } while(consulta.moveToNext());
        }
        return lista;
    }

    private boolean tryInsert() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put("nombre", this.favorito.getNombre());
        c.put("nombreTipico", this.favorito.getNombreTipico());
        c.put("descripcion", this.favorito.getDescripcion());

        try {
            db.insert("favorito", null, c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Favorito getFavorito()
    {
        return this.favorito;
    }

}
