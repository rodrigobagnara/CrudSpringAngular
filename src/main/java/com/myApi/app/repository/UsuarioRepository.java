package com.myApi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myApi.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByLogin(String login);

}
