package com.konnect.app.service.dto;

import com.konnect.app.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.DispatchRecord} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DispatchRecordDTO implements Serializable {

    private Long id;

    private Status status;

    private Instant publicationDate;

    private EmployeeDTO employee;

    private AreaDTO area;

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

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DispatchRecordDTO)) {
            return false;
        }

        DispatchRecordDTO dispatchRecordDTO = (DispatchRecordDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dispatchRecordDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DispatchRecordDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", employee=" + getEmployee() +
            ", area=" + getArea() +
            "}";
    }
}
