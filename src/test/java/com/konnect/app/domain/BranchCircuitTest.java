package com.konnect.app.domain;

import static com.konnect.app.domain.BranchCircuitTestSamples.*;
import static com.konnect.app.domain.BranchTestSamples.*;
import static com.konnect.app.domain.MasterCircuitTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.konnect.app.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class BranchCircuitTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchCircuit.class);
        BranchCircuit branchCircuit1 = getBranchCircuitSample1();
        BranchCircuit branchCircuit2 = new BranchCircuit();
        assertThat(branchCircuit1).isNotEqualTo(branchCircuit2);

        branchCircuit2.setId(branchCircuit1.getId());
        assertThat(branchCircuit1).isEqualTo(branchCircuit2);

        branchCircuit2 = getBranchCircuitSample2();
        assertThat(branchCircuit1).isNotEqualTo(branchCircuit2);
    }

    @Test
    void masterCircuitTest() throws Exception {
        BranchCircuit branchCircuit = getBranchCircuitRandomSampleGenerator();
        MasterCircuit masterCircuitBack = getMasterCircuitRandomSampleGenerator();

        branchCircuit.setMasterCircuit(masterCircuitBack);
        assertThat(branchCircuit.getMasterCircuit()).isEqualTo(masterCircuitBack);

        branchCircuit.masterCircuit(null);
        assertThat(branchCircuit.getMasterCircuit()).isNull();
    }

    @Test
    void branchTest() throws Exception {
        BranchCircuit branchCircuit = getBranchCircuitRandomSampleGenerator();
        Branch branchBack = getBranchRandomSampleGenerator();

        branchCircuit.addBranch(branchBack);
        assertThat(branchCircuit.getBranches()).containsOnly(branchBack);

        branchCircuit.removeBranch(branchBack);
        assertThat(branchCircuit.getBranches()).doesNotContain(branchBack);

        branchCircuit.branches(new HashSet<>(Set.of(branchBack)));
        assertThat(branchCircuit.getBranches()).containsOnly(branchBack);

        branchCircuit.setBranches(new HashSet<>());
        assertThat(branchCircuit.getBranches()).doesNotContain(branchBack);
    }
}
