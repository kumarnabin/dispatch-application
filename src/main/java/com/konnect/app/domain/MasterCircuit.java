package com.konnect.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MasterCircuit.
 */
@Entity
@Table(name = "master_circuit")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MasterCircuit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_master_circuit__service_provider",
        joinColumns = @JoinColumn(name = "master_circuit_id"),
        inverseJoinColumns = @JoinColumn(name = "service_provider_id")
    )
    @JsonIgnoreProperties(value = { "masterCircuits" }, allowSetters = true)
    private Set<ServiceProvider> serviceProviders = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MasterCircuit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public MasterCircuit name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public MasterCircuit address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<ServiceProvider> getServiceProviders() {
        return this.serviceProviders;
    }

    public void setServiceProviders(Set<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public MasterCircuit serviceProviders(Set<ServiceProvider> serviceProviders) {
        this.setServiceProviders(serviceProviders);
        return this;
    }

    public MasterCircuit addServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProviders.add(serviceProvider);
        return this;
    }

    public MasterCircuit removeServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProviders.remove(serviceProvider);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterCircuit)) {
            return false;
        }
        return getId() != null && getId().equals(((MasterCircuit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MasterCircuit{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
