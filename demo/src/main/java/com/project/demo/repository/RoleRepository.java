package com.project.demo.repository;

import com.project.demo.entity.Role;
import com.project.demo.entity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name); // Asegúrate de que el nombre coincida con la propiedad de la entidad
}
