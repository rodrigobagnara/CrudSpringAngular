package com.myApi.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.myApi.app.dto.AbstractDTO;
import com.myApi.app.model.AbstractModel;
import com.myApi.app.service.CRUDService;

import jakarta.validation.Valid;

public abstract class AbstractController<E extends AbstractModel, DTO extends AbstractDTO> {

    @Autowired
    private ModelMapper modelMapper;

    public abstract Class<E> getModelClass();
    public abstract Class<DTO> getDTOClass();
    public abstract CRUDService<E> getService();

    @GetMapping
    public ResponseEntity<List<DTO>> findAll() {

        List<E> models = getService().findAll();
        List<DTO> listaDTO = convertToDTO(models);
        return ResponseEntity.status(HttpStatus.OK).body(listaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable Long id) {

        Optional<E> model = getService().findById(id);
        DTO dto = convertToDTO(model);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) {

        E model = convertToEntity(dto);
        getService().save(model);
        dto = convertToDTO(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping
    public ResponseEntity<DTO> update(@Valid @RequestBody DTO dto) {

        E model = convertToEntity(dto);
        getService().update(model);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public E convertToEntity(Optional<DTO> dto) {
        return convertToEntity(dto.orElse(null));
    }

    public DTO convertToDTO(Optional<E> entity) {
        return convertToDTO(entity.orElse(null));
    }

    public E convertToEntity(DTO dto) {
        return modelMapper.map(dto, getModelClass());
    }

    public DTO convertToDTO(E entity) {
        return modelMapper.map(entity, getDTOClass());
    }

    public List<E> convertToEntity(List<DTO> dtoList) {
        List<E> arrayList = new ArrayList<>();
        dtoList.forEach(dto -> arrayList.add(convertToEntity(dto)));
        return arrayList;
    }

    public List<DTO> convertToDTO(List<E> entityList) {
        List<DTO> arrayList = new ArrayList<>();
        entityList.forEach(entity -> arrayList.add(convertToDTO(entity)));
        return arrayList;
    }

}
