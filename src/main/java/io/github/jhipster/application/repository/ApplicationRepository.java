package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Application entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query(value = "select distinct application from Application application left join fetch application.notificationDefinations",
        countQuery = "select count(distinct application) from Application application")
    Page<Application> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct application from Application application left join fetch application.notificationDefinations")
    List<Application> findAllWithEagerRelationships();

    @Query("select application from Application application left join fetch application.notificationDefinations where application.id =:id")
    Optional<Application> findOneWithEagerRelationships(@Param("id") Long id);

}
