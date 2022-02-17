package com.startupsreactor.maya.web.rest;

import com.startupsreactor.maya.domain.Lookup;
import com.startupsreactor.maya.repository.LookupRepository;
import com.startupsreactor.maya.service.ContractInputService;
import com.startupsreactor.maya.service.ContractService;
import com.startupsreactor.maya.service.ContractarticleService;
import com.startupsreactor.maya.service.LookupService;
import com.startupsreactor.maya.service.dto.ContractDTO;
import com.startupsreactor.maya.service.dto.ContractInputDTO;
import com.startupsreactor.maya.service.dto.ContractarticleDTO;
import com.startupsreactor.maya.service.dto.LookupDTO;
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
@RequestMapping("/api/contractlookupselect")
@Transactional
public class ContractLookupSelectController {

    protected final Logger log = LoggerFactory.getLogger(ContractLookupSelectController.class);

    @Autowired
    LookupService service;

    /**
     * {@code GET  /countries} : get all the countries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of countries in body.
     */
    @GetMapping("/countries")
    public List<LookupDTO> getAllCountries() {
        log.debug("REST request to get all counties");
        return service.getLookupListByParentId(13L);
    }

    /**
     * {@code GET  /cities/:id} : get the "countryId" cities.
     *
     * @param countryId the countryId of the cities to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cities, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cities/{countryId}")
    public List<LookupDTO> getCitiesByCountryId(@PathVariable Long countryId) {
        log.debug("REST request to get citis by countryId : {}", countryId);
        return service.getLookupListByParentId(countryId);
    }
}
