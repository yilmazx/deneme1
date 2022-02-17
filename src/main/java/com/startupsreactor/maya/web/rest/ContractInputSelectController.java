package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.ContractInput;
import com.startupsreactor.maya.repository.ContractInputRepository;
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
@RequestMapping("/api/contractinputselect")
@Transactional
public class ContractInputSelectController
    extends BaseUserController<ContractInputService, ContractInputDTO, ContractInput, ContractInputRepository, Long> {

    /**
     * {@code GET  /contactinputs/:contactId} : get the "contactId" contactinputs.
     *
     * @param contactId the contactId of the contactinputs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contactinputs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contactinputs/{contactId}")
    public List<ContractInputDTO> getContractInputsByContractId(@PathVariable Long contactId) {
        log.debug("REST request to get contactinputs by contactId : {}", contactId);
        return service.getContractInputByContactId(contactId);
    }
}
