package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.SourceType;
import io.github.jhipster.application.repository.SourceTypeRepository;
import io.github.jhipster.application.service.SourceTypeService;
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

/**
 * Test class for the SourceTypeResource REST controller.
 *
 * @see SourceTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class SourceTypeResourceIntTest {

    private static final String DEFAULT_SOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TYPE = "BBBBBBBBBB";

    @Autowired
    private SourceTypeRepository sourceTypeRepository;

    @Autowired
    private SourceTypeService sourceTypeService;

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

    private MockMvc restSourceTypeMockMvc;

    private SourceType sourceType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SourceTypeResource sourceTypeResource = new SourceTypeResource(sourceTypeService);
        this.restSourceTypeMockMvc = MockMvcBuilders.standaloneSetup(sourceTypeResource)
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
    public static SourceType createEntity(EntityManager em) {
        SourceType sourceType = new SourceType()
            .sourceType(DEFAULT_SOURCE_TYPE);
        return sourceType;
    }

    @Before
    public void initTest() {
        sourceType = createEntity(em);
    }

    @Test
    @Transactional
    public void createSourceType() throws Exception {
        int databaseSizeBeforeCreate = sourceTypeRepository.findAll().size();

        // Create the SourceType
        restSourceTypeMockMvc.perform(post("/api/source-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sourceType)))
            .andExpect(status().isCreated());

        // Validate the SourceType in the database
        List<SourceType> sourceTypeList = sourceTypeRepository.findAll();
        assertThat(sourceTypeList).hasSize(databaseSizeBeforeCreate + 1);
        SourceType testSourceType = sourceTypeList.get(sourceTypeList.size() - 1);
        assertThat(testSourceType.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
    }

    @Test
    @Transactional
    public void createSourceTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sourceTypeRepository.findAll().size();

        // Create the SourceType with an existing ID
        sourceType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSourceTypeMockMvc.perform(post("/api/source-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sourceType)))
            .andExpect(status().isBadRequest());

        // Validate the SourceType in the database
        List<SourceType> sourceTypeList = sourceTypeRepository.findAll();
        assertThat(sourceTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSourceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sourceTypeRepository.findAll().size();
        // set the field null
        sourceType.setSourceType(null);

        // Create the SourceType, which fails.

        restSourceTypeMockMvc.perform(post("/api/source-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sourceType)))
            .andExpect(status().isBadRequest());

        List<SourceType> sourceTypeList = sourceTypeRepository.findAll();
        assertThat(sourceTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSourceTypes() throws Exception {
        // Initialize the database
        sourceTypeRepository.saveAndFlush(sourceType);

        // Get all the sourceTypeList
        restSourceTypeMockMvc.perform(get("/api/source-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sourceType.getId().intValue())))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getSourceType() throws Exception {
        // Initialize the database
        sourceTypeRepository.saveAndFlush(sourceType);

        // Get the sourceType
        restSourceTypeMockMvc.perform(get("/api/source-types/{id}", sourceType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sourceType.getId().intValue()))
            .andExpect(jsonPath("$.sourceType").value(DEFAULT_SOURCE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSourceType() throws Exception {
        // Get the sourceType
        restSourceTypeMockMvc.perform(get("/api/source-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSourceType() throws Exception {
        // Initialize the database
        sourceTypeService.save(sourceType);

        int databaseSizeBeforeUpdate = sourceTypeRepository.findAll().size();

        // Update the sourceType
        SourceType updatedSourceType = sourceTypeRepository.findById(sourceType.getId()).get();
        // Disconnect from session so that the updates on updatedSourceType are not directly saved in db
        em.detach(updatedSourceType);
        updatedSourceType
            .sourceType(UPDATED_SOURCE_TYPE);

        restSourceTypeMockMvc.perform(put("/api/source-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSourceType)))
            .andExpect(status().isOk());

        // Validate the SourceType in the database
        List<SourceType> sourceTypeList = sourceTypeRepository.findAll();
        assertThat(sourceTypeList).hasSize(databaseSizeBeforeUpdate);
        SourceType testSourceType = sourceTypeList.get(sourceTypeList.size() - 1);
        assertThat(testSourceType.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSourceType() throws Exception {
        int databaseSizeBeforeUpdate = sourceTypeRepository.findAll().size();

        // Create the SourceType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSourceTypeMockMvc.perform(put("/api/source-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sourceType)))
            .andExpect(status().isBadRequest());

        // Validate the SourceType in the database
        List<SourceType> sourceTypeList = sourceTypeRepository.findAll();
        assertThat(sourceTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSourceType() throws Exception {
        // Initialize the database
        sourceTypeService.save(sourceType);

        int databaseSizeBeforeDelete = sourceTypeRepository.findAll().size();

        // Get the sourceType
        restSourceTypeMockMvc.perform(delete("/api/source-types/{id}", sourceType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SourceType> sourceTypeList = sourceTypeRepository.findAll();
        assertThat(sourceTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SourceType.class);
        SourceType sourceType1 = new SourceType();
        sourceType1.setId(1L);
        SourceType sourceType2 = new SourceType();
        sourceType2.setId(sourceType1.getId());
        assertThat(sourceType1).isEqualTo(sourceType2);
        sourceType2.setId(2L);
        assertThat(sourceType1).isNotEqualTo(sourceType2);
        sourceType1.setId(null);
        assertThat(sourceType1).isNotEqualTo(sourceType2);
    }
}
