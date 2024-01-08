package com.konnect.app.service.dto;

import com.konnect.app.domain.enumeration.Status;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.Olt} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OltDTO implements Serializable {

    private Long id;

    private String name;

    private String detail;

    private Status status;

    private BranchDTO branch;

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

    public BranchDTO getBranch() {
        return branch;
    }

    public void setBranch(BranchDTO branch) {
        this.branch = branch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OltDTO)) {
            return false;
        }

        OltDTO oltDTO = (OltDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, oltDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OltDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", detail='" + getDetail() + "'" +
            ", status='" + getStatus() + "'" +
            ", branch=" + getBranch() +
            "}";
    }
}
