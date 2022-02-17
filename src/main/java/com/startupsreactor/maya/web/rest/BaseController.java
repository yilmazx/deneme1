package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.User;
import com.startupsreactor.maya.service.BaseService;
import com.startupsreactor.maya.service.UserService;
import com.startupsreactor.maya.service.dto.BaseDTO;
import com.startupsreactor.maya.web.rest.errors.BadRequestAlertException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

//@RestController
public abstract class BaseController<
    S extends BaseService<R, E, DTO, ID>, DTO extends BaseDTO, E, R extends JpaRepository<E, ID>, ID extends Serializable
> {

    protected final Logger log = LoggerFactory.getLogger(BaseController.class);

    private static final String ENTITY_NAME = "questionAnswer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    protected User user;

    @Autowired
    private S service;

    @Autowired
    private UserService userService;

    // public void userGet() {
    //     if (
    //         SecurityContextHolder.getContext().getAuthentication() != null &&
    //         SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
    //     ) {
    //         Optional<User> userLocal = userService.getUserWithAuthoritiesByLogin(
    //             SecurityContextHolder.getContext().getAuthentication().getName()
    //         );
    //         user = userLocal.orElse(new User());
    //         log.debug("***************** :" + user.getEmail());
    //     }
    // }

    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) throws URISyntaxException {
        log.debug("REST request to save QuestionAnswer : {}", dto);
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new questionAnswer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DTO result = service.save(dto);
        return ResponseEntity
            .created(new URI("/api/question/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-answers} : Updates an existing questionAnswer.
     *
     * @param questionAnswer the questionAnswer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionAnswer,
     * or with status {@code 400 (Bad Request)} if the questionAnswer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionAnswer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping
    public ResponseEntity<DTO> update(@Valid @RequestBody DTO dto) throws URISyntaxException {
        log.debug("REST request to update QuestionAnswer : {}", dto);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DTO result = service.save(dto);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /question-answers} : get all the questionAnswers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionAnswers in body.
     */
    @GetMapping
    public List<DTO> getAllQuestionAnswers() {
        log.debug("REST request to get all QuestionAnswers");
        return service.findAll();
    }

    /**
     * {@code GET  /question-answers/:id} : get the "id" questionAnswer.
     *
     * @param id the id of the questionAnswer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionAnswer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("{id}")
    public ResponseEntity<DTO> get(@PathVariable ID id) {
        log.debug("REST request to get QuestionAnswer : {}", id);
        Optional<DTO> questionAnswer = service.get(id);
        return ResponseUtil.wrapOrNotFound(questionAnswer);
    }

    /**
     * {@code DELETE  /question-answers/:id} : delete the "id" questionAnswer.
     *
     * @param id the id of the questionAnswer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        log.debug("REST request to delete QuestionAnswer : {}", id);
        service.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
