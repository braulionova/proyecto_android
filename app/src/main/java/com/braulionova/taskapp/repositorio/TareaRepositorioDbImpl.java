package com.braulionova.taskapp.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.db.ConexionDb;
import com.braulionova.taskapp.repositorio.db.EstructuraDb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TareaRepositorioDbImpl implements TareaRepositorio {

    private ConexionDb conexionDb;
    private Context context;
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
        this.context = context;
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
        cv.put(CAMPO_USUARIO_ASIGNADO_ID, tarea.getUsuarioAsignado().getId());
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
    public List<Tarea> buscarAsignadaA(Usuario usuario) throws ParseException {
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        //columnas
        Cursor cr =  db.rawQuery("select * from " + TABLA_TAREA + " where " +  CAMPO_USUARIO_ASIGNADO_ID + " = " + usuario.getId().toString() , null);

        cr.moveToFirst();

        List<Tarea> tareas = new ArrayList<>();
        Tarea tarea = null;

        //cursor
        if (cr.moveToFirst())
        {
            do {
                int id = cr.getInt(cr.getColumnIndex("id"));
                String nombreTarea = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
                String fecha = cr.getString(cr.getColumnIndex(CAMPO_FECHA));
                String usuarioCreadorId = cr.getString(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID));
                String usuarioAsignadoId = cr.getString(cr.getColumnIndex(CAMPO_USUARIO_ASIGNADO_ID));
                String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));
                String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRIPCION));
                String categoriaId = cr.getString(cr.getColumnIndex(CAMPO_CATEGORIA_ID));

                //usuario
                tarea = new Tarea();
                //id
                tarea.setId(id);
                tarea.setNombre(nombreTarea);
                //fecha
                Date fechaTarea = null;
                try {
                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                    fechaTarea = parser.parse(fecha);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
                tarea.setFecha(fechaTarea);
                //usuario creador
                UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorioDbImpl(this.context);
                Usuario usuarioCreador = usuarioRepositorio.buscar(Integer.parseInt(usuarioCreadorId));
                tarea.setUsuarioCreador(usuarioCreador);
                //usuario asignado
                Usuario usuarioAsignado = usuarioRepositorio.buscar(Integer.parseInt(usuarioAsignadoId));
                tarea.setUsuarioAsignado(usuarioAsignado);
                //estado
                tarea.setEstado(Tarea.TareaEstado.valueOf(estado));
                tarea.setDescripcion(descripcion);
                //buscar categoria
                CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorioImp(this.context);
                Categoria categoria = categoriaRepositorio.buscar(Integer.parseInt(categoriaId));
                tarea.setCategoria(categoria);
                //agregar tarea a la lista
                tareas.add(tarea);
            }
            while (cr.moveToNext());
        }
        //close
        cr.close();
        db.close();
        //return
        return tareas;
    }

    @Override
    public List<Tarea> buscarCreadaPor(Usuario usuario) {
        //tabla tarea
        //SQLiteDatabase dbCreate = conexionDb.getWritableDatabase();
        //dbCreate.execSQL(EstructuraDb.TABLA_TAREA);
        //db
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        //columnas
        Cursor cr =  db.rawQuery("select * from " + TABLA_TAREA + " where " +  CAMPO_USUARIO_CREADOR_ID + " = " + usuario.getId().toString() , null);

        cr.moveToFirst();

        List<Tarea> tareas = new ArrayList<>();
        Tarea tarea = null;

        //cursor
        if (cr.moveToFirst())
        {
            do {
                int id = cr.getInt(cr.getColumnIndex("id"));
                String nombreTarea = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
                String fecha = cr.getString(cr.getColumnIndex(CAMPO_FECHA));
                String usuarioCreadorId = cr.getString(cr.getColumnIndex(CAMPO_USUARIO_CREADOR_ID));
                String usuarioAsignadoId = cr.getString(cr.getColumnIndex(CAMPO_USUARIO_ASIGNADO_ID));
                String estado = cr.getString(cr.getColumnIndex(CAMPO_ESTADO));
                String descripcion = cr.getString(cr.getColumnIndex(CAMPO_DESCRIPCION));
                String categoriaId = cr.getString(cr.getColumnIndex(CAMPO_CATEGORIA_ID));

                //usuario
                tarea = new Tarea();
                //id
                tarea.setId(id);
                //nombre tarea
                //tarea.setNombre("Asignado id: " + usuarioAsignadoId);
                tarea.setNombre(nombreTarea);
                //fecha
                Date fechaTarea = null;
                try {
                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                    fechaTarea = parser.parse(fecha);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
                tarea.setFecha(fechaTarea);
                //usuario creador
                UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorioDbImpl(this.context);
                Usuario usuarioCreador = usuarioRepositorio.buscar(Integer.parseInt(usuarioCreadorId));
                tarea.setUsuarioCreador(usuarioCreador);
                //usuario asignado
                Usuario usuarioAsignado = usuarioRepositorio.buscar(Integer.parseInt(usuarioAsignadoId));
                tarea.setUsuarioAsignado(usuarioAsignado);
                //estado
                tarea.setEstado(Tarea.TareaEstado.valueOf(estado));
                tarea.setDescripcion(descripcion);
                //buscar categoria
                CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorioImp(this.context);
                Categoria categoria = categoriaRepositorio.buscar(Integer.parseInt(categoriaId));
                tarea.setCategoria(categoria);
                //agregar tarea a la lista
                tareas.add(tarea);
            }
            while (cr.moveToNext());
        }
        //close
        cr.close();
        db.close();
        //return
        return tareas;
    }
}
