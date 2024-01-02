package com.konnect.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DispatchRecordDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DispatchRecordDTO.class);
        DispatchRecordDTO dispatchRecordDTO1 = new DispatchRecordDTO();
        dispatchRecordDTO1.setId(1L);
        DispatchRecordDTO dispatchRecordDTO2 = new DispatchRecordDTO();
        assertThat(dispatchRecordDTO1).isNotEqualTo(dispatchRecordDTO2);
        dispatchRecordDTO2.setId(dispatchRecordDTO1.getId());
        assertThat(dispatchRecordDTO1).isEqualTo(dispatchRecordDTO2);
        dispatchRecordDTO2.setId(2L);
        assertThat(dispatchRecordDTO1).isNotEqualTo(dispatchRecordDTO2);
        dispatchRecordDTO1.setId(null);
        assertThat(dispatchRecordDTO1).isNotEqualTo(dispatchRecordDTO2);
    }
}
