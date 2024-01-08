package com.konnect.app.domain;

import static com.konnect.app.domain.DispatchTestSamples.*;
import static com.konnect.app.domain.TeamTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DispatchTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dispatch.class);
        Dispatch dispatch1 = getDispatchSample1();
        Dispatch dispatch2 = new Dispatch();
        assertThat(dispatch1).isNotEqualTo(dispatch2);

        dispatch2.setId(dispatch1.getId());
        assertThat(dispatch1).isEqualTo(dispatch2);

        dispatch2 = getDispatchSample2();
        assertThat(dispatch1).isNotEqualTo(dispatch2);
    }

    @Test
    void teamTest() throws Exception {
        Dispatch dispatch = getDispatchRandomSampleGenerator();
        Team teamBack = getTeamRandomSampleGenerator();

        dispatch.setTeam(teamBack);
        assertThat(dispatch.getTeam()).isEqualTo(teamBack);

        dispatch.team(null);
        assertThat(dispatch.getTeam()).isNull();
    }
}
