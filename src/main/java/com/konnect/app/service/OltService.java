package com.konnect.app.service;

import com.konnect.app.domain.Olt;
import com.konnect.app.repository.OltRepository;
import com.konnect.app.service.dto.OltDTO;
import com.konnect.app.service.mapper.OltMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.Olt}.
 */
@Service
@Transactional
public class OltService {

    private final Logger log = LoggerFactory.getLogger(OltService.class);

    private final OltRepository oltRepository;

    private final OltMapper oltMapper;

    public OltService(OltRepository oltRepository, OltMapper oltMapper) {
        this.oltRepository = oltRepository;
        this.oltMapper = oltMapper;
    }

    /**
     * Save a olt.
     *
     * @param oltDTO the entity to save.
     * @return the persisted entity.
     */
    public OltDTO save(OltDTO oltDTO) {
        log.debug("Request to save Olt : {}", oltDTO);
        Olt olt = oltMapper.toEntity(oltDTO);
        olt = oltRepository.save(olt);
        return oltMapper.toDto(olt);
    }

    /**
     * Update a olt.
     *
     * @param oltDTO the entity to save.
     * @return the persisted entity.
     */
    public OltDTO update(OltDTO oltDTO) {
        log.debug("Request to update Olt : {}", oltDTO);
        Olt olt = oltMapper.toEntity(oltDTO);
        olt = oltRepository.save(olt);
        return oltMapper.toDto(olt);
    }

    /**
     * Partially update a olt.
     *
     * @param oltDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OltDTO> partialUpdate(OltDTO oltDTO) {
        log.debug("Request to partially update Olt : {}", oltDTO);

        return oltRepository
            .findById(oltDTO.getId())
            .map(existingOlt -> {
                oltMapper.partialUpdate(existingOlt, oltDTO);

                return existingOlt;
            })
            .map(oltRepository::save)
            .map(oltMapper::toDto);
    }

    /**
     * Get all the olts.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OltDTO> findAll() {
        log.debug("Request to get all Olts");
        return oltRepository.findAll().stream().map(oltMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one olt by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OltDTO> findOne(Long id) {
        log.debug("Request to get Olt : {}", id);
        return oltRepository.findById(id).map(oltMapper::toDto);
    }

    /**
     * Delete the olt by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Olt : {}", id);
        oltRepository.deleteById(id);
    }
}
