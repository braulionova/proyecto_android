package com.braulionova.taskapp.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.Nota;
import java.text.SimpleDateFormat;
import java.util.List;

public class NotaListAdapter extends BaseAdapter {

    private Context context;
    private List<Nota> notas;

    public NotaListAdapter(Context context, List<Nota> notas)
    {
        this.context = context;
        this.notas = notas;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int i) {
        return notas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return notas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.nota_listview_row, null, true);
        }
        //TextView
        TextView lbFecha = view.findViewById(R.id.lbFecha);
        //lbUsuario
        TextView lbUsuario = view.findViewById(R.id.lbUsuario);
        //lbMensaje
        TextView lbMensaje = view.findViewById(R.id.lbMensaje);
        //nota
        Nota nota = notas.get(i);
        //fecha
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(nota.getFecha());
        lbFecha.setText(formattedDate);
        //usuario
        lbUsuario.setText(nota.getUsuario().getNombre());
        //lbMensaje
        lbMensaje.setText(nota.getMensaje());
        //return vista con datos
        return view;
    }
}
