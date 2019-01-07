package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.NotificationDefination;
import io.github.jhipster.application.repository.NotificationDefinationRepository;
import io.github.jhipster.application.service.NotificationDefinationService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.application.domain.enumeration.Channel;
/**
 * Test class for the NotificationDefinationResource REST controller.
 *
 * @see NotificationDefinationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class NotificationDefinationResourceIntTest {

    private static final String DEFAULT_NOTIFICATION_KEY = "AAAAAAAAAA";
    private static final String UPDATED_NOTIFICATION_KEY = "BBBBBBBBBB";

    private static final Channel DEFAULT_CHANNEL_NAME = Channel.EMAIL;
    private static final Channel UPDATED_CHANNEL_NAME = Channel.SMS;

    private static final String DEFAULT_TEMPLATE_PATH = "AAAAAAAAAA";
    private static final String UPDATED_TEMPLATE_PATH = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_SOURCE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_TYPE = "BBBBBBBBBB";

    @Autowired
    private NotificationDefinationRepository notificationDefinationRepository;

    @Mock
    private NotificationDefinationRepository notificationDefinationRepositoryMock;

    @Mock
    private NotificationDefinationService notificationDefinationServiceMock;

    @Autowired
    private NotificationDefinationService notificationDefinationService;

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

    private MockMvc restNotificationDefinationMockMvc;

    private NotificationDefination notificationDefination;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NotificationDefinationResource notificationDefinationResource = new NotificationDefinationResource(notificationDefinationService);
        this.restNotificationDefinationMockMvc = MockMvcBuilders.standaloneSetup(notificationDefinationResource)
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
    public static NotificationDefination createEntity(EntityManager em) {
        NotificationDefination notificationDefination = new NotificationDefination()
            .notificationKey(DEFAULT_NOTIFICATION_KEY)
            .channelName(DEFAULT_CHANNEL_NAME)
            .templatePath(DEFAULT_TEMPLATE_PATH)
            .isActive(DEFAULT_IS_ACTIVE)
            .sourceType(DEFAULT_SOURCE_TYPE);
        return notificationDefination;
    }

    @Before
    public void initTest() {
        notificationDefination = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotificationDefination() throws Exception {
        int databaseSizeBeforeCreate = notificationDefinationRepository.findAll().size();

        // Create the NotificationDefination
        restNotificationDefinationMockMvc.perform(post("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isCreated());

        // Validate the NotificationDefination in the database
        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeCreate + 1);
        NotificationDefination testNotificationDefination = notificationDefinationList.get(notificationDefinationList.size() - 1);
        assertThat(testNotificationDefination.getNotificationKey()).isEqualTo(DEFAULT_NOTIFICATION_KEY);
        assertThat(testNotificationDefination.getChannelName()).isEqualTo(DEFAULT_CHANNEL_NAME);
        assertThat(testNotificationDefination.getTemplatePath()).isEqualTo(DEFAULT_TEMPLATE_PATH);
        assertThat(testNotificationDefination.isIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testNotificationDefination.getSourceType()).isEqualTo(DEFAULT_SOURCE_TYPE);
    }

    @Test
    @Transactional
    public void createNotificationDefinationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificationDefinationRepository.findAll().size();

        // Create the NotificationDefination with an existing ID
        notificationDefination.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificationDefinationMockMvc.perform(post("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationDefination in the database
        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNotificationKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationDefinationRepository.findAll().size();
        // set the field null
        notificationDefination.setNotificationKey(null);

        // Create the NotificationDefination, which fails.

        restNotificationDefinationMockMvc.perform(post("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isBadRequest());

        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChannelNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationDefinationRepository.findAll().size();
        // set the field null
        notificationDefination.setChannelName(null);

        // Create the NotificationDefination, which fails.

        restNotificationDefinationMockMvc.perform(post("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isBadRequest());

        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemplatePathIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationDefinationRepository.findAll().size();
        // set the field null
        notificationDefination.setTemplatePath(null);

        // Create the NotificationDefination, which fails.

        restNotificationDefinationMockMvc.perform(post("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isBadRequest());

        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificationDefinationRepository.findAll().size();
        // set the field null
        notificationDefination.setSourceType(null);

        // Create the NotificationDefination, which fails.

        restNotificationDefinationMockMvc.perform(post("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isBadRequest());

        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotificationDefinations() throws Exception {
        // Initialize the database
        notificationDefinationRepository.saveAndFlush(notificationDefination);

        // Get all the notificationDefinationList
        restNotificationDefinationMockMvc.perform(get("/api/notification-definations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notificationDefination.getId().intValue())))
            .andExpect(jsonPath("$.[*].notificationKey").value(hasItem(DEFAULT_NOTIFICATION_KEY.toString())))
            .andExpect(jsonPath("$.[*].channelName").value(hasItem(DEFAULT_CHANNEL_NAME.toString())))
            .andExpect(jsonPath("$.[*].templatePath").value(hasItem(DEFAULT_TEMPLATE_PATH.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].sourceType").value(hasItem(DEFAULT_SOURCE_TYPE.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllNotificationDefinationsWithEagerRelationshipsIsEnabled() throws Exception {
        NotificationDefinationResource notificationDefinationResource = new NotificationDefinationResource(notificationDefinationServiceMock);
        when(notificationDefinationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restNotificationDefinationMockMvc = MockMvcBuilders.standaloneSetup(notificationDefinationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restNotificationDefinationMockMvc.perform(get("/api/notification-definations?eagerload=true"))
        .andExpect(status().isOk());

        verify(notificationDefinationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllNotificationDefinationsWithEagerRelationshipsIsNotEnabled() throws Exception {
        NotificationDefinationResource notificationDefinationResource = new NotificationDefinationResource(notificationDefinationServiceMock);
            when(notificationDefinationServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restNotificationDefinationMockMvc = MockMvcBuilders.standaloneSetup(notificationDefinationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restNotificationDefinationMockMvc.perform(get("/api/notification-definations?eagerload=true"))
        .andExpect(status().isOk());

            verify(notificationDefinationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getNotificationDefination() throws Exception {
        // Initialize the database
        notificationDefinationRepository.saveAndFlush(notificationDefination);

        // Get the notificationDefination
        restNotificationDefinationMockMvc.perform(get("/api/notification-definations/{id}", notificationDefination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(notificationDefination.getId().intValue()))
            .andExpect(jsonPath("$.notificationKey").value(DEFAULT_NOTIFICATION_KEY.toString()))
            .andExpect(jsonPath("$.channelName").value(DEFAULT_CHANNEL_NAME.toString()))
            .andExpect(jsonPath("$.templatePath").value(DEFAULT_TEMPLATE_PATH.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.sourceType").value(DEFAULT_SOURCE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNotificationDefination() throws Exception {
        // Get the notificationDefination
        restNotificationDefinationMockMvc.perform(get("/api/notification-definations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotificationDefination() throws Exception {
        // Initialize the database
        notificationDefinationService.save(notificationDefination);

        int databaseSizeBeforeUpdate = notificationDefinationRepository.findAll().size();

        // Update the notificationDefination
        NotificationDefination updatedNotificationDefination = notificationDefinationRepository.findById(notificationDefination.getId()).get();
        // Disconnect from session so that the updates on updatedNotificationDefination are not directly saved in db
        em.detach(updatedNotificationDefination);
        updatedNotificationDefination
            .notificationKey(UPDATED_NOTIFICATION_KEY)
            .channelName(UPDATED_CHANNEL_NAME)
            .templatePath(UPDATED_TEMPLATE_PATH)
            .isActive(UPDATED_IS_ACTIVE)
            .sourceType(UPDATED_SOURCE_TYPE);

        restNotificationDefinationMockMvc.perform(put("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNotificationDefination)))
            .andExpect(status().isOk());

        // Validate the NotificationDefination in the database
        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeUpdate);
        NotificationDefination testNotificationDefination = notificationDefinationList.get(notificationDefinationList.size() - 1);
        assertThat(testNotificationDefination.getNotificationKey()).isEqualTo(UPDATED_NOTIFICATION_KEY);
        assertThat(testNotificationDefination.getChannelName()).isEqualTo(UPDATED_CHANNEL_NAME);
        assertThat(testNotificationDefination.getTemplatePath()).isEqualTo(UPDATED_TEMPLATE_PATH);
        assertThat(testNotificationDefination.isIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testNotificationDefination.getSourceType()).isEqualTo(UPDATED_SOURCE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingNotificationDefination() throws Exception {
        int databaseSizeBeforeUpdate = notificationDefinationRepository.findAll().size();

        // Create the NotificationDefination

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificationDefinationMockMvc.perform(put("/api/notification-definations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(notificationDefination)))
            .andExpect(status().isBadRequest());

        // Validate the NotificationDefination in the database
        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotificationDefination() throws Exception {
        // Initialize the database
        notificationDefinationService.save(notificationDefination);

        int databaseSizeBeforeDelete = notificationDefinationRepository.findAll().size();

        // Get the notificationDefination
        restNotificationDefinationMockMvc.perform(delete("/api/notification-definations/{id}", notificationDefination.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NotificationDefination> notificationDefinationList = notificationDefinationRepository.findAll();
        assertThat(notificationDefinationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NotificationDefination.class);
        NotificationDefination notificationDefination1 = new NotificationDefination();
        notificationDefination1.setId(1L);
        NotificationDefination notificationDefination2 = new NotificationDefination();
        notificationDefination2.setId(notificationDefination1.getId());
        assertThat(notificationDefination1).isEqualTo(notificationDefination2);
        notificationDefination2.setId(2L);
        assertThat(notificationDefination1).isNotEqualTo(notificationDefination2);
        notificationDefination1.setId(null);
        assertThat(notificationDefination1).isNotEqualTo(notificationDefination2);
    }
}
