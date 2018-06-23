package com.braulionova.taskapp.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.repositorio.CategoriaRepositorio;
import com.braulionova.taskapp.repositorio.CategoriaRepositorioImp;

public class CrearCategoriaActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CategoriaActivity";
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);

        categoriaRepositorio = new CategoriaRepositorioImp(this);

        final EditText txtNombre = (EditText) findViewById(R.id.txtNombreCategoria);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardarCategoria);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria categoria = new Categoria();
                categoria.setNombre(txtNombre.getText().toString());
                //log
                Log.i(LOG_TAG, categoria.toString());
                //guardar
                categoriaRepositorio.guardar(categoria);
                //log
                Log.i(LOG_TAG, categoria.toString());
                //mensaje
                Toast toast = Toast.makeText(getApplicationContext(), "Guardado correctamente.", Toast.LENGTH_SHORT);
                //toast1.setGravity(Gravity.CENTER,,);
                toast.show();
                //en blanco luego de guardar
                txtNombre.setText("");
            }
        });

    }
}
