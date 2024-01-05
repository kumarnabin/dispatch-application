package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OltDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OltDTO.class);
        OltDTO oltDTO1 = new OltDTO();
        oltDTO1.setId(1L);
        OltDTO oltDTO2 = new OltDTO();
        assertThat(oltDTO1).isNotEqualTo(oltDTO2);
        oltDTO2.setId(oltDTO1.getId());
        assertThat(oltDTO1).isEqualTo(oltDTO2);
        oltDTO2.setId(2L);
        assertThat(oltDTO1).isNotEqualTo(oltDTO2);
        oltDTO1.setId(null);
        assertThat(oltDTO1).isNotEqualTo(oltDTO2);
    }
}
