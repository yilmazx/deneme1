// package com.startupsreactor.maya.web.rest;

// import static com.startupsreactor.maya.web.rest.TestUtil.sameInstant;
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.hamcrest.Matchers.hasItem;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import com.startupsreactor.maya.IntegrationTest;
// import com.startupsreactor.maya.domain.Contract;
// import com.startupsreactor.maya.repository.ContractRepository;
// import java.time.Instant;
// import java.time.ZoneId;
// import java.time.ZoneOffset;
// import java.time.ZonedDateTime;
// import java.util.List;
// import java.util.Random;
// import java.util.concurrent.atomic.AtomicLong;
// import javax.persistence.EntityManager;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.transaction.annotation.Transactional;

// /**
//  * Integration tests for the {@link ContractResource} REST controller.
//  */
// @IntegrationTest
// @AutoConfigureMockMvc
// @WithMockUser
// class ContractResourceIT {

//     private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
//     private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

//     private static final String DEFAULT_CONTRACTPATH = "AAAAAAAAAA";
//     private static final String UPDATED_CONTRACTPATH = "BBBBBBBBBB";

//     private static final String DEFAULT_UID = "AAAAAAAAAA";
//     private static final String UPDATED_UID = "BBBBBBBBBB";

//     private static final String DEFAULT_USER = "AAAAAAAAAA";
//     private static final String UPDATED_USER = "BBBBBBBBBB";

//     private static final Boolean DEFAULT_ISDELETED = false;
//     private static final Boolean UPDATED_ISDELETED = true;

//     private static final Boolean DEFAULT_ISENABLED = false;
//     private static final Boolean UPDATED_ISENABLED = true;

//     private static final ZonedDateTime DEFAULT_UPDATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
//     private static final ZonedDateTime UPDATED_UPDATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

//     private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
//     private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

//     private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
//     private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

//     private static final String DEFAULT_UPDATE_BY = "AAAAAAAAAA";
//     private static final String UPDATED_UPDATE_BY = "BBBBBBBBBB";

//     private static final String ENTITY_API_URL = "/api/contracts";
//     private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

//     private static Random random = new Random();
//     private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

//     @Autowired
//     private ContractRepository contractRepository;

//     @Autowired
//     private EntityManager em;

//     @Autowired
//     private MockMvc restContractMockMvc;

//     private Contract contract;

//     /**
//      * Create an entity for this test.
//      *
//      * This is a static method, as tests for other entities might also need it,
//      * if they test an entity which requires the current entity.
//      */
//     public static Contract createEntity(EntityManager em) {
//         Contract contract = new Contract()
//             .contractname(DEFAULT_CONTRACTNAME)
//             .contractpath(DEFAULT_CONTRACTPATH)
//             .uid(DEFAULT_UID)
//             .user(DEFAULT_USER)
//             .isdeleted(DEFAULT_ISDELETED)
//             .isenabled(DEFAULT_ISENABLED)
//             .updateDate(DEFAULT_UPDATE_DATE)
//             .createDate(DEFAULT_CREATE_DATE)
//             .createBy(DEFAULT_CREATE_BY)
//             .updateBy(DEFAULT_UPDATE_BY);
//         return contract;
//     }

//     /**
//      * Create an updated entity for this test.
//      *
//      * This is a static method, as tests for other entities might also need it,
//      * if they test an entity which requires the current entity.
//      */
//     public static Contract createUpdatedEntity(EntityManager em) {
//         Contract contract = new Contract()
//             .contractname(UPDATED_CONTRACTNAME)
//             .contractpath(UPDATED_CONTRACTPATH)
//             .uid(UPDATED_UID)
//             .user(UPDATED_USER)
//             .isdeleted(UPDATED_ISDELETED)
//             .isenabled(UPDATED_ISENABLED)
//             .updateDate(UPDATED_UPDATE_DATE)
//             .createDate(UPDATED_CREATE_DATE)
//             .createBy(UPDATED_CREATE_BY)
//             .updateBy(UPDATED_UPDATE_BY);
//         return contract;
//     }

