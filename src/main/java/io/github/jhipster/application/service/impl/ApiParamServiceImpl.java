package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ApiParamService;
import io.github.jhipster.application.domain.ApiParam;
import io.github.jhipster.application.repository.ApiParamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApiParam.
 */
@Service
@Transactional
public class ApiParamServiceImpl implements ApiParamService {

    private final Logger log = LoggerFactory.getLogger(ApiParamServiceImpl.class);

    private final ApiParamRepository apiParamRepository;

    public ApiParamServiceImpl(ApiParamRepository apiParamRepository) {
        this.apiParamRepository = apiParamRepository;
    }

    /**
     * Save a apiParam.
     *
     * @param apiParam the entity to save
     * @return the persisted entity
     */
    @Override
    public ApiParam save(ApiParam apiParam) {
        log.debug("Request to save ApiParam : {}", apiParam);
        return apiParamRepository.save(apiParam);
    }

    /**
     * Get all the apiParams.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApiParam> findAll(Pageable pageable) {
        log.debug("Request to get all ApiParams");
        return apiParamRepository.findAll(pageable);
    }


    /**
     * Get one apiParam by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApiParam> findOne(Long id) {
        log.debug("Request to get ApiParam : {}", id);
        return apiParamRepository.findById(id);
    }

    /**
     * Delete the apiParam by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiParam : {}", id);
        apiParamRepository.deleteById(id);
    }
}
