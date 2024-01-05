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

    private String remark;

    private Status status;

    private Instant publicationDate;

    private DispatchDTO dispatch;

    private EmployeeDTO employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public DispatchDTO getDispatch() {
        return dispatch;
    }

    public void setDispatch(DispatchDTO dispatch) {
        this.dispatch = dispatch;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
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
            ", remark='" + getRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", dispatch=" + getDispatch() +
            ", employee=" + getEmployee() +
            "}";
    }
}
