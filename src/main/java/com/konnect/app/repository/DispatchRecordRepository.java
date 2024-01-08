package com.konnect.app.repository;

import com.konnect.app.domain.DispatchRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DispatchRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DispatchRecordRepository extends JpaRepository<DispatchRecord, Long> {}
