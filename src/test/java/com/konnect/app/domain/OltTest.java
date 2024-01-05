package com.konnect.app.domain;

import static com.konnect.app.domain.BranchTestSamples.*;
import static com.konnect.app.domain.OltTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OltTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Olt.class);
        Olt olt1 = getOltSample1();
        Olt olt2 = new Olt();
        assertThat(olt1).isNotEqualTo(olt2);

        olt2.setId(olt1.getId());
        assertThat(olt1).isEqualTo(olt2);

        olt2 = getOltSample2();
        assertThat(olt1).isNotEqualTo(olt2);
    }

    @Test
    void branchTest() throws Exception {
        Olt olt = getOltRandomSampleGenerator();
        Branch branchBack = getBranchRandomSampleGenerator();

        olt.setBranch(branchBack);
        assertThat(olt.getBranch()).isEqualTo(branchBack);

        olt.branch(null);
        assertThat(olt.getBranch()).isNull();
    }
}
