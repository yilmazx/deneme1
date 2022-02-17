package com.startupsreactor.maya.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<R extends JpaRepository<E, ID>, E, DTO, ID extends Serializable> {

    protected final Logger log = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    protected R repository;

    @Autowired
    protected ObjectMapper objectMapper;

    protected E e;
    protected DTO d;

    public BaseService() {
        try {
            e = (E) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]).newInstance();
            d = (DTO) ((Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2]).newInstance();
        } catch (InstantiationException e) {
            log.error("BaseService.BaseService", e);
        } catch (IllegalAccessException e) {
            log.error("BaseService.BaseService", e);
        }
    }

    public DTO add(DTO dto) {
        return convertDTO((E) (repository.save(convertEntity(dto))));
    }

    public DTO save(DTO dto) {
        return add(dto);
    }

    public void delete(DTO dto) {
        repository.delete(convertEntity(dto));
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public Optional<DTO> get(ID id) {
        Optional<E> result = repository.findById(id);
        Optional<DTO> dto = Optional.empty();
        if (result.isPresent()) {
            dto = Optional.of(convertDTO(result.get()));
        }
        return dto;
    }

    public DTO findOne(ID id) {
        return get(id).get();
    }

    public List<DTO> getAll() {
        List<E> resultE = repository.findAll();
        List<DTO> resultD = resultE.stream().map(c -> convertDTO((E) c)).collect(Collectors.toList());
        return resultD;
    }

    public List<DTO> findAll() {
        return getAll();
    }

    protected E convertEntity(DTO dto) {
        E sonuc = (E) objectMapper.convertValue(dto, e.getClass());
        return sonuc;
    }

    protected DTO convertDTO(E entity) {
        return (DTO) objectMapper.convertValue(entity, d.getClass());
    }
}
