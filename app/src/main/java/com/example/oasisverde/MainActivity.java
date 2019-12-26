package com.example.oasisverde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnCat;
    private Button btnFav;
    private Button btnContacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnCat = findViewById(R.id.btnCatalogo);
        this.btnFav = findViewById(R.id.btnFavoritos);
        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.btnContacto = (Button) findViewById(R.id.btnContacto);

        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCatalogo();
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFavoritos();
            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFavoritos();
            }
        });
        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirContacto();
            }
        });


    }
    private void abrirCatalogo() {
        Intent intento = new Intent(MainActivity.this, CatalogoActivity.class);
        startActivityForResult(intento, 100);
    }
    private void abrirFavoritos() {
        Intent intento = new Intent(MainActivity.this, FavoritoActivity.class);
        startActivityForResult(intento, 100);
    }
    private void abrirContacto() {
        Intent intento = new Intent(MainActivity.this, ContactoActivity.class);
        startActivityForResult(intento, 100);
    }
}
