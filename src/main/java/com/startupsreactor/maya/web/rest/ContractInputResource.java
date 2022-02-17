package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.ContractInput;
import com.startupsreactor.maya.repository.ContractInputRepository;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.startupsreactor.maya.domain.ContractInput}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ContractInputResource {

    private final Logger log = LoggerFactory.getLogger(ContractInputResource.class);

    private static final String ENTITY_NAME = "contractInput";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractInputRepository contractInputRepository;

    public ContractInputResource(ContractInputRepository contractInputRepository) {
        this.contractInputRepository = contractInputRepository;
    }

    /**
     * {@code POST  /contract-inputs} : Create a new contractInput.
     *
     * @param contractInput the contractInput to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractInput, or with status {@code 400 (Bad Request)} if the contractInput has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contract-inputs")
    public ResponseEntity<ContractInput> createContractInput(@Valid @RequestBody ContractInput contractInput) throws URISyntaxException {
        log.debug("REST request to save ContractInput : {}", contractInput);
        if (contractInput.getId() != null) {
            throw new BadRequestAlertException("A new contractInput cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContractInput result = contractInputRepository.save(contractInput);
        return ResponseEntity
            .created(new URI("/api/contract-inputs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contract-inputs/:id} : Updates an existing contractInput.
     *
     * @param id the id of the contractInput to save.
     * @param contractInput the contractInput to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractInput,
     * or with status {@code 400 (Bad Request)} if the contractInput is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractInput couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contract-inputs/{id}")
    public ResponseEntity<ContractInput> updateContractInput(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractInput contractInput
    ) throws URISyntaxException {
        log.debug("REST request to update ContractInput : {}, {}", id, contractInput);
        if (contractInput.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractInput.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractInputRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContractInput result = contractInputRepository.save(contractInput);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractInput.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contract-inputs/:id} : Partial updates given fields of an existing contractInput, field will ignore if it is null
     *
     * @param id the id of the contractInput to save.
     * @param contractInput the contractInput to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractInput,
     * or with status {@code 400 (Bad Request)} if the contractInput is not valid,
     * or with status {@code 404 (Not Found)} if the contractInput is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractInput couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contract-inputs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractInput> partialUpdateContractInput(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractInput contractInput
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContractInput partially : {}, {}", id, contractInput);
        if (contractInput.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractInput.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractInputRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractInput> result = contractInputRepository
            .findById(contractInput.getId())
            .map(existingContractInput -> {
                if (contractInput.getInputname() != null) {
                    existingContractInput.setInputname(contractInput.getInputname());
                }
                if (contractInput.getInputvalue() != null) {
                    existingContractInput.setInputvalue(contractInput.getInputvalue());
                }
                if (contractInput.getUid() != null) {
                    existingContractInput.setUid(contractInput.getUid());
                }

                if (contractInput.getIsenabled() != null) {
                    existingContractInput.setIsenabled(contractInput.getIsenabled());
                }
                if (contractInput.getUpdateDate() != null) {
                    existingContractInput.setUpdateDate(contractInput.getUpdateDate());
                }
                if (contractInput.getCreateDate() != null) {
                    existingContractInput.setCreateDate(contractInput.getCreateDate());
                }
                if (contractInput.getCreateBy() != null) {
                    existingContractInput.setCreateBy(contractInput.getCreateBy());
                }
                if (contractInput.getUpdateBy() != null) {
                    existingContractInput.setUpdateBy(contractInput.getUpdateBy());
                }

                return existingContractInput;
            })
            .map(contractInputRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractInput.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-inputs} : get all the contractInputs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractInputs in body.
     */
    @GetMapping("/contract-inputs")
    public List<ContractInput> getAllContractInputs() {
        log.debug("REST request to get all ContractInputs");
        return contractInputRepository.findAll();
    }

    /**
     * {@code GET  /contract-inputs/:id} : get the "id" contractInput.
     *
     * @param id the id of the contractInput to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractInput, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract-inputs/{id}")
    public ResponseEntity<ContractInput> getContractInput(@PathVariable Long id) {
        log.debug("REST request to get ContractInput : {}", id);
        Optional<ContractInput> contractInput = contractInputRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contractInput);
    }

    /**
     * {@code DELETE  /contract-inputs/:id} : delete the "id" contractInput.
     *
     * @param id the id of the contractInput to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contract-inputs/{id}")
    public ResponseEntity<Void> deleteContractInput(@PathVariable Long id) {
        log.debug("REST request to delete ContractInput : {}", id);
        contractInputRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
