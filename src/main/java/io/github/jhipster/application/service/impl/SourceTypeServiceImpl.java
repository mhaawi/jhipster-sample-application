package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.SourceTypeService;
import io.github.jhipster.application.domain.SourceType;
import io.github.jhipster.application.repository.SourceTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SourceType.
 */
@Service
@Transactional
public class SourceTypeServiceImpl implements SourceTypeService {

    private final Logger log = LoggerFactory.getLogger(SourceTypeServiceImpl.class);

    private final SourceTypeRepository sourceTypeRepository;

    public SourceTypeServiceImpl(SourceTypeRepository sourceTypeRepository) {
        this.sourceTypeRepository = sourceTypeRepository;
    }

    /**
     * Save a sourceType.
     *
     * @param sourceType the entity to save
     * @return the persisted entity
     */
    @Override
    public SourceType save(SourceType sourceType) {
        log.debug("Request to save SourceType : {}", sourceType);
        return sourceTypeRepository.save(sourceType);
    }

    /**
     * Get all the sourceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SourceType> findAll(Pageable pageable) {
        log.debug("Request to get all SourceTypes");
        return sourceTypeRepository.findAll(pageable);
    }


    /**
     * Get one sourceType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SourceType> findOne(Long id) {
        log.debug("Request to get SourceType : {}", id);
        return sourceTypeRepository.findById(id);
    }

    /**
     * Delete the sourceType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SourceType : {}", id);
        sourceTypeRepository.deleteById(id);
    }
}
