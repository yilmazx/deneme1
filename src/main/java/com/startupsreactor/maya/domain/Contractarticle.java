package com.startupsreactor.maya.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Contractarticle.
 */
@Entity
@Table(name = "contractarticle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contractarticle extends BaseEntityLong {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "articlename", nullable = false)
    private String articlename;

    @NotNull
    @Column(name = "detail", nullable = false)
    private String detail;

    @NotNull
    @Column(name = "isenabled", nullable = false)
    private Boolean isenabled;

    @ManyToOne
    @JsonIgnoreProperties(value = { "city", "country", "legalarea", "industry", "contracttype" }, allowSetters = true)
    private Contract contract;

    @ManyToOne
    @JsonIgnoreProperties(value = { "contract", "parent" }, allowSetters = true)
    private Contractarticle parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getArticlename() {
        return this.articlename;
    }

    public Contractarticle articlename(String articlename) {
        this.setArticlename(articlename);
        return this;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getDetail() {
        return this.detail;
    }

    public Contractarticle detail(String detail) {
        this.setDetail(detail);
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUid() {
        return this.uid;
    }

    public Contractarticle uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Contractarticle isdeleted(Boolean isdeleted) {
        setIsdeleted(isdeleted);
        return this;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public Contractarticle isenabled(Boolean isenabled) {
        this.setIsenabled(isenabled);
        return this;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Contractarticle contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public Contractarticle getParent() {
        return this.parent;
    }

    public void setParent(Contractarticle contractarticle) {
        this.parent = contractarticle;
    }

    public Contractarticle parent(Contractarticle contractarticle) {
        this.setParent(contractarticle);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contractarticle)) {
            return false;
        }
        return id != null && id.equals(((Contractarticle) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contractarticle{" +
            "id=" + getId() +
            ", articlename='" + getArticlename() + "'" +
            ", detail='" + getDetail() + "'" +
            ", uid='" + getUid() + "'" +
           
            ", isenabled='" + getIsenabled() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            "}";
    }
}
