package com.braulionova.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.AppConfig;
import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.TareaRepositorio;
import com.braulionova.taskapp.repositorio.TareaRepositorioDbImpl;
import java.util.List;

public class ListaTareasTecnicoActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ListaTareasTecnico";
    private TareaRepositorio tareaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas_tecnico);

        try {

        tareaRepositorio = new TareaRepositorioDbImpl(this);
        Usuario usuarioTecnico = AppConfig.getConfig().getUsuario();
        //mensaje con el usuario que inicio sesion
        //Toast toastTecnico = Toast.makeText(getApplicationContext(), "Tecnico: " + usuarioTecnico.getId() + ", " + usuarioTecnico.toString(), Toast.LENGTH_LONG);
        //toastTecnico.show();
        //lista de tareas
        List<Tarea> tareas = null;

        //tareas = tareaRepositorio.buscarCreadaPor(usuarioTecnico);
        //tareas asignadas al usuario tecnico que inicio sesion

        tareas = tareaRepositorio.buscarAsignadaA(usuarioTecnico);
        //ciclo para mostrar las tareas
        //for (Tarea tarea: tareas) {
        //    Toast toast = Toast.makeText(getApplicationContext(), "Tarea: " + tarea.getNombre() + ", " + tarea.toString(), Toast.LENGTH_LONG);
        //    toast.show();
        //}
        //total de tareas
        Toast toast = Toast.makeText(getApplicationContext(), "Total de tareas: " + tareas.size(), Toast.LENGTH_LONG);
        toast.show();
        //lista de tareas
        ListView tareasListView = (ListView) findViewById(R.id.lvTareas);
        //adapter
        tareasListView.setAdapter(new TareaTecnicoListAdapter(this, tareas));
        //list view
        tareasListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                try {
                    //item
                    Tarea tarea = (Tarea) adapterView.getItemAtPosition(i);
                    //intent
                    Intent listaDeTareasIntent = new Intent(ListaTareasTecnicoActivity.this, DetalleTareaActivity.class);
                    listaDeTareasIntent.putExtra("tarea", tarea);
                    startActivity(listaDeTareasIntent);
                }
                catch (Exception e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_LONG);
                    toast.show();
                    //e.printStackTrace();
                }
            }
        });

        }
        catch (Exception e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_LONG);
            toast.show();
            //e.printStackTrace();
        }
    }
}
