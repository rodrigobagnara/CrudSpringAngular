package com.myApi.app.service.implementation;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myApi.app.exception.BusinessException;
import com.myApi.app.model.AbstractModel;
import com.myApi.app.service.CRUDService;

public abstract class CRUDServiceImplementation<T extends AbstractModel> implements CRUDService<T> {

    @Override
    public T save(T model) {
        return getRepository().save(model);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Optional<T> findById(Long id) {
        return getRepository().findById(id);
    }

    @Override
    public T update(T model) {
        if(Objects.isNull(model.getId())) {
            throw new BusinessException("Entidade " + model + "sem ID para atualizacao");
        }
        return getRepository().save(model);
    }

    @Override
    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    public abstract JpaRepository<T, Long> getRepository();

}
