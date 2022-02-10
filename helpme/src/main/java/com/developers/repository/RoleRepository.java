package com.developers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.model.Case;

@Repository
public interface RoleRepository extends JpaRepository<Case, Long> {

}
