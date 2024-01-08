package com.konnect.app.domain;

import com.konnect.app.domain.enumeration.Status;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A Dispatch.
 */
@Entity
@Table(name = "dispatch")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Dispatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "voice")
    private String voice;

    @Column(name = "data")
    private String data;

    @Column(name = "iptv")
    private String iptv;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "olt_port")
    private String oltPort;

    @Column(name = "reg_date")
    private String regDate;

    @Column(name = "fap_port")
    private String fapPort;

    @Column(name = "cpe_sn")
    private String cpeSn;

    @Column(name = "cpe_rx")
    private String cpeRx;

    @Column(name = "complain")
    private String complain;

    @Column(name = "remark")
    private String remark;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "location")
    private String location;

    @Column(name = "print_date")
    private LocalDate printDate;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Dispatch id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoice() {
        return this.voice;
    }

    public Dispatch voice(String voice) {
        this.setVoice(voice);
        return this;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getData() {
        return this.data;
    }

    public Dispatch data(String data) {
        this.setData(data);
        return this;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIptv() {
        return this.iptv;
    }

    public Dispatch iptv(String iptv) {
        this.setIptv(iptv);
        return this;
    }

    public void setIptv(String iptv) {
        this.iptv = iptv;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Dispatch customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public Dispatch contactNo(String contactNo) {
        this.setContactNo(contactNo);
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOltPort() {
        return this.oltPort;
    }

    public Dispatch oltPort(String oltPort) {
        this.setOltPort(oltPort);
        return this;
    }

    public void setOltPort(String oltPort) {
        this.oltPort = oltPort;
    }

    public String getRegDate() {
        return this.regDate;
    }

    public Dispatch regDate(String regDate) {
        this.setRegDate(regDate);
        return this;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getFapPort() {
        return this.fapPort;
    }

    public Dispatch fapPort(String fapPort) {
        this.setFapPort(fapPort);
        return this;
    }

    public void setFapPort(String fapPort) {
        this.fapPort = fapPort;
    }

    public String getCpeSn() {
        return this.cpeSn;
    }

    public Dispatch cpeSn(String cpeSn) {
        this.setCpeSn(cpeSn);
        return this;
    }

    public void setCpeSn(String cpeSn) {
        this.cpeSn = cpeSn;
    }

    public String getCpeRx() {
        return this.cpeRx;
    }

    public Dispatch cpeRx(String cpeRx) {
        this.setCpeRx(cpeRx);
        return this;
    }

    public void setCpeRx(String cpeRx) {
        this.cpeRx = cpeRx;
    }

    public String getComplain() {
        return this.complain;
    }

    public Dispatch complain(String complain) {
        this.setComplain(complain);
        return this;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getRemark() {
        return this.remark;
    }

    public Dispatch remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Status getStatus() {
        return this.status;
    }

    public Dispatch status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLocation() {
        return this.location;
    }

    public Dispatch location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getPrintDate() {
        return this.printDate;
    }

    public Dispatch printDate(LocalDate printDate) {
        this.setPrintDate(printDate);
        return this;
    }

    public void setPrintDate(LocalDate printDate) {
        this.printDate = printDate;
    }

    public Instant getPublicationDate() {
        return this.publicationDate;
    }

    public Dispatch publicationDate(Instant publicationDate) {
        this.setPublicationDate(publicationDate);
        return this;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Dispatch team(Team team) {
        this.setTeam(team);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dispatch)) {
            return false;
        }
        return getId() != null && getId().equals(((Dispatch) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dispatch{" +
            "id=" + getId() +
            ", voice='" + getVoice() + "'" +
            ", data='" + getData() + "'" +
            ", iptv='" + getIptv() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", oltPort='" + getOltPort() + "'" +
            ", regDate='" + getRegDate() + "'" +
            ", fapPort='" + getFapPort() + "'" +
            ", cpeSn='" + getCpeSn() + "'" +
            ", cpeRx='" + getCpeRx() + "'" +
            ", complain='" + getComplain() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status='" + getStatus() + "'" +
            ", location='" + getLocation() + "'" +
            ", printDate='" + getPrintDate() + "'" +
            ", publicationDate='" + getPublicationDate() + "'" +
            "}";
    }
}
