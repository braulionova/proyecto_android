package com.braulionova.taskapp.repositorio.db;

public class EstructuraDb {
    //tabla Categoria
    public static final String TABLA_CATEGORIA =
            "CREATE TABLE categoria ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT )";
    //tabla Usuario
    public static final String TABLA_USUARIO =
            "CREATE TABLE usuario ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT)";
    //tabla usuario_categoria
    public static final String TABLA_USUARIO_CATEGORIA =
            "CREATE TABLE usuario_categoria ( usuario_id INTEGER, categoria_id INTEGER)";
    //tabla tarea
    public static final String TABLA_TAREA =
            "CREATE TABLE tarea ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, fecha NUMERIC, usuario_creador_id INTEGER," +
                    " usuario_asignado_id INTEGER, estado TEXT DEFAULT 'PENDIENTE', " +
                    "descripcion TEXT, categoria_id INTEGER)";

}
