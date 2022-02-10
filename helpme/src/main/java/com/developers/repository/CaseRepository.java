package com.developers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.developers.model.Case;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

	@Query("UPDATE Case c SET c.visible = ?1 WHERE c.idCase = ?2")
	Boolean setVisible(Boolean visible, Long id);
}
