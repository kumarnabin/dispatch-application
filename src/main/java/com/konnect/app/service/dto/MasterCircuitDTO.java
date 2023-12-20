package com.konnect.app.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.konnect.app.domain.MasterCircuit} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MasterCircuitDTO implements Serializable {

    private Long id;

    private String name;

    private String address;

    private Set<ServiceProviderDTO> serviceProviders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<ServiceProviderDTO> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(Set<ServiceProviderDTO> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MasterCircuitDTO)) {
            return false;
        }

        MasterCircuitDTO masterCircuitDTO = (MasterCircuitDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, masterCircuitDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MasterCircuitDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", serviceProviders=" + getServiceProviders() +
            "}";
    }
}
