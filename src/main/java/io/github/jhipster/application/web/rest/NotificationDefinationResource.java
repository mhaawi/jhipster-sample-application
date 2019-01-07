package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.NotificationDefination;
import io.github.jhipster.application.service.NotificationDefinationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NotificationDefination.
 */
@RestController
@RequestMapping("/api")
public class NotificationDefinationResource {

    private final Logger log = LoggerFactory.getLogger(NotificationDefinationResource.class);

    private static final String ENTITY_NAME = "notificationDefination";

    private final NotificationDefinationService notificationDefinationService;

    public NotificationDefinationResource(NotificationDefinationService notificationDefinationService) {
        this.notificationDefinationService = notificationDefinationService;
    }

    /**
     * POST  /notification-definations : Create a new notificationDefination.
     *
     * @param notificationDefination the notificationDefination to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificationDefination, or with status 400 (Bad Request) if the notificationDefination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notification-definations")
    @Timed
    public ResponseEntity<NotificationDefination> createNotificationDefination(@Valid @RequestBody NotificationDefination notificationDefination) throws URISyntaxException {
        log.debug("REST request to save NotificationDefination : {}", notificationDefination);
        if (notificationDefination.getId() != null) {
            throw new BadRequestAlertException("A new notificationDefination cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificationDefination result = notificationDefinationService.save(notificationDefination);
        return ResponseEntity.created(new URI("/api/notification-definations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notification-definations : Updates an existing notificationDefination.
     *
     * @param notificationDefination the notificationDefination to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificationDefination,
     * or with status 400 (Bad Request) if the notificationDefination is not valid,
     * or with status 500 (Internal Server Error) if the notificationDefination couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notification-definations")
    @Timed
    public ResponseEntity<NotificationDefination> updateNotificationDefination(@Valid @RequestBody NotificationDefination notificationDefination) throws URISyntaxException {
        log.debug("REST request to update NotificationDefination : {}", notificationDefination);
        if (notificationDefination.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificationDefination result = notificationDefinationService.save(notificationDefination);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificationDefination.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notification-definations : get all the notificationDefinations.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of notificationDefinations in body
     */
    @GetMapping("/notification-definations")
    @Timed
    public ResponseEntity<List<NotificationDefination>> getAllNotificationDefinations(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of NotificationDefinations");
        Page<NotificationDefination> page;
        if (eagerload) {
            page = notificationDefinationService.findAllWithEagerRelationships(pageable);
        } else {
            page = notificationDefinationService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/notification-definations?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /notification-definations/:id : get the "id" notificationDefination.
     *
     * @param id the id of the notificationDefination to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificationDefination, or with status 404 (Not Found)
     */
    @GetMapping("/notification-definations/{id}")
    @Timed
    public ResponseEntity<NotificationDefination> getNotificationDefination(@PathVariable Long id) {
        log.debug("REST request to get NotificationDefination : {}", id);
        Optional<NotificationDefination> notificationDefination = notificationDefinationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificationDefination);
    }

    /**
     * DELETE  /notification-definations/:id : delete the "id" notificationDefination.
     *
     * @param id the id of the notificationDefination to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notification-definations/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotificationDefination(@PathVariable Long id) {
        log.debug("REST request to delete NotificationDefination : {}", id);
        notificationDefinationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
