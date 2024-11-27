package com.myApi.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myApi.app.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
