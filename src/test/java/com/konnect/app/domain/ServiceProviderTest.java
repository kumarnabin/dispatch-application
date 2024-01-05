package com.konnect.app.domain;

import static com.konnect.app.domain.ServiceProviderTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ServiceProviderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceProvider.class);
        ServiceProvider serviceProvider1 = getServiceProviderSample1();
        ServiceProvider serviceProvider2 = new ServiceProvider();
        assertThat(serviceProvider1).isNotEqualTo(serviceProvider2);

        serviceProvider2.setId(serviceProvider1.getId());
        assertThat(serviceProvider1).isEqualTo(serviceProvider2);

        serviceProvider2 = getServiceProviderSample2();
        assertThat(serviceProvider1).isNotEqualTo(serviceProvider2);
    }
}
