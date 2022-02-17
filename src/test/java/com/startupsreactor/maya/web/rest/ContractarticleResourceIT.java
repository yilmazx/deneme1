package com.startupsreactor.maya.web.rest;

import static com.startupsreactor.maya.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.startupsreactor.maya.IntegrationTest;
import com.startupsreactor.maya.domain.Contractarticle;
import com.startupsreactor.maya.repository.ContractarticleRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ContractarticleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractarticleResourceIT {

    private static final String DEFAULT_ARTICLENAME = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_DETAIL = "AAAAAAAAAA";
    private static final String UPDATED_DETAIL = "BBBBBBBBBB";

    private static final String DEFAULT_UID = "AAAAAAAAAA";
    private static final String UPDATED_UID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISDELETED = false;
    private static final Boolean UPDATED_ISDELETED = true;

    private static final Boolean DEFAULT_ISENABLED = false;
    private static final Boolean UPDATED_ISENABLED = true;

    private static final Instant DEFAULT_UPDATE_DATE = Instant.now();
    private static final Instant UPDATED_UPDATE_DATE = Instant.now();

    private static final Instant DEFAULT_CREATE_DATE = Instant.now();
    private static final Instant UPDATED_CREATE_DATE = Instant.now();

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contractarticles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractarticleRepository contractarticleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractarticleMockMvc;

    private Contractarticle contractarticle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contractarticle createEntity(EntityManager em) {
        Contractarticle contractarticle = new Contractarticle()
            .articlename(DEFAULT_ARTICLENAME)
            .detail(DEFAULT_DETAIL)
            .uid(DEFAULT_UID)
            .isenabled(DEFAULT_ISENABLED);

        contractarticle.setUpdateDate(DEFAULT_UPDATE_DATE);
        contractarticle.setCreateDate(DEFAULT_CREATE_DATE);
        contractarticle.setCreateBy(DEFAULT_CREATE_BY);
        contractarticle.setUpdateBy(DEFAULT_UPDATE_BY);
        return contractarticle;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Contractarticle createUpdatedEntity(EntityManager em) {
        Contractarticle contractarticle = new Contractarticle()
            .articlename(UPDATED_ARTICLENAME)
            .detail(UPDATED_DETAIL)
            .uid(UPDATED_UID)
            .isenabled(UPDATED_ISENABLED);

        contractarticle.setUpdateDate(UPDATED_UPDATE_DATE);
        contractarticle.setCreateDate(UPDATED_CREATE_DATE);
        contractarticle.setCreateBy(UPDATED_CREATE_BY);
        contractarticle.setUpdateBy(UPDATED_UPDATE_BY);
        return contractarticle;
    }

    @BeforeEach
    public void initTest() {
        contractarticle = createEntity(em);
    }

    @Test
    @Transactional
    void createContractarticle() throws Exception {
        int databaseSizeBeforeCreate = contractarticleRepository.findAll().size();
        // Create the Contractarticle
        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isCreated());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeCreate + 1);
        Contractarticle testContractarticle = contractarticleList.get(contractarticleList.size() - 1);
        assertThat(testContractarticle.getArticlename()).isEqualTo(DEFAULT_ARTICLENAME);
        assertThat(testContractarticle.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testContractarticle.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testContractarticle.getIsenabled()).isEqualTo(DEFAULT_ISENABLED);
        assertThat(testContractarticle.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testContractarticle.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testContractarticle.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testContractarticle.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
    }

    @Test
    @Transactional
    void createContractarticleWithExistingId() throws Exception {
        // Create the Contractarticle with an existing ID
        contractarticle.setId(1L);

        int databaseSizeBeforeCreate = contractarticleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkArticlenameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setArticlename(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDetailIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setDetail(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUidIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setUid(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsdeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setIsdeleted(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsenabledIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setIsenabled(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setUpdateDate(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setCreateDate(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractarticleRepository.findAll().size();
        // set the field null
        contractarticle.setCreateBy(null);

        // Create the Contractarticle, which fails.

        restContractarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContractarticles() throws Exception {
        // Initialize the database
        contractarticleRepository.saveAndFlush(contractarticle);

        // Get all the contractarticleList
        restContractarticleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractarticle.getId().intValue())))
            .andExpect(jsonPath("$.[*].articlename").value(hasItem(DEFAULT_ARTICLENAME)))
            .andExpect(jsonPath("$.[*].detail").value(hasItem(DEFAULT_DETAIL)))
            .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
            .andExpect(jsonPath("$.[*].isdeleted").value(hasItem(DEFAULT_ISDELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].isenabled").value(hasItem(DEFAULT_ISENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)));
    }

    @Test
    @Transactional
    void getContractarticle() throws Exception {
        // Initialize the database
        contractarticleRepository.saveAndFlush(contractarticle);

        // Get the contractarticle
        restContractarticleMockMvc
            .perform(get(ENTITY_API_URL_ID, contractarticle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractarticle.getId().intValue()))
            .andExpect(jsonPath("$.articlename").value(DEFAULT_ARTICLENAME))
            .andExpect(jsonPath("$.detail").value(DEFAULT_DETAIL))
            .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
            .andExpect(jsonPath("$.isdeleted").value(DEFAULT_ISDELETED.booleanValue()))
            .andExpect(jsonPath("$.isenabled").value(DEFAULT_ISENABLED.booleanValue()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY));
    }

    @Test
    @Transactional
    void getNonExistingContractarticle() throws Exception {
        // Get the contractarticle
        restContractarticleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractarticle() throws Exception {
        // Initialize the database
        contractarticleRepository.saveAndFlush(contractarticle);

        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();

        // Update the contractarticle
        Contractarticle updatedContractarticle = contractarticleRepository.findById(contractarticle.getId()).get();
        // Disconnect from session so that the updates on updatedContractarticle are not directly saved in db
        em.detach(updatedContractarticle);
        updatedContractarticle
            .articlename(UPDATED_ARTICLENAME)
            .detail(UPDATED_DETAIL)
            .uid(UPDATED_UID)
            .isdeleted(UPDATED_ISDELETED)
            .isenabled(UPDATED_ISENABLED)
            .updateDate(UPDATED_UPDATE_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY);

        restContractarticleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractarticle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedContractarticle))
            )
            .andExpect(status().isOk());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
        Contractarticle testContractarticle = contractarticleList.get(contractarticleList.size() - 1);
        assertThat(testContractarticle.getArticlename()).isEqualTo(UPDATED_ARTICLENAME);
        assertThat(testContractarticle.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testContractarticle.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testContractarticle.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
        assertThat(testContractarticle.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testContractarticle.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testContractarticle.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testContractarticle.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void putNonExistingContractarticle() throws Exception {
        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();
        contractarticle.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractarticleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractarticle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractarticle() throws Exception {
        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();
        contractarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractarticleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractarticle() throws Exception {
        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();
        contractarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractarticleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractarticleWithPatch() throws Exception {
        // Initialize the database
        contractarticleRepository.saveAndFlush(contractarticle);

        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();

        // Update the contractarticle using partial update
        Contractarticle partialUpdatedContractarticle = new Contractarticle();
        partialUpdatedContractarticle.setId(contractarticle.getId());

        partialUpdatedContractarticle
            .articlename(UPDATED_ARTICLENAME)
            .uid(UPDATED_UID)
            .updateDate(UPDATED_UPDATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY);

        restContractarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractarticle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractarticle))
            )
            .andExpect(status().isOk());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
        Contractarticle testContractarticle = contractarticleList.get(contractarticleList.size() - 1);
        assertThat(testContractarticle.getArticlename()).isEqualTo(UPDATED_ARTICLENAME);
        assertThat(testContractarticle.getDetail()).isEqualTo(DEFAULT_DETAIL);
        assertThat(testContractarticle.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testContractarticle.getIsenabled()).isEqualTo(DEFAULT_ISENABLED);
        assertThat(testContractarticle.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testContractarticle.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testContractarticle.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testContractarticle.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void fullUpdateContractarticleWithPatch() throws Exception {
        // Initialize the database
        contractarticleRepository.saveAndFlush(contractarticle);

        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();

        // Update the contractarticle using partial update
        Contractarticle partialUpdatedContractarticle = new Contractarticle();
        partialUpdatedContractarticle.setId(contractarticle.getId());

        partialUpdatedContractarticle
            .articlename(UPDATED_ARTICLENAME)
            .detail(UPDATED_DETAIL)
            .uid(UPDATED_UID)
            .isdeleted(UPDATED_ISDELETED)
            .isenabled(UPDATED_ISENABLED)
            .updateDate(UPDATED_UPDATE_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY);

        restContractarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractarticle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractarticle))
            )
            .andExpect(status().isOk());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
        Contractarticle testContractarticle = contractarticleList.get(contractarticleList.size() - 1);
        assertThat(testContractarticle.getArticlename()).isEqualTo(UPDATED_ARTICLENAME);
        assertThat(testContractarticle.getDetail()).isEqualTo(UPDATED_DETAIL);
        assertThat(testContractarticle.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testContractarticle.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
        assertThat(testContractarticle.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testContractarticle.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testContractarticle.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testContractarticle.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void patchNonExistingContractarticle() throws Exception {
        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();
        contractarticle.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractarticle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractarticle() throws Exception {
        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();
        contractarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractarticle() throws Exception {
        int databaseSizeBeforeUpdate = contractarticleRepository.findAll().size();
        contractarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractarticleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractarticle))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Contractarticle in the database
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractarticle() throws Exception {
        // Initialize the database
        contractarticleRepository.saveAndFlush(contractarticle);

        int databaseSizeBeforeDelete = contractarticleRepository.findAll().size();

        // Delete the contractarticle
        restContractarticleMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractarticle.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Contractarticle> contractarticleList = contractarticleRepository.findAll();
        assertThat(contractarticleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
