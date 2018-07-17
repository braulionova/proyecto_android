package com.braulionova.taskapp.repositorio;

import com.braulionova.taskapp.entidad.Usuario;

import java.util.List;

public interface UsuarioRepositorio {

    boolean guardar(Usuario usuario);

    boolean actualizar(Usuario usuario);

    Usuario buscar(int id);

    Usuario validarUsuario(String email, String contrasena);

    List<Usuario> buscar(String nombre);

    List<Usuario> buscar_todos();

}
