package com.braulionova.taskapp.entidad;
import java.io.Serializable;

public class Categoria implements Serializable{
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public Categoria setId(Integer id) {
        this.id = id;
        //return
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria setNombre(String nombre) {
        this.nombre = nombre;
        //return
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(nombre);
        return sb.toString();
    }
}
