package com.konnect.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.konnect.app.domain.enumeration.Status;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A Attendance.
 */
@Entity
@Table(name = "attendance")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "meter_pics")
    private String meterPics;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "user" }, allowSetters = true)
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Attendance id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return this.status;
    }

    public Attendance status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMeterPics() {
        return this.meterPics;
    }

    public Attendance meterPics(String meterPics) {
        this.setMeterPics(meterPics);
        return this;
    }

    public void setMeterPics(String meterPics) {
        this.meterPics = meterPics;
    }

    public Instant getPublicationDate() {
        return this.publicationDate;
    }

    public Attendance publicationDate(Instant publicationDate) {
        this.setPublicationDate(publicationDate);
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Attendance employee(Employee employee) {
        this.setEmployee(employee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Attendance)) {
            return false;
        }
        return getId() != null && getId().equals(((Attendance) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Attendance{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", meterPics='" + getMeterPics() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            "}";
    }
}
