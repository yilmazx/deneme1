package com.startupsreactor.maya.service.dto;

import com.startupsreactor.maya.domain.Contract;
import com.startupsreactor.maya.domain.Lookup;
import java.time.Instant;

public class ContractDTO extends BaseDTO {

    private Long id;

    private String contractname;

    private String contractpath;
    private String user;

    private Boolean isenabled;

    private LookupDTO city;

    private LookupDTO country;

    private LookupDTO legalarea;

    private LookupDTO industry;

    private LookupDTO contracttype;

    public ContractDTO() {}

    public ContractDTO(Contract contract) {
        this.id = contract.getId();
        this.contractname = contract.getContractname();
        this.contractpath = contract.getContractpath();
        this.user = contract.getUser();
        this.isenabled = contract.getIsenabled();
        this.city = contract.getCity() != null ? new LookupDTO(contract.getCity()) : null;
        this.country = contract.getCountry() != null ? new LookupDTO(contract.getCountry()) : null;
        this.legalarea = contract.getLegalarea() != null ? new LookupDTO(contract.getLegalarea()) : null;
        this.industry = contract.getIndustry() != null ? new LookupDTO(contract.getIndustry()) : null;
        this.contracttype = contract.getContracttype() != null ? new LookupDTO(contract.getContracttype()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractname() {
        return this.contractname;
    }

    public ContractDTO contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getContractpath() {
        return this.contractpath;
    }

    public ContractDTO contractpath(String contractpath) {
        this.setContractpath(contractpath);
        return this;
    }

    public void setContractpath(String contractpath) {
        this.contractpath = contractpath;
    }

    public String getUser() {
        return this.user;
    }

    public ContractDTO user(String user) {
        this.setUser(user);
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public ContractDTO isenabled(Boolean isenabled) {
        this.setIsenabled(isenabled);
        return this;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    public LookupDTO getCity() {
        return this.city;
    }

    public void setCity(LookupDTO lookup) {
        this.city = lookup;
    }

    public ContractDTO city(LookupDTO lookup) {
        this.setCity(lookup);
        return this;
    }

    public LookupDTO getCountry() {
        return this.country;
    }

    public void setCountry(LookupDTO lookup) {
        this.country = lookup;
    }

    public ContractDTO country(LookupDTO lookup) {
        this.setCountry(lookup);
        return this;
    }

    public LookupDTO getLegalarea() {
        return this.legalarea;
    }

    public void setLegalarea(LookupDTO lookup) {
        this.legalarea = lookup;
    }

    public ContractDTO legalarea(LookupDTO lookup) {
        this.setLegalarea(lookup);
        return this;
    }

    public LookupDTO getIndustry() {
        return this.industry;
    }

    public void setIndustry(LookupDTO lookup) {
        this.industry = lookup;
    }

    public ContractDTO industry(LookupDTO lookup) {
        this.setIndustry(lookup);
        return this;
    }

    public LookupDTO getContracttype() {
        return this.contracttype;
    }

    public void setContracttype(LookupDTO lookup) {
        this.contracttype = lookup;
    }

    public ContractDTO contracttype(LookupDTO lookup) {
        this.setContracttype(lookup);
        return this;
    }
}
