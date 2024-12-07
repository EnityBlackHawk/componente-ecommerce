package com.example.componenteecommerce.service;

import org.hibernate.validator.internal.metadata.aggregated.ClassMetaData;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class GenericService<TEntity, TPk, TRepo extends JpaRepository<TEntity, TPk>, TDto, TCreateDTO> {

    private final TRepo repository;
    private final ModelMapper modelMapper;
    private final Class<TEntity> entityClass;
    private final Class<TDto> dtoClass;
    private final Class<TCreateDTO> createDtoClass;

    protected GenericService(TRepo repository, Class<TEntity> entityClass, Class<TDto> dtoClass, Class<TCreateDTO> createDtoClass) {
        this.repository = repository;
        this.modelMapper = new ModelMapper();
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
        this.createDtoClass = createDtoClass;
    }


    public TDto create(TCreateDTO entity) {
        TEntity result = repository.save(modelMapper.map(entity, entityClass));
        var dto = modelMapper.map(result, dtoClass);
        return dto;
    }

    public void delete(TPk id) {
        repository.deleteById(id);
    }

    public TDto findById(TPk id) {
        TEntity result = repository.findById(id).orElse(null);
        return result != null ? modelMapper.map(result, dtoClass) : null;
    }

    public List<TDto> findAll() {
        List<TEntity> result = repository.findAll();
        return result.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .toList();
    }

}
