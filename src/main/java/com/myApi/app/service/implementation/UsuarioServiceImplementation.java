package com.myApi.app.service.implementation;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myApi.app.exception.BusinessException;
import com.myApi.app.model.Usuario;
import com.myApi.app.repository.UsuarioRepository;
import com.myApi.app.service.UsuarioService;

@Service
public class UsuarioServiceImplementation extends CRUDServiceImplementation<Usuario> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JpaRepository<Usuario, Long> getRepository() {
        return usuarioRepository;
    }

    @Override
    public Usuario save(Usuario model) {
        encodePassword(model);
        return super.save(model);
    }

    @Override
    public Usuario update(Usuario model) {
        if (isSenhaAlterada(model)) {
            encodePassword(model);
        }
        return super.update(model);
    }

    private boolean isSenhaAlterada(Usuario usuario) {

        Usuario usuarioBanco = findById(usuario.getId()).orElseThrow(() -> new BusinessException("Usuario nao encontrado para atualizacao"));

        if(Objects.nonNull(usuario.getPassword())) {
            return !passwordEncoder.matches(usuario.getPassword(), usuarioBanco.getPassword());
        }
        return false;
    }

    private void encodePassword(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
    }

}
