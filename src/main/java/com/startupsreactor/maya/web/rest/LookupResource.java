package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.Lookup;
import com.startupsreactor.maya.repository.LookupRepository;
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
 * REST controller for managing {@link com.startupsreactor.maya.domain.Lookup}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LookupResource {

    private final Logger log = LoggerFactory.getLogger(LookupResource.class);

    private static final String ENTITY_NAME = "lookup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LookupRepository lookupRepository;

    public LookupResource(LookupRepository lookupRepository) {
        this.lookupRepository = lookupRepository;
    }

    /**
     * {@code POST  /lookups} : Create a new lookup.
     *
     * @param lookup the lookup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lookup, or with status {@code 400 (Bad Request)} if the lookup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lookups")
    public ResponseEntity<Lookup> createLookup(@Valid @RequestBody Lookup lookup) throws URISyntaxException {
        log.debug("REST request to save Lookup : {}", lookup);
        if (lookup.getId() != null) {
            throw new BadRequestAlertException("A new lookup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Lookup result = lookupRepository.save(lookup);
        return ResponseEntity
            .created(new URI("/api/lookups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lookups/:id} : Updates an existing lookup.
     *
     * @param id the id of the lookup to save.
     * @param lookup the lookup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lookup,
     * or with status {@code 400 (Bad Request)} if the lookup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lookup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lookups/{id}")
    public ResponseEntity<Lookup> updateLookup(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Lookup lookup
    ) throws URISyntaxException {
        log.debug("REST request to update Lookup : {}, {}", id, lookup);
        if (lookup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lookup.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lookupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Lookup result = lookupRepository.save(lookup);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lookup.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /lookups/:id} : Partial updates given fields of an existing lookup, field will ignore if it is null
     *
     * @param id the id of the lookup to save.
     * @param lookup the lookup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lookup,
     * or with status {@code 400 (Bad Request)} if the lookup is not valid,
     * or with status {@code 404 (Not Found)} if the lookup is not found,
     * or with status {@code 500 (Internal Server Error)} if the lookup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/lookups/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Lookup> partialUpdateLookup(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Lookup lookup
    ) throws URISyntaxException {
        log.debug("REST request to partial update Lookup partially : {}, {}", id, lookup);
        if (lookup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lookup.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lookupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Lookup> result = lookupRepository
            .findById(lookup.getId())
            .map(existingLookup -> {
                if (lookup.getName() != null) {
                    existingLookup.setName(lookup.getName());
                }
                if (lookup.getLang() != null) {
                    existingLookup.setLang(lookup.getLang());
                }
                if (lookup.getUid() != null) {
                    existingLookup.setUid(lookup.getUid());
                }
                if (lookup.getValue() != null) {
                    existingLookup.setValue(lookup.getValue());
                }
                if (lookup.getDescription() != null) {
                    existingLookup.setDescription(lookup.getDescription());
                }

                return existingLookup;
            })
            .map(lookupRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lookup.getId().toString())
        );
    }

    /**
     * {@code GET  /lookups} : get all the lookups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lookups in body.
     */
    @GetMapping("/lookups")
    public List<Lookup> getAllLookups() {
        log.debug("REST request to get all Lookups");
        return lookupRepository.findAll();
    }

    /**
     * {@code GET  /lookups/:id} : get the "id" lookup.
     *
     * @param id the id of the lookup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lookup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lookups/{id}")
    public ResponseEntity<Lookup> getLookup(@PathVariable Long id) {
        log.debug("REST request to get Lookup : {}", id);
        Optional<Lookup> lookup = lookupRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lookup);
    }

    /**
     * {@code DELETE  /lookups/:id} : delete the "id" lookup.
     *
     * @param id the id of the lookup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lookups/{id}")
    public ResponseEntity<Void> deleteLookup(@PathVariable Long id) {
        log.debug("REST request to delete Lookup : {}", id);
        lookupRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
