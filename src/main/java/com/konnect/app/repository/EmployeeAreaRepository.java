package com.konnect.app.repository;

import com.konnect.app.domain.EmployeeArea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EmployeeArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeAreaRepository extends JpaRepository<EmployeeArea, Long> {}
