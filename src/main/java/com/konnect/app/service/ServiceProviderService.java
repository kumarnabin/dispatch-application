package com.konnect.app.service;

import com.konnect.app.domain.ServiceProvider;
import com.konnect.app.repository.ServiceProviderRepository;
import com.konnect.app.service.dto.ServiceProviderDTO;
import com.konnect.app.service.mapper.ServiceProviderMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.konnect.app.domain.ServiceProvider}.
 */
@Service
@Transactional
public class ServiceProviderService {

    private final Logger log = LoggerFactory.getLogger(ServiceProviderService.class);

    private final ServiceProviderRepository serviceProviderRepository;

    private final ServiceProviderMapper serviceProviderMapper;

    public ServiceProviderService(ServiceProviderRepository serviceProviderRepository, ServiceProviderMapper serviceProviderMapper) {
        this.serviceProviderRepository = serviceProviderRepository;
        this.serviceProviderMapper = serviceProviderMapper;
    }

    /**
     * Save a serviceProvider.
     *
     * @param serviceProviderDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceProviderDTO save(ServiceProviderDTO serviceProviderDTO) {
        log.debug("Request to save ServiceProvider : {}", serviceProviderDTO);
        ServiceProvider serviceProvider = serviceProviderMapper.toEntity(serviceProviderDTO);
        serviceProvider = serviceProviderRepository.save(serviceProvider);
        return serviceProviderMapper.toDto(serviceProvider);
    }

    /**
     * Update a serviceProvider.
     *
     * @param serviceProviderDTO the entity to save.
     * @return the persisted entity.
     */
    public ServiceProviderDTO update(ServiceProviderDTO serviceProviderDTO) {
        log.debug("Request to update ServiceProvider : {}", serviceProviderDTO);
        ServiceProvider serviceProvider = serviceProviderMapper.toEntity(serviceProviderDTO);
        serviceProvider = serviceProviderRepository.save(serviceProvider);
        return serviceProviderMapper.toDto(serviceProvider);
    }

    /**
     * Partially update a serviceProvider.
     *
     * @param serviceProviderDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ServiceProviderDTO> partialUpdate(ServiceProviderDTO serviceProviderDTO) {
        log.debug("Request to partially update ServiceProvider : {}", serviceProviderDTO);

        return serviceProviderRepository
            .findById(serviceProviderDTO.getId())
            .map(existingServiceProvider -> {
                serviceProviderMapper.partialUpdate(existingServiceProvider, serviceProviderDTO);

                return existingServiceProvider;
            })
            .map(serviceProviderRepository::save)
            .map(serviceProviderMapper::toDto);
    }

    /**
     * Get all the serviceProviders.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ServiceProviderDTO> findAll() {
        log.debug("Request to get all ServiceProviders");
        return serviceProviderRepository
            .findAll()
            .stream()
            .map(serviceProviderMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one serviceProvider by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ServiceProviderDTO> findOne(Long id) {
        log.debug("Request to get ServiceProvider : {}", id);
        return serviceProviderRepository.findById(id).map(serviceProviderMapper::toDto);
    }

    /**
     * Delete the serviceProvider by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ServiceProvider : {}", id);
        serviceProviderRepository.deleteById(id);
    }
}
