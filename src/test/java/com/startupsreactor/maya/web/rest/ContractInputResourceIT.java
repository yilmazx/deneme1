package com.startupsreactor.maya.web.rest;

import static com.startupsreactor.maya.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.startupsreactor.maya.IntegrationTest;
import com.startupsreactor.maya.domain.ContractInput;
import com.startupsreactor.maya.repository.ContractInputRepository;
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
 * Integration tests for the {@link ContractInputResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractInputResourceIT {

    private static final String DEFAULT_INPUTNAME = "AAAAAAAAAA";
    private static final String UPDATED_INPUTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_INPUTVALUE = "AAAAAAAAAA";
    private static final String UPDATED_INPUTVALUE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/contract-inputs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContractInputRepository contractInputRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractInputMockMvc;

    private ContractInput contractInput;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractInput createEntity(EntityManager em) {
        ContractInput contractInput = new ContractInput()
            .inputname(DEFAULT_INPUTNAME)
            .inputvalue(DEFAULT_INPUTVALUE)
            .isenabled(DEFAULT_ISENABLED);
        contractInput.setUid(DEFAULT_UID);
        contractInput.setUpdateDate(DEFAULT_UPDATE_DATE);
        contractInput.setCreateDate(DEFAULT_CREATE_DATE);
        contractInput.setCreateBy(DEFAULT_CREATE_BY);
        contractInput.setUpdateBy(DEFAULT_UPDATE_BY);
        return contractInput;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractInput createUpdatedEntity(EntityManager em) {
        ContractInput contractInput = new ContractInput()
            .inputname(UPDATED_INPUTNAME)
            .inputvalue(UPDATED_INPUTVALUE)
            .isenabled(UPDATED_ISENABLED);
        contractInput.setUid(UPDATED_UID);
        contractInput.setUpdateDate(UPDATED_UPDATE_DATE);
        contractInput.setCreateDate(UPDATED_CREATE_DATE);
        contractInput.setCreateBy(UPDATED_CREATE_BY);
        contractInput.setUpdateBy(UPDATED_UPDATE_BY);

        return contractInput;
    }

    @BeforeEach
    public void initTest() {
        contractInput = createEntity(em);
    }

    @Test
    @Transactional
    void createContractInput() throws Exception {
        int databaseSizeBeforeCreate = contractInputRepository.findAll().size();
        // Create the ContractInput
        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isCreated());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeCreate + 1);
        ContractInput testContractInput = contractInputList.get(contractInputList.size() - 1);
        assertThat(testContractInput.getInputname()).isEqualTo(DEFAULT_INPUTNAME);
        assertThat(testContractInput.getInputvalue()).isEqualTo(DEFAULT_INPUTVALUE);
        assertThat(testContractInput.getUid()).isEqualTo(DEFAULT_UID);
        assertThat(testContractInput.getIsenabled()).isEqualTo(DEFAULT_ISENABLED);
        assertThat(testContractInput.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testContractInput.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testContractInput.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testContractInput.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
    }

    @Test
    @Transactional
    void createContractInputWithExistingId() throws Exception {
        // Create the ContractInput with an existing ID
        contractInput.setId(1L);

        int databaseSizeBeforeCreate = contractInputRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkInputnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setInputname(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInputvalueIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setInputvalue(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUidIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setUid(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsdeletedIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setIsdeleted(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIsenabledIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setIsenabled(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUpdateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setUpdateDate(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setCreateDate(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = contractInputRepository.findAll().size();
        // set the field null
        contractInput.setCreateBy(null);

        // Create the ContractInput, which fails.

        restContractInputMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isBadRequest());

        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContractInputs() throws Exception {
        // Initialize the database
        contractInputRepository.saveAndFlush(contractInput);

        // Get all the contractInputList
        restContractInputMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractInput.getId().intValue())))
            .andExpect(jsonPath("$.[*].inputname").value(hasItem(DEFAULT_INPUTNAME)))
            .andExpect(jsonPath("$.[*].inputvalue").value(hasItem(DEFAULT_INPUTVALUE)))
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
    void getContractInput() throws Exception {
        // Initialize the database
        contractInputRepository.saveAndFlush(contractInput);

        // Get the contractInput
        restContractInputMockMvc
            .perform(get(ENTITY_API_URL_ID, contractInput.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractInput.getId().intValue()))
            .andExpect(jsonPath("$.inputname").value(DEFAULT_INPUTNAME))
            .andExpect(jsonPath("$.inputvalue").value(DEFAULT_INPUTVALUE))
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
    void getNonExistingContractInput() throws Exception {
        // Get the contractInput
        restContractInputMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContractInput() throws Exception {
        // Initialize the database
        contractInputRepository.saveAndFlush(contractInput);

        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();

        // Update the contractInput
        ContractInput updatedContractInput = contractInputRepository.findById(contractInput.getId()).get();
        // Disconnect from session so that the updates on updatedContractInput are not directly saved in db
        em.detach(updatedContractInput);
        updatedContractInput.inputname(UPDATED_INPUTNAME).inputvalue(UPDATED_INPUTVALUE).isenabled(UPDATED_ISENABLED);
        updatedContractInput.setUid(UPDATED_UID);
        updatedContractInput.setUpdateDate(UPDATED_UPDATE_DATE);
        updatedContractInput.setCreateDate(UPDATED_CREATE_DATE);
        updatedContractInput.setCreateBy(UPDATED_CREATE_BY);
        updatedContractInput.setUpdateBy(UPDATED_UPDATE_BY);

        restContractInputMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractInput.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedContractInput))
            )
            .andExpect(status().isOk());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
        ContractInput testContractInput = contractInputList.get(contractInputList.size() - 1);
        assertThat(testContractInput.getInputname()).isEqualTo(UPDATED_INPUTNAME);
        assertThat(testContractInput.getInputvalue()).isEqualTo(UPDATED_INPUTVALUE);
        assertThat(testContractInput.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testContractInput.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
        assertThat(testContractInput.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testContractInput.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testContractInput.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testContractInput.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void putNonExistingContractInput() throws Exception {
        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();
        contractInput.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractInputMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractInput.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractInput))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractInput() throws Exception {
        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();
        contractInput.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractInputMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contractInput))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractInput() throws Exception {
        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();
        contractInput.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractInputMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contractInput)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    }

    // @Test
    // @Transactional
    // void partialUpdateContractInputWithPatch() throws Exception {
    //     // Initialize the database
    //     contractInputRepository.saveAndFlush(contractInput);

    //     int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();

    //     // Update the contractInput using partial update
    //     ContractInput partialUpdatedContractInput = new ContractInput();
    //     partialUpdatedContractInput.setId(contractInput.getId());

    //     partialUpdatedContractInput.uid(UPDATED_UID).isenabled(UPDATED_ISENABLED).createBy(UPDATED_CREATE_BY).updateBy(UPDATED_UPDATE_BY);

    //     restContractInputMockMvc
    //         .perform(
    //             patch(ENTITY_API_URL_ID, partialUpdatedContractInput.getId())
    //                 .contentType("application/merge-patch+json")
    //                 .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractInput))
    //         )
    //         .andExpect(status().isOk());

    //     // Validate the ContractInput in the database
    //     List<ContractInput> contractInputList = contractInputRepository.findAll();
    //     assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    //     ContractInput testContractInput = contractInputList.get(contractInputList.size() - 1);
    //     assertThat(testContractInput.getInputname()).isEqualTo(DEFAULT_INPUTNAME);
    //     assertThat(testContractInput.getInputvalue()).isEqualTo(DEFAULT_INPUTVALUE);
    //     assertThat(testContractInput.getUid()).isEqualTo(UPDATED_UID);
    //     assertThat(testContractInput.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
    //     assertThat(testContractInput.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    //     assertThat(testContractInput.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
    //     assertThat(testContractInput.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
    //     assertThat(testContractInput.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    // }

    @Test
    @Transactional
    void fullUpdateContractInputWithPatch() throws Exception {
        // Initialize the database
        contractInputRepository.saveAndFlush(contractInput);

        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();

        // Update the contractInput using partial update
        ContractInput partialUpdatedContractInput = new ContractInput();
        partialUpdatedContractInput.setId(contractInput.getId());

        partialUpdatedContractInput.inputname(UPDATED_INPUTNAME).inputvalue(UPDATED_INPUTVALUE).isenabled(UPDATED_ISENABLED);
        partialUpdatedContractInput.setUid(UPDATED_UID);
        partialUpdatedContractInput.setUpdateDate(UPDATED_UPDATE_DATE);
        partialUpdatedContractInput.setCreateDate(UPDATED_CREATE_DATE);
        partialUpdatedContractInput.setCreateBy(UPDATED_CREATE_BY);
        partialUpdatedContractInput.setUpdateBy(UPDATED_UPDATE_BY);

        restContractInputMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractInput.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContractInput))
            )
            .andExpect(status().isOk());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
        ContractInput testContractInput = contractInputList.get(contractInputList.size() - 1);
        assertThat(testContractInput.getInputname()).isEqualTo(UPDATED_INPUTNAME);
        assertThat(testContractInput.getInputvalue()).isEqualTo(UPDATED_INPUTVALUE);
        assertThat(testContractInput.getUid()).isEqualTo(UPDATED_UID);
        assertThat(testContractInput.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
        assertThat(testContractInput.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testContractInput.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testContractInput.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testContractInput.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
    }

    @Test
    @Transactional
    void patchNonExistingContractInput() throws Exception {
        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();
        contractInput.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractInputMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractInput.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractInput))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractInput() throws Exception {
        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();
        contractInput.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractInputMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contractInput))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractInput() throws Exception {
        int databaseSizeBeforeUpdate = contractInputRepository.findAll().size();
        contractInput.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractInputMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(contractInput))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractInput in the database
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractInput() throws Exception {
        // Initialize the database
        contractInputRepository.saveAndFlush(contractInput);

        int databaseSizeBeforeDelete = contractInputRepository.findAll().size();

        // Delete the contractInput
        restContractInputMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractInput.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContractInput> contractInputList = contractInputRepository.findAll();
        assertThat(contractInputList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
