package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MasterCircuitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterCircuitDTO.class);
        MasterCircuitDTO masterCircuitDTO1 = new MasterCircuitDTO();
        masterCircuitDTO1.setId(1L);
        MasterCircuitDTO masterCircuitDTO2 = new MasterCircuitDTO();
        assertThat(masterCircuitDTO1).isNotEqualTo(masterCircuitDTO2);
        masterCircuitDTO2.setId(masterCircuitDTO1.getId());
        assertThat(masterCircuitDTO1).isEqualTo(masterCircuitDTO2);
        masterCircuitDTO2.setId(2L);
        assertThat(masterCircuitDTO1).isNotEqualTo(masterCircuitDTO2);
        masterCircuitDTO1.setId(null);
        assertThat(masterCircuitDTO1).isNotEqualTo(masterCircuitDTO2);
    }
}
