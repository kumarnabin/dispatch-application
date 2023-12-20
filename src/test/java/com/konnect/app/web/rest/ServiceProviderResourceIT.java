package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.ServiceProvider;
import com.konnect.app.repository.ServiceProviderRepository;
import com.konnect.app.service.dto.ServiceProviderDTO;
import com.konnect.app.service.mapper.ServiceProviderMapper;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ServiceProviderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServiceProviderResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/service-providers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private ServiceProviderMapper serviceProviderMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceProviderMockMvc;

    private ServiceProvider serviceProvider;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceProvider createEntity(EntityManager em) {
        ServiceProvider serviceProvider = new ServiceProvider().name(DEFAULT_NAME).address(DEFAULT_ADDRESS);
        return serviceProvider;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceProvider createUpdatedEntity(EntityManager em) {
        ServiceProvider serviceProvider = new ServiceProvider().name(UPDATED_NAME).address(UPDATED_ADDRESS);
        return serviceProvider;
    }

    @BeforeEach
    public void initTest() {
        serviceProvider = createEntity(em);
    }

    @Test
    @Transactional
    void createServiceProvider() throws Exception {
        int databaseSizeBeforeCreate = serviceProviderRepository.findAll().size();
        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);
        restServiceProviderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceProvider testServiceProvider = serviceProviderList.get(serviceProviderList.size() - 1);
        assertThat(testServiceProvider.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceProvider.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    void createServiceProviderWithExistingId() throws Exception {
        // Create the ServiceProvider with an existing ID
        serviceProvider.setId(1L);
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        int databaseSizeBeforeCreate = serviceProviderRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceProviderMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllServiceProviders() throws Exception {
        // Initialize the database
        serviceProviderRepository.saveAndFlush(serviceProvider);

        // Get all the serviceProviderList
        restServiceProviderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceProvider.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }

    @Test
    @Transactional
    void getServiceProvider() throws Exception {
        // Initialize the database
        serviceProviderRepository.saveAndFlush(serviceProvider);

        // Get the serviceProvider
        restServiceProviderMockMvc
            .perform(get(ENTITY_API_URL_ID, serviceProvider.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceProvider.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    void getNonExistingServiceProvider() throws Exception {
        // Get the serviceProvider
        restServiceProviderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingServiceProvider() throws Exception {
        // Initialize the database
        serviceProviderRepository.saveAndFlush(serviceProvider);

        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();

        // Update the serviceProvider
        ServiceProvider updatedServiceProvider = serviceProviderRepository.findById(serviceProvider.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedServiceProvider are not directly saved in db
        em.detach(updatedServiceProvider);
        updatedServiceProvider.name(UPDATED_NAME).address(UPDATED_ADDRESS);
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(updatedServiceProvider);

        restServiceProviderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceProviderDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isOk());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
        ServiceProvider testServiceProvider = serviceProviderList.get(serviceProviderList.size() - 1);
        assertThat(testServiceProvider.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceProvider.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void putNonExistingServiceProvider() throws Exception {
        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();
        serviceProvider.setId(longCount.incrementAndGet());

        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceProviderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, serviceProviderDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServiceProvider() throws Exception {
        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();
        serviceProvider.setId(longCount.incrementAndGet());

        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceProviderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServiceProvider() throws Exception {
        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();
        serviceProvider.setId(longCount.incrementAndGet());

        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceProviderMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServiceProviderWithPatch() throws Exception {
        // Initialize the database
        serviceProviderRepository.saveAndFlush(serviceProvider);

        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();

        // Update the serviceProvider using partial update
        ServiceProvider partialUpdatedServiceProvider = new ServiceProvider();
        partialUpdatedServiceProvider.setId(serviceProvider.getId());

        partialUpdatedServiceProvider.address(UPDATED_ADDRESS);

        restServiceProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceProvider.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceProvider))
            )
            .andExpect(status().isOk());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
        ServiceProvider testServiceProvider = serviceProviderList.get(serviceProviderList.size() - 1);
        assertThat(testServiceProvider.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceProvider.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void fullUpdateServiceProviderWithPatch() throws Exception {
        // Initialize the database
        serviceProviderRepository.saveAndFlush(serviceProvider);

        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();

        // Update the serviceProvider using partial update
        ServiceProvider partialUpdatedServiceProvider = new ServiceProvider();
        partialUpdatedServiceProvider.setId(serviceProvider.getId());

        partialUpdatedServiceProvider.name(UPDATED_NAME).address(UPDATED_ADDRESS);

        restServiceProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServiceProvider.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedServiceProvider))
            )
            .andExpect(status().isOk());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
        ServiceProvider testServiceProvider = serviceProviderList.get(serviceProviderList.size() - 1);
        assertThat(testServiceProvider.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceProvider.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void patchNonExistingServiceProvider() throws Exception {
        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();
        serviceProvider.setId(longCount.incrementAndGet());

        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, serviceProviderDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServiceProvider() throws Exception {
        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();
        serviceProvider.setId(longCount.incrementAndGet());

        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceProviderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServiceProvider() throws Exception {
        int databaseSizeBeforeUpdate = serviceProviderRepository.findAll().size();
        serviceProvider.setId(longCount.incrementAndGet());

        // Create the ServiceProvider
        ServiceProviderDTO serviceProviderDTO = serviceProviderMapper.toDto(serviceProvider);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServiceProviderMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(serviceProviderDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ServiceProvider in the database
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServiceProvider() throws Exception {
        // Initialize the database
        serviceProviderRepository.saveAndFlush(serviceProvider);

        int databaseSizeBeforeDelete = serviceProviderRepository.findAll().size();

        // Delete the serviceProvider
        restServiceProviderMockMvc
            .perform(delete(ENTITY_API_URL_ID, serviceProvider.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceProvider> serviceProviderList = serviceProviderRepository.findAll();
        assertThat(serviceProviderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