//     @BeforeEach
//     public void initTest() {
//         contract = createEntity(em);
//     }

//     @Test
//     @Transactional
//     void createContract() throws Exception {
//         int databaseSizeBeforeCreate = contractRepository.findAll().size();
//         // Create the Contract
//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isCreated());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeCreate + 1);
//         Contract testContract = contractList.get(contractList.size() - 1);
//         assertThat(testContract.getContractname()).isEqualTo(DEFAULT_CONTRACTNAME);
//         assertThat(testContract.getContractpath()).isEqualTo(DEFAULT_CONTRACTPATH);
//         assertThat(testContract.getUid()).isEqualTo(DEFAULT_UID);
//         assertThat(testContract.getUser()).isEqualTo(DEFAULT_USER);
//         assertThat(testContract.getIsdeleted()).isEqualTo(DEFAULT_ISDELETED);
//         assertThat(testContract.getIsenabled()).isEqualTo(DEFAULT_ISENABLED);
//         assertThat(testContract.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
//         assertThat(testContract.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
//         assertThat(testContract.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
//         assertThat(testContract.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
//     }

//     @Test
//     @Transactional
//     void createContractWithExistingId() throws Exception {
//         // Create the Contract with an existing ID
//         contract.setId(1L);

//         int databaseSizeBeforeCreate = contractRepository.findAll().size();

//         // An entity with an existing ID cannot be created, so this API call must fail
//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeCreate);
//     }

//     @Test
//     @Transactional
//     void checkContractnameIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setContractname(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkContractpathIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setContractpath(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkUidIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setUid(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkUserIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setUser(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkIsdeletedIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setIsdeleted(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkIsenabledIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setIsenabled(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkUpdateDateIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setUpdateDate(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkCreateDateIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setCreateDate(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void checkCreateByIsRequired() throws Exception {
//         int databaseSizeBeforeTest = contractRepository.findAll().size();
//         // set the field null
//         contract.setCreateBy(null);

//         // Create the Contract, which fails.

//         restContractMockMvc
//             .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isBadRequest());

//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeTest);
//     }

//     @Test
//     @Transactional
//     void getAllContracts() throws Exception {
//         // Initialize the database
//         contractRepository.saveAndFlush(contract);

//         // Get all the contractList
//         restContractMockMvc
//             .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//             .andExpect(status().isOk())
//             .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//             .andExpect(jsonPath("$.[*].id").value(hasItem(contract.getId().intValue())))
//             .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
//             .andExpect(jsonPath("$.[*].contractpath").value(hasItem(DEFAULT_CONTRACTPATH)))
//             .andExpect(jsonPath("$.[*].uid").value(hasItem(DEFAULT_UID)))
//             .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
//             .andExpect(jsonPath("$.[*].isdeleted").value(hasItem(DEFAULT_ISDELETED.booleanValue())))
//             .andExpect(jsonPath("$.[*].isenabled").value(hasItem(DEFAULT_ISENABLED.booleanValue())))
//             .andExpect(jsonPath("$.[*].updateDate").value(hasItem(sameInstant(DEFAULT_UPDATE_DATE))))
//             .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
//             .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY)))
//             .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY)));
//     }

//     @Test
//     @Transactional
//     void getContract() throws Exception {
//         // Initialize the database
//         contractRepository.saveAndFlush(contract);

//         // Get the contract
//         restContractMockMvc
//             .perform(get(ENTITY_API_URL_ID, contract.getId()))
//             .andExpect(status().isOk())
//             .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//             .andExpect(jsonPath("$.id").value(contract.getId().intValue()))
//             .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
//             .andExpect(jsonPath("$.contractpath").value(DEFAULT_CONTRACTPATH))
//             .andExpect(jsonPath("$.uid").value(DEFAULT_UID))
//             .andExpect(jsonPath("$.user").value(DEFAULT_USER))
//             .andExpect(jsonPath("$.isdeleted").value(DEFAULT_ISDELETED.booleanValue()))
//             .andExpect(jsonPath("$.isenabled").value(DEFAULT_ISENABLED.booleanValue()))
//             .andExpect(jsonPath("$.updateDate").value(sameInstant(DEFAULT_UPDATE_DATE)))
//             .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
//             .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY))
//             .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY));
//     }

