package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ApiDefination;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApiDefination entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiDefinationRepository extends JpaRepository<ApiDefination, Long> {

}
