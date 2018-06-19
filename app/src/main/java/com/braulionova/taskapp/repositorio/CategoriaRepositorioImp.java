package com.braulionova.taskapp.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.repositorio.db.ConexionDb;

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
        //datos de categoria
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, categoria.getDescripcion());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id = db.insert(TABLA_CATEGORIA,null, cv);

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
        return false;
    }

    @Override
    public Categoria buscar(int id) {
        return null;
    }

    @Override
    public List<Categoria> buscar(String nombre) {
        return null;
    }

}
