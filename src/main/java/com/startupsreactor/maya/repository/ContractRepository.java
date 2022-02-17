package com.startupsreactor.maya.repository;

import com.startupsreactor.UserEntityRepository;
import com.startupsreactor.maya.domain.Contract;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Contract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractRepository extends UserEntityRepository<Contract, Long> {
    List<Contract> findByContractnameContainingAndIsdeletedFalse(String contactname);
}
