package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.Dispatch;
import com.konnect.app.repository.DispatchRepository;
import com.konnect.app.service.dto.DispatchDTO;
import com.konnect.app.service.mapper.DispatchMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link DispatchResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DispatchResourceIT {

    private static final String DEFAULT_VOICE = "AAAAAAAAAA";
    private static final String UPDATED_VOICE = "BBBBBBBBBB";

    private static final String DEFAULT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_IPTV = "AAAAAAAAAA";
    private static final String UPDATED_IPTV = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_NO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_OLT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_OLT_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_REG_DATE = "AAAAAAAAAA";
    private static final String UPDATED_REG_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_FAP_PORT = "AAAAAAAAAA";
    private static final String UPDATED_FAP_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_CPE_SN = "AAAAAAAAAA";
    private static final String UPDATED_CPE_SN = "BBBBBBBBBB";

    private static final String DEFAULT_CPE_RX = "AAAAAAAAAA";
    private static final String UPDATED_CPE_RX = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLAIN = "AAAAAAAAAA";
    private static final String UPDATED_COMPLAIN = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PRINT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PRINT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_PUBLICATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PUBLICATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/dispatches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DispatchRepository dispatchRepository;

    @Autowired
    private DispatchMapper dispatchMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDispatchMockMvc;

    private Dispatch dispatch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dispatch createEntity(EntityManager em) {
        Dispatch dispatch = new Dispatch()
            .voice(DEFAULT_VOICE)
            .data(DEFAULT_DATA)
            .iptv(DEFAULT_IPTV)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .contactNo(DEFAULT_CONTACT_NO)
            .oltPort(DEFAULT_OLT_PORT)
            .regDate(DEFAULT_REG_DATE)
            .fapPort(DEFAULT_FAP_PORT)
            .cpeSn(DEFAULT_CPE_SN)
            .cpeRx(DEFAULT_CPE_RX)
            .complain(DEFAULT_COMPLAIN)
            .remark(DEFAULT_REMARK)
            .status(DEFAULT_STATUS)
            .location(DEFAULT_LOCATION)
            .printDate(DEFAULT_PRINT_DATE)
            .publicationDate(DEFAULT_PUBLICATION_DATE);
        return dispatch;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Dispatch createUpdatedEntity(EntityManager em) {
        Dispatch dispatch = new Dispatch()
            .voice(UPDATED_VOICE)
            .data(UPDATED_DATA)
            .iptv(UPDATED_IPTV)
            .customerName(UPDATED_CUSTOMER_NAME)
            .contactNo(UPDATED_CONTACT_NO)
            .oltPort(UPDATED_OLT_PORT)
            .regDate(UPDATED_REG_DATE)
            .fapPort(UPDATED_FAP_PORT)
            .cpeSn(UPDATED_CPE_SN)
            .cpeRx(UPDATED_CPE_RX)
            .complain(UPDATED_COMPLAIN)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .printDate(UPDATED_PRINT_DATE)
            .publicationDate(UPDATED_PUBLICATION_DATE);
        return dispatch;
    }

    @BeforeEach
    public void initTest() {
        dispatch = createEntity(em);
    }

    @Test
    @Transactional
    void createDispatch() throws Exception {
        int databaseSizeBeforeCreate = dispatchRepository.findAll().size();
        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);
        restDispatchMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dispatchDTO)))
            .andExpect(status().isCreated());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeCreate + 1);
        Dispatch testDispatch = dispatchList.get(dispatchList.size() - 1);
        assertThat(testDispatch.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testDispatch.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testDispatch.getIptv()).isEqualTo(DEFAULT_IPTV);
        assertThat(testDispatch.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testDispatch.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testDispatch.getOltPort()).isEqualTo(DEFAULT_OLT_PORT);
        assertThat(testDispatch.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testDispatch.getFapPort()).isEqualTo(DEFAULT_FAP_PORT);
        assertThat(testDispatch.getCpeSn()).isEqualTo(DEFAULT_CPE_SN);
        assertThat(testDispatch.getCpeRx()).isEqualTo(DEFAULT_CPE_RX);
        assertThat(testDispatch.getComplain()).isEqualTo(DEFAULT_COMPLAIN);
        assertThat(testDispatch.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDispatch.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDispatch.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testDispatch.getPrintDate()).isEqualTo(DEFAULT_PRINT_DATE);
        assertThat(testDispatch.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createDispatchWithExistingId() throws Exception {
        // Create the Dispatch with an existing ID
        dispatch.setId(1L);
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        int databaseSizeBeforeCreate = dispatchRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDispatchMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dispatchDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDispatches() throws Exception {
        // Initialize the database
        dispatchRepository.saveAndFlush(dispatch);

        // Get all the dispatchList
        restDispatchMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dispatch.getId().intValue())))
            .andExpect(jsonPath("$.[*].voice").value(hasItem(DEFAULT_VOICE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA)))
            .andExpect(jsonPath("$.[*].iptv").value(hasItem(DEFAULT_IPTV)))
            .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME)))
            .andExpect(jsonPath("$.[*].contactNo").value(hasItem(DEFAULT_CONTACT_NO)))
            .andExpect(jsonPath("$.[*].oltPort").value(hasItem(DEFAULT_OLT_PORT)))
            .andExpect(jsonPath("$.[*].regDate").value(hasItem(DEFAULT_REG_DATE)))
            .andExpect(jsonPath("$.[*].fapPort").value(hasItem(DEFAULT_FAP_PORT)))
            .andExpect(jsonPath("$.[*].cpeSn").value(hasItem(DEFAULT_CPE_SN)))
            .andExpect(jsonPath("$.[*].cpeRx").value(hasItem(DEFAULT_CPE_RX)))
            .andExpect(jsonPath("$.[*].complain").value(hasItem(DEFAULT_COMPLAIN)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].printDate").value(hasItem(DEFAULT_PRINT_DATE.toString())))
            .andExpect(jsonPath("$.[*].publicationDate").value(hasItem(DEFAULT_PUBLICATION_DATE.toString())));
    }

    @Test
    @Transactional
    void getDispatch() throws Exception {
        // Initialize the database
        dispatchRepository.saveAndFlush(dispatch);

        // Get the dispatch
        restDispatchMockMvc
            .perform(get(ENTITY_API_URL_ID, dispatch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dispatch.getId().intValue()))
            .andExpect(jsonPath("$.voice").value(DEFAULT_VOICE))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA))
            .andExpect(jsonPath("$.iptv").value(DEFAULT_IPTV))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME))
            .andExpect(jsonPath("$.contactNo").value(DEFAULT_CONTACT_NO))
            .andExpect(jsonPath("$.oltPort").value(DEFAULT_OLT_PORT))
            .andExpect(jsonPath("$.regDate").value(DEFAULT_REG_DATE))
            .andExpect(jsonPath("$.fapPort").value(DEFAULT_FAP_PORT))
            .andExpect(jsonPath("$.cpeSn").value(DEFAULT_CPE_SN))
            .andExpect(jsonPath("$.cpeRx").value(DEFAULT_CPE_RX))
            .andExpect(jsonPath("$.complain").value(DEFAULT_COMPLAIN))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.printDate").value(DEFAULT_PRINT_DATE.toString()))
            .andExpect(jsonPath("$.publicationDate").value(DEFAULT_PUBLICATION_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDispatch() throws Exception {
        // Get the dispatch
        restDispatchMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDispatch() throws Exception {
        // Initialize the database
        dispatchRepository.saveAndFlush(dispatch);

        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();

        // Update the dispatch
        Dispatch updatedDispatch = dispatchRepository.findById(dispatch.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDispatch are not directly saved in db
        em.detach(updatedDispatch);
        updatedDispatch
            .voice(UPDATED_VOICE)
            .data(UPDATED_DATA)
            .iptv(UPDATED_IPTV)
            .customerName(UPDATED_CUSTOMER_NAME)
            .contactNo(UPDATED_CONTACT_NO)
            .oltPort(UPDATED_OLT_PORT)
            .regDate(UPDATED_REG_DATE)
            .fapPort(UPDATED_FAP_PORT)
            .cpeSn(UPDATED_CPE_SN)
            .cpeRx(UPDATED_CPE_RX)
            .complain(UPDATED_COMPLAIN)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .printDate(UPDATED_PRINT_DATE)
            .publicationDate(UPDATED_PUBLICATION_DATE);
        DispatchDTO dispatchDTO = dispatchMapper.toDto(updatedDispatch);

        restDispatchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dispatchDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dispatchDTO))
            )
            .andExpect(status().isOk());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
        Dispatch testDispatch = dispatchList.get(dispatchList.size() - 1);
        assertThat(testDispatch.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testDispatch.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testDispatch.getIptv()).isEqualTo(UPDATED_IPTV);
        assertThat(testDispatch.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testDispatch.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testDispatch.getOltPort()).isEqualTo(UPDATED_OLT_PORT);
        assertThat(testDispatch.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testDispatch.getFapPort()).isEqualTo(UPDATED_FAP_PORT);
        assertThat(testDispatch.getCpeSn()).isEqualTo(UPDATED_CPE_SN);
        assertThat(testDispatch.getCpeRx()).isEqualTo(UPDATED_CPE_RX);
        assertThat(testDispatch.getComplain()).isEqualTo(UPDATED_COMPLAIN);
        assertThat(testDispatch.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDispatch.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDispatch.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testDispatch.getPrintDate()).isEqualTo(UPDATED_PRINT_DATE);
        assertThat(testDispatch.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingDispatch() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();
        dispatch.setId(longCount.incrementAndGet());

        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDispatchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, dispatchDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dispatchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDispatch() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();
        dispatch.setId(longCount.incrementAndGet());

        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dispatchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDispatch() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();
        dispatch.setId(longCount.incrementAndGet());

        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(dispatchDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDispatchWithPatch() throws Exception {
        // Initialize the database
        dispatchRepository.saveAndFlush(dispatch);

        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();

        // Update the dispatch using partial update
        Dispatch partialUpdatedDispatch = new Dispatch();
        partialUpdatedDispatch.setId(dispatch.getId());

        partialUpdatedDispatch
            .iptv(UPDATED_IPTV)
            .customerName(UPDATED_CUSTOMER_NAME)
            .contactNo(UPDATED_CONTACT_NO)
            .oltPort(UPDATED_OLT_PORT)
            .regDate(UPDATED_REG_DATE)
            .fapPort(UPDATED_FAP_PORT)
            .cpeSn(UPDATED_CPE_SN)
            .complain(UPDATED_COMPLAIN)
            .location(UPDATED_LOCATION)
            .printDate(UPDATED_PRINT_DATE);

        restDispatchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDispatch.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDispatch))
            )
            .andExpect(status().isOk());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
        Dispatch testDispatch = dispatchList.get(dispatchList.size() - 1);
        assertThat(testDispatch.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testDispatch.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testDispatch.getIptv()).isEqualTo(UPDATED_IPTV);
        assertThat(testDispatch.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testDispatch.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testDispatch.getOltPort()).isEqualTo(UPDATED_OLT_PORT);
        assertThat(testDispatch.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testDispatch.getFapPort()).isEqualTo(UPDATED_FAP_PORT);
        assertThat(testDispatch.getCpeSn()).isEqualTo(UPDATED_CPE_SN);
        assertThat(testDispatch.getCpeRx()).isEqualTo(DEFAULT_CPE_RX);
        assertThat(testDispatch.getComplain()).isEqualTo(UPDATED_COMPLAIN);
        assertThat(testDispatch.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testDispatch.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testDispatch.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testDispatch.getPrintDate()).isEqualTo(UPDATED_PRINT_DATE);
        assertThat(testDispatch.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateDispatchWithPatch() throws Exception {
        // Initialize the database
        dispatchRepository.saveAndFlush(dispatch);

        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();

        // Update the dispatch using partial update
        Dispatch partialUpdatedDispatch = new Dispatch();
        partialUpdatedDispatch.setId(dispatch.getId());

        partialUpdatedDispatch
            .voice(UPDATED_VOICE)
            .data(UPDATED_DATA)
            .iptv(UPDATED_IPTV)
            .customerName(UPDATED_CUSTOMER_NAME)
            .contactNo(UPDATED_CONTACT_NO)
            .oltPort(UPDATED_OLT_PORT)
            .regDate(UPDATED_REG_DATE)
            .fapPort(UPDATED_FAP_PORT)
            .cpeSn(UPDATED_CPE_SN)
            .cpeRx(UPDATED_CPE_RX)
            .complain(UPDATED_COMPLAIN)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS)
            .location(UPDATED_LOCATION)
            .printDate(UPDATED_PRINT_DATE)
            .publicationDate(UPDATED_PUBLICATION_DATE);

        restDispatchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDispatch.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDispatch))
            )
            .andExpect(status().isOk());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
        Dispatch testDispatch = dispatchList.get(dispatchList.size() - 1);
        assertThat(testDispatch.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testDispatch.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testDispatch.getIptv()).isEqualTo(UPDATED_IPTV);
        assertThat(testDispatch.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testDispatch.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testDispatch.getOltPort()).isEqualTo(UPDATED_OLT_PORT);
        assertThat(testDispatch.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testDispatch.getFapPort()).isEqualTo(UPDATED_FAP_PORT);
        assertThat(testDispatch.getCpeSn()).isEqualTo(UPDATED_CPE_SN);
        assertThat(testDispatch.getCpeRx()).isEqualTo(UPDATED_CPE_RX);
        assertThat(testDispatch.getComplain()).isEqualTo(UPDATED_COMPLAIN);
        assertThat(testDispatch.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testDispatch.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testDispatch.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testDispatch.getPrintDate()).isEqualTo(UPDATED_PRINT_DATE);
        assertThat(testDispatch.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingDispatch() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();
        dispatch.setId(longCount.incrementAndGet());

        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDispatchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, dispatchDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dispatchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDispatch() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();
        dispatch.setId(longCount.incrementAndGet());

        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(dispatchDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDispatch() throws Exception {
        int databaseSizeBeforeUpdate = dispatchRepository.findAll().size();
        dispatch.setId(longCount.incrementAndGet());

        // Create the Dispatch
        DispatchDTO dispatchDTO = dispatchMapper.toDto(dispatch);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDispatchMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(dispatchDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Dispatch in the database
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDispatch() throws Exception {
        // Initialize the database
        dispatchRepository.saveAndFlush(dispatch);

        int databaseSizeBeforeDelete = dispatchRepository.findAll().size();

        // Delete the dispatch
        restDispatchMockMvc
            .perform(delete(ENTITY_API_URL_ID, dispatch.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Dispatch> dispatchList = dispatchRepository.findAll();
        assertThat(dispatchList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
