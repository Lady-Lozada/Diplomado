package com.developers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developers.model.Crime;

@Repository
public interface CrimeRepository extends JpaRepository<Crime, Long> {

}
