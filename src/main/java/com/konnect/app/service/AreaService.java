package com.konnect.app.service;

import com.konnect.app.domain.Area;
import com.konnect.app.repository.AreaRepository;
import com.konnect.app.service.dto.AreaDTO;
import com.konnect.app.service.mapper.AreaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.Area}.
 */
@Service
@Transactional
public class AreaService {

    private final Logger log = LoggerFactory.getLogger(AreaService.class);

    private final AreaRepository areaRepository;

    private final AreaMapper areaMapper;

    public AreaService(AreaRepository areaRepository, AreaMapper areaMapper) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
    }

    /**
     * Save a area.
     *
     * @param areaDTO the entity to save.
     * @return the persisted entity.
     */
    public AreaDTO save(AreaDTO areaDTO) {
        log.debug("Request to save Area : {}", areaDTO);
        Area area = areaMapper.toEntity(areaDTO);
        area = areaRepository.save(area);
        return areaMapper.toDto(area);
    }

    /**
     * Update a area.
     *
     * @param areaDTO the entity to save.
     * @return the persisted entity.
     */
    public AreaDTO update(AreaDTO areaDTO) {
        log.debug("Request to update Area : {}", areaDTO);
        Area area = areaMapper.toEntity(areaDTO);
        area = areaRepository.save(area);
        return areaMapper.toDto(area);
    }

    /**
     * Partially update a area.
     *
     * @param areaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AreaDTO> partialUpdate(AreaDTO areaDTO) {
        log.debug("Request to partially update Area : {}", areaDTO);

        return areaRepository
            .findById(areaDTO.getId())
            .map(existingArea -> {
                areaMapper.partialUpdate(existingArea, areaDTO);

                return existingArea;
            })
            .map(areaRepository::save)
            .map(areaMapper::toDto);
    }

    /**
     * Get all the areas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AreaDTO> findAll() {
        log.debug("Request to get all Areas");
        return areaRepository.findAll().stream().map(areaMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the areas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<AreaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return areaRepository.findAllWithEagerRelationships(pageable).map(areaMapper::toDto);
    }

    /**
     * Get one area by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AreaDTO> findOne(Long id) {
        log.debug("Request to get Area : {}", id);
        return areaRepository.findOneWithEagerRelationships(id).map(areaMapper::toDto);
    }

    /**
     * Delete the area by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Area : {}", id);
        areaRepository.deleteById(id);
    }
}
