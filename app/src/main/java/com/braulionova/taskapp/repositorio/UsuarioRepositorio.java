package com.braulionova.taskapp.repositorio;

import com.braulionova.taskapp.entidad.Usuario;

import java.util.List;

public interface UsuarioRepositorio {

    boolean guardar(Usuario usuario);

    boolean actualizar(Usuario usuario);

    Usuario buscar(int id);

    List<Usuario> buscar(String nombre);

}
