package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.SourceType;
import io.github.jhipster.application.service.SourceTypeService;
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
 * REST controller for managing SourceType.
 */
@RestController
@RequestMapping("/api")
public class SourceTypeResource {

    private final Logger log = LoggerFactory.getLogger(SourceTypeResource.class);

    private static final String ENTITY_NAME = "sourceType";

    private final SourceTypeService sourceTypeService;

    public SourceTypeResource(SourceTypeService sourceTypeService) {
        this.sourceTypeService = sourceTypeService;
    }

    /**
     * POST  /source-types : Create a new sourceType.
     *
     * @param sourceType the sourceType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sourceType, or with status 400 (Bad Request) if the sourceType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/source-types")
    @Timed
    public ResponseEntity<SourceType> createSourceType(@Valid @RequestBody SourceType sourceType) throws URISyntaxException {
        log.debug("REST request to save SourceType : {}", sourceType);
        if (sourceType.getId() != null) {
            throw new BadRequestAlertException("A new sourceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SourceType result = sourceTypeService.save(sourceType);
        return ResponseEntity.created(new URI("/api/source-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /source-types : Updates an existing sourceType.
     *
     * @param sourceType the sourceType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sourceType,
     * or with status 400 (Bad Request) if the sourceType is not valid,
     * or with status 500 (Internal Server Error) if the sourceType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/source-types")
    @Timed
    public ResponseEntity<SourceType> updateSourceType(@Valid @RequestBody SourceType sourceType) throws URISyntaxException {
        log.debug("REST request to update SourceType : {}", sourceType);
        if (sourceType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SourceType result = sourceTypeService.save(sourceType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sourceType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /source-types : get all the sourceTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sourceTypes in body
     */
    @GetMapping("/source-types")
    @Timed
    public ResponseEntity<List<SourceType>> getAllSourceTypes(Pageable pageable) {
        log.debug("REST request to get a page of SourceTypes");
        Page<SourceType> page = sourceTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/source-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /source-types/:id : get the "id" sourceType.
     *
     * @param id the id of the sourceType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sourceType, or with status 404 (Not Found)
     */
    @GetMapping("/source-types/{id}")
    @Timed
    public ResponseEntity<SourceType> getSourceType(@PathVariable Long id) {
        log.debug("REST request to get SourceType : {}", id);
        Optional<SourceType> sourceType = sourceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sourceType);
    }

    /**
     * DELETE  /source-types/:id : delete the "id" sourceType.
     *
     * @param id the id of the sourceType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/source-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteSourceType(@PathVariable Long id) {
        log.debug("REST request to delete SourceType : {}", id);
        sourceTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
