package com.konnect.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.konnect.app.domain.enumeration.Status;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Branch.
 */
@Entity
@Table(name = "branch")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "masterCircuits" }, allowSetters = true)
    private ServiceProvider serviceProvider;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "branches")
    @JsonIgnoreProperties(value = { "masterCircuit", "branches" }, allowSetters = true)
    private Set<BranchCircuit> branchCircuits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Branch id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Branch name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public Branch code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return this.status;
    }

    public Branch status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ServiceProvider getServiceProvider() {
        return this.serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Branch serviceProvider(ServiceProvider serviceProvider) {
        this.setServiceProvider(serviceProvider);
        return this;
    }

    public Set<BranchCircuit> getBranchCircuits() {
        return this.branchCircuits;
    }

    public void setBranchCircuits(Set<BranchCircuit> branchCircuits) {
        if (this.branchCircuits != null) {
            this.branchCircuits.forEach(i -> i.removeBranch(this));
        }
        if (branchCircuits != null) {
            branchCircuits.forEach(i -> i.addBranch(this));
        }
        this.branchCircuits = branchCircuits;
    }

    public Branch branchCircuits(Set<BranchCircuit> branchCircuits) {
        this.setBranchCircuits(branchCircuits);
        return this;
    }

    public Branch addBranchCircuit(BranchCircuit branchCircuit) {
        this.branchCircuits.add(branchCircuit);
        branchCircuit.getBranches().add(this);
        return this;
    }

    public Branch removeBranchCircuit(BranchCircuit branchCircuit) {
        this.branchCircuits.remove(branchCircuit);
        branchCircuit.getBranches().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branch)) {
            return false;
        }
        return getId() != null && getId().equals(((Branch) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Branch{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
