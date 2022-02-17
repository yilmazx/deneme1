package com.startupsreactor.maya.service;

import com.startupsreactor.maya.domain.Contractarticle;
import com.startupsreactor.maya.repository.ContractarticleRepository;
import com.startupsreactor.maya.service.dto.ContractarticleDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContractarticleService extends BaseService<ContractarticleRepository, Contractarticle, ContractarticleDTO, Long> {

    public List<ContractarticleDTO> getContractarticleByContactId(Long contactId) {
        List<Contractarticle> contractarticleList = repository.findByContractIdAndIsdeletedFalse(contactId);
        List<ContractarticleDTO> dtoList = new ArrayList<>();
        for (Contractarticle contractarticle : contractarticleList) {
            ContractarticleDTO dto = new ContractarticleDTO(contractarticle);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
