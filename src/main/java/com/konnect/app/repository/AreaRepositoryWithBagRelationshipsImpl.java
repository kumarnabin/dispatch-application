package com.konnect.app.repository;

import com.konnect.app.domain.Area;
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
public class AreaRepositoryWithBagRelationshipsImpl implements AreaRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Area> fetchBagRelationships(Optional<Area> area) {
        return area.map(this::fetchEmployees);
    }

    @Override
    public Page<Area> fetchBagRelationships(Page<Area> areas) {
        return new PageImpl<>(fetchBagRelationships(areas.getContent()), areas.getPageable(), areas.getTotalElements());
    }

    @Override
    public List<Area> fetchBagRelationships(List<Area> areas) {
        return Optional.of(areas).map(this::fetchEmployees).orElse(Collections.emptyList());
    }

    Area fetchEmployees(Area result) {
        return entityManager
            .createQuery("select area from Area area left join fetch area.employees where area.id = :id", Area.class)
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<Area> fetchEmployees(List<Area> areas) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, areas.size()).forEach(index -> order.put(areas.get(index).getId(), index));
        List<Area> result = entityManager
            .createQuery("select area from Area area left join fetch area.employees where area in :areas", Area.class)
            .setParameter("areas", areas)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
