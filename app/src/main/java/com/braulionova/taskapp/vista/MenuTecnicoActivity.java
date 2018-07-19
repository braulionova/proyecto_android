package com.braulionova.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braulionova.taskapp.R;

public class MenuTecnicoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tecnico);

        Button btnCategoria = findViewById(R.id.btnCategoria);

        btnCategoria.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuTecnicoActivity.this, CrearCategoriaActivity.class);
                startActivity(intent);
            }
        });

        Button btnVerCategorias = findViewById(R.id.btnVerCategoria);

        btnVerCategorias.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuTecnicoActivity.this, CategoriaListaActivity.class);
                startActivity(intent);
            }
        });

    }
}
