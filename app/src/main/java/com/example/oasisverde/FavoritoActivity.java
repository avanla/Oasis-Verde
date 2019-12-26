package com.example.oasisverde;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
        this.favoritoDAL = new FavoritoDAL(getApplicationContext());
        this.listaFavoritos = new FavoritoDAL(getBaseContext()).seleccionar();

        this.listFavoritos = (ListView) findViewById(R.id.listFavoritos);

        this.adapter = new ArrayAdapter<Favorito>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                this.listaFavoritos
        );

        this.listFavoritos.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea eliminar de favoritos??");
        builder.setPositiveButton("Si, eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id = ((Favorito) listaFavoritos.get(codPosicion)).getId();
                boolean r = favoritoDAL.eliminar(id);
                if(r){
                    Toast.makeText(getApplicationContext(), "Se ha quitado la planta de favoritos.", Toast.LENGTH_SHORT).show();
                    actualizarLista();
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

        listFavoritos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                dialog.show();
                return true;
            }
        });

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
    private void actualizarLista() {
        adapter.clear();
        adapter.addAll(favoritoDAL.seleccionar());
        adapter.notifyDataSetChanged();
    }
}
