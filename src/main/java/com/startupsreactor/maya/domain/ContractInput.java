package com.startupsreactor.maya.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ContractInput.
 */
@Entity
@Table(name = "contractinput")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ContractInput extends BaseEntityLong {

    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    // @SequenceGenerator(name = "sequenceGenerator")
    // @Column(name = "id")
    // private Long id;

    @NotNull
    @Column(name = "inputname", nullable = false)
    private String inputname;

    @NotNull
    @Column(name = "inputvalue", nullable = false)
    private String inputvalue;

    @NotNull
    @Column(name = "isenabled", nullable = false)
    private Boolean isenabled;

    @ManyToOne
    @JsonIgnoreProperties(value = { "city", "country", "legalarea", "industry", "contracttype" }, allowSetters = true)
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getInputname() {
        return this.inputname;
    }

    public ContractInput inputname(String inputname) {
        this.setInputname(inputname);
        return this;
    }

    public void setInputname(String inputname) {
        this.inputname = inputname;
    }

    public String getInputvalue() {
        return this.inputvalue;
    }

    public ContractInput inputvalue(String inputvalue) {
        this.setInputvalue(inputvalue);
        return this;
    }

    public void setInputvalue(String inputvalue) {
        this.inputvalue = inputvalue;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public ContractInput isenabled(Boolean isenabled) {
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

    public ContractInput contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractInput)) {
            return false;
        }
        return id != null && id.equals(((ContractInput) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractInput{" +
            "id=" + getId() +
            ", inputname='" + getInputname() + "'" +
            ", inputvalue='" + getInputvalue() + "'" +
            ", uid='" + getUid() + "'" +
          
            ", isenabled='" + getIsenabled() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            "}";
    }
}