//     @Test
//     @Transactional
//     void getNonExistingContract() throws Exception {
//         // Get the contract
//         restContractMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//     }

//     @Test
//     @Transactional
//     void putNewContract() throws Exception {
//         // Initialize the database
//         contractRepository.saveAndFlush(contract);

//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();

//         // Update the contract
//         Contract updatedContract = contractRepository.findById(contract.getId()).get();
//         // Disconnect from session so that the updates on updatedContract are not directly saved in db
//         em.detach(updatedContract);
//         updatedContract
//             .contractname(UPDATED_CONTRACTNAME)
//             .contractpath(UPDATED_CONTRACTPATH)
//             .uid(UPDATED_UID)
//             .user(UPDATED_USER)
//             .isdeleted(UPDATED_ISDELETED)
//             .isenabled(UPDATED_ISENABLED)
//             .updateDate(UPDATED_UPDATE_DATE)
//             .createDate(UPDATED_CREATE_DATE)
//             .createBy(UPDATED_CREATE_BY)
//             .updateBy(UPDATED_UPDATE_BY);

//         restContractMockMvc
//             .perform(
//                 put(ENTITY_API_URL_ID, updatedContract.getId())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(TestUtil.convertObjectToJsonBytes(updatedContract))
//             )
//             .andExpect(status().isOk());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//         Contract testContract = contractList.get(contractList.size() - 1);
//         assertThat(testContract.getContractname()).isEqualTo(UPDATED_CONTRACTNAME);
//         assertThat(testContract.getContractpath()).isEqualTo(UPDATED_CONTRACTPATH);
//         assertThat(testContract.getUid()).isEqualTo(UPDATED_UID);
//         assertThat(testContract.getUser()).isEqualTo(UPDATED_USER);
//         assertThat(testContract.getIsdeleted()).isEqualTo(UPDATED_ISDELETED);
//         assertThat(testContract.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
//         assertThat(testContract.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//         assertThat(testContract.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//         assertThat(testContract.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
//         assertThat(testContract.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
//     }

//     @Test
//     @Transactional
//     void putNonExistingContract() throws Exception {
//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();
//         contract.setId(count.incrementAndGet());

