package com.example.oasisverde;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oasisverde.DAL.FavoritoDAL;
import com.example.oasisverde.DAL.PlantaDAL;
import com.example.oasisverde.DTO.Favorito;
import com.example.oasisverde.DTO.Planta;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {
    private PlantaDAL plantaDAL;
    private FavoritoDAL favoritoDAL;
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
        this.favoritoDAL = new FavoritoDAL(getApplicationContext());

        this.listPlantas = (ListView) findViewById(R.id.listFavoritos);

        this.adapter = new ArrayAdapter<Planta>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                this.listaPlantas
        );

        this.listPlantas.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea agregar a favoritos?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean r = favoritoDAL.insertar(
                        String.valueOf(listaPlantas.get(codPosicion).getNombre()),
                        String.valueOf(listaPlantas.get(codPosicion).getNombreTipico()),
                        String.valueOf(listaPlantas.get(codPosicion).getDescripcion())
                );
                if(r){
                    Toast.makeText(getApplicationContext(), "Planta agregada a favoritos.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Ocurrió un error inesperado.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = builder.create();

        listPlantas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                abrirPlantaActivity();
            }
        });

        listPlantas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                dialog.show();
                return true;
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
