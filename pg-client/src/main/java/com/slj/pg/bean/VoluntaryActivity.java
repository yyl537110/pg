package com.slj.pg.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class VoluntaryActivity {
    private Integer id;

    @NotNull
    private Integer volunteerId;

    @NotBlank
    private String name;

    private String orgName;

    @NotNull
    private Integer voluntaryActivityType;

    private LocalDateTime gmtStart;

    private LocalDateTime gmtEnd;

    @NotNull
    private Double duration;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public Integer getVoluntaryActivityType() {
        return voluntaryActivityType;
    }

    public void setVoluntaryActivityType(Integer voluntaryActivityType) {
        this.voluntaryActivityType = voluntaryActivityType;
    }

    public LocalDateTime getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(LocalDateTime gmtStart) {
        this.gmtStart = gmtStart;
    }

    public LocalDateTime getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(LocalDateTime gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}