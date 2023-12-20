package com.konnect.app.repository;

import com.konnect.app.domain.MasterCircuit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class MasterCircuitRepositoryWithBagRelationshipsImpl implements MasterCircuitRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<MasterCircuit> fetchBagRelationships(Optional<MasterCircuit> masterCircuit) {
        return masterCircuit.map(this::fetchServiceProviders);
    }

    @Override
    public Page<MasterCircuit> fetchBagRelationships(Page<MasterCircuit> masterCircuits) {
        return new PageImpl<>(
            fetchBagRelationships(masterCircuits.getContent()),
            masterCircuits.getPageable(),
            masterCircuits.getTotalElements()
        );
    }

    @Override
    public List<MasterCircuit> fetchBagRelationships(List<MasterCircuit> masterCircuits) {
        return Optional.of(masterCircuits).map(this::fetchServiceProviders).orElse(Collections.emptyList());
    }

    MasterCircuit fetchServiceProviders(MasterCircuit result) {
        return entityManager
            .createQuery(
                "select masterCircuit from MasterCircuit masterCircuit left join fetch masterCircuit.serviceProviders where masterCircuit.id = :id",
                MasterCircuit.class
            )
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<MasterCircuit> fetchServiceProviders(List<MasterCircuit> masterCircuits) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, masterCircuits.size()).forEach(index -> order.put(masterCircuits.get(index).getId(), index));
        List<MasterCircuit> result = entityManager
            .createQuery(
                "select masterCircuit from MasterCircuit masterCircuit left join fetch masterCircuit.serviceProviders where masterCircuit in :masterCircuits",
                MasterCircuit.class
            )
            .setParameter("masterCircuits", masterCircuits)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
