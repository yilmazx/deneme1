package com.startupsreactor.maya.repository;

import com.startupsreactor.maya.domain.Lookup;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Lookup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LookupRepository extends JpaRepository<Lookup, Long> {
    @Query(value = "select l.* from Lookup l where l.parent_id = :parentid", nativeQuery = true)
    List<Lookup> findAllByParentId(@Param("parentid") Long parent);
}
