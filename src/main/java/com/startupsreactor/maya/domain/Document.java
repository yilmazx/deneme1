package com.startupsreactor.maya.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "documentname", nullable = false)
    private String documentname;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "uid", nullable = false)
    private String uid;

    @Column(name = "path")
    private String path;

    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "inputs")
    private String inputs;

    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted;

    @NotNull
    @Column(name = "isenabled", nullable = false)
    private Boolean isenabled;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Document id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentname() {
        return this.documentname;
    }

    public Document documentname(String documentname) {
        this.setDocumentname(documentname);
        return this;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public String getDescription() {
        return this.description;
    }

    public Document description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return this.uid;
    }

    public Document uid(String uid) {
        this.setUid(uid);
        return this;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPath() {
        return this.path;
    }

    public Document path(String path) {
        this.setPath(path);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getContactId() {
        return this.contactId;
    }

    public Document contactId(Long contactId) {
        this.setContactId(contactId);
        return this;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getInputs() {
        return this.inputs;
    }

    public Document inputs(String inputs) {
        this.setInputs(inputs);
        return this;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public Boolean getIs_deleted() {
        return this.is_deleted;
    }

    public Document is_deleted(Boolean is_deleted) {
        this.setIs_deleted(is_deleted);
        return this;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public Document isenabled(Boolean isenabled) {
        this.setIsenabled(isenabled);
        return this;
    }

    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    public ZonedDateTime getUpdateDate() {
        return this.updateDate;
    }

    public Document updateDate(ZonedDateTime updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(ZonedDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }

    public Document createDate(ZonedDateTime createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public Document createBy(String createBy) {
        this.setCreateBy(createBy);
        return this;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public Document updateBy(String updateBy) {
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

    public Document contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", documentname='" + getDocumentname() + "'" +
            ", description='" + getDescription() + "'" +
            ", uid='" + getUid() + "'" +
            ", path='" + getPath() + "'" +
            ", contactId=" + getContactId() +
            ", inputs='" + getInputs() + "'" +
            ", is_deleted='" + getIs_deleted() + "'" +
            ", isenabled='" + getIsenabled() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            "}";
    }
}
