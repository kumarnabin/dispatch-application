package com.konnect.app.domain;

import static com.konnect.app.domain.CustomerTestSamples.*;
import static com.konnect.app.domain.TeamTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customer.class);
        Customer customer1 = getCustomerSample1();
        Customer customer2 = new Customer();
        assertThat(customer1).isNotEqualTo(customer2);

        customer2.setId(customer1.getId());
        assertThat(customer1).isEqualTo(customer2);

        customer2 = getCustomerSample2();
        assertThat(customer1).isNotEqualTo(customer2);
    }

    @Test
    void teamTest() throws Exception {
        Customer customer = getCustomerRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        customer.setTeam(teamBack);
        assertThat(customer.getTeam()).isEqualTo(teamBack);

        customer.team(null);
        assertThat(customer.getTeam()).isNull();
    }
}
