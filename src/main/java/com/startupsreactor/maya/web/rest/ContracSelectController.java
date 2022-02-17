package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.Contract;
import com.startupsreactor.maya.repository.ContractRepository;
import com.startupsreactor.maya.service.ContractInputService;
import com.startupsreactor.maya.service.ContractService;
import com.startupsreactor.maya.service.ContractarticleService;
import com.startupsreactor.maya.service.LookupService;
import com.startupsreactor.maya.service.dto.ContractDTO;
import com.startupsreactor.maya.service.dto.ContractInputDTO;
import com.startupsreactor.maya.service.dto.ContractarticleDTO;
import com.startupsreactor.maya.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/contractselect")
@Transactional
public class ContracSelectController extends BaseUserController<ContractService, ContractDTO, Contract, ContractRepository, Long> {

    /**
     * {@code GET  /contacts/:name} : get the "name" contacts.
     *
     * @param name the name of the contacts to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contacts, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contacts/{name}")
    public List<ContractDTO> getContractsByName(@PathVariable String name) {
        log.debug("REST request to get contacts by name : {}", name);
        return service.getContractByContactnameContaining(name);
    }
}
