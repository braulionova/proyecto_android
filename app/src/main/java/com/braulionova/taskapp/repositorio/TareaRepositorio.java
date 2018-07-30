package com.braulionova.taskapp.repositorio;


import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;

import java.text.ParseException;
import java.util.List;

public interface TareaRepositorio {

    boolean guardar(Tarea tarea);

    boolean actualizar(Tarea tarea);

    void actualizarEstatus(Tarea tarea);

    Tarea buscar(int id);

    List<Tarea> buscarAsignadaA(Usuario usuario) throws ParseException;

    List<Tarea> buscarCreadaPor(Usuario usuario);

}
