package com.startupsreactor.maya.service;

import com.startupsreactor.maya.domain.ContractInput;
import com.startupsreactor.maya.repository.ContractInputRepository;
import com.startupsreactor.maya.service.dto.ContractInputDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContractInputService extends BaseService<ContractInputRepository, ContractInput, ContractInputDTO, Long> {

    public List<ContractInputDTO> getContractInputByContactId(Long contactId) {
        List<ContractInput> contractInputList = repository.findByContractIdAndIsdeletedFalse(contactId);
        List<ContractInputDTO> dtoList = new ArrayList<>();
        for (ContractInput contractInput : contractInputList) {
            ContractInputDTO dto = new ContractInputDTO(contractInput);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
