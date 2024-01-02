package com.konnect.app.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Team.
 */
@Entity
@Table(name = "team")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "team_no")
    private String teamNo;

    @Column(name = "supervisor")
    private String supervisor;

    @Column(name = "supervisor_phone_no")
    private String supervisorPhoneNo;

    @Column(name = "team_leader")
    private String teamLeader;

    @Column(name = "team_leader_phone")
    private String teamLeaderPhone;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Team id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamNo() {
        return this.teamNo;
    }

    public Team teamNo(String teamNo) {
        this.setTeamNo(teamNo);
        return this;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public String getSupervisor() {
        return this.supervisor;
    }

    public Team supervisor(String supervisor) {
        this.setSupervisor(supervisor);
        return this;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorPhoneNo() {
        return this.supervisorPhoneNo;
    }

    public Team supervisorPhoneNo(String supervisorPhoneNo) {
        this.setSupervisorPhoneNo(supervisorPhoneNo);
        return this;
    }

    public void setSupervisorPhoneNo(String supervisorPhoneNo) {
        this.supervisorPhoneNo = supervisorPhoneNo;
    }

    public String getTeamLeader() {
        return this.teamLeader;
    }

    public Team teamLeader(String teamLeader) {
        this.setTeamLeader(teamLeader);
        return this;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamLeaderPhone() {
        return this.teamLeaderPhone;
    }

    public Team teamLeaderPhone(String teamLeaderPhone) {
        this.setTeamLeaderPhone(teamLeaderPhone);
        return this;
    }

    public void setTeamLeaderPhone(String teamLeaderPhone) {
        this.teamLeaderPhone = teamLeaderPhone;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Team)) {
            return false;
        }
        return getId() != null && getId().equals(((Team) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Team{" +
            "id=" + getId() +
            ", teamNo='" + getTeamNo() + "'" +
            ", supervisor='" + getSupervisor() + "'" +
            ", supervisorPhoneNo='" + getSupervisorPhoneNo() + "'" +
            ", teamLeader='" + getTeamLeader() + "'" +
            ", teamLeaderPhone='" + getTeamLeaderPhone() + "'" +
            "}";
    }
}
