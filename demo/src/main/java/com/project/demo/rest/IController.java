package com.project.demo.rest;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

public interface IController <T, ID>{
    ResponseEntity<T> create(T entity);
    ResponseEntity<List<T>> retrieveAll();
    ResponseEntity<T> retrieveById(ID id);
    ResponseEntity<T> update(T entity);
    ResponseEntity<Void> deleteById(ID id);
}
