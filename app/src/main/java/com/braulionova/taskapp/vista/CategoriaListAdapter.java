package com.braulionova.taskapp.vista;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.Categoria;

import java.util.List;

public class CategoriaListAdapter extends BaseAdapter {

    private Context context;
    private List<Categoria> categorias;

    public CategoriaListAdapter(Context context, List<Categoria> categorias)
    {
        this.context = context;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categorias.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.categoria_listview_row, null, true);
        }

        TextView lbCategoriaId = view.findViewById(R.id.lbId);
        TextView lbCategoriaNombre = view.findViewById(R.id.lbNombre);

        Categoria cat = categorias.get(i);

        lbCategoriaId.setText(cat.getId().toString());
        lbCategoriaNombre.setText(cat.getNombre());

        return view;
    }
}
