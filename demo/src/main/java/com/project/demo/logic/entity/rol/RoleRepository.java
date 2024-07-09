package com.project.demo.logic.entity.rol;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);  // Asegúrate de que el nombre coincida con la propiedad de la entidad
}
