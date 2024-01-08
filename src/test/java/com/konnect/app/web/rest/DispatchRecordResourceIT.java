package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.DispatchRecord;
import com.konnect.app.domain.enumeration.Status;
import com.konnect.app.repository.DispatchRecordRepository;
import com.konnect.app.service.dto.DispatchRecordDTO;
import com.konnect.app.service.mapper.DispatchRecordMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link DispatchRecordResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DispatchRecordResourceIT {

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.WAITING_FOR_RESPONSE;

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/dispatch-records";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DispatchRecordRepository dispatchRecordRepository;

    @Autowired
    private DispatchRecordMapper dispatchRecordMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDispatchRecordMockMvc;

    private DispatchRecord dispatchRecord;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DispatchRecord createEntity(EntityManager em) {
        DispatchRecord dispatchRecord = new DispatchRecord()
            .remark(DEFAULT_REMARK)
            .status(DEFAULT_STATUS)
            .publicationDate(DEFAULT_PUBLICATION_DATE);
        return dispatchRecord;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DispatchRecord createUpdatedEntity(EntityManager em) {
        DispatchRecord dispatchRecord = new DispatchRecord()
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .publicationDate(UPDATED_PUBLICATION_DATE);
        return dispatchRecord;
    }

    @BeforeEach
    public void initTest() {
        dispatchRecord = createEntity(em);
    }

    @Test
    @Transactional
    void createDispatchRecord() throws Exception {
        int databaseSizeBeforeCreate = dispatchRecordRepository.findAll().size();
        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);
        restDispatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isCreated());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeCreate + 1);
        DispatchRecord testDispatchRecord = dispatchRecordList.get(dispatchRecordList.size() - 1);
        assertThat(testDispatchRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDispatchRecord.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDispatchRecord.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createDispatchRecordWithExistingId() throws Exception {
        // Create the DispatchRecord with an existing ID
        dispatchRecord.setId(1L);
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        int databaseSizeBeforeCreate = dispatchRecordRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDispatchRecordMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDispatchRecords() throws Exception {
        // Initialize the database
        dispatchRecordRepository.saveAndFlush(dispatchRecord);

        // Get all the dispatchRecordList
        restDispatchRecordMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dispatchRecord.getId().intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getDispatchRecord() throws Exception {
        // Initialize the database
        dispatchRecordRepository.saveAndFlush(dispatchRecord);

        // Get the dispatchRecord
        restDispatchRecordMockMvc
            .perform(get(ENTITY_API_URL_ID, dispatchRecord.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dispatchRecord.getId().intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDispatchRecord() throws Exception {
        // Get the dispatchRecord
        restDispatchRecordMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDispatchRecord() throws Exception {
        // Initialize the database
        dispatchRecordRepository.saveAndFlush(dispatchRecord);

        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();

        // Update the dispatchRecord
        DispatchRecord updatedDispatchRecord = dispatchRecordRepository.findById(dispatchRecord.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDispatchRecord are not directly saved in db
        em.detach(updatedDispatchRecord);
        updatedDispatchRecord.remark(UPDATED_REMARK).status(UPDATED_STATUS).publicationDate(UPDATED_PUBLICATION_DATE);
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(updatedDispatchRecord);

        restDispatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dispatchRecordDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isOk());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
        DispatchRecord testDispatchRecord = dispatchRecordList.get(dispatchRecordList.size() - 1);
        assertThat(testDispatchRecord.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDispatchRecord.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDispatchRecord.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingDispatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();
        dispatchRecord.setId(longCount.incrementAndGet());

        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDispatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dispatchRecordDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDispatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();
        dispatchRecord.setId(longCount.incrementAndGet());

        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDispatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();
        dispatchRecord.setId(longCount.incrementAndGet());

        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchRecordMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDispatchRecordWithPatch() throws Exception {
        // Initialize the database
        dispatchRecordRepository.saveAndFlush(dispatchRecord);

        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();

        // Update the dispatchRecord using partial update
        DispatchRecord partialUpdatedDispatchRecord = new DispatchRecord();
        partialUpdatedDispatchRecord.setId(dispatchRecord.getId());

        partialUpdatedDispatchRecord.status(UPDATED_STATUS).publicationDate(UPDATED_PUBLICATION_DATE);

        restDispatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDispatchRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDispatchRecord))
            )
            .andExpect(status().isOk());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
        DispatchRecord testDispatchRecord = dispatchRecordList.get(dispatchRecordList.size() - 1);
        assertThat(testDispatchRecord.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDispatchRecord.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDispatchRecord.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateDispatchRecordWithPatch() throws Exception {
        // Initialize the database
        dispatchRecordRepository.saveAndFlush(dispatchRecord);

        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();

        // Update the dispatchRecord using partial update
        DispatchRecord partialUpdatedDispatchRecord = new DispatchRecord();
        partialUpdatedDispatchRecord.setId(dispatchRecord.getId());

        partialUpdatedDispatchRecord.remark(UPDATED_REMARK).status(UPDATED_STATUS).publicationDate(UPDATED_PUBLICATION_DATE);

        restDispatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDispatchRecord.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDispatchRecord))
            )
            .andExpect(status().isOk());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
        DispatchRecord testDispatchRecord = dispatchRecordList.get(dispatchRecordList.size() - 1);
        assertThat(testDispatchRecord.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDispatchRecord.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDispatchRecord.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingDispatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();
        dispatchRecord.setId(longCount.incrementAndGet());

        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDispatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dispatchRecordDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDispatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();
        dispatchRecord.setId(longCount.incrementAndGet());

        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDispatchRecord() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRecordRepository.findAll().size();
        dispatchRecord.setId(longCount.incrementAndGet());

        // Create the DispatchRecord
        DispatchRecordDTO dispatchRecordDTO = dispatchRecordMapper.toDto(dispatchRecord);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchRecordMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dispatchRecordDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DispatchRecord in the database
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDispatchRecord() throws Exception {
        // Initialize the database
        dispatchRecordRepository.saveAndFlush(dispatchRecord);

        int databaseSizeBeforeDelete = dispatchRecordRepository.findAll().size();

        // Delete the dispatchRecord
        restDispatchRecordMockMvc
            .perform(delete(ENTITY_API_URL_ID, dispatchRecord.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DispatchRecord> dispatchRecordList = dispatchRecordRepository.findAll();
        assertThat(dispatchRecordList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
