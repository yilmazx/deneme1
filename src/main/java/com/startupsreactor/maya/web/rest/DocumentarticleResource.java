package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.Documentarticle;
import com.startupsreactor.maya.repository.DocumentarticleRepository;
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
 * REST controller for managing {@link com.startupsreactor.maya.domain.Documentarticle}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DocumentarticleResource {

    private final Logger log = LoggerFactory.getLogger(DocumentarticleResource.class);

    private static final String ENTITY_NAME = "documentarticle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentarticleRepository documentarticleRepository;

    public DocumentarticleResource(DocumentarticleRepository documentarticleRepository) {
        this.documentarticleRepository = documentarticleRepository;
    }

    /**
     * {@code POST  /documentarticles} : Create a new documentarticle.
     *
     * @param documentarticle the documentarticle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentarticle, or with status {@code 400 (Bad Request)} if the documentarticle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/documentarticles")
    public ResponseEntity<Documentarticle> createDocumentarticle(@Valid @RequestBody Documentarticle documentarticle)
        throws URISyntaxException {
        log.debug("REST request to save Documentarticle : {}", documentarticle);
        if (documentarticle.getId() != null) {
            throw new BadRequestAlertException("A new documentarticle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Documentarticle result = documentarticleRepository.save(documentarticle);
        return ResponseEntity
            .created(new URI("/api/documentarticles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /documentarticles/:id} : Updates an existing documentarticle.
     *
     * @param id the id of the documentarticle to save.
     * @param documentarticle the documentarticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentarticle,
     * or with status {@code 400 (Bad Request)} if the documentarticle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentarticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/documentarticles/{id}")
    public ResponseEntity<Documentarticle> updateDocumentarticle(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Documentarticle documentarticle
    ) throws URISyntaxException {
        log.debug("REST request to update Documentarticle : {}, {}", id, documentarticle);
        if (documentarticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentarticle.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentarticleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Documentarticle result = documentarticleRepository.save(documentarticle);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentarticle.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /documentarticles/:id} : Partial updates given fields of an existing documentarticle, field will ignore if it is null
     *
     * @param id the id of the documentarticle to save.
     * @param documentarticle the documentarticle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentarticle,
     * or with status {@code 400 (Bad Request)} if the documentarticle is not valid,
     * or with status {@code 404 (Not Found)} if the documentarticle is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentarticle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/documentarticles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Documentarticle> partialUpdateDocumentarticle(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Documentarticle documentarticle
    ) throws URISyntaxException {
        log.debug("REST request to partial update Documentarticle partially : {}, {}", id, documentarticle);
        if (documentarticle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentarticle.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentarticleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Documentarticle> result = documentarticleRepository
            .findById(documentarticle.getId())
            .map(existingDocumentarticle -> {
                if (documentarticle.getDocumentId() != null) {
                    existingDocumentarticle.setDocumentId(documentarticle.getDocumentId());
                }
                if (documentarticle.getContractarticleId() != null) {
                    existingDocumentarticle.setContractarticleId(documentarticle.getContractarticleId());
                }
                if (documentarticle.getIs_deleted() != null) {
                    existingDocumentarticle.setIs_deleted(documentarticle.getIs_deleted());
                }
                if (documentarticle.getUpdateDate() != null) {
                    existingDocumentarticle.setUpdateDate(documentarticle.getUpdateDate());
                }
                if (documentarticle.getCreateDate() != null) {
                    existingDocumentarticle.setCreateDate(documentarticle.getCreateDate());
                }
                if (documentarticle.getCreateBy() != null) {
                    existingDocumentarticle.setCreateBy(documentarticle.getCreateBy());
                }
                if (documentarticle.getUpdateBy() != null) {
                    existingDocumentarticle.setUpdateBy(documentarticle.getUpdateBy());
                }

                return existingDocumentarticle;
            })
            .map(documentarticleRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentarticle.getId().toString())
        );
    }

    /**
     * {@code GET  /documentarticles} : get all the documentarticles.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentarticles in body.
     */
    @GetMapping("/documentarticles")
    public List<Documentarticle> getAllDocumentarticles() {
        log.debug("REST request to get all Documentarticles");
        return documentarticleRepository.findAll();
    }

    /**
     * {@code GET  /documentarticles/:id} : get the "id" documentarticle.
     *
     * @param id the id of the documentarticle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentarticle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/documentarticles/{id}")
    public ResponseEntity<Documentarticle> getDocumentarticle(@PathVariable Long id) {
        log.debug("REST request to get Documentarticle : {}", id);
        Optional<Documentarticle> documentarticle = documentarticleRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(documentarticle);
    }

    /**
     * {@code DELETE  /documentarticles/:id} : delete the "id" documentarticle.
     *
     * @param id the id of the documentarticle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/documentarticles/{id}")
    public ResponseEntity<Void> deleteDocumentarticle(@PathVariable Long id) {
        log.debug("REST request to delete Documentarticle : {}", id);
        documentarticleRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
