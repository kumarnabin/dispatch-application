package com.konnect.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.konnect.app.domain.Team} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TeamDTO implements Serializable {

    private Long id;

    private String teamNo;

    private String supervisor;

    private String supervisorPhoneNo;

    private String teamLeader;

    private String teamLeaderPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorPhoneNo() {
        return supervisorPhoneNo;
    }

    public void setSupervisorPhoneNo(String supervisorPhoneNo) {
        this.supervisorPhoneNo = supervisorPhoneNo;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public String getTeamLeaderPhone() {
        return teamLeaderPhone;
    }

    public void setTeamLeaderPhone(String teamLeaderPhone) {
        this.teamLeaderPhone = teamLeaderPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamDTO)) {
            return false;
        }

        TeamDTO teamDTO = (TeamDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, teamDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeamDTO{" +
            "id=" + getId() +
            ", teamNo='" + getTeamNo() + "'" +
            ", supervisor='" + getSupervisor() + "'" +
            ", supervisorPhoneNo='" + getSupervisorPhoneNo() + "'" +
            ", teamLeader='" + getTeamLeader() + "'" +
            ", teamLeaderPhone='" + getTeamLeaderPhone() + "'" +
            "}";
    }
}
