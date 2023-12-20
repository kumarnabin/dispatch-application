package com.konnect.app.domain;

import static com.konnect.app.domain.MasterCircuitTestSamples.*;
import static com.konnect.app.domain.ServiceProviderTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class MasterCircuitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterCircuit.class);
        MasterCircuit masterCircuit1 = getMasterCircuitSample1();
        MasterCircuit masterCircuit2 = new MasterCircuit();
        assertThat(masterCircuit1).isNotEqualTo(masterCircuit2);

        masterCircuit2.setId(masterCircuit1.getId());
        assertThat(masterCircuit1).isEqualTo(masterCircuit2);

        masterCircuit2 = getMasterCircuitSample2();
        assertThat(masterCircuit1).isNotEqualTo(masterCircuit2);
    }

    @Test
    void serviceProviderTest() throws Exception {
        MasterCircuit masterCircuit = getMasterCircuitRandomSampleGenerator();
        ServiceProvider serviceProviderBack = getServiceProviderRandomSampleGenerator();

        masterCircuit.addServiceProvider(serviceProviderBack);
        assertThat(masterCircuit.getServiceProviders()).containsOnly(serviceProviderBack);

        masterCircuit.removeServiceProvider(serviceProviderBack);
        assertThat(masterCircuit.getServiceProviders()).doesNotContain(serviceProviderBack);

        masterCircuit.serviceProviders(new HashSet<>(Set.of(serviceProviderBack)));
        assertThat(masterCircuit.getServiceProviders()).containsOnly(serviceProviderBack);

        masterCircuit.setServiceProviders(new HashSet<>());
        assertThat(masterCircuit.getServiceProviders()).doesNotContain(serviceProviderBack);
    }
}