//         // If the entity doesn't have an ID, it will throw BadRequestAlertException
//         restContractMockMvc
//             .perform(
//                 put(ENTITY_API_URL_ID, contract.getId())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(TestUtil.convertObjectToJsonBytes(contract))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void putWithIdMismatchContract() throws Exception {
//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();
//         contract.setId(count.incrementAndGet());

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restContractMockMvc
//             .perform(
//                 put(ENTITY_API_URL_ID, count.incrementAndGet())
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(TestUtil.convertObjectToJsonBytes(contract))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void putWithMissingIdPathParamContract() throws Exception {
//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();
//         contract.setId(count.incrementAndGet());

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restContractMockMvc
//             .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isMethodNotAllowed());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void partialUpdateContractWithPatch() throws Exception {
//         // Initialize the database
//         contractRepository.saveAndFlush(contract);

//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();

//         // Update the contract using partial update
//         Contract partialUpdatedContract = new Contract();
//         partialUpdatedContract.setId(contract.getId());

//         partialUpdatedContract.contractpath(UPDATED_CONTRACTPATH).uid(UPDATED_UID).isenabled(UPDATED_ISENABLED).createBy(UPDATED_CREATE_BY);

//         restContractMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, partialUpdatedContract.getId())
//                     .contentType("application/merge-patch+json")
//                     .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContract))
//             )
//             .andExpect(status().isOk());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//         Contract testContract = contractList.get(contractList.size() - 1);
//         assertThat(testContract.getContractname()).isEqualTo(DEFAULT_CONTRACTNAME);
//         assertThat(testContract.getContractpath()).isEqualTo(UPDATED_CONTRACTPATH);
//         assertThat(testContract.getUid()).isEqualTo(UPDATED_UID);
//         assertThat(testContract.getUser()).isEqualTo(DEFAULT_USER);
//         assertThat(testContract.getIsdeleted()).isEqualTo(DEFAULT_ISDELETED);
//         assertThat(testContract.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
//         assertThat(testContract.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
//         assertThat(testContract.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
//         assertThat(testContract.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
//         assertThat(testContract.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
//     }

//     @Test
//     @Transactional
//     void fullUpdateContractWithPatch() throws Exception {
//         // Initialize the database
//         contractRepository.saveAndFlush(contract);

//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();

//         // Update the contract using partial update
//         Contract partialUpdatedContract = new Contract();
//         partialUpdatedContract.setId(contract.getId());

//         partialUpdatedContract
//             .contractname(UPDATED_CONTRACTNAME)
//             .contractpath(UPDATED_CONTRACTPATH)
//             .uid(UPDATED_UID)
//             .user(UPDATED_USER)
//             .isdeleted(UPDATED_ISDELETED)
//             .isenabled(UPDATED_ISENABLED)
//             .updateDate(UPDATED_UPDATE_DATE)
//             .createDate(UPDATED_CREATE_DATE)
//             .createBy(UPDATED_CREATE_BY)
//             .updateBy(UPDATED_UPDATE_BY);

//         restContractMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, partialUpdatedContract.getId())
//                     .contentType("application/merge-patch+json")
//                     .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContract))
//             )
//             .andExpect(status().isOk());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//         Contract testContract = contractList.get(contractList.size() - 1);
//         assertThat(testContract.getContractname()).isEqualTo(UPDATED_CONTRACTNAME);
//         assertThat(testContract.getContractpath()).isEqualTo(UPDATED_CONTRACTPATH);
//         assertThat(testContract.getUid()).isEqualTo(UPDATED_UID);
//         assertThat(testContract.getUser()).isEqualTo(UPDATED_USER);
//         assertThat(testContract.getIsdeleted()).isEqualTo(UPDATED_ISDELETED);
//         assertThat(testContract.getIsenabled()).isEqualTo(UPDATED_ISENABLED);
//         assertThat(testContract.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
//         assertThat(testContract.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
//         assertThat(testContract.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
//         assertThat(testContract.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
//     }

//     @Test
//     @Transactional
//     void patchNonExistingContract() throws Exception {
//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();
//         contract.setId(count.incrementAndGet());

//         // If the entity doesn't have an ID, it will throw BadRequestAlertException
//         restContractMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, contract.getId())
//                     .contentType("application/merge-patch+json")
//                     .content(TestUtil.convertObjectToJsonBytes(contract))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void patchWithIdMismatchContract() throws Exception {
//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();
//         contract.setId(count.incrementAndGet());

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restContractMockMvc
//             .perform(
//                 patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                     .contentType("application/merge-patch+json")
//                     .content(TestUtil.convertObjectToJsonBytes(contract))
//             )
//             .andExpect(status().isBadRequest());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void patchWithMissingIdPathParamContract() throws Exception {
//         int databaseSizeBeforeUpdate = contractRepository.findAll().size();
//         contract.setId(count.incrementAndGet());

//         // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//         restContractMockMvc
//             .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(contract)))
//             .andExpect(status().isMethodNotAllowed());

//         // Validate the Contract in the database
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeUpdate);
//     }

//     @Test
//     @Transactional
//     void deleteContract() throws Exception {
//         // Initialize the database
//         contractRepository.saveAndFlush(contract);

//         int databaseSizeBeforeDelete = contractRepository.findAll().size();

//         // Delete the contract
//         restContractMockMvc
//             .perform(delete(ENTITY_API_URL_ID, contract.getId()).accept(MediaType.APPLICATION_JSON))
//             .andExpect(status().isNoContent());

//         // Validate the database contains one less item
//         List<Contract> contractList = contractRepository.findAll();
//         assertThat(contractList).hasSize(databaseSizeBeforeDelete - 1);
//     }
// }
