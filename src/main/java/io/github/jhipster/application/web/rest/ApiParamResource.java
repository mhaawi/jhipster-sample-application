package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.ApiParam;
import io.github.jhipster.application.service.ApiParamService;
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
 * REST controller for managing ApiParam.
 */
@RestController
@RequestMapping("/api")
public class ApiParamResource {

    private final Logger log = LoggerFactory.getLogger(ApiParamResource.class);

    private static final String ENTITY_NAME = "apiParam";

    private final ApiParamService apiParamService;

    public ApiParamResource(ApiParamService apiParamService) {
        this.apiParamService = apiParamService;
    }

    /**
     * POST  /api-params : Create a new apiParam.
     *
     * @param apiParam the apiParam to create
     * @return the ResponseEntity with status 201 (Created) and with body the new apiParam, or with status 400 (Bad Request) if the apiParam has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/api-params")
    @Timed
    public ResponseEntity<ApiParam> createApiParam(@Valid @RequestBody ApiParam apiParam) throws URISyntaxException {
        log.debug("REST request to save ApiParam : {}", apiParam);
        if (apiParam.getId() != null) {
            throw new BadRequestAlertException("A new apiParam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiParam result = apiParamService.save(apiParam);
        return ResponseEntity.created(new URI("/api/api-params/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /api-params : Updates an existing apiParam.
     *
     * @param apiParam the apiParam to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated apiParam,
     * or with status 400 (Bad Request) if the apiParam is not valid,
     * or with status 500 (Internal Server Error) if the apiParam couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/api-params")
    @Timed
    public ResponseEntity<ApiParam> updateApiParam(@Valid @RequestBody ApiParam apiParam) throws URISyntaxException {
        log.debug("REST request to update ApiParam : {}", apiParam);
        if (apiParam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApiParam result = apiParamService.save(apiParam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, apiParam.getId().toString()))
            .body(result);
    }

    /**
     * GET  /api-params : get all the apiParams.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of apiParams in body
     */
    @GetMapping("/api-params")
    @Timed
    public ResponseEntity<List<ApiParam>> getAllApiParams(Pageable pageable) {
        log.debug("REST request to get a page of ApiParams");
        Page<ApiParam> page = apiParamService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/api-params");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /api-params/:id : get the "id" apiParam.
     *
     * @param id the id of the apiParam to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the apiParam, or with status 404 (Not Found)
     */
    @GetMapping("/api-params/{id}")
    @Timed
    public ResponseEntity<ApiParam> getApiParam(@PathVariable Long id) {
        log.debug("REST request to get ApiParam : {}", id);
        Optional<ApiParam> apiParam = apiParamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiParam);
    }

    /**
     * DELETE  /api-params/:id : delete the "id" apiParam.
     *
     * @param id the id of the apiParam to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/api-params/{id}")
    @Timed
    public ResponseEntity<Void> deleteApiParam(@PathVariable Long id) {
        log.debug("REST request to delete ApiParam : {}", id);
        apiParamService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
