package com.braulionova.taskapp.entidad;

public class Categoria {
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
        final StringBuffer sb = new StringBuffer("Categoria{");
        sb.append("id=").append(id);
        sb.append(", descripcion='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
