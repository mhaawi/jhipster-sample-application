package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.ApiParam;
import io.github.jhipster.application.repository.ApiParamRepository;
import io.github.jhipster.application.service.ApiParamService;
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

import io.github.jhipster.application.domain.enumeration.ParamType;
/**
 * Test class for the ApiParamResource REST controller.
 *
 * @see ApiParamResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ApiParamResourceIntTest {

    private static final ParamType DEFAULT_PARAM_TYPE = ParamType.HEADER;
    private static final ParamType UPDATED_PARAM_TYPE = ParamType.BODY;

    private static final String DEFAULT_PARAM_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARAM_DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_PARAM_DEFAULT_VALUE = "BBBBBBBBBB";

    @Autowired
    private ApiParamRepository apiParamRepository;

    @Autowired
    private ApiParamService apiParamService;

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

    private MockMvc restApiParamMockMvc;

    private ApiParam apiParam;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApiParamResource apiParamResource = new ApiParamResource(apiParamService);
        this.restApiParamMockMvc = MockMvcBuilders.standaloneSetup(apiParamResource)
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
    public static ApiParam createEntity(EntityManager em) {
        ApiParam apiParam = new ApiParam()
            .paramType(DEFAULT_PARAM_TYPE)
            .paramName(DEFAULT_PARAM_NAME)
            .paramDefaultValue(DEFAULT_PARAM_DEFAULT_VALUE);
        return apiParam;
    }

    @Before
    public void initTest() {
        apiParam = createEntity(em);
    }

    @Test
    @Transactional
    public void createApiParam() throws Exception {
        int databaseSizeBeforeCreate = apiParamRepository.findAll().size();

        // Create the ApiParam
        restApiParamMockMvc.perform(post("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiParam)))
            .andExpect(status().isCreated());

        // Validate the ApiParam in the database
        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeCreate + 1);
        ApiParam testApiParam = apiParamList.get(apiParamList.size() - 1);
        assertThat(testApiParam.getParamType()).isEqualTo(DEFAULT_PARAM_TYPE);
        assertThat(testApiParam.getParamName()).isEqualTo(DEFAULT_PARAM_NAME);
        assertThat(testApiParam.getParamDefaultValue()).isEqualTo(DEFAULT_PARAM_DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createApiParamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = apiParamRepository.findAll().size();

        // Create the ApiParam with an existing ID
        apiParam.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApiParamMockMvc.perform(post("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiParam)))
            .andExpect(status().isBadRequest());

        // Validate the ApiParam in the database
        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkParamTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiParamRepository.findAll().size();
        // set the field null
        apiParam.setParamType(null);

        // Create the ApiParam, which fails.

        restApiParamMockMvc.perform(post("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiParam)))
            .andExpect(status().isBadRequest());

        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParamNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiParamRepository.findAll().size();
        // set the field null
        apiParam.setParamName(null);

        // Create the ApiParam, which fails.

        restApiParamMockMvc.perform(post("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiParam)))
            .andExpect(status().isBadRequest());

        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkParamDefaultValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = apiParamRepository.findAll().size();
        // set the field null
        apiParam.setParamDefaultValue(null);

        // Create the ApiParam, which fails.

        restApiParamMockMvc.perform(post("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiParam)))
            .andExpect(status().isBadRequest());

        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllApiParams() throws Exception {
        // Initialize the database
        apiParamRepository.saveAndFlush(apiParam);

        // Get all the apiParamList
        restApiParamMockMvc.perform(get("/api/api-params?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(apiParam.getId().intValue())))
            .andExpect(jsonPath("$.[*].paramType").value(hasItem(DEFAULT_PARAM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].paramName").value(hasItem(DEFAULT_PARAM_NAME.toString())))
            .andExpect(jsonPath("$.[*].paramDefaultValue").value(hasItem(DEFAULT_PARAM_DEFAULT_VALUE.toString())));
    }
    
    @Test
    @Transactional
    public void getApiParam() throws Exception {
        // Initialize the database
        apiParamRepository.saveAndFlush(apiParam);

        // Get the apiParam
        restApiParamMockMvc.perform(get("/api/api-params/{id}", apiParam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(apiParam.getId().intValue()))
            .andExpect(jsonPath("$.paramType").value(DEFAULT_PARAM_TYPE.toString()))
            .andExpect(jsonPath("$.paramName").value(DEFAULT_PARAM_NAME.toString()))
            .andExpect(jsonPath("$.paramDefaultValue").value(DEFAULT_PARAM_DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApiParam() throws Exception {
        // Get the apiParam
        restApiParamMockMvc.perform(get("/api/api-params/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApiParam() throws Exception {
        // Initialize the database
        apiParamService.save(apiParam);

        int databaseSizeBeforeUpdate = apiParamRepository.findAll().size();

        // Update the apiParam
        ApiParam updatedApiParam = apiParamRepository.findById(apiParam.getId()).get();
        // Disconnect from session so that the updates on updatedApiParam are not directly saved in db
        em.detach(updatedApiParam);
        updatedApiParam
            .paramType(UPDATED_PARAM_TYPE)
            .paramName(UPDATED_PARAM_NAME)
            .paramDefaultValue(UPDATED_PARAM_DEFAULT_VALUE);

        restApiParamMockMvc.perform(put("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedApiParam)))
            .andExpect(status().isOk());

        // Validate the ApiParam in the database
        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeUpdate);
        ApiParam testApiParam = apiParamList.get(apiParamList.size() - 1);
        assertThat(testApiParam.getParamType()).isEqualTo(UPDATED_PARAM_TYPE);
        assertThat(testApiParam.getParamName()).isEqualTo(UPDATED_PARAM_NAME);
        assertThat(testApiParam.getParamDefaultValue()).isEqualTo(UPDATED_PARAM_DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingApiParam() throws Exception {
        int databaseSizeBeforeUpdate = apiParamRepository.findAll().size();

        // Create the ApiParam

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restApiParamMockMvc.perform(put("/api/api-params")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(apiParam)))
            .andExpect(status().isBadRequest());

        // Validate the ApiParam in the database
        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteApiParam() throws Exception {
        // Initialize the database
        apiParamService.save(apiParam);

        int databaseSizeBeforeDelete = apiParamRepository.findAll().size();

        // Get the apiParam
        restApiParamMockMvc.perform(delete("/api/api-params/{id}", apiParam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ApiParam> apiParamList = apiParamRepository.findAll();
        assertThat(apiParamList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApiParam.class);
        ApiParam apiParam1 = new ApiParam();
        apiParam1.setId(1L);
        ApiParam apiParam2 = new ApiParam();
        apiParam2.setId(apiParam1.getId());
        assertThat(apiParam1).isEqualTo(apiParam2);
        apiParam2.setId(2L);
        assertThat(apiParam1).isNotEqualTo(apiParam2);
        apiParam1.setId(null);
        assertThat(apiParam1).isNotEqualTo(apiParam2);
    }
}
