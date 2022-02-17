package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.Contractarticle;
import com.startupsreactor.maya.repository.ContractarticleRepository;
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
@RequestMapping("/api/contractarticleselect")
@Transactional
public class ContractarticleSelectController
    extends BaseUserController<ContractarticleService, ContractarticleDTO, Contractarticle, ContractarticleRepository, Long> {

    /**
     * {@code GET  /contactarticles/:contactId} : get the "contactId" contactarticles.
     *
     * @param contactId the contactId of the contactarticles to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactarticles, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contactarticles/{contactId}")
    public List<ContractarticleDTO> getContractarticlesByContractId(@PathVariable Long contactId) {
        log.debug("REST request to get contactarticles by contactId : {}", contactId);
        return service.getContractarticleByContactId(contactId);
    }
}
