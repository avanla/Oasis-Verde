package com.example.oasisverde;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.oasisverde.DAL.FavoritoDAL;
import com.example.oasisverde.DAL.PlantaDAL;
import com.example.oasisverde.DTO.Favorito;
import com.example.oasisverde.DTO.Planta;

public class PlantaActivity extends AppCompatActivity {
    private FavoritoDAL favoritoDAL;
    private PlantaDAL plantaDAL;
    private TextView txtNombre;
    private TextView txtNombreTipico;
    private TextView txtDescripcion;
    private ImageView imageView;
    private ImageButton imageButton;
    private ToggleButton toggleButton;
    private int codPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta);
        this.txtNombre = (TextView) findViewById(R.id.txtNombrePlanta);
        this.txtNombreTipico = (TextView) findViewById(R.id.txtNombreTipico);
        this.txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.imageButton = (ImageButton) findViewById(R.id.imageButton);

        this.plantaDAL = new PlantaDAL(getApplicationContext(), (Planta) getIntent().getSerializableExtra("planta") );

        this.txtNombre.setText(plantaDAL.getPlanta().getNombre());
        this.txtNombreTipico.setText(plantaDAL.getPlanta().getNombreTipico());
        this.txtDescripcion.setText(plantaDAL.getPlanta().getDescripcion());

        /*toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    favoritoDAL.insertar(plantaDAL.getPlanta().getNombre(), plantaDAL.getPlanta().getNombreTipico(), plantaDAL.getPlanta().getDescripcion());
                    Toast.makeText(getApplicationContext(),
                            "Planta añadida a favoritos", Toast.LENGTH_SHORT).show();
                }
                else{
                    favoritoDAL.eliminar(plantaDAL.getPlanta().getId());
                    Toast.makeText(getApplicationContext(),
                            "Planta eliminada de favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}
