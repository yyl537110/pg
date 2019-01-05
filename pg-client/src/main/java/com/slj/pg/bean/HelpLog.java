package com.slj.pg.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HelpLog {
    private Integer id;

    @NotNull
    private Integer poorId;

    @NotNull
    private Integer cadreId;

    @NotBlank
    private String project;

    @NotNull
    private Double money;

    @NotNull
    private LocalDate time;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer status;

    private HelpPoor helpPoor;

    private HelpCadre helpCadre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoorId() {
        return poorId;
    }

    public void setPoorId(Integer poorId) {
        this.poorId = poorId;
    }

    public Integer getCadreId() {
        return cadreId;
    }

    public void setCadreId(Integer cadreId) {
        this.cadreId = cadreId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
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

    public HelpPoor getHelpPoor() {
        return helpPoor;
    }

    public void setHelpPoor(HelpPoor helpPoor) {
        this.helpPoor = helpPoor;
    }

    public HelpCadre getHelpCadre() {
        return helpCadre;
    }

    public void setHelpCadre(HelpCadre helpCadre) {
        this.helpCadre = helpCadre;
    }
}