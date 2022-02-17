package com.startupsreactor.maya.web.rest;

import com.startupsreactor.UserEntityRepository;
import com.startupsreactor.maya.domain.BaseEntityGeneric;
import com.startupsreactor.maya.domain.User;
import com.startupsreactor.maya.service.BaseService;
import com.startupsreactor.maya.service.UserService;
import com.startupsreactor.maya.service.dto.BaseDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//@RestController
public abstract class BaseUserController<
    S extends BaseService<R, E, DTO, ID>,
    DTO extends BaseDTO,
    E extends BaseEntityGeneric,
    R extends UserEntityRepository<E, ID>,
    ID extends Serializable
>
    extends BaseController<S, DTO, E, R, ID> {

    protected final Logger log = LoggerFactory.getLogger(BaseUserController.class);

    private static final String ENTITY_NAME = "questionAnswer";

    @Value("${jhipster.clientApp.name}")
    protected String applicationName;

    protected User user;

    @Autowired
    protected S service;

    @Autowired
    protected R repository;

    @Autowired
    private UserService userService;

    public void userGet() {
        if (
            SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
        ) {
            Optional<User> userLocal = userService.getUserWithAuthoritiesByLogin(
                SecurityContextHolder.getContext().getAuthentication().getName()
            );
            user = userLocal.orElse(new User());
            log.debug("***************** :" + user.getEmail());
        }
    }

    @GetMapping("/me")
    public List<E> getAllCargosByCreateBy() {
        log.debug("REST request to get all Cargos" + user.getEmail());
        return repository.findAllByCreateBy(user.getLogin());
    }
}
