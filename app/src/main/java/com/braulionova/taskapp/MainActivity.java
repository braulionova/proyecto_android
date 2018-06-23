package com.braulionova.taskapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braulionova.taskapp.vista.CategoriaListaActivity;
import com.braulionova.taskapp.vista.CrearCategoriaActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //clase categoria
        //Categoria cat = new Categoria();

        //cat.setDescripcion("CATEGORIA 1");
        //cat.setId(2);
        //log de la app
        //Log.i(LOG_TAG, cat.toString());

        Button btnCategoria = findViewById(R.id.btnCategoria);

        btnCategoria.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, CrearCategoriaActivity.class);
                startActivity(intent);
            }
        });

        Button btnVerCategorias = findViewById(R.id.btnVerCategoria);

        btnVerCategorias.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, CategoriaListaActivity.class);
                startActivity(intent);
            }
        });
    }
}
