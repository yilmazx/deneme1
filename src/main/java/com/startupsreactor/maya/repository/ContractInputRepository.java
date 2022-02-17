package com.startupsreactor.maya.repository;

import com.startupsreactor.UserEntityRepository;
import com.startupsreactor.maya.domain.ContractInput;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContractInput entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractInputRepository extends UserEntityRepository<ContractInput, Long> {
    List<ContractInput> findByContractIdAndIsdeletedFalse(Long contractId);
}
