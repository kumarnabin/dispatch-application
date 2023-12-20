package com.konnect.app.repository;

import com.konnect.app.domain.MasterCircuit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface MasterCircuitRepositoryWithBagRelationships {
    Optional<MasterCircuit> fetchBagRelationships(Optional<MasterCircuit> masterCircuit);

    List<MasterCircuit> fetchBagRelationships(List<MasterCircuit> masterCircuits);

    Page<MasterCircuit> fetchBagRelationships(Page<MasterCircuit> masterCircuits);
}
