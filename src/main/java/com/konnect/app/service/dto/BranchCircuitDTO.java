package com.konnect.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.konnect.app.domain.BranchCircuit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchCircuitDTO implements Serializable {

    private Long id;

    private String title;

    private Instant publicationDate;

    private MasterCircuitDTO masterCircuit;

    private Set<BranchDTO> branches = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public MasterCircuitDTO getMasterCircuit() {
        return masterCircuit;
    }

    public void setMasterCircuit(MasterCircuitDTO masterCircuit) {
        this.masterCircuit = masterCircuit;
    }

    public Set<BranchDTO> getBranches() {
        return branches;
    }

    public void setBranches(Set<BranchDTO> branches) {
        this.branches = branches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchCircuitDTO)) {
            return false;
        }

        BranchCircuitDTO branchCircuitDTO = (BranchCircuitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, branchCircuitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchCircuitDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", masterCircuit=" + getMasterCircuit() +
            ", branches=" + getBranches() +
            "}";
    }
}
