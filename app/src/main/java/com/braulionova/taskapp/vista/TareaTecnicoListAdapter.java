package com.braulionova.taskapp.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.entidad.Tarea;

import java.text.SimpleDateFormat;
import java.util.List;

public class TareaTecnicoListAdapter extends BaseAdapter {

    private Context context;
    private List<Tarea> tareas;

    public TareaTecnicoListAdapter(Context context, List<Tarea> tareas)
    {
        this.context = context;
        this.tareas = tareas;
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

    @Override
    public Object getItem(int i) {
        return tareas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return tareas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.tarea_listview_row, null, true);
        }

        TextView lbFecha = view.findViewById(R.id.lbFecha);
        TextView lbDescripcion = view.findViewById(R.id.lbDescripcion);
        //lbUsuario
        TextView lbUsuario = view.findViewById(R.id.lbUsuario);
        //lbCategoria
        TextView lbCategoria = view.findViewById(R.id.lbCategoria);
        //lbEstado
        TextView lbEstado = view.findViewById(R.id.lbEstado);

        Tarea tarea = tareas.get(i);
        //fecha
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(tarea.getFecha());
        lbFecha.setText(formattedDate);
        lbDescripcion.setText(tarea.getDescripcion());
        //Hecho por:
        lbUsuario.setText("Hecho por: " + tarea.getUsuarioCreador().getNombre());
        //lbCategoria
        lbCategoria.setText(tarea.getCategoria().getNombre());
        //lbEstado
        if(tarea.getEstado().equals(Tarea.TareaEstado.EN_PROCESO)) {
            lbEstado.setText("EN PROCESO");
        }
        else {
            lbEstado.setText(tarea.getEstado().name());
        }
        //return vista con datos
        return view;
    }
}
