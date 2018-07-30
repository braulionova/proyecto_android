package com.braulionova.taskapp.entidad;

import java.io.Serializable;
import java.util.Date;

public class Nota implements Serializable {

    private Integer id;
    private String mensaje;
    private Date fecha;
    private Usuario usuario;
    private Tarea tarea;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Nota{");
        sb.append("id=").append(id);
        sb.append(", mensaje='").append(mensaje).append('\'');
        sb.append(", fecha=").append(fecha);
        sb.append(", usuario=").append(usuario);
        sb.append(", tarea=").append(tarea);
        sb.append('}');
        return sb.toString();
    }
}
