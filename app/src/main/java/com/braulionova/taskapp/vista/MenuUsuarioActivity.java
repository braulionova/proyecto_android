package com.braulionova.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braulionova.taskapp.R;

public class MenuUsuarioActivity extends AppCompatActivity {

    private static final String LOG_TAG = MenuUsuarioActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        //btnRegistrarTarea
        Button btnRegistrarTarea = findViewById(R.id.btnRegistrarTarea);

        btnRegistrarTarea.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuUsuarioActivity.this, RegistrarTareaActivity.class);
                startActivity(intent);
            }
        });

        //btnSalir
        Button btnSalir = findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //btnVerTareas
        Button btnVerTareas = findViewById(R.id.btnVerTareas);

        btnVerTareas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MenuUsuarioActivity.this, ListaTareasUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
