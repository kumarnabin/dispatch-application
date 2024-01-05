package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.Olt;
import com.konnect.app.domain.enumeration.Status;
import com.konnect.app.repository.OltRepository;
import com.konnect.app.service.dto.OltDTO;
import com.konnect.app.service.mapper.OltMapper;
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
 * Integration tests for the {@link OltResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OltResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.WAITING_FOR_RESPONSE;

    private static final String ENTITY_API_URL = "/api/olts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OltRepository oltRepository;

    @Autowired
    private OltMapper oltMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOltMockMvc;

    private Olt olt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Olt createEntity(EntityManager em) {
        Olt olt = new Olt().name(DEFAULT_NAME).detail(DEFAULT_DETAIL).status(DEFAULT_STATUS);
        return olt;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Olt createUpdatedEntity(EntityManager em) {
        Olt olt = new Olt().name(UPDATED_NAME).detail(UPDATED_DETAIL).status(UPDATED_STATUS);
        return olt;
    }

    @BeforeEach
    public void initTest() {
        olt = createEntity(em);
    }

    @Test
    @Transactional
    void createOlt() throws Exception {
        int databaseSizeBeforeCreate = oltRepository.findAll().size();
        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);
        restOltMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oltDTO)))
            .andExpect(status().isCreated());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeCreate + 1);
        Olt testOlt = oltList.get(oltList.size() - 1);
        assertThat(testOlt.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testOlt.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testOlt.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createOltWithExistingId() throws Exception {
        // Create the Olt with an existing ID
        olt.setId(1L);
        OltDTO oltDTO = oltMapper.toDto(olt);

        int databaseSizeBeforeCreate = oltRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOltMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oltDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOlts() throws Exception {
        // Initialize the database
        oltRepository.saveAndFlush(olt);

        // Get all the oltList
        restOltMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(olt.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getOlt() throws Exception {
        // Initialize the database
        oltRepository.saveAndFlush(olt);

        // Get the olt
        restOltMockMvc
            .perform(get(ENTITY_API_URL_ID, olt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(olt.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.detail").value(DEFAULT_DETAIL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOlt() throws Exception {
        // Get the olt
        restOltMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOlt() throws Exception {
        // Initialize the database
        oltRepository.saveAndFlush(olt);

        int databaseSizeBeforeUpdate = oltRepository.findAll().size();

        // Update the olt
        Olt updatedOlt = oltRepository.findById(olt.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOlt are not directly saved in db
        em.detach(updatedOlt);
        updatedOlt.name(UPDATED_NAME).detail(UPDATED_DETAIL).status(UPDATED_STATUS);
        OltDTO oltDTO = oltMapper.toDto(updatedOlt);

        restOltMockMvc
            .perform(
                put(ENTITY_API_URL_ID, oltDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oltDTO))
            )
            .andExpect(status().isOk());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
        Olt testOlt = oltList.get(oltList.size() - 1);
        assertThat(testOlt.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOlt.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testOlt.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingOlt() throws Exception {
        int databaseSizeBeforeUpdate = oltRepository.findAll().size();
        olt.setId(longCount.incrementAndGet());

        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOltMockMvc
            .perform(
                put(ENTITY_API_URL_ID, oltDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oltDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOlt() throws Exception {
        int databaseSizeBeforeUpdate = oltRepository.findAll().size();
        olt.setId(longCount.incrementAndGet());

        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOltMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oltDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOlt() throws Exception {
        int databaseSizeBeforeUpdate = oltRepository.findAll().size();
        olt.setId(longCount.incrementAndGet());

        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOltMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oltDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOltWithPatch() throws Exception {
        // Initialize the database
        oltRepository.saveAndFlush(olt);

        int databaseSizeBeforeUpdate = oltRepository.findAll().size();

        // Update the olt using partial update
        Olt partialUpdatedOlt = new Olt();
        partialUpdatedOlt.setId(olt.getId());

        partialUpdatedOlt.name(UPDATED_NAME);

        restOltMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOlt.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOlt))
            )
            .andExpect(status().isOk());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
        Olt testOlt = oltList.get(oltList.size() - 1);
        assertThat(testOlt.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOlt.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testOlt.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateOltWithPatch() throws Exception {
        // Initialize the database
        oltRepository.saveAndFlush(olt);

        int databaseSizeBeforeUpdate = oltRepository.findAll().size();

        // Update the olt using partial update
        Olt partialUpdatedOlt = new Olt();
        partialUpdatedOlt.setId(olt.getId());

        partialUpdatedOlt.name(UPDATED_NAME).detail(UPDATED_DETAIL).status(UPDATED_STATUS);

        restOltMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOlt.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOlt))
            )
            .andExpect(status().isOk());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
        Olt testOlt = oltList.get(oltList.size() - 1);
        assertThat(testOlt.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testOlt.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testOlt.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingOlt() throws Exception {
        int databaseSizeBeforeUpdate = oltRepository.findAll().size();
        olt.setId(longCount.incrementAndGet());

        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOltMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, oltDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oltDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOlt() throws Exception {
        int databaseSizeBeforeUpdate = oltRepository.findAll().size();
        olt.setId(longCount.incrementAndGet());

        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOltMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oltDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOlt() throws Exception {
        int databaseSizeBeforeUpdate = oltRepository.findAll().size();
        olt.setId(longCount.incrementAndGet());

        // Create the Olt
        OltDTO oltDTO = oltMapper.toDto(olt);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOltMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(oltDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Olt in the database
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOlt() throws Exception {
        // Initialize the database
        oltRepository.saveAndFlush(olt);

        int databaseSizeBeforeDelete = oltRepository.findAll().size();

        // Delete the olt
        restOltMockMvc.perform(delete(ENTITY_API_URL_ID, olt.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Olt> oltList = oltRepository.findAll();
        assertThat(oltList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
