package com.konnect.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.ServiceProvider} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ServiceProviderDTO implements Serializable {

    private Long id;

    private String name;

    private String address;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceProviderDTO)) {
            return false;
        }

        ServiceProviderDTO serviceProviderDTO = (ServiceProviderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, serviceProviderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceProviderDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
