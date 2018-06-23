package com.braulionova.taskapp.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.repositorio.CategoriaRepositorio;
import com.braulionova.taskapp.repositorio.CategoriaRepositorioImp;

import java.util.List;

public class CategoriaListaActivity extends AppCompatActivity {

    private static final String LOG_TAG = "CategoriaListaActivity";
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_lista);

        categoriaRepositorio = new CategoriaRepositorioImp(this);

        List<Categoria> categorias = categoriaRepositorio.buscar(null);
        //log
        Log.i(LOG_TAG, "Total de categorias: " + categorias.size());

        ListView catListView = (ListView) findViewById(R.id.categoria_listview);
        //adapter
        catListView.setAdapter(new CategoriaListAdapter(this, categorias));

    }
}
