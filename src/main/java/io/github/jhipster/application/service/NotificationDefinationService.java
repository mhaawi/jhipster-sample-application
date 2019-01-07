package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.NotificationDefination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NotificationDefination.
 */
public interface NotificationDefinationService {

    /**
     * Save a notificationDefination.
     *
     * @param notificationDefination the entity to save
     * @return the persisted entity
     */
    NotificationDefination save(NotificationDefination notificationDefination);

    /**
     * Get all the notificationDefinations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NotificationDefination> findAll(Pageable pageable);

    /**
     * Get all the NotificationDefination with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NotificationDefination> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" notificationDefination.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NotificationDefination> findOne(Long id);

    /**
     * Delete the "id" notificationDefination.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
