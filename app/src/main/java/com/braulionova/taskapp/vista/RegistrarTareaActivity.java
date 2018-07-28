package com.braulionova.taskapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.braulionova.taskapp.R;
import com.braulionova.taskapp.entidad.AppConfig;
import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.CategoriaRepositorio;
import com.braulionova.taskapp.repositorio.CategoriaRepositorioImp;
import com.braulionova.taskapp.repositorio.TareaRepositorio;
import com.braulionova.taskapp.repositorio.TareaRepositorioDbImpl;
import com.braulionova.taskapp.repositorio.UsuarioRepositorio;
import com.braulionova.taskapp.repositorio.UsuarioRepositorioDbImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegistrarTareaActivity extends AppCompatActivity {

    private CategoriaRepositorio categoriaRepositorio;
    private UsuarioRepositorio usuarioRepositorio;
    private TareaRepositorio tareaRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tarea);
        //categoria repositorio
        categoriaRepositorio = new CategoriaRepositorioImp(this);
        //list
        List<Categoria> categorias = categoriaRepositorio.buscar(null);
        //descripcion
        final EditText txtDescripcion = findViewById(R.id.txtDescripcionTarea);
        //array list
        //List<String> categorias_array_list = new ArrayList<>(categorias.size());
        //for (Categoria categoria : categorias) {
        //    categorias_array_list.add(categoria != null ? categoria.getNombre() : null);
        //}
        //spinner_categorias
        final Spinner spinnerCategorias = findViewById(R.id.spinner_categorias);
        // create the adapter.
        final ArrayAdapter<Categoria> spinner_adapter_categorias = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_dropdown_item, categorias);
        spinner_adapter_categorias.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerCategorias.setAdapter(spinner_adapter_categorias);
        //usuario repositorio
        usuarioRepositorio = new UsuarioRepositorioDbImpl(this);
        List<Usuario> listTecnicos = usuarioRepositorio.buscar_tecnicos();
        //array list
        //List<String> tecnicos_array_list = new ArrayList<>(listTecnicos.size());
        //for (Usuario usuario : listTecnicos) {
        //    tecnicos_array_list.add(usuario != null ? usuario.getNombre() : null);
        //}
        //spinner_categorias
        final Spinner spinnerTecnicos = findViewById(R.id.spinner_tecnicos);
        // create the adapter.
        ArrayAdapter<Usuario> spinner_adapter_tecnicos = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_dropdown_item, listTecnicos);
        spinner_adapter_tecnicos.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerTecnicos.setAdapter(spinner_adapter_tecnicos);
        //btnCancelarTarea
        Button btnCancelarTarea = findViewById(R.id.btnCancelarTarea);
        //on click
        btnCancelarTarea.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(RegistrarTareaActivity.this, MenuUsuarioActivity.class);
                startActivity(intent);
            }
        });

        //btnGuardarTarea
        Button btnGuardarTarea = findViewById(R.id.btnGuardarTarea);
        //on click
        btnGuardarTarea.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(TextUtils.isEmpty(txtDescripcion.getText())) {
                    txtDescripcion.setError("Descripción es requerido.");
                    txtDescripcion.requestFocus();
                    return;
                }
                tareaRepositorio = new TareaRepositorioDbImpl(getApplicationContext());
                //tarea
                Tarea tarea = null;
                //String textCategoria = spinnerCategorias.getSelectedItem().toString();
                Categoria categoria = (Categoria) spinnerCategorias.getSelectedItem();
                //String textTecnico = spinnerTecnicos.getSelectedItem().toString();
                Usuario usuario_asignado = (Usuario) spinnerTecnicos.getSelectedItem();
                //nuevo
                boolean nuevo = false;

                if(tarea == null)
                {
                    nuevo = true;
                    tarea = new Tarea();
                }
                //nombre
                tarea.setNombre("Tarea reportada");
                //descripcion
                tarea.setDescripcion(txtDescripcion.getText().toString());
                //estado
                tarea.setEstado(Tarea.TareaEstado.PENDIENTE);
                //fecha
                Date currentTime = Calendar.getInstance().getTime();
                tarea.setFecha(currentTime);
                //mensaje
                //Toast toastFecha = Toast.makeText(getApplicationContext(), "Fecha: " + currentTime.toString(), Toast.LENGTH_SHORT);
                //toastFecha.show();
                //categoria_id
                tarea.setCategoria(categoria);
                //Toast toastCategoria = Toast.makeText(getApplicationContext(), "Categoria: " + categoria.getId() + " " + categoria.getNombre(), Toast.LENGTH_SHORT);
                //toastCategoria.show();
                //usuario_asignado_id
                tarea.setUsuarioAgignado(usuario_asignado);
                //Toast toastAsignado = Toast.makeText(getApplicationContext(), "Asignado: " + usuario_asignado.getId() + " " + usuario_asignado.getNombre(), Toast.LENGTH_SHORT);
                //toastAsignado.show();
                tarea.setUsuarioCreador(AppConfig.getConfig().getUsuario());
                //Usuario usuarioCreador = AppConfig.getConfig().getUsuario();
                //Toast toastUsuarioCreador = Toast.makeText(getApplicationContext(), "Usuario Creador: " + usuarioCreador.getId() + " " + usuarioCreador.getNombre(), Toast.LENGTH_LONG);
                //toastUsuarioCreador.show();

                if(nuevo) {
                    //guardar
                    tareaRepositorio.guardar(tarea);

                    //mensaje
                    Toast toastSave = Toast.makeText(getApplicationContext(), "Guardado correctamente.", Toast.LENGTH_SHORT);
                    toastSave.show();
                    //en blanco luego de guardar
                    spinnerCategorias.setId(0);
                    spinnerTecnicos.setId(0);
                    txtDescripcion.setText("");

                }
                else {
                    //tareaRepositorio.actualizar(tarea);
                    //mensaje
                    Toast toast_update = Toast.makeText(getApplicationContext(), "Actualizado correctamente.", Toast.LENGTH_SHORT);
                    //toast1.setGravity(Gravity.CENTER,,);
                    toast_update.show();
                    //intent
                    Intent regTareaIntent = new Intent(RegistrarTareaActivity.this, MenuUsuarioActivity.class);
                    startActivity(regTareaIntent);
                }
            }
        });
    }
}
