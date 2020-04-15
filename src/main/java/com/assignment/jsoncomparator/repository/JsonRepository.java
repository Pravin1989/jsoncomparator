package com.assignment.jsoncomparator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assignment.jsoncomparator.domain.Json;

/**
 * @author Pravin
 *
 */
@Repository("jsonRepository")
public interface JsonRepository extends JpaRepository<Json, Long> {

	@Modifying
	@Query("UPDATE Json j set j.jsonNode = :jsonObject where j.jsonId = :id")
	void updateJson(@Param("id") Long jsonId, @Param("jsonObject") byte[] jsonInBytes);
}
