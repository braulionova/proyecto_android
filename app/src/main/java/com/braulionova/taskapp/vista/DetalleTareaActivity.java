package com.braulionova.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.AppConfig;
import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.TareaRepositorio;
import com.braulionova.taskapp.repositorio.TareaRepositorioDbImpl;

import java.text.SimpleDateFormat;

public class DetalleTareaActivity extends AppCompatActivity {

    private Tarea tarea;
    private TareaRepositorio tareaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tarea);

        try{
            //btnTareaLista
            Button btnTareaLista = findViewById(R.id.btnTareaLista);
            //bundle
            Bundle paraBundle = getIntent().getExtras();
            if(paraBundle != null && paraBundle.containsKey("tarea")) {
                tarea = (Tarea) paraBundle.getSerializable("tarea");
                //get usuario que inicio sesion
                Usuario usuario = AppConfig.getConfig().getUsuario();
                //tecnico o usuario
                if(usuario.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO)) {
                    tareaRepositorio = new TareaRepositorioDbImpl(this);
                    tarea.setEstado(Tarea.TareaEstado.EN_PROCESO);
                    tareaRepositorio.actualizarEstatus(tarea);
                }
                //cargando la tarea
                //txtFechaTarea
                TextView txtFechaTarea = findViewById(R.id.txtFechaTarea);
                //fecha
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = formatter.format(tarea.getFecha());
                txtFechaTarea.setText(formattedDate);
                //txtNombreCategoriaTarea
                TextView txtNombreCategoriaTarea = findViewById(R.id.txtNombreCategoriaTarea);
                txtNombreCategoriaTarea.setText(tarea.getCategoria().getNombre());
                //txtTituloUsuarioTarea
                TextView txtTituloUsuarioTarea = findViewById(R.id.txtTituloUsuarioTarea);

                //txtNombreUsuarioTarea
                TextView txtNombreUsuarioTarea = findViewById(R.id.txtNombreUsuarioTarea);
                //tecnico o usuario
                if(usuario.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO)) {
                    //si es tecnico mostramos los datos del usuario creador
                    txtTituloUsuarioTarea.setText("Usuario:");
                    txtNombreUsuarioTarea.setText(tarea.getUsuarioCreador().getNombre());

                }else {
                    //si es usuario creador mostramos los datos del tecnico
                    txtTituloUsuarioTarea.setText("Técnico:");
                    txtNombreUsuarioTarea.setText(tarea.getUsuarioAsignado().getNombre());
                }
                //txtEstado
                TextView txtEstado = findViewById(R.id.txtEstado);
                if(tarea.getEstado().equals(Tarea.TareaEstado.EN_PROCESO)) {
                    txtEstado.setText("EN PROCESO");
                }
                else {
                    txtEstado.setText(tarea.getEstado().name());
                }
                //txtDescripcionTarea
                TextView txtDescripcionTarea = findViewById(R.id.txtDescripcionTarea);
                txtDescripcionTarea.setText(tarea.getDescripcion());
                //tecnico o usuario
                if(usuario.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO)) {
                    btnTareaLista.setVisibility(View.VISIBLE);
                }
                else{
                    btnTareaLista.setVisibility(View.INVISIBLE);
                }
            }
            //on click
            btnTareaLista.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    tareaRepositorio = new TareaRepositorioDbImpl(getApplicationContext());
                    tarea.setEstado(Tarea.TareaEstado.TERMINADO);
                    tareaRepositorio.actualizarEstatus(tarea);
                    Toast toast = Toast.makeText(getApplicationContext(), "Tarea Finalizada.", Toast.LENGTH_LONG);
                    toast.show();
                    //luego de actualizar el estatus redireccionamos al menu del tecnico
                    Intent intent = new Intent(DetalleTareaActivity.this, MenuTecnicoActivity.class);
                    startActivity(intent);
                }
            });
        }
        catch (Exception ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Error: " + ex.toString(), Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
