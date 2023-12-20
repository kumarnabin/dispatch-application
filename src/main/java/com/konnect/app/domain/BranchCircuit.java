package com.konnect.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A BranchCircuit.
 */
@Entity
@Table(name = "branch_circuit")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchCircuit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "serviceProviders" }, allowSetters = true)
    private MasterCircuit masterCircuit;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_branch_circuit__branch",
        joinColumns = @JoinColumn(name = "branch_circuit_id"),
        inverseJoinColumns = @JoinColumn(name = "branch_id")
    )
    @JsonIgnoreProperties(value = { "serviceProvider", "branchCircuits" }, allowSetters = true)
    private Set<Branch> branches = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BranchCircuit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public BranchCircuit title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getPublicationDate() {
        return this.publicationDate;
    }

    public BranchCircuit publicationDate(Instant publicationDate) {
        this.setPublicationDate(publicationDate);
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public MasterCircuit getMasterCircuit() {
        return this.masterCircuit;
    }

    public void setMasterCircuit(MasterCircuit masterCircuit) {
        this.masterCircuit = masterCircuit;
    }

    public BranchCircuit masterCircuit(MasterCircuit masterCircuit) {
        this.setMasterCircuit(masterCircuit);
        return this;
    }

    public Set<Branch> getBranches() {
        return this.branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public BranchCircuit branches(Set<Branch> branches) {
        this.setBranches(branches);
        return this;
    }

    public BranchCircuit addBranch(Branch branch) {
        this.branches.add(branch);
        return this;
    }

    public BranchCircuit removeBranch(Branch branch) {
        this.branches.remove(branch);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchCircuit)) {
            return false;
        }
        return getId() != null && getId().equals(((BranchCircuit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchCircuit{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            "}";
    }
}
