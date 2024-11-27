package com.myApi.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myApi.app.dto.ProdutoDTO;
import com.myApi.app.model.Produto;
import com.myApi.app.service.CRUDService;
import com.myApi.app.service.ProdutoService;

@RestController
@RequestMapping("produto")
public class ProdutoController extends AbstractController<Produto, ProdutoDTO>{

    @Autowired
    private ProdutoService produtoService;

    @Override
    public Class<Produto> getModelClass() {
        return Produto.class;
    }

    @Override
    public Class<ProdutoDTO> getDTOClass() {
        return ProdutoDTO.class;
    }

    @Override
    public CRUDService<Produto> getService() {
        return produtoService;
    }

}