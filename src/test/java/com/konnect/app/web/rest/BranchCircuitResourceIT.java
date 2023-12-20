package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.BranchCircuit;
import com.konnect.app.repository.BranchCircuitRepository;
import com.konnect.app.service.BranchCircuitService;
import com.konnect.app.service.dto.BranchCircuitDTO;
import com.konnect.app.service.mapper.BranchCircuitMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link BranchCircuitResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class BranchCircuitResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/branch-circuits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BranchCircuitRepository branchCircuitRepository;

    @Mock
    private BranchCircuitRepository branchCircuitRepositoryMock;

    @Autowired
    private BranchCircuitMapper branchCircuitMapper;

    @Mock
    private BranchCircuitService branchCircuitServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBranchCircuitMockMvc;

    private BranchCircuit branchCircuit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchCircuit createEntity(EntityManager em) {
        BranchCircuit branchCircuit = new BranchCircuit().title(DEFAULT_TITLE).publicationDate(DEFAULT_PUBLICATION_DATE);
        return branchCircuit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BranchCircuit createUpdatedEntity(EntityManager em) {
        BranchCircuit branchCircuit = new BranchCircuit().title(UPDATED_TITLE).publicationDate(UPDATED_PUBLICATION_DATE);
        return branchCircuit;
    }

    @BeforeEach
    public void initTest() {
        branchCircuit = createEntity(em);
    }

    @Test
    @Transactional
    void createBranchCircuit() throws Exception {
        int databaseSizeBeforeCreate = branchCircuitRepository.findAll().size();
        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);
        restBranchCircuitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isCreated());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeCreate + 1);
        BranchCircuit testBranchCircuit = branchCircuitList.get(branchCircuitList.size() - 1);
        assertThat(testBranchCircuit.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBranchCircuit.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createBranchCircuitWithExistingId() throws Exception {
        // Create the BranchCircuit with an existing ID
        branchCircuit.setId(1L);
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        int databaseSizeBeforeCreate = branchCircuitRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBranchCircuitMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBranchCircuits() throws Exception {
        // Initialize the database
        branchCircuitRepository.saveAndFlush(branchCircuit);

        // Get all the branchCircuitList
        restBranchCircuitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(branchCircuit.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBranchCircuitsWithEagerRelationshipsIsEnabled() throws Exception {
        when(branchCircuitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBranchCircuitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(branchCircuitServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBranchCircuitsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(branchCircuitServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restBranchCircuitMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(branchCircuitRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getBranchCircuit() throws Exception {
        // Initialize the database
        branchCircuitRepository.saveAndFlush(branchCircuit);

        // Get the branchCircuit
        restBranchCircuitMockMvc
            .perform(get(ENTITY_API_URL_ID, branchCircuit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(branchCircuit.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBranchCircuit() throws Exception {
        // Get the branchCircuit
        restBranchCircuitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBranchCircuit() throws Exception {
        // Initialize the database
        branchCircuitRepository.saveAndFlush(branchCircuit);

        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();

        // Update the branchCircuit
        BranchCircuit updatedBranchCircuit = branchCircuitRepository.findById(branchCircuit.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBranchCircuit are not directly saved in db
        em.detach(updatedBranchCircuit);
        updatedBranchCircuit.title(UPDATED_TITLE).publicationDate(UPDATED_PUBLICATION_DATE);
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(updatedBranchCircuit);

        restBranchCircuitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchCircuitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isOk());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
        BranchCircuit testBranchCircuit = branchCircuitList.get(branchCircuitList.size() - 1);
        assertThat(testBranchCircuit.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBranchCircuit.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingBranchCircuit() throws Exception {
        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();
        branchCircuit.setId(longCount.incrementAndGet());

        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchCircuitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, branchCircuitDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBranchCircuit() throws Exception {
        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();
        branchCircuit.setId(longCount.incrementAndGet());

        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchCircuitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBranchCircuit() throws Exception {
        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();
        branchCircuit.setId(longCount.incrementAndGet());

        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchCircuitMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBranchCircuitWithPatch() throws Exception {
        // Initialize the database
        branchCircuitRepository.saveAndFlush(branchCircuit);

        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();

        // Update the branchCircuit using partial update
        BranchCircuit partialUpdatedBranchCircuit = new BranchCircuit();
        partialUpdatedBranchCircuit.setId(branchCircuit.getId());

        restBranchCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchCircuit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchCircuit))
            )
            .andExpect(status().isOk());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
        BranchCircuit testBranchCircuit = branchCircuitList.get(branchCircuitList.size() - 1);
        assertThat(testBranchCircuit.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBranchCircuit.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateBranchCircuitWithPatch() throws Exception {
        // Initialize the database
        branchCircuitRepository.saveAndFlush(branchCircuit);

        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();

        // Update the branchCircuit using partial update
        BranchCircuit partialUpdatedBranchCircuit = new BranchCircuit();
        partialUpdatedBranchCircuit.setId(branchCircuit.getId());

        partialUpdatedBranchCircuit.title(UPDATED_TITLE).publicationDate(UPDATED_PUBLICATION_DATE);

        restBranchCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBranchCircuit.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBranchCircuit))
            )
            .andExpect(status().isOk());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
        BranchCircuit testBranchCircuit = branchCircuitList.get(branchCircuitList.size() - 1);
        assertThat(testBranchCircuit.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBranchCircuit.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingBranchCircuit() throws Exception {
        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();
        branchCircuit.setId(longCount.incrementAndGet());

        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBranchCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, branchCircuitDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBranchCircuit() throws Exception {
        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();
        branchCircuit.setId(longCount.incrementAndGet());

        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBranchCircuit() throws Exception {
        int databaseSizeBeforeUpdate = branchCircuitRepository.findAll().size();
        branchCircuit.setId(longCount.incrementAndGet());

        // Create the BranchCircuit
        BranchCircuitDTO branchCircuitDTO = branchCircuitMapper.toDto(branchCircuit);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBranchCircuitMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(branchCircuitDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BranchCircuit in the database
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBranchCircuit() throws Exception {
        // Initialize the database
        branchCircuitRepository.saveAndFlush(branchCircuit);

        int databaseSizeBeforeDelete = branchCircuitRepository.findAll().size();

        // Delete the branchCircuit
        restBranchCircuitMockMvc
            .perform(delete(ENTITY_API_URL_ID, branchCircuit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BranchCircuit> branchCircuitList = branchCircuitRepository.findAll();
        assertThat(branchCircuitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
