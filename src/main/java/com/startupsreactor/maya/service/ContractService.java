package com.startupsreactor.maya.service;

import com.startupsreactor.maya.domain.Contract;
import com.startupsreactor.maya.repository.ContractRepository;
import com.startupsreactor.maya.service.dto.ContractDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContractService extends BaseService<ContractRepository, Contract, ContractDTO, Long> {

    public List<ContractDTO> getContractByContactnameContaining(String contactname) {
        List<Contract> contractList = repository.findByContractnameContainingAndIsdeletedFalse(contactname);
        List<ContractDTO> dtoList = new ArrayList<>();
        for (Contract contract : contractList) {
            ContractDTO dto = new ContractDTO(contract);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
