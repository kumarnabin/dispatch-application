package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.MasterCircuit;
import com.konnect.app.repository.MasterCircuitRepository;
import com.konnect.app.service.MasterCircuitService;
import com.konnect.app.service.dto.MasterCircuitDTO;
import com.konnect.app.service.mapper.MasterCircuitMapper;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link MasterCircuitResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class MasterCircuitResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/master-circuits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MasterCircuitRepository masterCircuitRepository;

    @Mock
    private MasterCircuitRepository masterCircuitRepositoryMock;

    @Autowired
    private MasterCircuitMapper masterCircuitMapper;

    @Mock
    private MasterCircuitService masterCircuitServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMasterCircuitMockMvc;

    private MasterCircuit masterCircuit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterCircuit createEntity(EntityManager em) {
        MasterCircuit masterCircuit = new MasterCircuit().name(DEFAULT_NAME).address(DEFAULT_ADDRESS);
        return masterCircuit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterCircuit createUpdatedEntity(EntityManager em) {
        MasterCircuit masterCircuit = new MasterCircuit().name(UPDATED_NAME).address(UPDATED_ADDRESS);
        return masterCircuit;
    }

    @BeforeEach
    public void initTest() {
        masterCircuit = createEntity(em);
    }

    @Test
    @Transactional
    void createMasterCircuit() throws Exception {
        int databaseSizeBeforeCreate = masterCircuitRepository.findAll().size();
        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);
        restMasterCircuitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isCreated());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeCreate + 1);
        MasterCircuit testMasterCircuit = masterCircuitList.get(masterCircuitList.size() - 1);
        assertThat(testMasterCircuit.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMasterCircuit.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    void createMasterCircuitWithExistingId() throws Exception {
        // Create the MasterCircuit with an existing ID
        masterCircuit.setId(1L);
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        int databaseSizeBeforeCreate = masterCircuitRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasterCircuitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMasterCircuits() throws Exception {
        // Initialize the database
        masterCircuitRepository.saveAndFlush(masterCircuit);

        // Get all the masterCircuitList
        restMasterCircuitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masterCircuit.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMasterCircuitsWithEagerRelationshipsIsEnabled() throws Exception {
        when(masterCircuitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMasterCircuitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(masterCircuitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMasterCircuitsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(masterCircuitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMasterCircuitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(masterCircuitRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getMasterCircuit() throws Exception {
        // Initialize the database
        masterCircuitRepository.saveAndFlush(masterCircuit);

        // Get the masterCircuit
        restMasterCircuitMockMvc
            .perform(get(ENTITY_API_URL_ID, masterCircuit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(masterCircuit.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS));
    }

    @Test
    @Transactional
    void getNonExistingMasterCircuit() throws Exception {
        // Get the masterCircuit
        restMasterCircuitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMasterCircuit() throws Exception {
        // Initialize the database
        masterCircuitRepository.saveAndFlush(masterCircuit);

        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();

        // Update the masterCircuit
        MasterCircuit updatedMasterCircuit = masterCircuitRepository.findById(masterCircuit.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMasterCircuit are not directly saved in db
        em.detach(updatedMasterCircuit);
        updatedMasterCircuit.name(UPDATED_NAME).address(UPDATED_ADDRESS);
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(updatedMasterCircuit);

        restMasterCircuitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, masterCircuitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isOk());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
        MasterCircuit testMasterCircuit = masterCircuitList.get(masterCircuitList.size() - 1);
        assertThat(testMasterCircuit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMasterCircuit.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void putNonExistingMasterCircuit() throws Exception {
        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();
        masterCircuit.setId(longCount.incrementAndGet());

        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasterCircuitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, masterCircuitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMasterCircuit() throws Exception {
        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();
        masterCircuit.setId(longCount.incrementAndGet());

        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMasterCircuitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMasterCircuit() throws Exception {
        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();
        masterCircuit.setId(longCount.incrementAndGet());

        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMasterCircuitMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMasterCircuitWithPatch() throws Exception {
        // Initialize the database
        masterCircuitRepository.saveAndFlush(masterCircuit);

        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();

        // Update the masterCircuit using partial update
        MasterCircuit partialUpdatedMasterCircuit = new MasterCircuit();
        partialUpdatedMasterCircuit.setId(masterCircuit.getId());

        partialUpdatedMasterCircuit.name(UPDATED_NAME);

        restMasterCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMasterCircuit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMasterCircuit))
            )
            .andExpect(status().isOk());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
        MasterCircuit testMasterCircuit = masterCircuitList.get(masterCircuitList.size() - 1);
        assertThat(testMasterCircuit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMasterCircuit.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    void fullUpdateMasterCircuitWithPatch() throws Exception {
        // Initialize the database
        masterCircuitRepository.saveAndFlush(masterCircuit);

        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();

        // Update the masterCircuit using partial update
        MasterCircuit partialUpdatedMasterCircuit = new MasterCircuit();
        partialUpdatedMasterCircuit.setId(masterCircuit.getId());

        partialUpdatedMasterCircuit.name(UPDATED_NAME).address(UPDATED_ADDRESS);

        restMasterCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMasterCircuit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMasterCircuit))
            )
            .andExpect(status().isOk());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
        MasterCircuit testMasterCircuit = masterCircuitList.get(masterCircuitList.size() - 1);
        assertThat(testMasterCircuit.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMasterCircuit.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    void patchNonExistingMasterCircuit() throws Exception {
        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();
        masterCircuit.setId(longCount.incrementAndGet());

        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasterCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, masterCircuitDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMasterCircuit() throws Exception {
        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();
        masterCircuit.setId(longCount.incrementAndGet());

        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMasterCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMasterCircuit() throws Exception {
        int databaseSizeBeforeUpdate = masterCircuitRepository.findAll().size();
        masterCircuit.setId(longCount.incrementAndGet());

        // Create the MasterCircuit
        MasterCircuitDTO masterCircuitDTO = masterCircuitMapper.toDto(masterCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMasterCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(masterCircuitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MasterCircuit in the database
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMasterCircuit() throws Exception {
        // Initialize the database
        masterCircuitRepository.saveAndFlush(masterCircuit);

        int databaseSizeBeforeDelete = masterCircuitRepository.findAll().size();

        // Delete the masterCircuit
        restMasterCircuitMockMvc
            .perform(delete(ENTITY_API_URL_ID, masterCircuit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MasterCircuit> masterCircuitList = masterCircuitRepository.findAll();
        assertThat(masterCircuitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
