package com.braulionova.taskapp.repositorio;

import com.braulionova.taskapp.entidad.Categoria;

import java.util.List;

public interface CategoriaRepositorio {

    boolean guardar(Categoria categoria);

    boolean actualizar(Categoria categoria);

    Categoria buscar(int id);

    Categoria buscarPorNombre(String nombre);

    List<Categoria> buscar(String nombre);

}
