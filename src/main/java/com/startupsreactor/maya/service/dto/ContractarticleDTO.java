package com.startupsreactor.maya.service.dto;

import com.startupsreactor.maya.domain.Contract;
import com.startupsreactor.maya.domain.Contractarticle;
import com.startupsreactor.maya.domain.Lookup;
import java.time.Instant;

public class ContractarticleDTO extends BaseDTO {

    private Long id;

    private String articlename;

    private String detail;

    private Boolean isenabled;

    private ContractDTO contract;

    private ContractarticleDTO parent;

    public ContractarticleDTO() {}

    public ContractarticleDTO(Contractarticle contractarticle) {
        this.id = contractarticle.getId();
        this.articlename = contractarticle.getArticlename();
        this.detail = contractarticle.getDetail();
        this.isenabled = contractarticle.getIsenabled();
        this.contract = contractarticle.getContract() != null ? new ContractDTO(contractarticle.getContract()) : null;
        this.parent = contractarticle.getParent() != null ? new ContractarticleDTO(contractarticle.getParent()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticlename() {
        return this.articlename;
    }

    public ContractarticleDTO articlename(String articlename) {
        this.setArticlename(articlename);
        return this;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getDetail() {
        return this.detail;
    }

    public ContractarticleDTO detail(String detail) {
        this.setDetail(detail);
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public ContractarticleDTO isenabled(Boolean isenabled) {
        this.setIsenabled(isenabled);
        return this;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    public ContractDTO getContract() {
        return this.contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public ContractarticleDTO contract(ContractDTO contract) {
        this.setContract(contract);
        return this;
    }

    public ContractarticleDTO getParent() {
        return this.parent;
    }

    public void setParent(ContractarticleDTO contractarticle) {
        this.parent = contractarticle;
    }

    public ContractarticleDTO parent(ContractarticleDTO contractarticle) {
        this.setParent(contractarticle);
        return this;
    }
}
