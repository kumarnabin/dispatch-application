package com.konnect.app.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.Branch} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BranchDTO implements Serializable {

    private Long id;

    private String name;

    private Instant publicationDate;

    private ServiceProviderDTO serviceProvider;

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

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public ServiceProviderDTO getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProviderDTO serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchDTO)) {
            return false;
        }

        BranchDTO branchDTO = (BranchDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, branchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BranchDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", serviceProvider=" + getServiceProvider() +
            "}";
    }
}
