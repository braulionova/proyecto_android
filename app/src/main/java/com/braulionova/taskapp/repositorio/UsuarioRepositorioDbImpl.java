package com.braulionova.taskapp.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.braulionova.taskapp.entidad.Categoria;
import com.braulionova.taskapp.entidad.Usuario;
import com.braulionova.taskapp.repositorio.db.ConexionDb;

import java.util.ArrayList;
import java.util.List;

import se.simbio.encryption.Encryption;

public class UsuarioRepositorioDbImpl implements UsuarioRepositorio {

    private ConexionDb conexionDb;

    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_EMAIL = "email";
    private static final String CAMPO_CONTRASENA = "contrasena";
    private static final String CAMPO_TIPO_USUARIO = "tipoUsuario";
    private static final String TABLA_USUARIO = "usuario";


    public UsuarioRepositorioDbImpl(Context context)
    {
        conexionDb = new ConexionDb(context);
    }

    @Override
    public boolean guardar(Usuario usuario) {

        if(usuario.getId() != null && usuario.getId() > 0)
        {
            return actualizar(usuario);
        }
        //datos de categoria
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        Long id = db.insert(TABLA_USUARIO,null, cv);
        //cerrando la db
        db.close();

        if(id.intValue() > 0)
        {
            usuario.setId(id.intValue());
            //retorna true si el usuario se guardo
            return true;
        }
        //retorna false si no guardo
        return false;
    }

    @Override
    public boolean actualizar(Usuario usuario) {

        //datos
        ContentValues cv = new ContentValues();
        cv.put(CAMPO_NOMBRE, usuario.getNombre());
        cv.put(CAMPO_EMAIL, usuario.getEmail());
        //db
        SQLiteDatabase db = conexionDb.getWritableDatabase();
        int cantidad = db.update(TABLA_USUARIO, cv, "id = ?"
                , new String[]{usuario.getId().toString()});
        //cerrando la db
        db.close();
        //retorna false si no guardo
        return cantidad > 0;
    }

    @Override
    public Usuario buscar(int id) {
        return null;
    }

    @Override
    public List<Usuario> buscar(String buscar) {

        //TODO: buscar las categorias por su nombre (LIKE)

        List<Usuario> usuarios = new ArrayList<>();

        SQLiteDatabase db = conexionDb.getReadableDatabase();

        String[] columnas = {"email"};

        String whereClause = "email = ?";
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(buscar);

        String[] selectionArgs = new String[arrayList.size()];
        selectionArgs = arrayList.toArray(selectionArgs);

        //query
        Cursor cr = db.query(TABLA_USUARIO, columnas, whereClause, selectionArgs
        ,null, null,null);

        cr.moveToFirst();

        while (!cr.isAfterLast())
        {
            int id = cr.getInt(cr.getColumnIndex("id"));
            String nombre = cr.getString(cr.getColumnIndex(CAMPO_NOMBRE));
            String email = cr.getString(cr.getColumnIndex(CAMPO_EMAIL));
            Encryption encryption = Encryption.getDefault("NovaLab", "braulion", new byte[16]);
            String decrypted = encryption.decryptOrNull(cr.getString(cr.getColumnIndex(CAMPO_CONTRASENA)));
            String contrasena = decrypted;
            String tipoUsuario = cr.getString(cr.getColumnIndex(CAMPO_TIPO_USUARIO));


            Usuario u = new Usuario();
            u.setId(id);
            u.setNombre(nombre);
            u.setEmail(email);
            u.setContrasena(contrasena);
            if(tipoUsuario == "TECNICO") {
                u.setTipoUsuario(Usuario.TipoUsuario.TECNICO);
            }
            else
            {
                u.setTipoUsuario(Usuario.TipoUsuario.NORMAL);
            }
            //add categoria
            usuarios.add(u);
            //move next
            cr.moveToNext();
        }
        //close
        cr.close();
        db.close();
        //return
        return usuarios;
    }

}
