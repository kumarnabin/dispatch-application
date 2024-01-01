package com.konnect.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Area.
 */
@Entity
@Table(name = "area")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "detail")
    private String detail;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_area__employee",
        joinColumns = @JoinColumn(name = "area_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JsonIgnoreProperties(value = { "user", "areas" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Area id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Area code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return this.detail;
    }

    public Area detail(String detail) {
        this.setDetail(detail);
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Instant getPublicationDate() {
        return this.publicationDate;
    }

    public Area publicationDate(Instant publicationDate) {
        this.setPublicationDate(publicationDate);
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Area employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public Area addEmployee(Employee employee) {
        this.employees.add(employee);
        return this;
    }

    public Area removeEmployee(Employee employee) {
        this.employees.remove(employee);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Area)) {
            return false;
        }
        return getId() != null && getId().equals(((Area) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Area{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", detail='" + getDetail() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            "}";
    }
}
