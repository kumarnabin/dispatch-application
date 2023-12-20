package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BranchCircuitDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchCircuitDTO.class);
        BranchCircuitDTO branchCircuitDTO1 = new BranchCircuitDTO();
        branchCircuitDTO1.setId(1L);
        BranchCircuitDTO branchCircuitDTO2 = new BranchCircuitDTO();
        assertThat(branchCircuitDTO1).isNotEqualTo(branchCircuitDTO2);
        branchCircuitDTO2.setId(branchCircuitDTO1.getId());
        assertThat(branchCircuitDTO1).isEqualTo(branchCircuitDTO2);
        branchCircuitDTO2.setId(2L);
        assertThat(branchCircuitDTO1).isNotEqualTo(branchCircuitDTO2);
        branchCircuitDTO1.setId(null);
        assertThat(branchCircuitDTO1).isNotEqualTo(branchCircuitDTO2);
    }
}
