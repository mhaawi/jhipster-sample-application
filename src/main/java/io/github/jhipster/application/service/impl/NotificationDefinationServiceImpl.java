package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.NotificationDefinationService;
import io.github.jhipster.application.domain.NotificationDefination;
import io.github.jhipster.application.repository.NotificationDefinationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NotificationDefination.
 */
@Service
@Transactional
public class NotificationDefinationServiceImpl implements NotificationDefinationService {

    private final Logger log = LoggerFactory.getLogger(NotificationDefinationServiceImpl.class);

    private final NotificationDefinationRepository notificationDefinationRepository;

    public NotificationDefinationServiceImpl(NotificationDefinationRepository notificationDefinationRepository) {
        this.notificationDefinationRepository = notificationDefinationRepository;
    }

    /**
     * Save a notificationDefination.
     *
     * @param notificationDefination the entity to save
     * @return the persisted entity
     */
    @Override
    public NotificationDefination save(NotificationDefination notificationDefination) {
        log.debug("Request to save NotificationDefination : {}", notificationDefination);
        return notificationDefinationRepository.save(notificationDefination);
    }

    /**
     * Get all the notificationDefinations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NotificationDefination> findAll(Pageable pageable) {
        log.debug("Request to get all NotificationDefinations");
        return notificationDefinationRepository.findAll(pageable);
    }

    /**
     * Get all the NotificationDefination with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NotificationDefination> findAllWithEagerRelationships(Pageable pageable) {
        return notificationDefinationRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one notificationDefination by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificationDefination> findOne(Long id) {
        log.debug("Request to get NotificationDefination : {}", id);
        return notificationDefinationRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the notificationDefination by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NotificationDefination : {}", id);
        notificationDefinationRepository.deleteById(id);
    }
}
