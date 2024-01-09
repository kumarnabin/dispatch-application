package com.konnect.app.service.dto;

import com.konnect.app.domain.enumeration.Status;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.Area} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AreaDTO implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String detail;

    private Status status;

    private OltDTO olt;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OltDTO getOlt() {
        return olt;
    }

    public void setOlt(OltDTO olt) {
        this.olt = olt;
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
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", detail='" + getDetail() + "'" +
            ", status='" + getStatus() + "'" +
            ", olt=" + getOlt() +
            "}";
    }
}
