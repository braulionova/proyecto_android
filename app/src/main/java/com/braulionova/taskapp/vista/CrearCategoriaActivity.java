package com.braulionova.taskapp.vista;

import android.content.Intent;
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
    private Categoria categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);

        categoriaRepositorio = new CategoriaRepositorioImp(this);

        final EditText txtNombre = (EditText) findViewById(R.id.txtNombreCategoria);
        Button btnGuardar = (Button) findViewById(R.id.btnGuardarCategoria);

        //bundle
        Bundle paraBundle = getIntent().getExtras();
        if(paraBundle != null && paraBundle.containsKey("categoria")) {
            categoria = (Categoria) paraBundle.getSerializable("categoria");
            txtNombre.setText(categoria.getNombre());
            btnGuardar.setText("Actualizar");
        }
        //on click
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Categoria categoria = new Categoria();

                boolean nuevo = false;

                if(categoria == null)
                {
                    nuevo = true;
                    categoria = new Categoria();
                }
                //nombre
                categoria.setNombre(txtNombre.getText().toString());
                //log
                Log.i(LOG_TAG, categoria.toString());

                if(nuevo) {
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
                else {
                    categoriaRepositorio.actualizar(categoria);
                    //mensaje
                    Toast toast = Toast.makeText(getApplicationContext(), "Actualizado correctamente.", Toast.LENGTH_SHORT);
                    //toast1.setGravity(Gravity.CENTER,,);
                    toast.show();
                    //intent
                    Intent regCatIntent = new Intent(CrearCategoriaActivity.this, CategoriaListaActivity.class);
                    startActivity(regCatIntent);
                }

            }
        });

    }
}
