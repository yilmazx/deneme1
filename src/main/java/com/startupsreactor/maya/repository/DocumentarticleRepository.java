package com.startupsreactor.maya.repository;

import com.startupsreactor.maya.domain.Documentarticle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Documentarticle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentarticleRepository extends JpaRepository<Documentarticle, Long> {}
