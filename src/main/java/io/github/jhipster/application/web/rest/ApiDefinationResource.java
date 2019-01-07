package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ApiDefination;
import io.github.jhipster.application.service.ApiDefinationService;
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
 * REST controller for managing ApiDefination.
 */
@RestController
@RequestMapping("/api")
public class ApiDefinationResource {

    private final Logger log = LoggerFactory.getLogger(ApiDefinationResource.class);

    private static final String ENTITY_NAME = "apiDefination";

    private final ApiDefinationService apiDefinationService;

    public ApiDefinationResource(ApiDefinationService apiDefinationService) {
        this.apiDefinationService = apiDefinationService;
    }

    /**
     * POST  /api-definations : Create a new apiDefination.
     *
     * @param apiDefination the apiDefination to create
     * @return the ResponseEntity with status 201 (Created) and with body the new apiDefination, or with status 400 (Bad Request) if the apiDefination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/api-definations")
    @Timed
    public ResponseEntity<ApiDefination> createApiDefination(@Valid @RequestBody ApiDefination apiDefination) throws URISyntaxException {
        log.debug("REST request to save ApiDefination : {}", apiDefination);
        if (apiDefination.getId() != null) {
            throw new BadRequestAlertException("A new apiDefination cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiDefination result = apiDefinationService.save(apiDefination);
        return ResponseEntity.created(new URI("/api/api-definations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /api-definations : Updates an existing apiDefination.
     *
     * @param apiDefination the apiDefination to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated apiDefination,
     * or with status 400 (Bad Request) if the apiDefination is not valid,
     * or with status 500 (Internal Server Error) if the apiDefination couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/api-definations")
    @Timed
    public ResponseEntity<ApiDefination> updateApiDefination(@Valid @RequestBody ApiDefination apiDefination) throws URISyntaxException {
        log.debug("REST request to update ApiDefination : {}", apiDefination);
        if (apiDefination.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApiDefination result = apiDefinationService.save(apiDefination);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, apiDefination.getId().toString()))
            .body(result);
    }

    /**
     * GET  /api-definations : get all the apiDefinations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of apiDefinations in body
     */
    @GetMapping("/api-definations")
    @Timed
    public ResponseEntity<List<ApiDefination>> getAllApiDefinations(Pageable pageable) {
        log.debug("REST request to get a page of ApiDefinations");
        Page<ApiDefination> page = apiDefinationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/api-definations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /api-definations/:id : get the "id" apiDefination.
     *
     * @param id the id of the apiDefination to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the apiDefination, or with status 404 (Not Found)
     */
    @GetMapping("/api-definations/{id}")
    @Timed
    public ResponseEntity<ApiDefination> getApiDefination(@PathVariable Long id) {
        log.debug("REST request to get ApiDefination : {}", id);
        Optional<ApiDefination> apiDefination = apiDefinationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiDefination);
    }

    /**
     * DELETE  /api-definations/:id : delete the "id" apiDefination.
     *
     * @param id the id of the apiDefination to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/api-definations/{id}")
    @Timed
    public ResponseEntity<Void> deleteApiDefination(@PathVariable Long id) {
        log.debug("REST request to delete ApiDefination : {}", id);
        apiDefinationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
