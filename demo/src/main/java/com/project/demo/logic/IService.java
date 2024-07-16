package com.project.demo.logic;

import java.util.List;
import java.util.Optional;

public interface IService <T, ID>{
    /**
     * Save or update an entity.
     *
     * @param entity the entity to save or update
     * @return the saved entity
     */
    T save(T entity);

    /**
     * Retrieve all entities.
     *
     * @return a list of all entities
     */
    List<T> findAll();

    /**
     * Retrieve an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return the entity, if found
     */
    Optional<T> findById(ID id);

    /**
     * Update an existing entity.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

    /**
     * Delete an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    void deleteById(ID id);
}
