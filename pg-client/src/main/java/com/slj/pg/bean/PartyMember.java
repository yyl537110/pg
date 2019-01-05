package com.slj.pg.bean;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PartyMember {
    private Integer id;

    @NotNull
    private Integer orgId;

    @NotNull
    private String name;

    @NotNull
    private Integer sex;

    private String nameUsed;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private Integer nation;

    @NotNull
    private String nativePlace;

    @NotNull
    private String birthPlace;

    @NotNull
    private Integer marriageState;

    @NotNull
    private String idCard;

    @NotNull
    private String phone;

    @NotNull
    private LocalDate gmtJoined;

    @NotNull
    private Integer passState;

    @NotNull
    private String introducer;

    @NotNull
    private LocalDate gmtPassed;

    @NotNull
    private Double dues;

    @NotNull
    private String address;

    @NotNull
    private String accountAddress;

    @NotNull
    private String identity;

    @NotNull
    private String school;

    @NotNull
    private String degree;

    @NotNull
    private String major;

    @NotNull
    private Integer qualification;

    @NotNull
    private String company;

    private String companyAddress;

    private String job;

    private String technique;

    private LocalDate gmtRetired;

    private LocalDate gmtLeaved;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNameUsed() {
        return nameUsed;
    }

    public void setNameUsed(String nameUsed) {
        this.nameUsed = nameUsed == null ? null : nameUsed.trim();
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace == null ? null : birthPlace.trim();
    }

    public Integer getMarriageState() {
        return marriageState;
    }

    public void setMarriageState(Integer marriageState) {
        this.marriageState = marriageState;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public LocalDate getGmtJoined() {
        return gmtJoined;
    }

    public void setGmtJoined(LocalDate gmtJoined) {
        this.gmtJoined = gmtJoined;
    }

    public Integer getPassState() {
        return passState;
    }

    public void setPassState(Integer passState) {
        this.passState = passState;
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer == null ? null : introducer.trim();
    }

    public LocalDate getGmtPassed() {
        return gmtPassed;
    }

    public void setGmtPassed(LocalDate gmtPassed) {
        this.gmtPassed = gmtPassed;
    }

    public Double getDues() {
        return dues;
    }

    public void setDues(Double dues) {
        this.dues = dues;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress == null ? null : accountAddress.trim();
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getTechnique() {
        return technique;
    }

    public void setTechnique(String technique) {
        this.technique = technique == null ? null : technique.trim();
    }

    public LocalDate getGmtRetired() {
        return gmtRetired;
    }

    public void setGmtRetired(LocalDate gmtRetired) {
        this.gmtRetired = gmtRetired;
    }

    public LocalDate getGmtLeaved() {
        return gmtLeaved;
    }

    public void setGmtLeaved(LocalDate gmtLeaved) {
        this.gmtLeaved = gmtLeaved;
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