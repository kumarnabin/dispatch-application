package com.konnect.app.domain;

import static com.konnect.app.domain.BranchTestSamples.*;
import static com.konnect.app.domain.ServiceProviderTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Branch.class);
        Branch branch1 = getBranchSample1();
        Branch branch2 = new Branch();
        assertThat(branch1).isNotEqualTo(branch2);

        branch2.setId(branch1.getId());
        assertThat(branch1).isEqualTo(branch2);

        branch2 = getBranchSample2();
        assertThat(branch1).isNotEqualTo(branch2);
    }

    @Test
    void serviceProviderTest() throws Exception {
        Branch branch = getBranchRandomSampleGenerator();
        ServiceProvider serviceProviderBack = getServiceProviderRandomSampleGenerator();

        branch.setServiceProvider(serviceProviderBack);
        assertThat(branch.getServiceProvider()).isEqualTo(serviceProviderBack);

        branch.serviceProvider(null);
        assertThat(branch.getServiceProvider()).isNull();
    }
}
