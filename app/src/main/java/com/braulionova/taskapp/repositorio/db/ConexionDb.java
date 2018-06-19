package com.braulionova.taskapp.repositorio.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ConexionDb extends SQLiteOpenHelper{

    private static final String LOG_TAG = "ConexionDb";
    private static final String NOMBRE_DB = "taskpp.db";
    private static final int VERSION_DB = 1;

    //CONEXION DB
    public ConexionDb(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creado tablas
        Log.i(LOG_TAG, "Creando la base de datos");
        //
       // db.execSQL(EstructuraDb.TABLA_CATEGORIA);
        //tabla categoria
        db.execSQL(EstructuraDb.TABLA_CATEGORIA);
        //tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: lo veremos una segunda etapa DB Upgrade
        db.execSQL("DROP TABLE categoria");
        this.onCreate(db);

    }
}
