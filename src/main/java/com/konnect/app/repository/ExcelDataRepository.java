package com.konnect.app.repository;

import com.konnect.app.domain.ExcelData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ExcelData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExcelDataRepository extends JpaRepository<ExcelData, Long> {}
