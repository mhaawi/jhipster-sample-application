package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ApiDefination;
import io.github.jhipster.application.repository.ApiDefinationRepository;
import io.github.jhipster.application.service.ApiDefinationService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.ApiType;
/**
 * Test class for the ApiDefinationResource REST controller.
 *
 * @see ApiDefinationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ApiDefinationResourceIntTest {

    private static final String DEFAULT_API_KEY = "AAAAAAAAAA";
    private static final String UPDATED_API_KEY = "BBBBBBBBBB";

    private static final ApiType DEFAULT_API_TYPE = ApiType.GET;
    private static final ApiType UPDATED_API_TYPE = ApiType.POST;

    private static final String DEFAULT_API_URL = "AAAAAAAAAA";
    private static final String UPDATED_API_URL = "BBBBBBBBBB";

    @Autowired
    private ApiDefinationRepository apiDefinationRepository;

    @Autowired
    private ApiDefinationService apiDefinationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restApiDefinationMockMvc;

    private ApiDefination apiDefination;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApiDefinationResource apiDefinationResource = new ApiDefinationResource(apiDefinationService);
        this.restApiDefinationMockMvc = MockMvcBuilders.standaloneSetup(apiDefinationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ApiDefination createEntity(EntityManager em) {
        ApiDefination apiDefination = new ApiDefination()
            .apiKey(DEFAULT_API_KEY)
            .apiType(DEFAULT_API_TYPE)
            .apiUrl(DEFAULT_API_URL);
        return apiDefination;
    }

    @Before
    public void initTest() {
        apiDefination = createEntity(em);
    }

    @Test
    @Transactional
    public void createApiDefination() throws Exception {
        int databaseSizeBeforeCreate = apiDefinationRepository.findAll().size();

        // Create the ApiDefination
        restApiDefinationMockMvc.perform(post("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDefination)))
            .andExpect(status().isCreated());

        // Validate the ApiDefination in the database
        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeCreate + 1);
        ApiDefination testApiDefination = apiDefinationList.get(apiDefinationList.size() - 1);
        assertThat(testApiDefination.getApiKey()).isEqualTo(DEFAULT_API_KEY);
        assertThat(testApiDefination.getApiType()).isEqualTo(DEFAULT_API_TYPE);
        assertThat(testApiDefination.getApiUrl()).isEqualTo(DEFAULT_API_URL);
    }

    @Test
    @Transactional
    public void createApiDefinationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apiDefinationRepository.findAll().size();

        // Create the ApiDefination with an existing ID
        apiDefination.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiDefinationMockMvc.perform(post("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDefination)))
            .andExpect(status().isBadRequest());

        // Validate the ApiDefination in the database
        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkApiKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiDefinationRepository.findAll().size();
        // set the field null
        apiDefination.setApiKey(null);

        // Create the ApiDefination, which fails.

        restApiDefinationMockMvc.perform(post("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDefination)))
            .andExpect(status().isBadRequest());

        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApiTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiDefinationRepository.findAll().size();
        // set the field null
        apiDefination.setApiType(null);

        // Create the ApiDefination, which fails.

        restApiDefinationMockMvc.perform(post("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDefination)))
            .andExpect(status().isBadRequest());

        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApiUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiDefinationRepository.findAll().size();
        // set the field null
        apiDefination.setApiUrl(null);

        // Create the ApiDefination, which fails.

        restApiDefinationMockMvc.perform(post("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDefination)))
            .andExpect(status().isBadRequest());

        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApiDefinations() throws Exception {
        // Initialize the database
        apiDefinationRepository.saveAndFlush(apiDefination);

        // Get all the apiDefinationList
        restApiDefinationMockMvc.perform(get("/api/api-definations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiDefination.getId().intValue())))
            .andExpect(jsonPath("$.[*].apiKey").value(hasItem(DEFAULT_API_KEY.toString())))
            .andExpect(jsonPath("$.[*].apiType").value(hasItem(DEFAULT_API_TYPE.toString())))
            .andExpect(jsonPath("$.[*].apiUrl").value(hasItem(DEFAULT_API_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getApiDefination() throws Exception {
        // Initialize the database
        apiDefinationRepository.saveAndFlush(apiDefination);

        // Get the apiDefination
        restApiDefinationMockMvc.perform(get("/api/api-definations/{id}", apiDefination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apiDefination.getId().intValue()))
            .andExpect(jsonPath("$.apiKey").value(DEFAULT_API_KEY.toString()))
            .andExpect(jsonPath("$.apiType").value(DEFAULT_API_TYPE.toString()))
            .andExpect(jsonPath("$.apiUrl").value(DEFAULT_API_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApiDefination() throws Exception {
        // Get the apiDefination
        restApiDefinationMockMvc.perform(get("/api/api-definations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApiDefination() throws Exception {
        // Initialize the database
        apiDefinationService.save(apiDefination);

        int databaseSizeBeforeUpdate = apiDefinationRepository.findAll().size();

        // Update the apiDefination
        ApiDefination updatedApiDefination = apiDefinationRepository.findById(apiDefination.getId()).get();
        // Disconnect from session so that the updates on updatedApiDefination are not directly saved in db
        em.detach(updatedApiDefination);
        updatedApiDefination
            .apiKey(UPDATED_API_KEY)
            .apiType(UPDATED_API_TYPE)
            .apiUrl(UPDATED_API_URL);

        restApiDefinationMockMvc.perform(put("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedApiDefination)))
            .andExpect(status().isOk());

        // Validate the ApiDefination in the database
        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeUpdate);
        ApiDefination testApiDefination = apiDefinationList.get(apiDefinationList.size() - 1);
        assertThat(testApiDefination.getApiKey()).isEqualTo(UPDATED_API_KEY);
        assertThat(testApiDefination.getApiType()).isEqualTo(UPDATED_API_TYPE);
        assertThat(testApiDefination.getApiUrl()).isEqualTo(UPDATED_API_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingApiDefination() throws Exception {
        int databaseSizeBeforeUpdate = apiDefinationRepository.findAll().size();

        // Create the ApiDefination

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiDefinationMockMvc.perform(put("/api/api-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiDefination)))
            .andExpect(status().isBadRequest());

        // Validate the ApiDefination in the database
        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApiDefination() throws Exception {
        // Initialize the database
        apiDefinationService.save(apiDefination);

        int databaseSizeBeforeDelete = apiDefinationRepository.findAll().size();

        // Get the apiDefination
        restApiDefinationMockMvc.perform(delete("/api/api-definations/{id}", apiDefination.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ApiDefination> apiDefinationList = apiDefinationRepository.findAll();
        assertThat(apiDefinationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiDefination.class);
        ApiDefination apiDefination1 = new ApiDefination();
        apiDefination1.setId(1L);
        ApiDefination apiDefination2 = new ApiDefination();
        apiDefination2.setId(apiDefination1.getId());
        assertThat(apiDefination1).isEqualTo(apiDefination2);
        apiDefination2.setId(2L);
        assertThat(apiDefination1).isNotEqualTo(apiDefination2);
        apiDefination1.setId(null);
        assertThat(apiDefination1).isNotEqualTo(apiDefination2);
    }
}
