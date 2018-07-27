package com.braulionova.taskapp.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.repositorio.db.ConexionDb;

import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioImp implements CategoriaRepositorio {

    private ConexionDb conexionDb;

    private static final String CAMPO_NOMBRE = "nombre";
    private static final String TABLA_CATEGORIA = "categoria";

    public CategoriaRepositorioImp(Context context)
    {
        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Categoria categoria) {

        if(categoria.getId() != null && categoria.getId() > 0)
        {
            return actualizar(categoria);
        }
        //datos de categoria
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, categoria.getNombre());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id = db.insert(TABLA_CATEGORIA,null, cv);
        //cerrando la db
        db.close();

        if(id.intValue() > 0)
        {
            categoria.setId(id.intValue());
            //retorna true si la categoria se guardo
            return true;
        }
        //retorna false si la categoria no guardo
        return false;
    }

    @Override
    public boolean actualizar(Categoria categoria) {

        //datos de categoria
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, categoria.getNombre());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        int cantidad = db.update(TABLA_CATEGORIA, cv, "id = ?"
                , new String[]{categoria.getId().toString()});
        //cerrando la db
        db.close();
        //retorna false si la categoria no guardo
        return cantidad > 0;
    }

    @Override
    public Categoria buscar(int id) {
        return null;
    }

    @Override
    public Categoria buscarPorNombre(String nombre) {
        SQLiteDatabase db = conexionDb.getReadableDatabase();

        //columnas
        Cursor cr =  db.rawQuery("select * from " + TABLA_CATEGORIA + " where nombre = '" + nombre + "'" , null);

        cr.moveToFirst();

        Categoria categoria = null;

        while (!cr.isAfterLast())
        {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre_categoria = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));

            categoria = new Categoria();
            categoria.setId(id);
            categoria.setNombre(nombre_categoria);
            //move next
            cr.moveToNext();
        }
        //close
        cr.close();
        db.close();
        //return
        return categoria;
    }

    @Override
    public List<Categoria> buscar(String buscar) {

        //TODO: buscar las categorias por su nombre (LIKE)

        List<Categoria> categorias = new ArrayList<>();

        SQLiteDatabase db = conexionDb.getReadableDatabase();

        String[] columnas = {"id", CAMPO_NOMBRE};
        //query
        Cursor cr = db.query(TABLA_CATEGORIA, columnas, null, null
        ,null, null,null);

        cr.moveToFirst();

        while (!cr.isAfterLast())
        {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));

            Categoria c = new Categoria();
            c.setId(id);
            c.setNombre(nombre);
            //add categoria
            categorias.add(c);
            //move next
            cr.moveToNext();
        }
        //close
        cr.close();
        db.close();
        //return
        return categorias;
    }

}
