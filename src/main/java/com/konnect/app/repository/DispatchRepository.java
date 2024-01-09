package com.konnect.app.repository;

import com.konnect.app.domain.Area;
import com.konnect.app.domain.Dispatch;
import com.konnect.app.service.dto.DispatchDTO;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Dispatch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
    Page<Dispatch> findAllByOltPortIn(List<String> userAreas, Pageable pageable);
}
