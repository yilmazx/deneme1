package com.startupsreactor.maya.repository;

import com.startupsreactor.UserEntityRepository;
import com.startupsreactor.maya.domain.Contractarticle;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Contractarticle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractarticleRepository extends UserEntityRepository<Contractarticle, Long> {
    List<Contractarticle> findByContractIdAndIsdeletedFalse(Long contractId);
}
