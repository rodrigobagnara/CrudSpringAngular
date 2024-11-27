package com.myApi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApi.app.dto.LoginDTO;
import com.myApi.app.dto.TokenDTO;
import com.myApi.app.dto.UsuarioDTO;
import com.myApi.app.exception.BusinessException;
import com.myApi.app.model.Usuario;
import com.myApi.app.service.CRUDService;
import com.myApi.app.service.TokenService;
import com.myApi.app.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController extends AbstractController<Usuario, UsuarioDTO>{

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public TokenDTO login(@Valid @RequestBody LoginDTO login) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            Usuario usuario = (Usuario)authenticate.getPrincipal();
            String token = tokenService.gerarToken(usuario);
            return new TokenDTO(token);
        } catch (Exception e) {
            throw new BusinessException("Login ou senha incorreto!", e);
        }
    }

    @Override
    public Class<Usuario> getModelClass() {
        return Usuario.class;
    }

    @Override
    public Class<UsuarioDTO> getDTOClass() {
        return UsuarioDTO.class;
    }

    @Override
    public CRUDService<Usuario> getService() {
        return usuarioService;
    }


}
