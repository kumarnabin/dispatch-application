package com.konnect.app.service;

import com.konnect.app.domain.Area;
import com.konnect.app.domain.Dispatch;
import com.konnect.app.domain.User;
import com.konnect.app.repository.DispatchRepository;
import com.konnect.app.repository.UserRepository;
import com.konnect.app.service.dto.DispatchDTO;
import com.konnect.app.service.mapper.DispatchMapper;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.Dispatch}.
 */
@Service
@Transactional
public class DispatchService {

    private final Logger log = LoggerFactory.getLogger(DispatchService.class);

    private final DispatchRepository dispatchRepository;

    private final DispatchMapper dispatchMapper;
    private final UserRepository userRepository;

    public DispatchService(DispatchRepository dispatchRepository, DispatchMapper dispatchMapper, UserRepository userRepository) {
        this.dispatchRepository = dispatchRepository;
        this.dispatchMapper = dispatchMapper;
        this.userRepository = userRepository;
    }

    /**
     * Save a dispatch.
     *
     * @param dispatchDTO the entity to save.
     * @return the persisted entity.
     */
    public DispatchDTO save(DispatchDTO dispatchDTO) {
        log.debug("Request to save Dispatch : {}", dispatchDTO);
        Dispatch dispatch = dispatchMapper.toEntity(dispatchDTO);
        dispatch = dispatchRepository.save(dispatch);
        return dispatchMapper.toDto(dispatch);
    }

    /**
     * Update a dispatch.
     *
     * @param dispatchDTO the entity to save.
     * @return the persisted entity.
     */
    public DispatchDTO update(DispatchDTO dispatchDTO) {
        log.debug("Request to update Dispatch : {}", dispatchDTO);
        Dispatch dispatch = dispatchMapper.toEntity(dispatchDTO);
        dispatch = dispatchRepository.save(dispatch);
        return dispatchMapper.toDto(dispatch);
    }

    /**
     * Partially update a dispatch.
     *
     * @param dispatchDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DispatchDTO> partialUpdate(DispatchDTO dispatchDTO) {
        log.debug("Request to partially update Dispatch : {}", dispatchDTO);

        return dispatchRepository
            .findById(dispatchDTO.getId())
            .map(existingDispatch -> {
                dispatchMapper.partialUpdate(existingDispatch, dispatchDTO);

                return existingDispatch;
            })
            .map(dispatchRepository::save)
            .map(dispatchMapper::toDto);
    }

    /**
     * Get all the dispatches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DispatchDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dispatches");
        return dispatchRepository.findAll(pageable).map(dispatchMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<Dispatch> findFilteredData(Pageable pageable) {
        log.debug("Request to get all Dispatches");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User authenticatedUser = userRepository.findByLogin(username).orElse(null);

        if (authenticatedUser != null && authenticatedUser.getEmployee() != null) {
            List<String> userAreas = authenticatedUser.getEmployee().getAreas().stream().map(Area::getCode).toList();

            return dispatchRepository.findAllByOltPortIn(userAreas, pageable);
        } else {
            return Page.empty(); // Or handle the case where user or employee information is not available
        }
    }

    /**
     * Get one dispatch by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DispatchDTO> findOne(Long id) {
        log.debug("Request to get Dispatch : {}", id);
        return dispatchRepository.findById(id).map(dispatchMapper::toDto);
    }

    /**
     * Delete the dispatch by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Dispatch : {}", id);
        dispatchRepository.deleteById(id);
    }
}
