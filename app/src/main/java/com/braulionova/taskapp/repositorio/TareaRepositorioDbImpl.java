package com.braulionova.taskapp.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.db.ConexionDb;

import java.util.List;

public class TareaRepositorioDbImpl implements TareaRepositorio {

    private ConexionDb conexionDb;
    //campos
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_USUARIO_CREADOR_ID = "usuario_creador_id";
    private static final String CAMPO_USUARIO_ASIGNADO_ID = "usuario_asignado_id";
    //estado
    private static final String CAMPO_ESTADO = "estado";
    //descripcion
    private static final String CAMPO_DESCRIPCION = "descripcion";
    //categoria_id
    private static final String CAMPO_CATEGORIA_ID = "categoria_id";
    //tabla tarea
    private static final String TABLA_TAREA = "tarea";

    public TareaRepositorioDbImpl(Context context)
    {
        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Tarea tarea) {
        if(tarea.getId() != null && tarea.getId() > 0)
        {
            return actualizar(tarea);
        }
        //datos de la tarea
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, tarea.getNombre());
        cv.put(CAMPO_FECHA, tarea.getFecha().toString());
        cv.put(CAMPO_USUARIO_CREADOR_ID, tarea.getUsuarioCreador().getId());
        cv.put(CAMPO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAgignado().getId());
        cv.put(CAMPO_ESTADO, tarea.getEstado().name());
        cv.put(CAMPO_DESCRIPCION, tarea.getDescripcion());
        cv.put(CAMPO_CATEGORIA_ID, tarea.getCategoria().getId());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id = db.insert(TABLA_TAREA,null, cv);
        //cerrando la db
        db.close();

        if(id.intValue() > 0)
        {
            tarea.setId(id.intValue());
            //retorna true si el usuario se guardo
            return true;
        }
        //retorna false si no guardo
        return false;
    }

    @Override
    public boolean actualizar(Tarea tarea) {
        return false;
    }

    @Override
    public Tarea buscar(int id) {
        return null;
    }

    @Override
    public List<Tarea> buscarAsignadaA(Usuario usuario) {
        return null;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario usuario) {
        return null;
    }
}
