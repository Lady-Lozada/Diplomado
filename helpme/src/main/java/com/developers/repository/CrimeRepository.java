package com.developers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.developers.model.Role;

@Repository
public interface CrimeRepository extends CrudRepository<Role, Integer> {

}
