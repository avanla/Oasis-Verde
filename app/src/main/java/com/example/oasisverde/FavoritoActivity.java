package com.example.oasisverde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oasisverde.DAL.FavoritoDAL;
import com.example.oasisverde.DAL.PlantaDAL;
import com.example.oasisverde.DTO.Favorito;
import com.example.oasisverde.DTO.Planta;

import java.util.ArrayList;

public class FavoritoActivity extends AppCompatActivity {
    private FavoritoDAL favoritoDAL;
    private ListView listFavoritos;
    private ArrayAdapter<Favorito> adapter;
    private ArrayList<Favorito> listaFavoritos;
    private int codPosicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito);

        this.favoritoDAL = new FavoritoDAL(getApplicationContext(), new Favorito());
        this.listaFavoritos = new FavoritoDAL(getBaseContext()).seleccionar();

        this.listFavoritos = (ListView) findViewById(R.id.listFavoritos);

        this.adapter = new ArrayAdapter<Favorito>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                this.listaFavoritos
        );

        this.listFavoritos.setAdapter(adapter);

        listFavoritos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                abrirPlantaActivity();
            }
        });
    }

    private void abrirPlantaActivity() {
        Intent intento = new Intent(FavoritoActivity.this, PlantaActivity.class);
        Favorito p = (Favorito) listaFavoritos.get(codPosicion);

        Planta a = new Planta();
        a.setNombre(listaFavoritos.get(codPosicion).getNombre());
        a.setNombre(listaFavoritos.get(codPosicion).getNombreTipico());
        a.setNombre(listaFavoritos.get(codPosicion).getDescripcion());

        intento.putExtra("planta", a);
        startActivityForResult(intento, 100);
    }
}
