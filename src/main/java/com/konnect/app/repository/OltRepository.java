package com.konnect.app.repository;

import com.konnect.app.domain.Olt;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Olt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OltRepository extends JpaRepository<Olt, Long> {}
