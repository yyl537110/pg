package com.slj.pg.bean;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PayLog {
    private Integer id;

    @NotNull
    private Integer partyMemberId;

    @NotNull
    private Integer orgId;

    @NotNull
    private Integer payYear;

    @NotNull
    private Integer payQuarter;

    @NotNull
    private Integer payType;

    @NotNull
    private Double money;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartyMemberId() {
        return partyMemberId;
    }

    public void setPartyMemberId(Integer partyMemberId) {
        this.partyMemberId = partyMemberId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getPayYear() {
        return payYear;
    }

    public void setPayYear(Integer payYear) {
        this.payYear = payYear;
    }

    public Integer getPayQuarter() {
        return payQuarter;
    }

    public void setPayQuarter(Integer payQuarter) {
        this.payQuarter = payQuarter;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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