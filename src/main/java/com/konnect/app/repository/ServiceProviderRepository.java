package com.konnect.app.repository;

import com.konnect.app.domain.ServiceProvider;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ServiceProvider entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {}
