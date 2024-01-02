package com.konnect.app.service.dto;

import com.konnect.app.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.EmployeeArea} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EmployeeAreaDTO implements Serializable {

    private Long id;

    private Status status;

    private Instant publicationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeAreaDTO)) {
            return false;
        }

        EmployeeAreaDTO employeeAreaDTO = (EmployeeAreaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, employeeAreaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeeAreaDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            "}";
    }
}
