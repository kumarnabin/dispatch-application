package com.konnect.app.repository;

import com.konnect.app.domain.BranchCircuit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface BranchCircuitRepositoryWithBagRelationships {
    Optional<BranchCircuit> fetchBagRelationships(Optional<BranchCircuit> branchCircuit);

    List<BranchCircuit> fetchBagRelationships(List<BranchCircuit> branchCircuits);

    Page<BranchCircuit> fetchBagRelationships(Page<BranchCircuit> branchCircuits);
}
