package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.EmployeeArea;
import com.konnect.app.domain.enumeration.Status;
import com.konnect.app.repository.EmployeeAreaRepository;
import com.konnect.app.service.dto.EmployeeAreaDTO;
import com.konnect.app.service.mapper.EmployeeAreaMapper;
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
 * Integration tests for the {@link EmployeeAreaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmployeeAreaResourceIT {

    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.WAITING_FOR_RESPONSE;

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/employee-areas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EmployeeAreaRepository employeeAreaRepository;

    @Autowired
    private EmployeeAreaMapper employeeAreaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeeAreaMockMvc;

    private EmployeeArea employeeArea;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmployeeArea createEntity(EntityManager em) {
        EmployeeArea employeeArea = new EmployeeArea().status(DEFAULT_STATUS).publicationDate(DEFAULT_PUBLICATION_DATE);
        return employeeArea;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmployeeArea createUpdatedEntity(EntityManager em) {
        EmployeeArea employeeArea = new EmployeeArea().status(UPDATED_STATUS).publicationDate(UPDATED_PUBLICATION_DATE);
        return employeeArea;
    }

    @BeforeEach
    public void initTest() {
        employeeArea = createEntity(em);
    }

    @Test
    @Transactional
    void createEmployeeArea() throws Exception {
        int databaseSizeBeforeCreate = employeeAreaRepository.findAll().size();
        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);
        restEmployeeAreaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeCreate + 1);
        EmployeeArea testEmployeeArea = employeeAreaList.get(employeeAreaList.size() - 1);
        assertThat(testEmployeeArea.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEmployeeArea.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createEmployeeAreaWithExistingId() throws Exception {
        // Create the EmployeeArea with an existing ID
        employeeArea.setId(1L);
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        int databaseSizeBeforeCreate = employeeAreaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeeAreaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmployeeAreas() throws Exception {
        // Initialize the database
        employeeAreaRepository.saveAndFlush(employeeArea);

        // Get all the employeeAreaList
        restEmployeeAreaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employeeArea.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getEmployeeArea() throws Exception {
        // Initialize the database
        employeeAreaRepository.saveAndFlush(employeeArea);

        // Get the employeeArea
        restEmployeeAreaMockMvc
            .perform(get(ENTITY_API_URL_ID, employeeArea.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employeeArea.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEmployeeArea() throws Exception {
        // Get the employeeArea
        restEmployeeAreaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEmployeeArea() throws Exception {
        // Initialize the database
        employeeAreaRepository.saveAndFlush(employeeArea);

        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();

        // Update the employeeArea
        EmployeeArea updatedEmployeeArea = employeeAreaRepository.findById(employeeArea.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEmployeeArea are not directly saved in db
        em.detach(updatedEmployeeArea);
        updatedEmployeeArea.status(UPDATED_STATUS).publicationDate(UPDATED_PUBLICATION_DATE);
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(updatedEmployeeArea);

        restEmployeeAreaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, employeeAreaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isOk());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
        EmployeeArea testEmployeeArea = employeeAreaList.get(employeeAreaList.size() - 1);
        assertThat(testEmployeeArea.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEmployeeArea.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingEmployeeArea() throws Exception {
        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();
        employeeArea.setId(longCount.incrementAndGet());

        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeAreaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, employeeAreaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmployeeArea() throws Exception {
        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();
        employeeArea.setId(longCount.incrementAndGet());

        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeAreaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmployeeArea() throws Exception {
        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();
        employeeArea.setId(longCount.incrementAndGet());

        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeAreaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmployeeAreaWithPatch() throws Exception {
        // Initialize the database
        employeeAreaRepository.saveAndFlush(employeeArea);

        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();

        // Update the employeeArea using partial update
        EmployeeArea partialUpdatedEmployeeArea = new EmployeeArea();
        partialUpdatedEmployeeArea.setId(employeeArea.getId());

        restEmployeeAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmployeeArea.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmployeeArea))
            )
            .andExpect(status().isOk());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
        EmployeeArea testEmployeeArea = employeeAreaList.get(employeeAreaList.size() - 1);
        assertThat(testEmployeeArea.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEmployeeArea.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateEmployeeAreaWithPatch() throws Exception {
        // Initialize the database
        employeeAreaRepository.saveAndFlush(employeeArea);

        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();

        // Update the employeeArea using partial update
        EmployeeArea partialUpdatedEmployeeArea = new EmployeeArea();
        partialUpdatedEmployeeArea.setId(employeeArea.getId());

        partialUpdatedEmployeeArea.status(UPDATED_STATUS).publicationDate(UPDATED_PUBLICATION_DATE);

        restEmployeeAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmployeeArea.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEmployeeArea))
            )
            .andExpect(status().isOk());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
        EmployeeArea testEmployeeArea = employeeAreaList.get(employeeAreaList.size() - 1);
        assertThat(testEmployeeArea.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEmployeeArea.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingEmployeeArea() throws Exception {
        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();
        employeeArea.setId(longCount.incrementAndGet());

        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeeAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, employeeAreaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmployeeArea() throws Exception {
        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();
        employeeArea.setId(longCount.incrementAndGet());

        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeAreaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmployeeArea() throws Exception {
        int databaseSizeBeforeUpdate = employeeAreaRepository.findAll().size();
        employeeArea.setId(longCount.incrementAndGet());

        // Create the EmployeeArea
        EmployeeAreaDTO employeeAreaDTO = employeeAreaMapper.toDto(employeeArea);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmployeeAreaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(employeeAreaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmployeeArea in the database
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmployeeArea() throws Exception {
        // Initialize the database
        employeeAreaRepository.saveAndFlush(employeeArea);

        int databaseSizeBeforeDelete = employeeAreaRepository.findAll().size();

        // Delete the employeeArea
        restEmployeeAreaMockMvc
            .perform(delete(ENTITY_API_URL_ID, employeeArea.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EmployeeArea> employeeAreaList = employeeAreaRepository.findAll();
        assertThat(employeeAreaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
