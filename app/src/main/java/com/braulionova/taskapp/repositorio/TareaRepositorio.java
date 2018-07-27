package com.braulionova.taskapp.repositorio;


import com.braulionova.taskapp.entidad.Tarea;
import com.braulionova.taskapp.entidad.Usuario;

import java.util.List;

public interface TareaRepositorio {

    boolean guardar(Tarea tarea);

    boolean actualizar(Tarea tarea);

    Tarea buscar(int id);

    List<Tarea> buscarAsignadaA(Usuario usuario);

    List<Tarea> buscarCreadaPor(Usuario usuario);

}
