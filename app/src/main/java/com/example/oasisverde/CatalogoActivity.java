package com.example.oasisverde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.oasisverde.DAL.PlantaDAL;
import com.example.oasisverde.DTO.Planta;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {
    private PlantaDAL plantaDAL;
    private ListView listPlantas;
    private ArrayAdapter<Planta> adapter;
    private ArrayList<Planta> listaPlantas;
    private int codPosicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        this.plantaDAL = new PlantaDAL(getApplicationContext(), new Planta());
        this.listaPlantas = new PlantaDAL(getBaseContext()).seleccionar();

        this.listPlantas = (ListView) findViewById(R.id.listFavoritos);

        this.adapter = new ArrayAdapter<Planta>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                this.listaPlantas
        );

        this.listPlantas.setAdapter(adapter);

        listPlantas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                abrirPlantaActivity();
            }
        });
    }
    private void abrirPlantaActivity() {
        Intent intento = new Intent(CatalogoActivity.this, PlantaActivity.class);
        Planta p = (Planta) listaPlantas.get(codPosicion);

        intento.putExtra("planta", p);
        startActivityForResult(intento, 100);
    }

}
