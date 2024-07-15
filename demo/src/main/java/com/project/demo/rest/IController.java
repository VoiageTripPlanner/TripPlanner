package com.project.demo.rest;

import java.util.List;
import java.util.Optional;

public interface IController <T, ID>{
    T create(T entity);
    List<T> retrieveAll();
    Optional<T> retrieveById(ID id);
    T update(T entity);
    void deleteById(ID id);
}
