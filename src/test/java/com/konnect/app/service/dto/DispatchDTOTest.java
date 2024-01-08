package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DispatchDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispatchDTO.class);
        DispatchDTO dispatchDTO1 = new DispatchDTO();
        dispatchDTO1.setId(1L);
        DispatchDTO dispatchDTO2 = new DispatchDTO();
        assertThat(dispatchDTO1).isNotEqualTo(dispatchDTO2);
        dispatchDTO2.setId(dispatchDTO1.getId());
        assertThat(dispatchDTO1).isEqualTo(dispatchDTO2);
        dispatchDTO2.setId(2L);
        assertThat(dispatchDTO1).isNotEqualTo(dispatchDTO2);
        dispatchDTO1.setId(null);
        assertThat(dispatchDTO1).isNotEqualTo(dispatchDTO2);
    }
}
