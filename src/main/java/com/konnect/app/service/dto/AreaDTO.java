package com.konnect.app.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.konnect.app.domain.Area} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AreaDTO implements Serializable {

    private Long id;

    private String code;

    private String detail;

    private Instant publicationDate;

    private Set<EmployeeDTO> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AreaDTO)) {
            return false;
        }

        AreaDTO areaDTO = (AreaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, areaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AreaDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", detail='" + getDetail() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", employees=" + getEmployees() +
            "}";
    }
}
