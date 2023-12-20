package com.konnect.app.repository;

import com.konnect.app.domain.MasterCircuit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MasterCircuit entity.
 *
 * When extending this class, extend MasterCircuitRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface MasterCircuitRepository extends MasterCircuitRepositoryWithBagRelationships, JpaRepository<MasterCircuit, Long> {
    default Optional<MasterCircuit> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<MasterCircuit> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<MasterCircuit> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
