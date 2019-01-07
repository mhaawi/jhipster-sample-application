package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ApiDefinationService;
import io.github.jhipster.application.domain.ApiDefination;
import io.github.jhipster.application.repository.ApiDefinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApiDefination.
 */
@Service
@Transactional
public class ApiDefinationServiceImpl implements ApiDefinationService {

    private final Logger log = LoggerFactory.getLogger(ApiDefinationServiceImpl.class);

    private final ApiDefinationRepository apiDefinationRepository;

    public ApiDefinationServiceImpl(ApiDefinationRepository apiDefinationRepository) {
        this.apiDefinationRepository = apiDefinationRepository;
    }

    /**
     * Save a apiDefination.
     *
     * @param apiDefination the entity to save
     * @return the persisted entity
     */
    @Override
    public ApiDefination save(ApiDefination apiDefination) {
        log.debug("Request to save ApiDefination : {}", apiDefination);
        return apiDefinationRepository.save(apiDefination);
    }

    /**
     * Get all the apiDefinations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApiDefination> findAll(Pageable pageable) {
        log.debug("Request to get all ApiDefinations");
        return apiDefinationRepository.findAll(pageable);
    }


    /**
     * Get one apiDefination by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApiDefination> findOne(Long id) {
        log.debug("Request to get ApiDefination : {}", id);
        return apiDefinationRepository.findById(id);
    }

    /**
     * Delete the apiDefination by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiDefination : {}", id);
        apiDefinationRepository.deleteById(id);
    }
}
