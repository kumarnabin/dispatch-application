package com.konnect.app.repository;

import com.konnect.app.domain.BranchCircuit;
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
public class BranchCircuitRepositoryWithBagRelationshipsImpl implements BranchCircuitRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<BranchCircuit> fetchBagRelationships(Optional<BranchCircuit> branchCircuit) {
        return branchCircuit.map(this::fetchBranches);
    }

    @Override
    public Page<BranchCircuit> fetchBagRelationships(Page<BranchCircuit> branchCircuits) {
        return new PageImpl<>(
            fetchBagRelationships(branchCircuits.getContent()),
            branchCircuits.getPageable(),
            branchCircuits.getTotalElements()
        );
    }

    @Override
    public List<BranchCircuit> fetchBagRelationships(List<BranchCircuit> branchCircuits) {
        return Optional.of(branchCircuits).map(this::fetchBranches).orElse(Collections.emptyList());
    }

    BranchCircuit fetchBranches(BranchCircuit result) {
        return entityManager
            .createQuery(
                "select branchCircuit from BranchCircuit branchCircuit left join fetch branchCircuit.branches where branchCircuit.id = :id",
                BranchCircuit.class
            )
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<BranchCircuit> fetchBranches(List<BranchCircuit> branchCircuits) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, branchCircuits.size()).forEach(index -> order.put(branchCircuits.get(index).getId(), index));
        List<BranchCircuit> result = entityManager
            .createQuery(
                "select branchCircuit from BranchCircuit branchCircuit left join fetch branchCircuit.branches where branchCircuit in :branchCircuits",
                BranchCircuit.class
            )
            .setParameter("branchCircuits", branchCircuits)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
