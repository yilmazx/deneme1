package com.startupsreactor.maya.service;

import com.startupsreactor.maya.domain.Lookup;
import com.startupsreactor.maya.repository.LookupRepository;
import com.startupsreactor.maya.service.dto.LookupDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LookupService extends BaseService<LookupRepository, Lookup, LookupDTO, Long> {

    public List<LookupDTO> getLookupListByParentId(Long parentId) {
        List<Lookup> entityList = repository.findAllByParentId(parentId);
        List<LookupDTO> list = new ArrayList<>();
        for (Lookup entity : entityList) {
            LookupDTO dto = new LookupDTO(entity);
            list.add(dto);
        }
        return list;
    }
}
