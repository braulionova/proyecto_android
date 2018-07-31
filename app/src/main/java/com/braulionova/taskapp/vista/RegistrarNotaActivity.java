package com.braulionova.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.AppConfig;
import com.braulionova.taskapp.entidad.Nota;
import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.NotaRepositorio;
import com.braulionova.taskapp.repositorio.NotaRepositorioDbImpl;

import java.util.Calendar;
import java.util.Date;

public class RegistrarNotaActivity extends AppCompatActivity {

    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nota);

        final EditText txtMensajeNota = findViewById(R.id.txtMensajeNota);
        //bundle
        Bundle paraBundle = getIntent().getExtras();
        if(paraBundle != null && paraBundle.containsKey("tarea")) {
            tarea = (Tarea) paraBundle.getSerializable("tarea");
            //get usuario que inicio sesion
            final Usuario usuario = AppConfig.getConfig().getUsuario();

            //btnGuardarNota
            Button btnGuardarNota = findViewById(R.id.btnGuardarNota);
            btnGuardarNota.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    //validar el mensaje
                    String mensajeNota = txtMensajeNota.getText().toString();
                    if (TextUtils.isEmpty(mensajeNota)) {
                        txtMensajeNota.setError("El mensaje es requerido.");
                        txtMensajeNota.requestFocus();
                        return;
                    }
                    Nota nota = new Nota();
                    nota.setMensaje(mensajeNota);
                    //fecha
                    Date currentTime = Calendar.getInstance().getTime();
                    nota.setFecha(currentTime);
                    //usuario
                    nota.setUsuario(usuario);
                    //tarea
                    nota.setTarea(tarea);
                    //repositorio
                    NotaRepositorio notaRepositorio = new NotaRepositorioDbImpl(getApplicationContext());
                    notaRepositorio.guardar(nota);
                    //nos dirijimos al DetalleTareaActivity
                    Intent intent = new Intent(RegistrarNotaActivity.this, DetalleTareaActivity.class);
                    intent.putExtra("tarea", tarea);
                    startActivity(intent);
                }
            });
            //btnCancelarNota
            Button btnCancelarNota = findViewById(R.id.btnCancelarNota);
            btnCancelarNota.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Usuario usuario = AppConfig.getConfig().getUsuario();
                    //enviamos al menu de acuerdo al tipo de usuario o tecnico
                    //tecnico o usuario
                    if(usuario.getTipoUsuario().equals(Usuario.TipoUsuario.TECNICO)) {
                        Intent intent = new Intent(RegistrarNotaActivity.this, MenuTecnicoActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(RegistrarNotaActivity.this, MenuUsuarioActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
