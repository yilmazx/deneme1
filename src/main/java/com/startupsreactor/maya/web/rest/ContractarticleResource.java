package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.Contractarticle;
import com.startupsreactor.maya.repository.ContractarticleRepository;
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
 * REST controller for managing {@link com.startupsreactor.maya.domain.Contractarticle}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ContractarticleResource {

    private final Logger log = LoggerFactory.getLogger(ContractarticleResource.class);

    private static final String ENTITY_NAME = "contractarticle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContractarticleRepository contractarticleRepository;

    public ContractarticleResource(ContractarticleRepository contractarticleRepository) {
        this.contractarticleRepository = contractarticleRepository;
    }

    /**
     * {@code POST  /contractarticles} : Create a new contractarticle.
     *
     * @param contractarticle the contractarticle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractarticle, or with status {@code 400 (Bad Request)} if the contractarticle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contractarticles")
    public ResponseEntity<Contractarticle> createContractarticle(@Valid @RequestBody Contractarticle contractarticle)
        throws URISyntaxException {
        log.debug("REST request to save Contractarticle : {}", contractarticle);
        if (contractarticle.getId() != null) {
            throw new BadRequestAlertException("A new contractarticle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contractarticle result = contractarticleRepository.save(contractarticle);
        return ResponseEntity
            .created(new URI("/api/contractarticles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contractarticles/:id} : Updates an existing contractarticle.
     *
     * @param id the id of the contractarticle to save.
     * @param contractarticle the contractarticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractarticle,
     * or with status {@code 400 (Bad Request)} if the contractarticle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractarticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contractarticles/{id}")
    public ResponseEntity<Contractarticle> updateContractarticle(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Contractarticle contractarticle
    ) throws URISyntaxException {
        log.debug("REST request to update Contractarticle : {}, {}", id, contractarticle);
        if (contractarticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractarticle.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractarticleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Contractarticle result = contractarticleRepository.save(contractarticle);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractarticle.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /contractarticles/:id} : Partial updates given fields of an existing contractarticle, field will ignore if it is null
     *
     * @param id the id of the contractarticle to save.
     * @param contractarticle the contractarticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractarticle,
     * or with status {@code 400 (Bad Request)} if the contractarticle is not valid,
     * or with status {@code 404 (Not Found)} if the contractarticle is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractarticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/contractarticles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Contractarticle> partialUpdateContractarticle(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Contractarticle contractarticle
    ) throws URISyntaxException {
        log.debug("REST request to partial update Contractarticle partially : {}, {}", id, contractarticle);
        if (contractarticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractarticle.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractarticleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Contractarticle> result = contractarticleRepository
            .findById(contractarticle.getId())
            .map(existingContractarticle -> {
                if (contractarticle.getArticlename() != null) {
                    existingContractarticle.setArticlename(contractarticle.getArticlename());
                }
                if (contractarticle.getDetail() != null) {
                    existingContractarticle.setDetail(contractarticle.getDetail());
                }
                if (contractarticle.getUid() != null) {
                    existingContractarticle.setUid(contractarticle.getUid());
                }

                if (contractarticle.getIsenabled() != null) {
                    existingContractarticle.setIsenabled(contractarticle.getIsenabled());
                }
                if (contractarticle.getUpdateDate() != null) {
                    existingContractarticle.setUpdateDate(contractarticle.getUpdateDate());
                }
                if (contractarticle.getCreateDate() != null) {
                    existingContractarticle.setCreateDate(contractarticle.getCreateDate());
                }
                if (contractarticle.getCreateBy() != null) {
                    existingContractarticle.setCreateBy(contractarticle.getCreateBy());
                }
                if (contractarticle.getUpdateBy() != null) {
                    existingContractarticle.setUpdateBy(contractarticle.getUpdateBy());
                }

                return existingContractarticle;
            })
            .map(contractarticleRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contractarticle.getId().toString())
        );
    }

    /**
     * {@code GET  /contractarticles} : get all the contractarticles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractarticles in body.
     */
    @GetMapping("/contractarticles")
    public List<Contractarticle> getAllContractarticles() {
        log.debug("REST request to get all Contractarticles");
        return contractarticleRepository.findAll();
    }

    /**
     * {@code GET  /contractarticles/:id} : get the "id" contractarticle.
     *
     * @param id the id of the contractarticle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractarticle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contractarticles/{id}")
    public ResponseEntity<Contractarticle> getContractarticle(@PathVariable Long id) {
        log.debug("REST request to get Contractarticle : {}", id);
        Optional<Contractarticle> contractarticle = contractarticleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contractarticle);
    }

    /**
     * {@code DELETE  /contractarticles/:id} : delete the "id" contractarticle.
     *
     * @param id the id of the contractarticle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contractarticles/{id}")
    public ResponseEntity<Void> deleteContractarticle(@PathVariable Long id) {
        log.debug("REST request to delete Contractarticle : {}", id);
        contractarticleRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
