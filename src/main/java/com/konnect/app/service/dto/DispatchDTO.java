package com.konnect.app.service.dto;

import com.konnect.app.domain.enumeration.Status;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.Dispatch} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DispatchDTO implements Serializable {

    private Long id;

    private String voice;

    private String data;

    private String iptv;

    private String customerName;

    private String contactNo;

    private String oltPort;

    private String regDate;

    private String fapPort;

    private String cpeSn;

    private String cpeRx;

    private String complain;

    private String remark;

    private Status status;

    private String location;

    private LocalDate printDate;

    private Instant publicationDate;

    private TeamDTO team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIptv() {
        return iptv;
    }

    public void setIptv(String iptv) {
        this.iptv = iptv;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getOltPort() {
        return oltPort;
    }

    public void setOltPort(String oltPort) {
        this.oltPort = oltPort;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getFapPort() {
        return fapPort;
    }

    public void setFapPort(String fapPort) {
        this.fapPort = fapPort;
    }

    public String getCpeSn() {
        return cpeSn;
    }

    public void setCpeSn(String cpeSn) {
        this.cpeSn = cpeSn;
    }

    public String getCpeRx() {
        return cpeRx;
    }

    public void setCpeRx(String cpeRx) {
        this.cpeRx = cpeRx;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getPrintDate() {
        return printDate;
    }

    public void setPrintDate(LocalDate printDate) {
        this.printDate = printDate;
    }

    public Instant getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Instant publicationDate) {
        this.publicationDate = publicationDate;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DispatchDTO)) {
            return false;
        }

        DispatchDTO dispatchDTO = (DispatchDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dispatchDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DispatchDTO{" +
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
            ", team=" + getTeam() +
            "}";
    }
}
