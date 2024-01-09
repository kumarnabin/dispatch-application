package com.konnect.app.service;

import com.konnect.app.domain.DispatchRecord;
import com.konnect.app.repository.DispatchRecordRepository;
import com.konnect.app.service.dto.DispatchRecordDTO;
import com.konnect.app.service.mapper.DispatchRecordMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.DispatchRecord}.
 */
@Service
@Transactional
public class DispatchRecordService {

    private final Logger log = LoggerFactory.getLogger(DispatchRecordService.class);

    private final DispatchRecordRepository dispatchRecordRepository;

    private final DispatchRecordMapper dispatchRecordMapper;

    public DispatchRecordService(DispatchRecordRepository dispatchRecordRepository, DispatchRecordMapper dispatchRecordMapper) {
        this.dispatchRecordRepository = dispatchRecordRepository;
        this.dispatchRecordMapper = dispatchRecordMapper;
    }

    /**
     * Save a dispatchRecord.
     *
     * @param dispatchRecordDTO the entity to save.
     * @return the persisted entity.
     */
    public DispatchRecordDTO save(DispatchRecordDTO dispatchRecordDTO) {
        log.debug("Request to save DispatchRecord : {}", dispatchRecordDTO);
        DispatchRecord dispatchRecord = dispatchRecordMapper.toEntity(dispatchRecordDTO);
        dispatchRecord = dispatchRecordRepository.save(dispatchRecord);
        return dispatchRecordMapper.toDto(dispatchRecord);
    }

    /**
     * Update a dispatchRecord.
     *
     * @param dispatchRecordDTO the entity to save.
     * @return the persisted entity.
     */
    public DispatchRecordDTO update(DispatchRecordDTO dispatchRecordDTO) {
        log.debug("Request to update DispatchRecord : {}", dispatchRecordDTO);
        DispatchRecord dispatchRecord = dispatchRecordMapper.toEntity(dispatchRecordDTO);
        dispatchRecord = dispatchRecordRepository.save(dispatchRecord);
        return dispatchRecordMapper.toDto(dispatchRecord);
    }

    /**
     * Partially update a dispatchRecord.
     *
     * @param dispatchRecordDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DispatchRecordDTO> partialUpdate(DispatchRecordDTO dispatchRecordDTO) {
        log.debug("Request to partially update DispatchRecord : {}", dispatchRecordDTO);

        return dispatchRecordRepository
            .findById(dispatchRecordDTO.getId())
            .map(existingDispatchRecord -> {
                dispatchRecordMapper.partialUpdate(existingDispatchRecord, dispatchRecordDTO);

                return existingDispatchRecord;
            })
            .map(dispatchRecordRepository::save)
            .map(dispatchRecordMapper::toDto);
    }

    /**
     * Get all the dispatchRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DispatchRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DispatchRecords");
        return dispatchRecordRepository.findAll(pageable).map(dispatchRecordMapper::toDto);
    }

    /**
     * Get one dispatchRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DispatchRecordDTO> findOne(Long id) {
        log.debug("Request to get DispatchRecord : {}", id);
        return dispatchRecordRepository.findById(id).map(dispatchRecordMapper::toDto);
    }

    /**
     * Delete the dispatchRecord by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DispatchRecord : {}", id);
        dispatchRecordRepository.deleteById(id);
    }
}
