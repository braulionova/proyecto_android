package com.braulionova.taskapp.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.entidad.Nota;
import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.db.ConexionDb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotaRepositorioDbImpl implements NotaRepositorio {

    private ConexionDb conexionDb;
    private Context context;

    private static final String CAMPO_MENSAJE = "mensaje";
    private static final String CAMPO_FECHA = "fecha";
    private static final String CAMPO_USUARIO_ID = "usuario_id";
    private static final String CAMPO_TAREA_ID = "tarea_id";
    private static final String TABLA_NOTA = "nota";

    public NotaRepositorioDbImpl(Context context)
    {
        conexionDb = new ConexionDb(context);
        this.context = context;
    }


    @Override
    public boolean guardar(Nota nota) {
        //tabla tarea
        //SQLiteDatabase dbCreate = conexionDb.getWritableDatabase();
        //dbCreate.execSQL(EstructuraDb.TABLA_TAREA);
        //datos de la tarea
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_MENSAJE, nota.getMensaje());
        cv.put(CAMPO_FECHA, nota.getFecha().toString());
        cv.put(CAMPO_USUARIO_ID, nota.getUsuario().getId());
        cv.put(CAMPO_TAREA_ID, nota.getTarea().getId());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id = db.insert(TABLA_NOTA,null, cv);
        //cerrando la db
        db.close();

        if(id.intValue() > 0)
        {
            nota.setId(id.intValue());
            //retorna true si el usuario se guardo
            return true;
        }
        //retorna false si no guardo
        return false;
    }

    @Override
    public List<Nota> buscarNotasPorTarea(Integer idTarea) {
        //tabla tarea
        //SQLiteDatabase dbCreate = conexionDb.getWritableDatabase();
        //dbCreate.execSQL(EstructuraDb.TABLA_TAREA);
        //db
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        //columnas
        Cursor cr =  db.rawQuery("select * from " + TABLA_NOTA + " where " +  CAMPO_TAREA_ID + " = " + idTarea , null);

        cr.moveToFirst();

        List<Nota> notas = new ArrayList<>();
        Nota nota = null;

        //cursor
        if (cr.moveToFirst())
        {
            do {
                int id = cr.getInt(cr.getColumnIndex("id"));
                String mensaje = cr.getString(cr.getColumnIndex(CAMPO_MENSAJE));
                String fecha = cr.getString(cr.getColumnIndex(CAMPO_FECHA));
                String usuarioId = cr.getString(cr.getColumnIndex(CAMPO_USUARIO_ID));
                String tareaId = cr.getString(cr.getColumnIndex(CAMPO_TAREA_ID));

                //nota
                nota = new Nota();
                //id
                nota.setId(id);
                //mensaje
                nota.setMensaje(mensaje);
                //fecha
                Date fechaNota = null;
                try {
                    SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                    fechaNota = parser.parse(fecha);
                } catch (ParseException e) {
                    //e.printStackTrace();
                }
                nota.setFecha(fechaNota);
                //usuarioId
                UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorioDbImpl(this.context);
                Usuario usuario = usuarioRepositorio.buscar(Integer.parseInt(usuarioId));
                nota.setUsuario(usuario);
                //tareaId
                TareaRepositorio tareaRepositorio = new TareaRepositorioDbImpl(this.context);
                Tarea tarea = tareaRepositorio.buscar(Integer.parseInt(tareaId));
                nota.setTarea(tarea);
                //agregar tarea a la lista
                notas.add(nota);
            }
            while (cr.moveToNext());
        }
        //close
        cr.close();
        db.close();
        //return
        return notas;
    }
}
