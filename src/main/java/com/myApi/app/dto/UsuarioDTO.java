package com.myApi.app.dto;

import java.time.LocalDateTime;

/**
 * @author Rodrigo Bagnara
 * @date 22/10/2024
 */

public class UsuarioDTO extends AbstractDTO{

    private Long idUsuario;
    private String login;
    private String password;
    private LocalDateTime data;

    @Override
    public Long getId() {
        return idUsuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
