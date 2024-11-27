package com.myApi.app.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoDTO extends AbstractDTO {


    private Long idProduto;

    @NotBlank(message = "Descricao é obrigatoria")
    private String descricao;

    @NotNull(message = "Data de fabricacao é obrigatória")
    private LocalDateTime dataFabricacao;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDateTime dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public Long getId() {
        return idProduto;
    }

}
