package com.startupsreactor.maya.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Documentarticle.
 */
@Entity
@Table(name = "documentarticle")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Documentarticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "document_id")
    private Long documentId;

    @Column(name = "contractarticle_id")
    private Long contractarticleId;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted;

    @NotNull
    @Column(name = "update_date", nullable = false)
    private ZonedDateTime updateDate;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private ZonedDateTime createDate;

    @NotNull
    @Column(name = "create_by", nullable = false)
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @ManyToOne
    @JsonIgnoreProperties(value = { "city", "country", "legalarea", "industry", "contracttype" }, allowSetters = true)
    private Contract contract;

    @ManyToOne
    @JsonIgnoreProperties(value = { "city", "country", "legalarea", "industry", "contracttype" }, allowSetters = true)
    private Contract parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Documentarticle id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return this.documentId;
    }

    public Documentarticle documentId(Long documentId) {
        this.setDocumentId(documentId);
        return this;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getContractarticleId() {
        return this.contractarticleId;
    }

    public Documentarticle contractarticleId(Long contractarticleId) {
        this.setContractarticleId(contractarticleId);
        return this;
    }

    public void setContractarticleId(Long contractarticleId) {
        this.contractarticleId = contractarticleId;
    }

    public Boolean getIs_deleted() {
        return this.is_deleted;
    }

    public Documentarticle is_deleted(Boolean is_deleted) {
        this.setIs_deleted(is_deleted);
        return this;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public ZonedDateTime getUpdateDate() {
        return this.updateDate;
    }

    public Documentarticle updateDate(ZonedDateTime updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }

    public Documentarticle createDate(ZonedDateTime createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public Documentarticle createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Documentarticle updateBy(String updateBy) {
        this.setUpdateBy(updateBy);
        return this;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Documentarticle contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public Contract getParent() {
        return this.parent;
    }

    public void setParent(Contract contract) {
        this.parent = contract;
    }

    public Documentarticle parent(Contract contract) {
        this.setParent(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Documentarticle)) {
            return false;
        }
        return id != null && id.equals(((Documentarticle) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Documentarticle{" +
            "id=" + getId() +
            ", documentId=" + getDocumentId() +
            ", contractarticleId=" + getContractarticleId() +
            ", is_deleted='" + getIs_deleted() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            "}";
    }
}
