package com.konnect.app.repository;

import com.konnect.app.domain.Dispatch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Dispatch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {}
