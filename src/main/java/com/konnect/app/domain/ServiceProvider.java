package com.konnect.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ServiceProvider.
 */
@Entity
@Table(name = "service_provider")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceProvider implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "serviceProviders")
    @JsonIgnoreProperties(value = { "serviceProviders" }, allowSetters = true)
    private Set<MasterCircuit> masterCircuits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ServiceProvider id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ServiceProvider name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public ServiceProvider address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<MasterCircuit> getMasterCircuits() {
        return this.masterCircuits;
    }

    public void setMasterCircuits(Set<MasterCircuit> masterCircuits) {
        if (this.masterCircuits != null) {
            this.masterCircuits.forEach(i -> i.removeServiceProvider(this));
        }
        if (masterCircuits != null) {
            masterCircuits.forEach(i -> i.addServiceProvider(this));
        }
        this.masterCircuits = masterCircuits;
    }

    public ServiceProvider masterCircuits(Set<MasterCircuit> masterCircuits) {
        this.setMasterCircuits(masterCircuits);
        return this;
    }

    public ServiceProvider addMasterCircuit(MasterCircuit masterCircuit) {
        this.masterCircuits.add(masterCircuit);
        masterCircuit.getServiceProviders().add(this);
        return this;
    }

    public ServiceProvider removeMasterCircuit(MasterCircuit masterCircuit) {
        this.masterCircuits.remove(masterCircuit);
        masterCircuit.getServiceProviders().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceProvider)) {
            return false;
        }
        return getId() != null && getId().equals(((ServiceProvider) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceProvider{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
