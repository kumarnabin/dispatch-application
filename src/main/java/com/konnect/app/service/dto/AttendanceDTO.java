package com.konnect.app.service.dto;

import com.konnect.app.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.Attendance} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttendanceDTO implements Serializable {

    private Long id;

    private Status status;

    private String meterPics;

    private Instant publicationDate;

    private EmployeeDTO employee;

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

    public String getMeterPics() {
        return meterPics;
    }

    public void setMeterPics(String meterPics) {
        this.meterPics = meterPics;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttendanceDTO)) {
            return false;
        }

        AttendanceDTO attendanceDTO = (AttendanceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, attendanceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttendanceDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", meterPics='" + getMeterPics() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            ", employee=" + getEmployee() +
            "}";
    }
}
