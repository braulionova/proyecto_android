package com.braulionova.taskapp.repositorio;

import com.braulionova.taskapp.entidad.Nota;

import java.util.List;

public interface NotaRepositorio {

    boolean guardar(Nota nota);

    List<Nota> buscarNotasPorTarea(Integer idTarea);

}
