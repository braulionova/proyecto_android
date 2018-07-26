package com.braulionova.taskapp.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.repositorio.CategoriaRepositorio;
import com.braulionova.taskapp.repositorio.CategoriaRepositorioImp;

import java.util.ArrayList;
import java.util.List;

public class RegistrarTareaActivity extends AppCompatActivity {

    private CategoriaRepositorio categoriaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarea);
        //categoria repositorio
        categoriaRepositorio = new CategoriaRepositorioImp(this);
        //list
        List<Categoria> categorias = categoriaRepositorio.buscar(null);
        //array list
        List<String> categorias_array_list = new ArrayList<>(categorias.size());
        for (Categoria categoria : categorias) {
            categorias_array_list.add(categoria != null ? categoria.getNombre() : null);
        }
        //spinner_categorias
        Spinner spinnerCategorias = findViewById(R.id.spinner_categorias);
        // create the adapter.
        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_dropdown_item, categorias_array_list);
        spinner_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCategorias.setAdapter(spinner_adapter);
    }
}
