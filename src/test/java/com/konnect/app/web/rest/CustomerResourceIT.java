package com.konnect.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.konnect.app.IntegrationTest;
import com.konnect.app.domain.Customer;
import com.konnect.app.repository.CustomerRepository;
import com.konnect.app.service.dto.CustomerDTO;
import com.konnect.app.service.mapper.CustomerMapper;
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
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomerResourceIT {

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

    private static final String ENTITY_API_URL = "/api/customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerMockMvc;

    private Customer customer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
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
        return customer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity(EntityManager em) {
        Customer customer = new Customer()
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
        return customer;
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity(em);
    }

    @Test
    @Transactional
    void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();
        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);
        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testCustomer.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testCustomer.getIptv()).isEqualTo(DEFAULT_IPTV);
        assertThat(testCustomer.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testCustomer.getContactNo()).isEqualTo(DEFAULT_CONTACT_NO);
        assertThat(testCustomer.getOltPort()).isEqualTo(DEFAULT_OLT_PORT);
        assertThat(testCustomer.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testCustomer.getFapPort()).isEqualTo(DEFAULT_FAP_PORT);
        assertThat(testCustomer.getCpeSn()).isEqualTo(DEFAULT_CPE_SN);
        assertThat(testCustomer.getCpeRx()).isEqualTo(DEFAULT_CPE_RX);
        assertThat(testCustomer.getComplain()).isEqualTo(DEFAULT_COMPLAIN);
        assertThat(testCustomer.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testCustomer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustomer.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testCustomer.getPrintDate()).isEqualTo(DEFAULT_PRINT_DATE);
        assertThat(testCustomer.getPublicationDate()).isEqualTo(DEFAULT_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void createCustomerWithExistingId() throws Exception {
        // Create the Customer with an existing ID
        customer.setId(1L);
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
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
    void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL_ID, customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
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
    void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
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
        CustomerDTO customerDTO = customerMapper.toDto(updatedCustomer);

        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customerDTO))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testCustomer.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testCustomer.getIptv()).isEqualTo(UPDATED_IPTV);
        assertThat(testCustomer.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCustomer.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testCustomer.getOltPort()).isEqualTo(UPDATED_OLT_PORT);
        assertThat(testCustomer.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testCustomer.getFapPort()).isEqualTo(UPDATED_FAP_PORT);
        assertThat(testCustomer.getCpeSn()).isEqualTo(UPDATED_CPE_SN);
        assertThat(testCustomer.getCpeRx()).isEqualTo(UPDATED_CPE_RX);
        assertThat(testCustomer.getComplain()).isEqualTo(UPDATED_COMPLAIN);
        assertThat(testCustomer.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCustomer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustomer.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testCustomer.getPrintDate()).isEqualTo(UPDATED_PRINT_DATE);
        assertThat(testCustomer.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void putNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(longCount.incrementAndGet());

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customerDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(longCount.incrementAndGet());

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(customerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(longCount.incrementAndGet());

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(customerDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .iptv(UPDATED_IPTV)
            .customerName(UPDATED_CUSTOMER_NAME)
            .contactNo(UPDATED_CONTACT_NO)
            .cpeSn(UPDATED_CPE_SN)
            .remark(UPDATED_REMARK)
            .location(UPDATED_LOCATION)
            .publicationDate(UPDATED_PUBLICATION_DATE);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getVoice()).isEqualTo(DEFAULT_VOICE);
        assertThat(testCustomer.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testCustomer.getIptv()).isEqualTo(UPDATED_IPTV);
        assertThat(testCustomer.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCustomer.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testCustomer.getOltPort()).isEqualTo(DEFAULT_OLT_PORT);
        assertThat(testCustomer.getRegDate()).isEqualTo(DEFAULT_REG_DATE);
        assertThat(testCustomer.getFapPort()).isEqualTo(DEFAULT_FAP_PORT);
        assertThat(testCustomer.getCpeSn()).isEqualTo(UPDATED_CPE_SN);
        assertThat(testCustomer.getCpeRx()).isEqualTo(DEFAULT_CPE_RX);
        assertThat(testCustomer.getComplain()).isEqualTo(DEFAULT_COMPLAIN);
        assertThat(testCustomer.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCustomer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustomer.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testCustomer.getPrintDate()).isEqualTo(DEFAULT_PRINT_DATE);
        assertThat(testCustomer.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void fullUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
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

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getVoice()).isEqualTo(UPDATED_VOICE);
        assertThat(testCustomer.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testCustomer.getIptv()).isEqualTo(UPDATED_IPTV);
        assertThat(testCustomer.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCustomer.getContactNo()).isEqualTo(UPDATED_CONTACT_NO);
        assertThat(testCustomer.getOltPort()).isEqualTo(UPDATED_OLT_PORT);
        assertThat(testCustomer.getRegDate()).isEqualTo(UPDATED_REG_DATE);
        assertThat(testCustomer.getFapPort()).isEqualTo(UPDATED_FAP_PORT);
        assertThat(testCustomer.getCpeSn()).isEqualTo(UPDATED_CPE_SN);
        assertThat(testCustomer.getCpeRx()).isEqualTo(UPDATED_CPE_RX);
        assertThat(testCustomer.getComplain()).isEqualTo(UPDATED_COMPLAIN);
        assertThat(testCustomer.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testCustomer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustomer.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testCustomer.getPrintDate()).isEqualTo(UPDATED_PRINT_DATE);
        assertThat(testCustomer.getPublicationDate()).isEqualTo(UPDATED_PUBLICATION_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(longCount.incrementAndGet());

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customerDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(customerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(longCount.incrementAndGet());

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(customerDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();
        customer.setId(longCount.incrementAndGet());

        // Create the Customer
        CustomerDTO customerDTO = customerMapper.toDto(customer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(customerDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Delete the customer
        restCustomerMockMvc
            .perform(delete(ENTITY_API_URL_ID, customer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
