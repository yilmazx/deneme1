package com.startupsreactor.maya.web.rest;

import static com.startupsreactor.maya.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.startupsreactor.maya.IntegrationTest;
import com.startupsreactor.maya.domain.Documentarticle;
import com.startupsreactor.maya.repository.DocumentarticleRepository;
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
 * Integration tests for the {@link DocumentarticleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DocumentarticleResourceIT {

    private static final Long DEFAULT_DOCUMENT_ID = 1L;
    private static final Long UPDATED_DOCUMENT_ID = 2L;

    private static final Long DEFAULT_CONTRACTARTICLE_ID = 1L;
    private static final Long UPDATED_CONTRACTARTICLE_ID = 2L;

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    private static final ZonedDateTime DEFAULT_UPDATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATE_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/documentarticles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DocumentarticleRepository documentarticleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDocumentarticleMockMvc;

    private Documentarticle documentarticle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documentarticle createEntity(EntityManager em) {
        Documentarticle documentarticle = new Documentarticle()
            .documentId(DEFAULT_DOCUMENT_ID)
            .contractarticleId(DEFAULT_CONTRACTARTICLE_ID)
            .is_deleted(DEFAULT_IS_DELETED)
            .updateDate(DEFAULT_UPDATE_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createBy(DEFAULT_CREATE_BY)
            .updateBy(DEFAULT_UPDATE_BY);
        return documentarticle;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Documentarticle createUpdatedEntity(EntityManager em) {
        Documentarticle documentarticle = new Documentarticle()
            .documentId(UPDATED_DOCUMENT_ID)
            .contractarticleId(UPDATED_CONTRACTARTICLE_ID)
            .is_deleted(UPDATED_IS_DELETED)
            .updateDate(UPDATED_UPDATE_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY);
        return documentarticle;
    }

    @BeforeEach
    public void initTest() {
        documentarticle = createEntity(em);
    }

    @Test
    @Transactional
    void createDocumentarticle() throws Exception {
        int databaseSizeBeforeCreate = documentarticleRepository.findAll().size();
        // Create the Documentarticle
        restDocumentarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isCreated());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeCreate + 1);
        Documentarticle testDocumentarticle = documentarticleList.get(documentarticleList.size() - 1);
        assertThat(testDocumentarticle.getDocumentId()).isEqualTo(DEFAULT_DOCUMENT_ID);
        assertThat(testDocumentarticle.getContractarticleId()).isEqualTo(DEFAULT_CONTRACTARTICLE_ID);
        assertThat(testDocumentarticle.getIs_deleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testDocumentarticle.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testDocumentarticle.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testDocumentarticle.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testDocumentarticle.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
    }

    @Test
    @Transactional
    void createDocumentarticleWithExistingId() throws Exception {
        // Create the Documentarticle with an existing ID
        documentarticle.setId(1L);

        int databaseSizeBeforeCreate = documentarticleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkIs_deletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentarticleRepository.findAll().size();
        // set the field null
        documentarticle.setIs_deleted(null);

        // Create the Documentarticle, which fails.

        restDocumentarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentarticleRepository.findAll().size();
        // set the field null
        documentarticle.setUpdateDate(null);

        // Create the Documentarticle, which fails.

        restDocumentarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentarticleRepository.findAll().size();
        // set the field null
        documentarticle.setCreateDate(null);

        // Create the Documentarticle, which fails.

        restDocumentarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentarticleRepository.findAll().size();
        // set the field null
        documentarticle.setCreateBy(null);

        // Create the Documentarticle, which fails.

        restDocumentarticleMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllDocumentarticles() throws Exception {
        // Initialize the database
        documentarticleRepository.saveAndFlush(documentarticle);

        // Get all the documentarticleList
        restDocumentarticleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentarticle.getId().intValue())))
            .andExpect(jsonPath("$.[*].documentId").value(hasItem(DEFAULT_DOCUMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].contractarticleId").value(hasItem(DEFAULT_CONTRACTARTICLE_ID.intValue())))
            .andExpect(jsonPath("$.[*].is_deleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(sameInstant(DEFAULT_UPDATE_DATE))))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)));
    }

    @Test
    @Transactional
    void getDocumentarticle() throws Exception {
        // Initialize the database
        documentarticleRepository.saveAndFlush(documentarticle);

        // Get the documentarticle
        restDocumentarticleMockMvc
            .perform(get(ENTITY_API_URL_ID, documentarticle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(documentarticle.getId().intValue()))
            .andExpect(jsonPath("$.documentId").value(DEFAULT_DOCUMENT_ID.intValue()))
            .andExpect(jsonPath("$.contractarticleId").value(DEFAULT_CONTRACTARTICLE_ID.intValue()))
            .andExpect(jsonPath("$.is_deleted").value(DEFAULT_IS_DELETED.booleanValue()))
            .andExpect(jsonPath("$.updateDate").value(sameInstant(DEFAULT_UPDATE_DATE)))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY));
    }

    @Test
    @Transactional
    void getNonExistingDocumentarticle() throws Exception {
        // Get the documentarticle
        restDocumentarticleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewDocumentarticle() throws Exception {
        // Initialize the database
        documentarticleRepository.saveAndFlush(documentarticle);

        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();

        // Update the documentarticle
        Documentarticle updatedDocumentarticle = documentarticleRepository.findById(documentarticle.getId()).get();
        // Disconnect from session so that the updates on updatedDocumentarticle are not directly saved in db
        em.detach(updatedDocumentarticle);
        updatedDocumentarticle
            .documentId(UPDATED_DOCUMENT_ID)
            .contractarticleId(UPDATED_CONTRACTARTICLE_ID)
            .is_deleted(UPDATED_IS_DELETED)
            .updateDate(UPDATED_UPDATE_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY);

        restDocumentarticleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDocumentarticle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDocumentarticle))
            )
            .andExpect(status().isOk());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
        Documentarticle testDocumentarticle = documentarticleList.get(documentarticleList.size() - 1);
        assertThat(testDocumentarticle.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testDocumentarticle.getContractarticleId()).isEqualTo(UPDATED_CONTRACTARTICLE_ID);
        assertThat(testDocumentarticle.getIs_deleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testDocumentarticle.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testDocumentarticle.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testDocumentarticle.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testDocumentarticle.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void putNonExistingDocumentarticle() throws Exception {
        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();
        documentarticle.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentarticleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, documentarticle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDocumentarticle() throws Exception {
        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();
        documentarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentarticleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDocumentarticle() throws Exception {
        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();
        documentarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentarticleMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDocumentarticleWithPatch() throws Exception {
        // Initialize the database
        documentarticleRepository.saveAndFlush(documentarticle);

        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();

        // Update the documentarticle using partial update
        Documentarticle partialUpdatedDocumentarticle = new Documentarticle();
        partialUpdatedDocumentarticle.setId(documentarticle.getId());

        partialUpdatedDocumentarticle.documentId(UPDATED_DOCUMENT_ID).createBy(UPDATED_CREATE_BY);

        restDocumentarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentarticle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentarticle))
            )
            .andExpect(status().isOk());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
        Documentarticle testDocumentarticle = documentarticleList.get(documentarticleList.size() - 1);
        assertThat(testDocumentarticle.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testDocumentarticle.getContractarticleId()).isEqualTo(DEFAULT_CONTRACTARTICLE_ID);
        assertThat(testDocumentarticle.getIs_deleted()).isEqualTo(DEFAULT_IS_DELETED);
        assertThat(testDocumentarticle.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testDocumentarticle.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testDocumentarticle.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testDocumentarticle.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
    }

    @Test
    @Transactional
    void fullUpdateDocumentarticleWithPatch() throws Exception {
        // Initialize the database
        documentarticleRepository.saveAndFlush(documentarticle);

        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();

        // Update the documentarticle using partial update
        Documentarticle partialUpdatedDocumentarticle = new Documentarticle();
        partialUpdatedDocumentarticle.setId(documentarticle.getId());

        partialUpdatedDocumentarticle
            .documentId(UPDATED_DOCUMENT_ID)
            .contractarticleId(UPDATED_CONTRACTARTICLE_ID)
            .is_deleted(UPDATED_IS_DELETED)
            .updateDate(UPDATED_UPDATE_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY);

        restDocumentarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDocumentarticle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDocumentarticle))
            )
            .andExpect(status().isOk());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
        Documentarticle testDocumentarticle = documentarticleList.get(documentarticleList.size() - 1);
        assertThat(testDocumentarticle.getDocumentId()).isEqualTo(UPDATED_DOCUMENT_ID);
        assertThat(testDocumentarticle.getContractarticleId()).isEqualTo(UPDATED_CONTRACTARTICLE_ID);
        assertThat(testDocumentarticle.getIs_deleted()).isEqualTo(UPDATED_IS_DELETED);
        assertThat(testDocumentarticle.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testDocumentarticle.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testDocumentarticle.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testDocumentarticle.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void patchNonExistingDocumentarticle() throws Exception {
        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();
        documentarticle.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, documentarticle.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDocumentarticle() throws Exception {
        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();
        documentarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentarticleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDocumentarticle() throws Exception {
        int databaseSizeBeforeUpdate = documentarticleRepository.findAll().size();
        documentarticle.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDocumentarticleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(documentarticle))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Documentarticle in the database
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDocumentarticle() throws Exception {
        // Initialize the database
        documentarticleRepository.saveAndFlush(documentarticle);

        int databaseSizeBeforeDelete = documentarticleRepository.findAll().size();

        // Delete the documentarticle
        restDocumentarticleMockMvc
            .perform(delete(ENTITY_API_URL_ID, documentarticle.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Documentarticle> documentarticleList = documentarticleRepository.findAll();
        assertThat(documentarticleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
