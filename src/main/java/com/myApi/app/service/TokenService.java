package com.myApi.app.service;

import com.myApi.app.model.Usuario;

public interface TokenService {

    public String gerarToken(Usuario usuario);

    public String getSubject(String token);

}
