package com.startupsreactor.maya.service.dto;

import com.startupsreactor.maya.domain.Contract;
import com.startupsreactor.maya.domain.ContractInput;
import com.startupsreactor.maya.domain.Lookup;
import java.time.Instant;

public class ContractInputDTO extends BaseDTO {

    private Long id;

    private String inputname;

    private String inputvalue;

    private Boolean isenabled;

    private ContractDTO contract;

    public ContractInputDTO() {}

    public ContractInputDTO(ContractInput contractInput) {
        this.id = contractInput.getId();
        this.inputname = contractInput.getInputname();
        this.inputvalue = contractInput.getInputvalue();
        this.isenabled = contractInput.getIsenabled();
        this.contract = contractInput.getContract() != null ? new ContractDTO(contractInput.getContract()) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputname() {
        return this.inputname;
    }

    public ContractInputDTO inputname(String inputname) {
        this.setInputname(inputname);
        return this;
    }

    public void setInputname(String inputname) {
        this.inputname = inputname;
    }

    public String getInputvalue() {
        return this.inputvalue;
    }

    public ContractInputDTO inputvalue(String inputvalue) {
        this.setInputvalue(inputvalue);
        return this;
    }

    public void setInputvalue(String inputvalue) {
        this.inputvalue = inputvalue;
    }

    public Boolean getIsenabled() {
        return this.isenabled;
    }

    public ContractInputDTO isenabled(Boolean isenabled) {
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

    public ContractInputDTO contract(ContractDTO contract) {
        this.setContract(contract);
        return this;
    }
}
