package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.NotificationDefination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NotificationDefination entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationDefinationRepository extends JpaRepository<NotificationDefination, Long> {

    @Query(value = "select distinct notification_defination from NotificationDefination notification_defination left join fetch notification_defination.applicationNames left join fetch notification_defination.apiKeys",
        countQuery = "select count(distinct notification_defination) from NotificationDefination notification_defination")
    Page<NotificationDefination> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct notification_defination from NotificationDefination notification_defination left join fetch notification_defination.applicationNames left join fetch notification_defination.apiKeys")
    List<NotificationDefination> findAllWithEagerRelationships();

    @Query("select notification_defination from NotificationDefination notification_defination left join fetch notification_defination.applicationNames left join fetch notification_defination.apiKeys where notification_defination.id =:id")
    Optional<NotificationDefination> findOneWithEagerRelationships(@Param("id") Long id);

}
