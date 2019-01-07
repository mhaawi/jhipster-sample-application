package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ApiDefination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApiDefination.
 */
public interface ApiDefinationService {

    /**
     * Save a apiDefination.
     *
     * @param apiDefination the entity to save
     * @return the persisted entity
     */
    ApiDefination save(ApiDefination apiDefination);

    /**
     * Get all the apiDefinations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApiDefination> findAll(Pageable pageable);


    /**
     * Get the "id" apiDefination.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApiDefination> findOne(Long id);

    /**
     * Delete the "id" apiDefination.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
