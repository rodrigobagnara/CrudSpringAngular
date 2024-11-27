package com.myApi.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Produto extends AbstractModel {

    @Id
    @GeneratedValue(generator = "sequence_produto", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequence_produto", sequenceName = "sequence_produto", allocationSize = 1)
    private Long idProduto;

    private String descricao;

    private LocalDateTime dataFabricacao;

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
