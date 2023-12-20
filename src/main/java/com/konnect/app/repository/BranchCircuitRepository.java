package com.konnect.app.repository;

import com.konnect.app.domain.BranchCircuit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BranchCircuit entity.
 *
 * When extending this class, extend BranchCircuitRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface BranchCircuitRepository extends BranchCircuitRepositoryWithBagRelationships, JpaRepository<BranchCircuit, Long> {
    default Optional<BranchCircuit> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<BranchCircuit> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<BranchCircuit> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
