package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.SourceType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SourceType.
 */
public interface SourceTypeService {

    /**
     * Save a sourceType.
     *
     * @param sourceType the entity to save
     * @return the persisted entity
     */
    SourceType save(SourceType sourceType);

    /**
     * Get all the sourceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SourceType> findAll(Pageable pageable);


    /**
     * Get the "id" sourceType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SourceType> findOne(Long id);

    /**
     * Delete the "id" sourceType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
