package com.konnect.app.domain;

import static com.konnect.app.domain.AreaTestSamples.*;
import static com.konnect.app.domain.OltTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AreaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Area.class);
        Area area1 = getAreaSample1();
        Area area2 = new Area();
        assertThat(area1).isNotEqualTo(area2);

        area2.setId(area1.getId());
        assertThat(area1).isEqualTo(area2);

        area2 = getAreaSample2();
        assertThat(area1).isNotEqualTo(area2);
    }

    @Test
    void oltTest() throws Exception {
        Area area = getAreaRandomSampleGenerator();
        Olt oltBack = getOltRandomSampleGenerator();

        area.setOlt(oltBack);
        assertThat(area.getOlt()).isEqualTo(oltBack);

        area.olt(null);
        assertThat(area.getOlt()).isNull();
    }
}
