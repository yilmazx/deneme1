package com.startupsreactor;

import com.startupsreactor.maya.domain.BaseEntityGeneric;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cargo entity.
 */
@SuppressWarnings("unused")
public interface UserEntityRepository<E extends BaseEntityGeneric, ID extends Serializable> extends JpaRepository<E, ID> {
    List<E> findAllByCreateBy(String CreateBy);
}
