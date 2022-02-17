package com.startupsreactor.maya.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Contract.
 */
@Entity
@Table(name = "contract")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contract extends BaseEntityLong {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "contractname", nullable = false)
    private String contractname;

    @NotNull
    @Column(name = "contractpath", nullable = false)
    private String contractpath;

    @NotNull
    @Column(name = "jhi_user", nullable = false)
    private String user;

    @NotNull
    @Column(name = "isenabled", nullable = false)
    private Boolean isenabled;

    @ManyToOne
    @JsonIgnoreProperties(value = { "parent" }, allowSetters = true)
    private Lookup city;

    @ManyToOne
    @JsonIgnoreProperties(value = { "parent" }, allowSetters = true)
    private Lookup country;

    @ManyToOne
    @JsonIgnoreProperties(value = { "parent" }, allowSetters = true)
    private Lookup legalarea;

    @ManyToOne
    @JsonIgnoreProperties(value = { "parent" }, allowSetters = true)
    private Lookup industry;

    @ManyToOne
    @JsonIgnoreProperties(value = { "parent" }, allowSetters = true)
    private Lookup contracttype;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Contract id(Long id) {
        this.setId(id);
        return this;
    }

    public String getContractname() {
        return this.contractname;
    }

    public Contract contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getContractpath() {
        return this.contractpath;
    }

    public Contract contractpath(String contractpath) {
        this.setContractpath(contractpath);
        return this;
    }

    public void setContractpath(String contractpath) {
        this.contractpath = contractpath;
    }

    public String getUid() {
        return this.uid;
    }

    public Contract uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser() {
        return this.user;
    }

    public Contract user(String user) {
        this.setUser(user);
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Contract isdeleted(Boolean isdeleted) {
        setIsdeleted(isdeleted);
        return this;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public Contract isenabled(Boolean isenabled) {
        this.setIsenabled(isenabled);
        return this;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    public Lookup getCity() {
        return this.city;
    }

    public void setCity(Lookup lookup) {
        this.city = lookup;
    }

    public Contract city(Lookup lookup) {
        this.setCity(lookup);
        return this;
    }

    public Lookup getCountry() {
        return this.country;
    }

    public void setCountry(Lookup lookup) {
        this.country = lookup;
    }

    public Contract country(Lookup lookup) {
        this.setCountry(lookup);
        return this;
    }

    public Lookup getLegalarea() {
        return this.legalarea;
    }

    public void setLegalarea(Lookup lookup) {
        this.legalarea = lookup;
    }

    public Contract legalarea(Lookup lookup) {
        this.setLegalarea(lookup);
        return this;
    }

    public Lookup getIndustry() {
        return this.industry;
    }

    public void setIndustry(Lookup lookup) {
        this.industry = lookup;
    }

    public Contract industry(Lookup lookup) {
        this.setIndustry(lookup);
        return this;
    }

    public Lookup getContracttype() {
        return this.contracttype;
    }

    public void setContracttype(Lookup lookup) {
        this.contracttype = lookup;
    }

    public Contract contracttype(Lookup lookup) {
        this.setContracttype(lookup);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        return id != null && id.equals(((Contract) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", contractname='" + getContractname() + "'" +
            ", contractpath='" + getContractpath() + "'" +
            ", uid='" + getUid() + "'" +
            ", user='" + getUser() + "'" +
           // ", isdeleted='" + getIsdeleted() + "'" +
            ", isenabled='" + getIsenabled() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            "}";
    }
}
