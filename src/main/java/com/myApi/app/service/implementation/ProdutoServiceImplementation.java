package com.myApi.app.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.myApi.app.model.Produto;
import com.myApi.app.repository.ProdutoRepository;
import com.myApi.app.service.ProdutoService;

@Service
public class ProdutoServiceImplementation extends CRUDServiceImplementation<Produto> implements ProdutoService  {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public JpaRepository<Produto, Long> getRepository() {
        return produtoRepository;
    }

}
