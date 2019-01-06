package com.slj.pg.bean;

import java.time.LocalDateTime;
import java.util.List;

public class Organization {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer level;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer status;

    private Integer left;

    private Integer top;

    private Double width;

    private Double height;

    private Integer iconx;

    private Integer icony;

    private String path;

    private String secretary;

    private String address;

    private Double area;

    private Integer streetNum;

    private Integer villageNum;

    private Integer peopleNum;

    private Integer subNum;

    private Integer memberNum;

    private Integer countyPartyNum;

    private Integer repNum;

    private String introduction;

    private Integer onlineNum;

    private Integer dutyNum;

    private Integer hqNum;

    private Integer branchNum;

    private List<Organization> subs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Integer getIconx() {
        return iconx;
    }

    public void setIconx(Integer iconx) {
        this.iconx = iconx;
    }

    public Integer getIcony() {
        return icony;
    }

    public void setIcony(Integer icony) {
        this.icony = icony;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(Integer streetNum) {
        this.streetNum = streetNum;
    }

    public Integer getVillageNum() {
        return villageNum;
    }

    public void setVillageNum(Integer villageNum) {
        this.villageNum = villageNum;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getSubNum() {
        return subNum;
    }

    public void setSubNum(Integer subNum) {
        this.subNum = subNum;
    }

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getCountyPartyNum() {
        return countyPartyNum;
    }

    public void setCountyPartyNum(Integer countyPartyNum) {
        this.countyPartyNum = countyPartyNum;
    }

    public Integer getRepNum() {
        return repNum;
    }

    public void setRepNum(Integer repNum) {
        this.repNum = repNum;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    public Integer getDutyNum() {
        return dutyNum;
    }

    public void setDutyNum(Integer dutyNum) {
        this.dutyNum = dutyNum;
    }

    public Integer getHqNum() {
        return hqNum;
    }

    public void setHqNum(Integer hqNum) {
        this.hqNum = hqNum;
    }

    public Integer getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(Integer branchNum) {
        this.branchNum = branchNum;
    }

    public List<Organization> getSubs() {
        return subs;
    }

    public void setSubs(List<Organization> subs) {
        this.subs = subs;
    }
}